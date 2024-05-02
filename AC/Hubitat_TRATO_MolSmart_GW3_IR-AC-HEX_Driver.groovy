/**
 *  MolSmart GW3 Driver - IR para Ar Condicionado. Usando HEX
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
 *            --- Driver para GW3 - IR - para Ar CondicionadoC ---
 *            V.1. 31/3/2024 . Usando HEX
 *            V.1. 17/4/2024 . Adicionando campos para alterar na TELA os HEX no lugar de ter que alterar no código. 
 *            V.1.2 5/1/2024 . Adicionei os botÕes de SetCooling e SetHeating
 */


metadata {
  definition (name: "MolSmart GW3 - IR - AC(HEX)", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
      
 
          
  }
    
    
  }

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
	input name: "OnIRsend", title:"On-HEX", type: "textarea"
	input name: "OffIRsend", title:"Off-HEX", type: "textarea"
    input name: "autoIRsend", title:"Auto-HEX(2)", type: "textarea"  
	input name: "heatIRsend", title:"Heat-HEX(3)", type: "textarea"	
    input name: "coolIRsend", title:"Cool-HEX(4)", type: "textarea"  
    input name: "fanIRsend", title:"fan-HEX(5)", type: "textarea"	
    input name: "dryIRsend", title:"dry-HEX(6)", type: "textarea"
	input name: "setCoolingSetpointIRsend", title:"setCooling-HEX(7)", type: "textarea"
	input name: "setHeatingSetpointIRsend", title:"setHeating-HEX(8)", type: "textarea"
	input name: "comandoextra1", title:"ex1-HEX(9)", type: "textarea"
	input name: "comandoextra2", title:"ex2-HEX(10)", type: "textarea"
	input name: "comandoextra3", title:"ex3-HEX(11)", type: "textarea"      
	input name: "comandoextra4", title:"ex4-HEX(12)", type: "textarea"      
	input name: "comandoextra5", title:"ex5-HEX(13)", type: "textarea"   
    input name: "repeatSendHEX", title:"Repeat for SendHex(14)", type: "string", defaultValue: "1"    
         
       
  }   
  

def initialized()
{
    state.currentip = ""  
    log.debug "initialized()"
    
}

def installed()
{
   

    sendEvent(name:"numberOfButtons", value:12)     
    log.debug "installed()"
    
}

def updated()
{
   
    sendEvent(name:"numberOfButtons", value:12)    
    log.debug "updated()"
    
}


def on() {
    sendEvent(name: "thermostatMode", value: "on", descriptionText: "Thermostat Mode set to on", isStateChange: true)
     def ircode =  (settings.OnIRsend ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "thermostatMode", value: "off", descriptionText: "Thermostat Mode set to off", isStateChange: true)
     def ircode =  (settings.OffIRsend ?: "")    
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
		case 2 : auto(); break
		case 3 : heat(); break
		case 4 : cool(); break
        case 5 : fan(); break
        case 6 : dry(); break
        case 7 : setCoolingSetpoint(); break                
        case 8 : setHeatingSetpoint(); break                
        case 9 : comandoextra1(); break    
        case 10 : comandoextra2(); break            
        case 11 : comandoextra3(); break            
        case 12 : comandoextra4(); break    
        case 13 : comandoextra5(); break            
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

//Botão #2 para dashboard
def auto(){
    sendEvent(name: "thermostatMode", value: "auto")
    def ircode =  (settings.autoIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #3 para dashboard
def heat(){
    sendEvent(name: "thermostatMode", value: "heat")
    def ircode =  (settings.heatIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def cool(){
    sendEvent(name: "thermostatMode", value: "cool")
    def ircode =  (settings.coolIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def fan(){
    sendEvent(name: "thermostatMode", value: "fan")
    def ircode =  (settings.fanIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #6 para dashboard
def dry(){
    sendEvent(name: "thermostatMode", value: "dry")
    def ircode =  (settings.dryIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #7 para dashboard
def setCoolingSetpoint(temperature){
    sendEvent(name: "setCoolingSetpoint", value: temperature )
    def ircode =  (settings.setCoolingSetpointIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #7 para dashboard
def setHeatingSetpoint(temperature){
    sendEvent(name: "setHeatingSetpoint", value: temperature )
    def ircode =  (settings.setHeatingSetpointIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #8 para dashboard
def comandoextra1(){
    sendEvent(name: "thermostatMode", value: "comandoextra1" )
    def ircode =  (settings.comandoextra1 ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def comandoextra2(){
    sendEvent(name: "thermostatMode", value: "comandoextra2" )
    def ircode =  (settings.comandoextra2 ?: "")
    EnviaComando(ircode)
}

//Botão #10 para dashboard
def comandoextra3(){
    sendEvent(name: "thermostatMode", value: "comandoextra3" )
    def ircode =  (settings.comandoextra3 ?: "")
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def comandoextra4(){
    sendEvent(name: "thermostatMode", value: "dcomandoextra4" )
    def ircode =  (settings.comandoextra4 ?: "")
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def comandoextra5(){
    sendEvent(name: "coolingSetpoint", value: "comandoextra5" )
    def ircode =  (settings.comandoextra5 ?: "")
    EnviaComando(ircode)
}


//Botão #13 para dashboard
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
    def ircode = (settings.heatIRsend ?: "")
    EnviaComando(ircode)  
}

//Botão #13 para dashboard
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
            valormodo = "2"; 
            break  
		case "cool" : 
            valormodo = "1"  ; 
            break
        case "on"  : 
            valormodo = "-1" ; 
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    def ircode = (settings.heatIRsend ?: "")
    EnviaComando(ircode)  
}


def AtualizaDadosGW3(ipADD,TempserialNum,TempverifyCode,Tempchannel) {
    state.currentip = ipADD
    state.serialNum = TempserialNum
    state.verifyCode = TempverifyCode
    state.channel = Tempchannel
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.channel 
}


def EnviaComando(command) {
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&pronto=" + command + "&cId=" + state.channel + "&r=" + settings.repeatSendHEX        
    httpPOSTExec(URI)
    log.info "HTTP" +  URI + "commando = "
    
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

