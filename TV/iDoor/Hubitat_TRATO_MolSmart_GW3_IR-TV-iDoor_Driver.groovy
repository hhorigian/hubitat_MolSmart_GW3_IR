/**
 *  MolSmart GW3 Driver - TV e SOM - iDoor (usando os controles do idoor)
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
 *            --- Driver para GW3 - TV e SOM - idoor
 *        V.1.0 11/07/2024 - First Version. Beta. 
 *	      V.1.1 22/07/2024 - Added comments for buttons. 	
 *	      V.1.2 23/07/2024 - Added extra buttons codes (6 buttons). 	
 *	      V.1.3 7/09/2025  - Added HTTP Check for Online/Offline Status of GW3. Added Version attribute to show in device. 
 *        V.1.4 5/11/2025  - Added  Buttons as Child Devices using "Recreate Buttons". Buttons shown as Switch, easier for dashboard use. 
 *
 */

metadata {
  definition (name: "MolSmart - GW3 - IR(idoor) - TV e SOM", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "TV"  
    capability "SamsungTV"
    capability "Switch"  
    capability "Actuator"
    capability "PushableButton"
    capability "Variable"      

	command "healthCheckNow"
        command "recreateChilds"
        command "removeChilds"      

    // NOVOS atributos de saúde/conectividade
    attribute "gw3Online", "ENUM", ONLINE_ENUM
    attribute "lastHealthAt", "STRING"
    attribute "healthLatencyMs", "NUMBER"

    // NOVO: versão do GW3 (6 caracteres após "Version: ")
    attribute "gw3Version", "STRING"      	  
	attribute "channel", "number"
	attribute "volume", "number"
	attribute "movieMode", "string"
	attribute "power", "string"
	attribute "sound", "string"
	attribute "picture", "string"  
      

  }
    
    
  }

    import groovy.transform.Field
    @Field static final String DRIVER = "by TRATO"
    @Field static final String USER_GUIDE = "https://github.com/hhorigian/hubitat_MolSmart_GW3_IR/tree/main/TV"


    String fmtHelpInfo(String str) {
    String prefLink = "<a href='${USER_GUIDE}' target='_blank'>${str}<br><div style='font-size: 70%;'>${DRIVER}</div></a>"
    return "<div style='font-size: 160%; font-style: bold; padding: 2px 0px; text-align: center;'>${prefLink}</div>"
    }


  preferences {
        input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true  //Example: P130-C101-A0259
	input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true //Example: 46866117
        input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", required: true, defaultValue: "1"   
    	input name: "cId", title:"Control ID (pego no idoor)", type: "string", required: true     //Example: 1800267462429118464
        input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false 

    // === NOVO: Health Check ===
    input name: "enableHealthCheck", type: "bool",   title: "Ativar verificação de online (HTTP /info)", defaultValue: true
    input name: "healthCheckMins",   type: "number", title: "Intervalo do health check (min)", defaultValue: 30, range: "1..1440"
      input name: "createChildsOnSave", type: "bool", title: "Criar/atualizar Child Switches ao salvar", defaultValue: false

        //help guide
        input name: "UserGuide", type: "hidden", title: fmtHelpInfo("Manual do Driver") 	  
  }   
  
def initialized()
{
    log.debug "initialized()"   
	  if (enableHealthCheck) scheduleHealth()    	
    //off()  
}

def installed()
{
    log.debug "installed()"
	//off()
    //setdefaults()  
  sendEvent(name:"gw3Online", value:"unknown")
	

}

def updated()
{  
    log.debug "updated()"
    //setdefaults()
    //off()
    AtualizaDadosGW3()   
	if (!device.currentValue("gw3Online")) sendEvent(name:"gw3Online", value:"unknown")    
    if (createChildsOnSave) createOrUpdateChildButtons(true)
    
	
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




def on() {
    sendEvent(name: "switch", value: "on", isStateChange: true)
    def ircode =  "1"
    EnviaComando(ircode)

}

def off() {
    sendEvent(name: "switch", value: "off", isStateChange: true)
    def ircode = "1"
    EnviaComando(ircode)
         
}

//Case for the buttons to be used in the Dashboard 
def push(pushed) {
	logDebug("push: button = ${pushed}")
	if (pushed == null) {
		logWarn("push: pushed is null.  Input ignored")
		return
	}
	pushed = pushed.toInteger()
	switch(pushed) {
        case 1 : power(); break
	case 2 : mute(); break
	case 3 : source(); break
	case 4 : back(); break
        case 5 : menu(); break
        case 6 : hdmi1(); break
        case 7 : hdmi2(); break                
	case 8 : left(); break
		case 9 : right(); break
		case 10: up(); break
		case 11: down(); break
		case 12: confirm(); break
		case 13: exit(); break
		case 14: home(); break
		case 18: channelUp(); break
		case 19: channelDown(); break
		case 21: volumeUp(); break
		case 22: volumeDown(); break
		case 23: num0(); break
		case 24: num1(); break
		case 25: num2(); break
		case 26: num3(); break
    	case 27: num4(); break        
	case 28: num5(); break
    	case 29: num6(); break
	case 30: num7(); break
    	case 31: num8(); break            
	case 32: num9(); break       
	case 33: btnextra1(); break                
	case 34: btnextra2(); break
	case 35: btnextra3(); break
	case 36: btnextra4(); break
	case 37: btnextra5(); break
	case 48: btnextra6(); break  
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

		
//Botão #1 para dashboard
def power(){
	sendEvent(name: "power", value: "toggle")
    def ircode =  "1"
    EnviaComando(ircode)    
}


//Botão #2 para dashboard
def mute(){
	sendEvent(name: "volume", value: "mute")
    def ircode =  "4"
    EnviaComando(ircode)    
}


//Botão #3 para dashboard
def source(){
	sendEvent(name: "action", value: "source")
    def ircode =  "8"
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def back(){
	sendEvent(name: "action", value: "back")
    def ircode = "9"
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def menu(){
	sendEvent(name: "action", value: "menu")
    def ircode = "15"
    EnviaComando(ircode)    
}


//Botão #8 para dashboard
def left(){
    sendEvent(name: "action", value: "left")
    def ircode =   "13"
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def right(){
    sendEvent(name: "action", value: "right")
     def ircode =  "11"
    EnviaComando(ircode)
}



//Botão #10 para dashboard
def up(){
    sendEvent(name: "action", value: "up")
    def ircode =  "10"
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def down(){
    sendEvent(name: "action", value: "down")
    def ircode =  "12"
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def confirm(){
    sendEvent(name: "action", value: "confirm")
    def ircode =  "14"
    EnviaComando(ircode)
}


//Botão #13 para dashboard
def exit(){
	sendEvent(name: "action", value: "exit")
    def ircode =  "9"
    EnviaComando(ircode)    
}




//Botão #14 para dashboard
def home(){
    sendEvent(name: "action", value: "home")
    def ircode =  "7"
    EnviaComando(ircode)
}



//Botão #18 para dashboard
def channelUp(){
	sendEvent(name: "channel", value: "chup")
   def ircode =   "5"
    EnviaComando(ircode)    
}

//Botão #19 para dashboard
def channelDown(){
	sendEvent(name: "channel", value: "chdown")
    def ircode =  "6"
    EnviaComando(ircode)    
}

//Botão #21 para dashboard
def volumeUp(){
	sendEvent(name: "volume", value: "volup")
    def ircode = "2"
    EnviaComando(ircode)    
}

//Botão #22 para dashboard
def volumeDown(){
	sendEvent(name: "volume", value: "voldown")
    def ircode = "3"
    EnviaComando(ircode)    
}


//Botão #23 para dashboard
def num0(){
    sendEvent(name: "action", value: "num0")
    def ircode =  "25"
    EnviaComando(ircode)
}

//Botão #24 para dashboard
def num1(){
    sendEvent(name: "action", value: "num1")
   def ircode =  "16"
    EnviaComando(ircode)
}

//Botão #25 para dashboard
def num2(){
    sendEvent(name: "action", value: "num2")
    def ircode =  "17"
    EnviaComando(ircode)
}


//Botão #26 para dashboard
def num3(){
    sendEvent(name: "action", value: "num3")
    def ircode =  "18"
    EnviaComando(ircode)
}

//Botão #27 para dashboard
def num4(){
    sendEvent(name: "action", value: "num4")
    def ircode =  "19"
    EnviaComando(ircode)
}

//Botão #28 para dashboard
def num5(){
    sendEvent(name: "action", value: "num5")
    def ircode =   "20"
    EnviaComando(ircode)
}

//Botão #29 para dashboard
def num6(){
    sendEvent(name: "action", value: "num6")
    def ircode =  "21"
    EnviaComando(ircode)
}


//Botão #30 para dashboard
def num7(){
    sendEvent(name: "action", value: "num7")
    def ircode =  "22"
    EnviaComando(ircode)
}

//Botão #31 para dashboard
def num8(){
    sendEvent(name: "action", value: "num8")
    def ircode =  "23"
    EnviaComando(ircode)
}

//Botão #32 para dashboard
def num9(){
    sendEvent(name: "action", value: "num9")
    def ircode = "24"
    EnviaComando(ircode)
}

//Botão #33 para dashboard
def btnextra1(){
    sendEvent(name: "action", value: "btnextra1")
    def ircode = "26"
    EnviaComando(ircode)
}

//Botão #34 para dashboard
def btnextra2(){
    sendEvent(name: "action", value: "btnextra2")
    def ircode = "34"
    EnviaComando(ircode)
}

//Botão #35 para dashboard
def btnextra3(){
    sendEvent(name: "action", value: "btnextra3")
    def ircode = "35"
    EnviaComando(ircode)
}

//Botão #36 para dashboard
def btnextra4(){
    sendEvent(name: "action", value: "btnextra4")
    def ircode = "36"
    EnviaComando(ircode)
}

//Botão #37 para dashboard
def btnextra5(){
    sendEvent(name: "action", value: "btnextra5")
    def ircode = "37"
    EnviaComando(ircode)
}

//Botão #38 para dashboard
def btnextra6(){
    sendEvent(name: "action", value: "btnextra6")
    def ircode = "38"
    EnviaComando(ircode)
}
	

def EnviaComando(command) {
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.cId + "&rcId=53" + "&state=" + command + "&c=" + state.channel
    httpPOSTExec(URI)
    log.info "Last command sent = " + URI
    state.lasturi = URI
        
}


def httpPOSTExec(URI)
{
    
    try
    {
        getString = URI
        segundo = ""
        httpPost(getString.replaceAll(' ', '%20'),segundo,  )
        { resp ->
            if (resp.data)
            {
                    
                        log.info "Response " + resp.data 
            
            }
        }
    }
                            

    catch (Exception e)
    {
        logDebug("httpPostExec() failed: ${e.message}")
    }
    
}

private logDebug(msg) {
  if (settings?.debugOutput || settings?.debugOutput == null) {
    log.debug "$msg"
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

////////

// Lista de botões mapeados para métodos já existentes no driver.
// Se algum método não existir no seu driver, ao acionar o child apenas será logado aviso.

import groovy.transform.Field

@Field static final List<Map> TV_CHILD_BUTTON_DEFS = [    
  [label:"TV - Power",               handler:"power"],
  [label:"TV - Mute",                handler:"mute"],
  [label:"TV - Source",              handler:"source"],
  [label:"TV - Back",                handler:"back"],
  [label:"TV - Menu",                handler:"menu"],
  [label:"TV - HDMI 1",              handler:"hdmi1"],
  [label:"TV - HDMI 2",              handler:"hdmi2"],
  [label:"TV - Left",                handler:"left"],
  [label:"TV - Right",               handler:"right"],
  [label:"TV - Up",                  handler:"up"],
  [label:"TV - Down",                handler:"down"],
  [label:"TV - OK/Confirm",          handler:"confirm"],
  [label:"TV - Exit",                handler:"exit"],
  [label:"TV - Home",                handler:"home"],
  [label:"TV - Channel Up",          handler:"channelUp"],
  [label:"TV - Channel Down",        handler:"channelDown"],
  [label:"TV - Volume Up",           handler:"volumeUp"],
  [label:"TV - Volume Down",         handler:"volumeDown"],
  [label:"TV - 0",                   handler:"num0"],
  [label:"TV - 1",                   handler:"num1"],
  [label:"TV - 2",                   handler:"num2"],
  [label:"TV - 3",                   handler:"num3"],
  [label:"TV - 4",                   handler:"num4"],
  [label:"TV - 5",                   handler:"num5"],
  [label:"TV - 6",                   handler:"num6"],
  [label:"TV - 7",                   handler:"num7"],
  [label:"TV - 8",                   handler:"num8"],
  [label:"TV - 9",                   handler:"num9"],
  [label:"TV - Extra 1",             handler:"btnextra1"],
  [label:"TV - Extra 2",             handler:"btnextra2"],
  [label:"TV - Extra 3",             handler:"btnextra3"],
  [label:"TV - Extra 4",             handler:"btnextra4"],
  [label:"TV - Extra 5",             handler:"btnextra5"],
  [label:"TV - Extra 6",             handler:"btnextra6"]
]

// Comandos públicos para o Hubitat
def recreateChilds() { createOrUpdateChildButtons(true) }
def removeChilds()  { removeAllChildButtons() }

// Criação/atualização de children (momentary)
private void createOrUpdateChildButtons(Boolean removeExtras=false) {
  try { if (logEnable) log.debug "Criando/atualizando Child Switches para botões da TV..." } catch (ignored) { }

  List<Map> defs = TV_CHILD_BUTTON_DEFS
  Set<String> keep = [] as Set

  defs.eachWithIndex { m, idx ->
    String dni = "${device.id}-TVBTN-${idx+1}"
    def child = getChildDevice(dni)
    String label = m.label as String

    if (!child) {
      try {
        child = addChildDevice("hubitat", "Generic Component Switch", dni,
          [name: label, label: label, isComponent: true])
        if (logEnable) log.debug "Child criado: ${label} (${dni})"
      } catch (e) {
        log.warn "Falha ao criar child '${label}': ${e.message}"
      }
    } else {
      try { if (child.label != label) child.setLabel(label) } catch (ignored) { }
    }

    if (child) {
      try {
        child.updateDataValue("handler", (m.handler as String))
        // garantir momento inicial OFF (componente momentâneo)
        child.parse([[name:"switch", value:"off"]])
      } catch (ignored) { }
      keep << dni
    }
  }

  if (removeExtras) {
    childDevices?.findAll { !(it.deviceNetworkId in keep) }?.each {
      try { deleteChildDevice(it.deviceNetworkId) } catch (ignored) { }
    }
  }
}

// Component callbacks do "Generic Component Switch"
def componentOn(cd)  { handleChildPress(cd) }
def componentOff(cd) { /* momentary: ignorar */ }

// Ao ligar o child, chama o método mapeado no driver pai e volta para OFF
private void handleChildPress(cd) {
  String handler = cd?.getDataValue("handler") ?: ""
  if (!handler) { log.warn "Child ${cd?.displayName} sem handler definido."; return }

  try {
    this."${handler}"()
  } catch (MissingMethodException e) {
    log.warn "Método '${handler}' não encontrado no driver pai."
  } catch (e) {
    log.warn "Falha ao executar handler '${handler}': ${e.message}"
  }

  // auto-off
  runIn(1, "childOffSafe", [data:[dni: cd?.deviceNetworkId]])
}

def childOffSafe(data) {
  def child = getChildDevice(data?.dni as String)
  if (child) {
    try { child.parse([[name:"switch", value:"off"]]) } catch (ignored) { }
  }
}

// Remover todos os children criados por este bloco
private void removeAllChildButtons() {
  try { if (logEnable) log.warn "Removendo todos os Child Switches de botões..." } catch (ignored) { }
  def toRemove = childDevices?.findAll { (it.deviceNetworkId ?: "").startsWith("${device.id}-TVBTN-") } ?: []
  Integer removed = 0
  toRemove.each { cd ->
    try {
      deleteChildDevice(cd.deviceNetworkId)
      removed++
    } catch (e) {
      log.warn "Falha ao remover child '${cd.displayName}': ${e.message}"
    }
  }
  if (logEnable) log.warn "Remoção concluída. Total removido: ${removed}"
}
