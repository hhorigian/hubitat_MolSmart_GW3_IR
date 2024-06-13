/**
 *  MolSmart GW3 Driver - IR(HEX) v.2 para Ar Condicionado. Usando HEX
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
 *            --- Driver para GW3 - IR - para Ar Condicionado ---
 *                  V.2.0  - 30/05/2024 - Versão com Driver sem precissar do APP
 *                  V.2.1  - 10/6/2024 - Fix URL
 *		    V.2.2  - 13/6/2024 - Added User Guide Link
 * 
*/


metadata {
  definition (name: "MolSmart - GW3 - IR(HEX)- AC", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "Switch"
	capability "Thermostat"
	capability "Thermostat Cooling Setpoint"
	capability "Thermostat Setpoint"
    capability "Temperature Measurement"      
	capability "Sensor"
	capability "Actuator"
	capability "Configuration"
	capability "Refresh"
	capability "HealthCheck"   
    capability "PushableButton"
    capability "FanControl"

		attribute "supportedThermostatFanModes", "JSON_OBJECT"
		attribute "supportedThermostatModes", "JSON_OBJECT"

       	command "setSupportedThermostatFanModes", ["JSON_OBJECT"]
		command "setSupportedThermostatModes", ["JSON_OBJECT"]
		command "setTemperature", ["NUMBER"]

              command "CodigoHEXON", [
            [name: "HEXcode", type: "STRING", description: "ON = COLAR O CODIGO HEX"]
        ]      
               command "CodigoHEXOFF", [
            [name: "HEXcode", type: "STRING", description: "OFF = COLAR O CODIGO HEX"]
        ]        
              command "CodigoHEXAUTO", [
            [name: "HEXcode", type: "STRING", description: "AUTO = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXCOOL", [
            [name: "HEXcode", type: "STRING", description: "COOL = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXHEAT", [
            [name: "HEXcode", type: "STRING", description: "HEAT = COLAR O CODIGO HEX"]
        ]          
              command "CodigoHEXFAN", [
            [name: "HEXcode", type: "STRING", description: "FAN = COLAR O CODIGO HEX"]
        ]
              command "CodigoHEXDRY", [
            [name: "HEXcode", type: "STRING", description: "DRY = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXFANAUTO", [
            [name: "HEXcode", type: "STRING", description: "FANAUTO = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXFANLOW", [
            [name: "HEXcode", type: "STRING", description: "FANLOW = COLAR O CODIGO HEX"]
        ]
              command "CodigoHEXFANMED", [
            [name: "HEXcode", type: "STRING", description: "FANMED = COLAR O CODIGO HEX"]
        ]
              command "CodigoHEXFANHIGH", [
            [name: "HEXcode", type: "STRING", description: "FANHIGH = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXEXTRA1(BTN8)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA1 = COLAR O CODIGO HEX"]
        ]      
              command "CodigoHEXEXTRA2(BTN9)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA2 = COLAR O CODIGO HEX"]
        ]    
              command "CodigoHEXEXTRA3(BTN10)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA3 = COLAR O CODIGO HEX"]
        ]            
              command "CodigoHEXEXTRA4(BTN11)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA4 = COLAR O CODIGO HEX"]
        ]
              command "CodigoHEXEXTRA5(BTN12)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA5 = COLAR O CODIGO HEX"]
        ]
              command "CodigoHEXEXTRA6(BTN17)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA6 = COLAR O CODIGO HEX"]
        ]   
              command "CodigoHEXEXTRA7(BTN18)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA7 = COLAR O CODIGO HEX"]
        ]    
              command "CodigoHEXEXTRA8(BTN19)", [
            [name: "HEXcode", type: "STRING", description: "EXTRA8 = COLAR O CODIGO HEX"]
        ]                                        
  }
    
    
  }

    import groovy.transform.Field
    @Field static final String DRIVER = "by TRATO"
    @Field static final String USER_GUIDE = "https://github.com/hhorigian/hubitat_MolSmart_GW3_IR/tree/main/AC/HEX"


    String fmtHelpInfo(String str) {
    String prefLink = "<a href='${USER_GUIDE}' target='_blank'>${str}<br><div style='font-size: 70%;'>${DRIVER}</div></a>"
    return "<div style='font-size: 160%; font-style: bold; padding: 2px 0px; text-align: center;'>${prefLink}</div>"
    }


  preferences {
	input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address",  required: true, defaultValue: "192.168.1.100" 
	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", defaultValue: "111111" 
	input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", defaultValue: "111111" 
	input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", defaultValue: "1"                 	
	input name: "repeatSendHEX", title:"Repeat for SendHex", type: "string", defaultValue: "1"        
	input name: "setCoolingSetpointIRsend", title:"setCooling-HEX(7)", type: "string"
	input name: "setHeatingSetpointIRsend", title:"setHeating-HEX(8)", type: "string"      
	input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
	input name: "UserGuide", type: "hidden", title: fmtHelpInfo("Manual do Driver") 	  

      
  }   

import groovy.json.JsonOutput

def configure()
{    
    log.debug "configure()"
}

def initialized()
{
    log.debug "initialized()"
 	off()    
}

def installed()
{ 
    sendEvent(name:"numberOfButtons", value:15)     
    log.debug "installed()"
	off()
    setdefaults()  
}

def updated()
{
    log.debug "updated()"
    setdefaults()
    off()
    AtualizaDadosGW3()       
}


//Get Device info and set as state to use during driver.
def AtualizaDadosGW3() {
    state.currentip = settings.molIPAddress
    state.serialNum = settings.serialNum
    state.verifyCode = settings.verifyCode
    state.channel = settings.channel
}

def CodigoHEXON(final String HEXcode){
    info "CodigoHEXON(${HEXcode})"
    varOnIRsend = HEXcode
    state.OnIRsend  = HEXcode
}

def CodigoHEXOFF(final String HEXcode){
    info "CodigoHEXOFF(${HEXcode})"
//    varOnIRsend = HEXcode
    state.OFFIRsend  = HEXcode
}

def CodigoHEXAUTO(final String HEXcode){
    info "CodigoHEXAUTO(${HEXcode})"
//    varOnIRsend = HEXcode
    state.AUTOIRsend  = HEXcode
}

def CodigoHEXCOOL(final String HEXcode){
    info "CodigoHEXCOOL(${HEXcode})"
//    varOnIRsend = HEXcode
    state.COOLIRsend  = HEXcode
}

def CodigoHEXHEAT(final String HEXcode){
    info "CodigoHEXHEAT(${HEXcode})"
//    varOnIRsend = HEXcode
    state.HEATIRsend  = HEXcode
}

def CodigoHEXDRY(final String HEXcode){
    info "CodigoHEXDRY(${HEXcode})"
//   varOnIRsend = HEXcode
    state.DRYIRsend  = HEXcode
}

def CodigoHEXFAN(final String HEXcode){
    info "CodigoHEXFAN(${HEXcode})"
//    varOnIRsend = HEXcode
    state.FANIRsend  = HEXcode
}

def CodigoHEXFANAUTO(final String HEXcode){
    info "CodigoHEXFANAUTO(${HEXcode})"
//    varOnIRsend = HEXcode
    state.FANAUTOIRsend  = HEXcode
}

def CodigoHEXFANMED(final String HEXcode){
    info "CodigoHEXFANMED(${HEXcode})"
//    varOnIRsend = HEXcode
    state.FANMEDIRsend  = HEXcode
}

def CodigoHEXFANHIGH(final String HEXcode){
    info "CodigoHEXFANHIGH(${HEXcode})"
//    varOnIRsend = HEXcode
    state.FANHIGHIRsend  = HEXcode
}

def CodigoHEXFANLOW(final String HEXcode){
    info "CodigoHEXFANLOW(${HEXcode})"
//    varOnIRsend = HEXcode
    state.FANLOWIRsend  = HEXcode
}

def CodigoHEXEXTRA1(final String HEXcode){
    info "CodigoHEXEXTRA1(${HEXcode})"
//    varOnIRsend = HEXcode
    state.EXTRA1IRsend  = HEXcode
}

def CodigoHEXEXTRA2(final String HEXcode){
    info "CodigoHEXEXTRA2(${HEXcode})"
//    varOnIRsend = HEXcode
    state.EXTRA2IRsend  = HEXcode
}

def CodigoHEXEXTRA3(final String HEXcode){
    info "CodigoHEXEXTRA3(${HEXcode})"
//    varOnIRsend = HEXcode
    state.EXTRA3IRsend  = HEXcode
}

def CodigoHEXEXTRA4(final String HEXcode){
    info "CodigoHEXEXTRA4(${HEXcode})"
//    varOnIRsend = HEXcode
    state.EXTRA4IRsend  = HEXcode
}

def CodigoHEXEXTRA5(final String HEXcode){
    info "CodigoHEXEXTRA5(${HEXcode})"
//    varOnIRsend = HEXcode
    state.EXTRA5IRsend  = HEXcode
}

def parse(String description) {
	logDebug "$description"
}


//Setup all the default values for AC Thermostat and Temperature measurements. 
def setdefaults() {
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



def on() {
    sendEvent(name: "thermostatMode", value: "auto", descriptionText: "Thermostat Mode set to auto", isStateChange: true)
     def ircode =  state.OnIRsend    
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "thermostatMode", value: "off", descriptionText: "Thermostat Mode set to off", isStateChange: true)
     def ircode =  state.OFFIRsend    
     EnviaComando(ircode)
         
}

//Case para os botões se usar no Dashboard 
def push(pushed) {
	logDebug("push: button = ${pushed}")
	if (pushed == null) {
		logWarn("push: pushed is null.  Input ignored")
		return
	}
	pushed = pushed.toInteger()
	switch(pushed) {
		case 2 : auto(); break
		case 3 : heat(); break
		case 4 : cool(); break
        case 5 : fan(); break
        case 6 : dry(); break
        case 7 : setautocool(); break                
        case 8 : comandoextra1(); break    
        case 9 : comandoextra2(); break            
        case 10 : comandoextra3(); break            
        case 11 : comandoextra4(); break    
        case 12 : comandoextra5(); break    
        case 13 : fanAuto(); break    
        case 14 : fanLow(); break    
        case 15 : fanMed(); break    
        case 16 : fanHigh(); break    
        case 17 : comandoextra6(); break   
        case 18 : comandoextra7(); break   
        case 19 : comandoextra8(); break                           
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

//Botão #2 para dashboard
def auto(){
    sendEvent(name: "thermostatMode", value: "auto")
    def ircode =  state.AUTOIRsend
    EnviaComando(ircode)    
}



//Botão #3 para dashboard
def heat(){
    sendEvent(name: "thermostatMode", value: "heat")
    def ircode =  state.HEATIRsend
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def cool(){
    sendEvent(name: "thermostatMode", value: "cool")
    def ircode =  state.COOLIRsend
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def fan(){
    sendEvent(name: "thermostatMode", value: "fan")
    def ircode =  state.FANIRsend
    EnviaComando(ircode)    
}


//Botão #6 para dashboard
def dry(){
    sendEvent(name: "thermostatMode", value: "dry")
    def ircode =  state.DRYIRsend
    EnviaComando(ircode)
}

def setCoolingSetpoint(temperature){
    sendEvent(name: "setCoolingSetpoint", value: temperature )
    def ircode =  (settings.setCoolingSetpointIRsend ?: "")
    EnviaComando(ircode)
}


def setHeatingSetpoint(temperature){
    sendEvent(name: "setHeatingSetpoint", value: temperature )
    def ircode =  (settings.setHeatingSetpointIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #8 para dashboard
def comandoextra1(){
    sendEvent(name: "thermostatMode", value: "comandoextra1" )
    def ircode =  state.EXTRA1IRsend
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def comandoextra2(){
    sendEvent(name: "thermostatMode", value: "comandoextra2" )
    def ircode =  state.EXTRA2IRsend
    EnviaComando(ircode)
}

//Botão #10 para dashboard
def comandoextra3(){
    sendEvent(name: "thermostatMode", value: "comandoextra3" )
    def ircode =  state.EXTRA3IRsend
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def comandoextra4(){
    sendEvent(name: "thermostatMode", value: "comandoextra3" )
    def ircode =  state.EXTRA4IRsend
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def comandoextra5(){
    sendEvent(name: "thermostatMode", value: "comandoextra5" )
    def ircode = state.EXTRA5IRsend
    EnviaComando(ircode)
}

//Botão #17 para dashboard
def comandoextra6(){
    sendEvent(name: "thermostatMode", value: "comandoextra6" )
    def ircode = state.EXTRA6IRsend
    EnviaComando(ircode)
}

//Botão #18 para dashboard
def comandoextra7(){
    sendEvent(name: "thermostatMode", value: "comandoextra7" )
    def ircode = state.EXTRA7IRsend
    EnviaComando(ircode)
}

//Botão #19 para dashboard
def comandoextra8(){
    sendEvent(name: "thermostatMode", value: "comandoextra8" )
    def ircode = state.EXTRA8IRsend
    EnviaComando(ircode)
}






//Botão #14 para dashboard
def fanLow(){
    sendEvent(name: "speed", value: "low")
    def ircode =  state.FANLOWIRsend
    EnviaComando(ircode)    
}

//Botão #15 para dashboard
def fanMed(){
    sendEvent(name: "speed", value: "medium")
    def ircode =  state.FANMEDIRsend
    EnviaComando(ircode)    
}

//Botão #16 para dashboard
def fanHigh(){
    sendEvent(name: "speed", value: "high")
    def ircode =  state.FANHIGHIRsend
    EnviaComando(ircode)    
}

def fanCirculate() {
    log.info "circulate"
    def ircode =  state.FANLOWIRsend
    EnviaComando(ircode) 
    sendEvent(name: "setThermostatFanMode", value: "circulate")
    
    
}

def fanOn() {
    log.info "fanOn"
    def ircode =  state.FANMEDIRsend 
    EnviaComando(ircode)  
    sendEvent(name: "setThermostatFanMode", value: "on")    

}

//Botão #13 para dashboard
def fanAuto(){
    sendEvent(name: "setThermostatFanMode", value: "auto")    
    def ircode =  state.FANAUTOIRsend 
    EnviaComando(ircode)    
}


//Set Fan Speed in LIST MODE (on/off/auto/high/low/medium/medium-low, etc)
def setSpeed(modo){
    def varmodo = modo
    sendEvent(name: "setSpeed", value: modo)
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
		case "auto" : 
            valormodo = "0"; 
            ircode =  state.FANAUTOIRsend
            break
		case "on" : 
            valormodo = "2"; 
            ircode = state.FANAUTOIRsend     
            break  
		case "off" : 
            valormodo = "1"  ; 
            ircode =  state.COOLIRsend 
            break
        case "low"  : 
            valormodo = "-1" ; 
            ircode =  state.FANLOWIRsend        
            break
        case "medium"  : 
            valormodo = "-1" ; 
            ircode =  state.FANMEDIRsend          
            break    
        case "medium-low"  : 
            valormodo = "-1" ; 
            ircode =  state.FANMEDIRsend               
            break  
        case "medium-high"  : 
            valormodo = "-1" ; 
            ircode =  state.FANMEDIRsend            
            break          
        default: 
            valormodo = "0" ; 
            ircode =  state.OFFIRsend   
            break   
    }
    //def ircode = (settings.heatIRsend ?: "")
    EnviaComando(ircode)  
}


//Set ThermoStat Mode in LIST MODE (cool/off/heat/auto)
def setThermostatMode(modo){
    def varmodo = modo
    sendEvent(name: "thermostatMode", value: modo)
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
		case "auto" : 
            valormodo = "0"; 
            ircode =  state.AUTOIRsend 
            break
		case "heat" : 
            valormodo = "2"; 
            ircode = state.HEATIRsend             
            break  
		case "cool" : 
            valormodo = "1"  ; 
            ircode =  state.COOLIRsend 
            break
        case "off"  : 
            valormodo = "-1" ; 
            ircode = state.OFFIRsend                
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    //def ircode = (settings.heatIRsend ?: "")
    EnviaComando(ircode)  
}


//Set ThermoStat FAN Mode in LIST MODE (on/auto/circulate)
def setThermostatFanMode(modo){
    def varmodo = modo
    sendEvent(name: "setThermostatFanMode", value: modo)    
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
		case "auto" : 
            valormodo = "0"; 
            ircode =  state.FANAUTOIRsend    
            break
		case "circulate" : 
            valormodo = "2"; 
            ircode = state.FANLOWIRsend     
            break  
        case "on"  : 
            valormodo = "-1" ; 
            ircode =  state.FANMEDIRsend         
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    //def ircode = (settings.heatIRsend ?: "")
    EnviaComando(ircode)  
}



//CONSTRUCT URI
def EnviaComando(command) {
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&pronto=" + command + "&c=" + state.channel + "&r=" + settings.repeatSendHEX        
    httpPOSTExec(URI)
    log.info "Sent HTTP: " +  URI + " // com commando = " + command
    
    
}


//SEND HTTP POST USING THE URI 
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

def info(msg) {
    if (logLevel == "INFO" || logLevel == "DEBUG") {
        log.info(msg)
    }
}


//DEBUG
private logDebug(msg) {
  if (settings?.debugOutput || settings?.debugOutput == null) {
    log.debug "$msg"
  }
}

