/**
 *  MolSmart GW3 Driver - AC - iDoor (usando os códigos e controles do idoor)
 *
 *  Copyright 2024 VH 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *
 *            --- Driver para GW3 - AC - idoor
 *            V.2.1 30/05/2024 - Removed the APP file. Only Driver File. 
 *            V.2.2 13/06/2024 - Added User Guide link 
 *	          V.2.3 10/12/2024 -  BE CAREFUL - Removed SWITCH capability to fix ALEXA Synch .
 * 			          Fixed Many Setup ThermoStat modes, ThermoStat Fan speeds. Added EZ Dashboard compatibility. 
 *	          V.2.4 20/3/2025 - Added Initialize function to Set Defaults values, fixed SetCoolpoint Decimal  			
 *	          V.2.5 26/9/2025 - Changed to AsyncHTTPPost Method 	
 *	          V.2.6 7/10/2025 - Added HTTP Check for Online/Offline Status of GW3. Added Version attribute to show in device.  	
*/


metadata {
  definition (name: "MolSmart - GW3 - IR(idoor) - AC", namespace: "TRATO", author: "VH", vid: "generic-contact") {
		capability "Actuator"
		capability "Sensor"
		capability "Temperature Measurement"
		capability "Thermostat"

		attribute "supportedThermostatFanModes", "JSON_OBJECT"
		attribute "supportedThermostatModes", "JSON_OBJECT"	  
		attribute "hysteresis", "NUMBER"
	  
	  
		command "setTemperature", ["NUMBER"]
		command "setThermostatOperatingState", ["ENUM"]
		command "setThermostatSetpoint", ["NUMBER"]
		command "setSupportedThermostatFanModes", ["JSON_OBJECT"]
		command "setSupportedThermostatModes", ["JSON_OBJECT"]
        command  "setCoolingSetpoint", ["NUMBER"]
         command  "initialize" 
	  
    command "cleanvars"  
//  command "setdefaults"

	command "healthCheckNow"

    // NOVOS atributos de saúde/conectividade
    attribute "gw3Online", "ENUM", ONLINE_ENUM
    attribute "lastHealthAt", "STRING"
    attribute "healthLatencyMs", "NUMBER"

    // NOVO: versão do GW3 (6 caracteres após "Version: ")
    attribute "gw3Version", "STRING"     	  
	  
  }
    
    
  }

    import groovy.transform.Field
    import groovy.json.JsonOutput

    @Field static final String DRIVER = "by TRATO"
    @Field static final String USER_GUIDE = "https://github.com/hhorigian/hubitat_MolSmart_GW3_IR/tree/main/AC/Idoor"


    String fmtHelpInfo(String str) {
    String prefLink = "<a href='${USER_GUIDE}' target='_blank'>${str}<br><div style='font-size: 70%;'>${DRIVER}</div></a>"
    return "<div style='font-size: 160%; font-style: bold; padding: 2px 0px; text-align: center;'>${prefLink}</div>"
    }


  preferences {
        input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true
	    input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true
	    input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", required: true
    	input name: "cId", title:"Control ID (pego no idoor)", type: "string", required: true     
        input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false 
        input name: "timeoutSec",   type: "number", title: "HTTP timeout (seconds, sync mode only)", defaultValue: 7, range: "3..30"

    // === NOVO: Health Check ===
    input name: "enableHealthCheck", type: "bool",   title: "Ativar verificação de online (HTTP /info)", defaultValue: true
    input name: "healthCheckMins",   type: "number", title: "Intervalo do health check (min)", defaultValue: 30, range: "1..1440"
      	  
      
        //help guide
        input name: "UserGuide", type: "hidden", title: fmtHelpInfo("Manual do Driver") 	  
  }   
  

def initialize() {
	log.debug "initialized()"  
	if (state?.lastRunningMode == null) {
		sendEvent(name: "temperature", value: convertTemperatureIfNeeded(68.0,"F",1))
		sendEvent(name: "thermostatSetpoint", value: convertTemperatureIfNeeded(68.0,"F",1))
    sendEvent(name: "coolingSetpoint", value: "20", descriptionText: "Thermostat coolingSetpoint set to 20") 
    sendEvent(name: "heatingSetpoint", value: "20", descriptionText: "Thermostat heatingSetpoint set to 20")     
		state.lastRunningMode = "heat"
		updateDataValue("lastRunningMode", "heat")
		setThermostatOperatingState("idle")
		setSupportedThermostatFanModes(JsonOutput.toJson(["auto","high","mid","low"]))
		setSupportedThermostatModes(JsonOutput.toJson(["auto", "cool", "heat", "off"]))
		off()
		fanAuto()
	}
	sendEvent(name: "hysteresis", value: (hysteresis ?: 0.5).toBigDecimal())
    if (enableHealthCheck) scheduleHealth()    
	
}


def cleanvars()  //Usada para limpar todos os states e controles aprendidos. 
{
//state.remove()
  state.clear() 
  AtualizaDadosGW3()  
}


def installed()
{
	log.warn "installed..."
	initialize()
    sendEvent(name:"gw3Online", value:"unknown")

}

def updated()
{  
    log.debug "updated()"
    AtualizaDadosGW3()   
	//if (logEnable) runIn(1800,logsOff)
	initialize()	
	if (!device.currentValue("gw3Online")) sendEvent(name:"gw3Online", value:"unknown")    
	
}

def AtualizaDadosGW3() {
    state.currentip = settings.molIPAddress
    state.serialNum = settings.serialNum
    state.verifyCode = settings.verifyCode
    state.channel = settings.channel
    state.cId = settings.cId
//    state.rcId = "52"
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.channel + " -- " + state.cId 
}

/*
def setdefaults() {
	log.info "Called SetDefaults Function " 
    sendEvent(name: "thermostatSetpoint", value: "20", descriptionText: "Thermostat thermostatSetpoint set to 20")
    sendEvent(name: "coolingSetpoint", value: "20", descriptionText: "Thermostat coolingSetpoint set to 20") 
    sendEvent(name: "heatingSetpoint", value: "20", descriptionText: "Thermostat heatingSetpoint set to 20")     
    sendEvent(name: "temperature", value: convertTemperatureIfNeeded(68.0,"F",1))    
    sendEvent(name: "thermostatOperatingState", value: "idle", descriptionText: "Set thermostatOperatingState to Idle")     
    sendEvent(name: "thermostatFanMode", value: "auto", descriptionText: "Set thermostatFanMode auto")     
    sendEvent(name: "setHeatingSetpoint", value: "15", descriptionText: "Set setHeatingSetpoint to 15")     
	sendEvent(name: "supportedThermostatFanModes", value: fanModes, descriptionText: "supportedThermostatFanModes set")
	sendEvent(name: "supportedThermostatModes", value: modes, descriptionText: "supportedThermostatModes set ")
}
*/


def setSupportedThermostatFanModes(fanModes) {
	logDebug "setSupportedThermostatFanModes(${fanModes}) foi chamado"
	// (auto, circulate, on)
	sendEvent(name: "supportedThermostatFanModes", value: fanModes, descriptionText: getDescriptionText("supportedThermostatFanModes set to ${fanModes}"))
}

def setSupportedThermostatModes(modes) {
	logDebug "setSupportedThermostatModes(${modes}) foi chamado"
	// (auto, cool, emergency heat, heat, off)
	sendEvent(name: "supportedThermostatModes", value: modes, descriptionText: getDescriptionText("supportedThermostatModes set to ${modes}"))
}



def on() {
    sendEvent(name: "thermostatMode", value: "on", descriptionText: "Thermostat Mode set to on", isStateChange: true)
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp 
    EnviaComando(ircode)
    log.info "Enviado o commando de thermostatMode =  on " 
	

}

def off() {
    sendEvent(name: "thermostatMode", value: "off", descriptionText: "Thermostat Mode set to off", isStateChange: true)
    def ircodetemp ="0"
    state.pw = "0"    
    def ircode = ircodetemp 
    EnviaComando(ircode)
    log.info "Enviado o commando de thermostatMode =  off " 
	
         
}


def setThermostatOperatingState (operatingState) {
	logDebug "setThermostatOperatingState (${operatingState}) was called"
	updateSetpoints(null,null,null,operatingState)
	sendEvent(name: "thermostatOperatingState", value: operatingState, descriptionText: getDescriptionText("thermostatOperatingState set to ${operatingState}"))

}


private updateSetpoints(sp = null, hsp = null, csp = null, operatingState = null){
	if (operatingState in ["off"]) return
	if (hsp == null) hsp = device.currentValue("heatingSetpoint",true)
	if (csp == null) csp = device.currentValue("coolingSetpoint",true)
	if (sp == null) sp = device.currentValue("thermostatSetpoint",true)

	if (operatingState == null) operatingState = state.lastRunningMode

	def hspChange = isStateChange(device,"heatingSetpoint",hsp.toString())
	def cspChange = isStateChange(device,"coolingSetpoint",csp.toString())
	def spChange = isStateChange(device,"thermostatSetpoint",sp.toString())
	def osChange = operatingState != state.lastRunningMode

	def newOS
	def descriptionText
	def name
	def value
	def unit = "°${location.temperatureScale}"
	switch (operatingState) {
		case ["pending heat","heating","heat"]:
			newOS = "heat"
			if (spChange) {
				hspChange = true
				hsp = sp
			} else if (hspChange || osChange) {
				spChange = true
				sp = hsp
			}
			if (csp - 2 < hsp) {
				csp = hsp + 2
				cspChange = true
			}
			break
		case ["pending cool","cooling","cool"]:
			newOS = "cool"
			if (spChange) {
				cspChange = true
				csp = sp
			} else if (cspChange || osChange) {
				spChange = true
				sp = csp
			}
			if (hsp + 2 > csp) {
				hsp = csp - 2
				hspChange = true
			}
			break
		default :
			return
	}

	if (hspChange) {
		value = hsp
		name = "heatingSetpoint"
		descriptionText = "${device.displayName} ${name} was set to ${value}${unit}"
		if (txtEnable) log.info descriptionText
		sendEvent(name: name, value: value, descriptionText: descriptionText, unit: unit, stateChange: true)
	}
	if (cspChange) {
		value = csp
		name = "coolingSetpoint"
		descriptionText = "${device.displayName} ${name} was set to ${value}${unit}"
		if (txtEnable) log.info descriptionText
		sendEvent(name: name, value: value, descriptionText: descriptionText, unit: unit, stateChange: true)
	}
	if (spChange) {
		value = sp
		name = "thermostatSetpoint"
		descriptionText = "${device.displayName} ${name} was set to ${value}${unit}"
		if (txtEnable) log.info descriptionText
		sendEvent(name: name, value: value, descriptionText: descriptionText, unit: unit, stateChange: true)
	}

	state.lastRunningMode = newOS
	updateDataValue("lastRunningMode", newOS)
}


//Case para los botones de push en el dashboard. 
def push(pushed) {
	logDebug("push: button = ${pushed}")
	if (pushed == null) {
		logWarn("push: pushed is null.  Input ignored")
		return
	}
	pushed = pushed.toInteger()
	switch(pushed) {
		case 2 : off(); break
		case 3 : auto(); break
		case 4 : heat(); break
		case 5 : cool(); break
        case 6 : fan(); break
        case 7 : dry(); break
        case 8 : fanAuto(); break                
        case 9 : fanOn(); break                
        case 10 : fanCirculate(); break    
        case 13 : fanAuto(); break    
        case 14 : fanLow(); break    
        case 15 : fanMed(); break    
        case 16 : fanHigh(); break  
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

//Botão #2 para dashboard
def auto(){
    sendEvent(name: "thermostatMode", value: "auto")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=0&tp=2"
    EnviaComando(ircode)  
    log.info "Enviado o commando de thermostatMode =  auto " 
	
}


//Botão #3 para dashboard
def heat(){
    sendEvent(name: "thermostatMode", value: "heat")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=2&tp=2"
    EnviaComando(ircode)  
    log.info "Enviado o commando de thermostatMode =  heat " 
	
}

//Botão #4 para dashboard
def cool(){
    sendEvent(name: "thermostatMode", value: "cool")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=1&tp=2"
    EnviaComando(ircode)     
    log.info "Enviado o commando de thermostatMode =  cool " 
	
}

//Botão #5 para dashboard
def fan(){
    sendEvent(name: "thermostatMode", value: "fan")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=4&tp=2"
    EnviaComando(ircode)      
    log.info "Enviado o commando de thermostatMode =  fan " 
	
}


//Botão #6 para dashboard
def dry(){
    sendEvent(name: "thermostatMode", value: "dry")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=3&tp=2"
    EnviaComando(ircode)  
    log.info "Enviado o commando de thermostatMode =  dry " 
	
}

//Botão #7 para dashboard
def setCoolingSetpoint(temperature){
    sendEvent(name: "setCoolingSetpoint", value: temperature )
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&t=" + temperature + "&tp=1"
    EnviaComando(ircode)  
    log.info "Enviado o commando de setCoolingSetpoint =  temp " 
	
}

//Botão #8 para dashboard
def fanAuto(){
    sendEvent(name: "setThermostatFanMode", value: "auto")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=0&tp=3"
    EnviaComando(ircode)  
    log.info "Enviado o commando de setThermostatFanMode =  fanAuto " 
	
}

//Botão #9 para dashboard
def fanOn(){
    sendEvent(name: "setThermostatFanMode", value: "on")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=3&tp=3"
    EnviaComando(ircode)  
    log.info "Enviado o commando de setThermostatFanMode =  fanOn " 
	
}

//Botão #9 para dashboard
def fanCirculate(){
    sendEvent(name: "setThermostatFanMode", value: "circulate")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=1&tp=3"
    EnviaComando(ircode)  
    log.info "Enviado o commando de setThermostatFanMode =  fanCirculate " 
	
}

//Botão #7 para dashboard
def setHeatingSetpoint(temperature){
    sendEvent(name: "setHeatingSetpoint", value: temperature )
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&t=" + temperature + "&tp=1"
    EnviaComando(ircode)  
    log.info "Enviado o commando de setHeatingSetpoint =  temp " 
	
}

//Botão #7 para dashboard
def setThermostatMode(modo){
    varmodo = modo
    sendEvent(name: "thermostatMode", value: varmodo)
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
		case "auto" : 
            valormodo = "0"; 
            break
		case "heat" : 
            valormodo = "2"; 
            break  
		case "cool" : 
            valormodo = "1"  ; 
            break        
		case "fan" : 
            valormodo = "4"  ; 
            break        
		case "dry" : 
            valormodo = "3"  ; 
            break        
		case "off"  : 
            valormodo = "-1" ; 
			ircodetemp = "0";
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    def ircode = ircodetemp + "&md=" + valormodo + "&tp=2"
    EnviaComando(ircode)  
    log.info "Enviado o commando de set Thermostat Mode =   " + varmodo 
	
}

def setThermostatFanMode(modo){
    varmodo = modo
    sendEvent(name: "setThermostatFanMode", value: varmodo)
    def ircodetemp = 1
    state.pw = "1"
    valormodo = " "
//		setSupportedThermostatFanModes(JsonOutput.toJson(["auto","high","mid","low"]))
	
    switch(varmodo) {
		case "high" : 
            valormodo = "5"; 
            break
		case "mid" : 
            valormodo = "3"; 
            break  
        case "low"  : 
            valormodo = "1" ; 
            break
        case "auto"  : 
            valormodo = "0" ; 
            break		
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=3"  
    EnviaComando(ircode)  
    log.info "Enviado o commando de set Thermostat Fan Mode =   " + varmodo 
	
}

/*
def setThermostatFanMode(modo){
    varmodo = modo
    sendEvent(name: "setThermostatFanMode", value: varmodo)
    def ircodetemp = 1
    state.pw = "1"
    valormodo = " "
    switch(varmodo) {
		case "auto" : 
            valormodo = "0"; 
            break
		case "circulate" : 
            valormodo = "1"; 
            break  
        case "on"  : 
            valormodo = "3" ; 
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=3"
    EnviaComando(ircode)  
    log.info "Enviado o commando de set Thermostat Fan Mode =   " + varmodo 
	
}
*/


//Botão #14 para dashboard
def fanLow(){
    sendEvent(name: "setThermostatFanMode", value: "fanLow")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=1"
    EnviaComando(ircode)    
    log.info "Enviado o commando de setThermostatFanMode =  fanLow " 
	
}

//Botão #15 para dashboard
def fanMed(){
    sendEvent(name: "setThermostatFanMode", value: "fanMed")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=3"
    EnviaComando(ircode)    
    log.info "Enviado o commando de setThermostatFanMode =  fanMed " 
	
}

//Botão #16 para dashboard
def fanHigh(){
    sendEvent(name: "setThermostatFanMode", value: "fanHigh")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=5"
    EnviaComando(ircode)    
    log.info "Enviado o commando de setThermostatFanMode =  fanHigh " 
	
}



private String buildFullUrl(button) {
    def ip   = settings.molIPAddress
    def sn   = settings.serialNum
    def vc   = settings.verifyCode
    def cid  = settings.cId
    def rcid = (settings.rcId ?: "52")
    // IMPORTANT: we deliberately do NOT URL-encode 'pw' here
    return "http://${ip}/api/device/deviceDetails/smartHomeAutoHttpControl" +
           "?serialNum=${sn}&verifyCode=${vc}&cId=${cid}&rcId=${rcid}&state=1&pw=${button}"
    
}



def EnviaComando(button) {
	  
    String fullUrl = buildFullUrl(button)
    log.info "FullURL = " + fullUrl

    // params: give only a 'uri' so Hubitat won't rebuild/encode the query
    Map params = [ uri: fullUrl, timeout: (settings.timeoutSec ?: 7) as int ]
    log.info "Params = " + params
	
        try {
            asynchttpPost('gw3PostCallback', params, [cmd: button])
        } catch (e) {
            log.warn "${device.displayName} Async POST scheduling failed: ${e.message}"
    }
}

void gw3PostCallback(resp, data) {
    String cmd = data?.cmd
    try {
        if (resp?.status in 200..299) {
            logDebug "POST OK (async) cmd=${cmd} status=${resp?.status}"
        } else {
            logWarn "POST error (async) status=${resp?.status} cmd=${cmd}"
        }
    } catch (e) {
        logWarn "Async callback exception: ${e.message} (cmd=${cmd})"
    }
}


/* ======================= HEALTH CHECK HTTP (/info) ======================= */

private void scheduleHealth() {
  Integer mins = Math.max(1, (healthCheckMins ?: 5) as int)
  unschedule("healthPoll")
  // Primeiro dispara agora, depois agenda em minutos
  runIn(2, "healthPoll")
  runEveryXMinutes(mins, "healthPoll")
}

private void runEveryXMinutes(Integer mins, String handler) {
  // Helper para intervalos arbitrários (Hubitat tem runEvery5/10/30, aqui simulamos)
  // Reagenda com runIn a cada ciclo
  state.healthEveryMins = mins
  runIn( mins * 60, "healthReschedule" )
}

def healthReschedule() {
  Integer mins = (state.healthEveryMins ?: (healthCheckMins ?: 5)) as int
  runIn( mins * 60, "healthReschedule" )
  healthPoll()
}

def healthPoll() {
  if (!enableHealthCheck) return
  String ip = (settings.molIPAddress ?: "").trim()
  if (!ip) return
  String uri = "http://${ip}/info"
  Long started = now()
  Map params = [ uri: uri, timeout: 5 ]
  try {
    asynchttpGet('healthPollCB', params, [t0: started, uri: uri])
  } catch (e) {
    if (logEnable) log.warn "healthPoll schedule failed: ${e.message}"
  }
}

void healthPollCB(resp, data) {
  String body = ""
  Integer st = null
  try {
    st = resp?.status as Integer
    body = resp?.getData() ?: ""
  } catch (ignored) { }
  String stamp = new Date().format("yyyy-MM-dd HH:mm:ss")
  Long t0 = (data?.t0 ?: now())
  Long dt = (now() - t0)

  if (st && st >= 200 && st <= 299 && body?.toString()?.contains("MolSmart Device Info")) {
    // Online
    if (device.currentValue("gw3Online") != "online") sendEvent(name:"gw3Online", value:"online", isStateChange:true)
    sendEvent(name:"healthLatencyMs", value: dt as Long)
    sendEvent(name:"lastHealthAt", value: stamp)

    // === NOVO: extrair "Version: X" e publicar 6 chars em gw3Version ===
    try {
      String txt = body?.toString() ?: ""
      // procura linha iniciando com "Version:"
      def m = (txt =~ /(?im)^\s*Version:\s*([^\r\n]+)/)
      if (m.find()) {
        String verFull = (m.group(1) ?: "").trim()
        String ver6 = (verFull.length() >= 6) ? verFull.substring(0, 6) : verFull
        if (ver6) {
          sendEvent(name:"gw3Version", value: ver6, isStateChange:true)
          if (logEnable) log.debug "Versão detectada: '${verFull}' -> gw3Version='${ver6}'"
        }
      } else if (logEnable) {
        log.debug "Versão não encontrada no corpo do /info."
      }
    } catch (e) {
      if (logEnable) log.warn "Falha ao extrair versão: ${e.message}"
    }

    if (logEnable) log.debug "Health OK in ${dt} ms"
  } else {
    // Offline
    if (device.currentValue("gw3Online") != "offline") sendEvent(name:"gw3Online", value:"offline", isStateChange:true)
    sendEvent(name:"healthLatencyMs", value: null)
    sendEvent(name:"lastHealthAt", value: stamp)
    if (logEnable) log.warn "Health FAIL (status=${st})"
  }
}

def healthCheckNow() { healthPoll() }



/* ────────────────────────────────────────────────────────────────────────── */
/* Helpers                                                                   */
/* ────────────────────────────────────────────────────────────────────────── */

private logInfo(msg)  { if (settings?.txtEnable   != false) log.info  "${device.displayName} ${msg}" }
private logDebug(msg) { if (settings?.debugOutput == true)  log.debug "${device.displayName} ${msg}" }
private logWarn(msg)  { log.warn "${device.displayName} ${msg}" }




private getDescriptionText(msg) {
	def descriptionText = "${device.displayName} ${msg}"
	if (settings?.txtEnable) log.info "${descriptionText}"
	return descriptionText
}
