/**
 *  MolSmart GW3 Driver - IR(HEX) para Ar Condicionado. Usando HEX
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
 *            V.1.3 5/8/2024 . Adicionei FanControl
 

*/


metadata {
  definition (name: "MolSmart - GW3 - IR(HEX)- AC", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
    input name: "repeatSendHEX", title:"Repeat for SendHex", type: "string", defaultValue: "1"    
    input name: "fanAuto", title:"fanAuto-Hex(13)", type: "textarea"	
    input name: "fanLow", title:"fanLow-Hex(14)", type: "textarea"	
    input name: "fanMed", title:"fanMed-Hex(15)", type: "textarea"	
    input name: "fanHigh", title:"fanHigh-Hex(16)", type: "textarea"	         
       
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
def fanAuto(){
    sendEvent(name: "thermostatMode", value: "fanAuto")
    def ircode =  (settings.fanAuto ?: "")
    EnviaComando(ircode)    
}

//Botão #14 para dashboard
def fanLow(){
    sendEvent(name: "thermostatMode", value: "fanLow")
    def ircode =  (settings.fanLow ?: "")
    EnviaComando(ircode)    
}

//Botão #15 para dashboard
def fanMed(){
    sendEvent(name: "thermostatMode", value: "fanMed")
    def ircode =  (settings.fanMed ?: "")
    EnviaComando(ircode)    
}

//Botão #16 para dashboard
def fanHigh(){
    sendEvent(name: "thermostatMode", value: "fanHigh")
    def ircode =  (settings.fanHigh ?: "")
    EnviaComando(ircode)    
}


def setThermostatMode(modo){
    def varmodo = modo
    sendEvent(name: "thermostatMode", value: modo)
    def ircodetemp = 1
    state.pw = "1"
    def valormodo = " "
    switch(modo) {
		case "auto" : 
            valormodo = "0"; 
            ircode =  (settings.autoIRsend ?: "")
            break
		case "heat" : 
            valormodo = "2"; 
            ircode = (settings.heatIRsend ?: "")        
            break  
		case "cool" : 
            valormodo = "1"  ; 
            ircode =  (settings.coolIRsend ?: "")
            break
        case "off"  : 
            valormodo = "-1" ; 
            ircode =  (settings.OffIRsend ?: "")            
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    //def ircode = (settings.heatIRsend ?: "")
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
            ircode =  (settings.fanIRsend ?: "")        
            break
		case "circulate" : 
            valormodo = "2"; 
            ircode =  (settings.fanIRsend ?: "")        
            break  
        case "on"  : 
            valormodo = "-1" ; 
            ircode =  (settings.fanIRsend ?: "")        
            break
        default: 
            logDebug("push: Botão inválido.")
            break   
    }
    //def ircode = (settings.heatIRsend ?: "")
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
