/**
 *  MolSmart GW3 Driver - AC - iDoor
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
 *            V.1. iDoor BETA 17/4/2024
 *            V.1.1 iDoor BETA 28/4/2024 - fixed the temperature idoor codes, added tp=1. 
 *            V.1.2 idoor BETA 7/5/2024 - fixed bugs setthermostatheating point
 *            V.1.3 idoor BETA 8/5/2024 - added fan modes speeds
 *
 */


metadata {
  definition (name: "MolSmart GW3 - IR - AC(idoor)", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "Switch"
	capability "Thermostat"
	capability "Thermostat Cooling Setpoint"
	capability "Thermostat Setpoint"
	capability "Sensor"
	capability "Actuator"
	capability "Configuration"
	capability "Refresh"
	capability "HealthCheck"   
    capability "PushableButton"
    capability "FanControl"
      
 
          
  }
    
    
  }

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
 
         
       
  }   
  

def initialized()
{
    state.currentip = ""  
    log.debug "initialized()"
    
}

def installed()
{

    log.debug "installed()"
}

def updated()
{
   
    log.debug "updated()"
}


def on() {
    sendEvent(name: "thermostatMode", value: "on", descriptionText: "Thermostat Mode set to on", isStateChange: true)
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp 
    EnviaComando(ircode)

}

def off() {
     sendEvent(name: "thermostatMode", value: "off", descriptionText: "Thermostat Mode set to off", isStateChange: true)
    def ircodetemp ="0"
    state.pw = "0"    
    def ircode = ircodetemp 
    EnviaComando(ircode)
         
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
}


//Botão #3 para dashboard
def heat(){
    sendEvent(name: "thermostatMode", value: "heat")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=2&tp=2"
    EnviaComando(ircode)  
}

//Botão #4 para dashboard
def cool(){
    sendEvent(name: "thermostatMode", value: "cool")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=1&tp=2"
    EnviaComando(ircode)     
}

//Botão #5 para dashboard
def fan(){
    sendEvent(name: "thermostatMode", value: "fan")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=4&tp=2"
    EnviaComando(ircode)      
}


//Botão #6 para dashboard
def dry(){
    sendEvent(name: "thermostatMode", value: "dry")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&md=3&tp=2"
    EnviaComando(ircode)  
}

//Botão #7 para dashboard
def setCoolingSetpoint(temperature){
    sendEvent(name: "setCoolingSetpoint", value: temperature )
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&t=" + temperature + "&tp=1"
    EnviaComando(ircode)  
}

//Botão #8 para dashboard
def fanAuto(){
    sendEvent(name: "FanMode", value: "fanAuto")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=0&tp=3"
    EnviaComando(ircode)  
}

//Botão #9 para dashboard
def fanOn(){
    sendEvent(name: "FanMode", value: "fanOn")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=3&tp=3"
    EnviaComando(ircode)  
}

//Botão #9 para dashboard
def fanCirculate(){
    sendEvent(name: "FanMode", value: "fanCirculate")
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&s=1&tp=3"
    EnviaComando(ircode)  
}

//Botão #7 para dashboard
def setHeatingSetpoint(temperature){
    sendEvent(name: "setHeatingSetpoint", value: temperature )
    def ircodetemp = 1
    state.pw = "1"
    def ircode = ircodetemp + "&t=" + temperature + "&tp=1"
    EnviaComando(ircode)  
}

//Botão #7 para dashboard
def setThermostatMode(modo){
    def varmodo = modo
    sendEvent(name: "thermostatMode", value: modo)
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
        case "off"  : 
            valormodo = "-1" ; 
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    def ircode = ircodetemp + "&md=" + valormodo + "&tp=2"
    EnviaComando(ircode)  
}

def setThermostatFanMode(modo){
    def varmodo = modo
    sendEvent(name: "setThermostatFanMode", value: modo)
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
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
}



//Botão #14 para dashboard
def fanLow(){
    sendEvent(name: "thermostatMode", value: "fanLow")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=1"
    EnviaComando(ircode)    
}

//Botão #15 para dashboard
def fanMed(){
    sendEvent(name: "thermostatMode", value: "fanMed")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=3"
    EnviaComando(ircode)    
}

//Botão #16 para dashboard
def fanHigh(){
    sendEvent(name: "thermostatMode", value: "fanHigh")
    def ircode = ircodetemp + "&s=" + valormodo + "&tp=5"
    EnviaComando(ircode)    
}


def AtualizaDadosGW3(ipADD,TempserialNum,TempverifyCode,Tempchannel, TempcId) {
    state.currentip = ipADD
    state.serialNum = TempserialNum
    state.verifyCode = TempverifyCode
    state.channel = Tempchannel
    state.cId = TempcId
//    state.rcId = "52"
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.channel + " -- " + state.cId 
}


def EnviaComando(command) {
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.cId + "&rcId=52" + "&state=1" + "&pw=" + command 
    httpPOSTExec(URI)
    log.info "HTTP" +  URI + " + commando = " + command
    
    //sendEvent(name: "status", value: tempStatus)

    
    
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
                       
                        //def resp_json
                        //def coverfile
                        //resp_json = resp.data
                        //coverfile = resp_json.track.album.image[1]."#text"
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
