/**
 *  MolSmart GW3 Driver - IR AC Learning -  Universal do iDoor
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
 *            --- Driver para GW3 - IR Universal do iDoor ---
 *            V.1. 2/4/2024 
 *
 */
metadata {
  definition (name: "MolSmart GW3 - IR AC(Learning)", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
	input name: "OnIRsend", title:"On-Sendir", type: "string"
	input name: "OffIRsend", title:"Off-Sendir", type: "string"
    input name: "autoIRsend", title:"Auto-Sendir(2)", type: "string"  
	input name: "heatIRsend", title:"Heat-Sendir(3)", type: "string"	
    input name: "coolIRsend", title:"Cool-Sendir(4)", type: "string"  
    input name: "fanIRsend", title:"fan-Sendir(5)", type: "string"	
    input name: "dryIRsend", title:"dry-Sendir(6)", type: "string"
	input name: "setCoolingSetpointIRsend", title:"setCooling-Sendir(7)", type: "string"
	input name: "comandoextra1", title:"Comandoextra1-Sendir(8)", type: "string"
	input name: "comandoextra2", title:"Comandoextra2-Sendir(9)", type: "string"
	input name: "comandoextra3", title:"Comandoextra3-Sendir(10)", type: "string"      
	input name: "comandoextra4", title:"Comandoextra4-Sendir(11)", type: "string"      
	input name: "comandoextra5", title:"Comandoextra5-Sendir(12)", type: "string"      
         
       
  }   
  

def initialized()
{
    state.currentip = ""  
    state.botaouniversal = ""
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
		case 3 : auto(); break
		case 4 : heat(); break
		case 5 : cool(); break
        case 6 : fan(); break
        case 7 : dry(); break
        case 8 : setautocool(); break                
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

//Botão #3 para dashboard
def auto(){
    sendEvent(name: "thermostatMode", value: "auto")
    def ircode =  (settings.autoIRsend ?: "")
    EnviaComando(ircode)    
    state.botaouniversal = ircode
}


//Botão #4 para dashboard
def heat(){
    sendEvent(name: "thermostatMode", value: "heat")
    def ircode =  (settings.heatIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def cool(){
    sendEvent(name: "thermostatMode", value: "cool")
    def ircode =  (settings.coolIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #6 para dashboard
def fan(){
    sendEvent(name: "thermostatMode", value: "fan")
    def ircode =  (settings.fanIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #7 para dashboard
def dry(){
    sendEvent(name: "thermostatMode", value: "dry")
    def ircode =  (settings.dryIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #8 para dashboard
def setCoolingSetpoint(temperature){
    sendEvent(name: "thermostatMode", value: temperature )
    def ircode =  (settings.setCoolingSetpointIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def comandoextra1(){
    sendEvent(name: "thermostatMode", value: "comandoextra1" )
    def ircode =  (settings.comandoextra1 ?: "")
    EnviaComando(ircode)
}

//Botão #10 para dashboard
def comandoextra2(){
    sendEvent(name: "thermostatMode", value: "comandoextra2" )
    def ircode =  (settings.comandoextra2 ?: "")
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def comandoextra3(){
    sendEvent(name: "thermostatMode", value: "comandoextra3" )
    def ircode =  (settings.comandoextra3 ?: "")
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def comandoextra4(){
    sendEvent(name: "thermostatMode", value: "comandoextra4" )
    def ircode =  (settings.comandoextra4 ?: "")
    EnviaComando(ircode)
}

//Botão #13 para dashboard
def comandoextra5(){
    sendEvent(name: "coolingSetpoint", value: "comandoextra5" )
    def ircode =  (settings.comandoextra5 ?: "")
    EnviaComando(ircode)
}


def AtualizaDadosGW3(ipADD,TempserialNum,TempverifyCode,TempcId,TemprcId) {
    state.currentip = ipADD
    state.serialNum = TempserialNum
    state.verifyCode = TempverifyCode
    state.cId = TempcId
    state.rcId = TemprcId
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.cId + " -- " +  state.rcId
}


def EnviaComando(buttonnumber) {


    def URI = "https://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.cId + "&state=" + buttonnumber + "&rcId=" + state.rcId                                                                                                        
    httpPOSTExec(URI)
    log.info "HTTP" +  URI + "+ commando = "
    
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



