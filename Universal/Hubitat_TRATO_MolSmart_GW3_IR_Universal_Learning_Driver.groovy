/**
 *  MolSmart GW3 Driver - IR Universal Learning -  Universal do iDoor
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
 *	      V.1.1 6/4/2024 - corregimos bom joão o erro no código. 
 *
 */
metadata {
  definition (name: "MolSmart GW3 - IR Universal(Learning)", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
    capability "TV"  
    capability "Samsung TV"        
      
        command "Comando3"      
        command "Comando4"
        command "Comando5"   
        command "Comando6"
        command "Comando7"
   	    command "Comando8"  
        command "Comando9"
    	command "Comando10"
          
  }
    
    
  }

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
	input name: "ComandoOn", title:"ComandoOn-Botão(1)", type: "string" , defaultValue: "1"
	input name: "ComandoOff", title:"ComandoOff-Botão(2)", type: "string" , defaultValue: "2"
    input name: "Comando3", title:"Comando3-Botão(3)", type: "string" , defaultValue: "3"
	input name: "Comando4", title:"Comando4-Botão(4)", type: "string" , defaultValue: "4" 	
    input name: "Comando5", title:"Comando5-Botão(5)", type: "string" , defaultValue: "5" 
    input name: "Comando6", title:"Comando6-Botão(6)", type: "string" , defaultValue: "6"
    input name: "Comando7", title:"Comando7-Botão(7)", type: "string" , defaultValue: "7"
	input name: "Comando8", title:"Comando8-Botão(8)", type: "string" , defaultValue: "8"
	input name: "Comando9", title:"Comando9-Botão(9)", type: "string" , defaultValue: "9"
	input name: "Comando10", title:"Comando10-Botão(10)", type: "string" , defaultValue: "10"
         
       
  }   
  

def initialized()
{
    state.currentip = ""  
    state.botaouniversal = ""
    log.debug "initialized()"
    
}

def installed()
{
   

    sendEvent(name:"numberOfButtons", value:10)     
    log.debug "installed()"
    
}

def updated()
{
   
    sendEvent(name:"numberOfButtons", value:10)    
    log.debug "updated()"
    
}


def on() {
    sendEvent(name: "status", value: "on", descriptionText: "Universal Remote Set to on", isStateChange: true)
     def ircode =  (settings.ComandoOn ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "status", value: "off", descriptionText: "Universal Remote Set to off", isStateChange: true)
     def ircode =  (settings.ComandoOff ?: "")    
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
		case 3 : Comando3(); break
		case 4 : Comando4(); break
		case 5 : Comando5(); break
        case 6 : Comando6(); break
        case 7 : Comando7(); break
        case 8 : Comando8(); break                
        case 9 : Comando9(); break    
        case 10 : Comando10(); break            
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

//Botão #3 para dashboard
def Comando3(){
    sendEvent(name: "status", value: "Comando3")
    def ircode =  (settings.Comando3 ?: "")
    EnviaComando(ircode)    
    state.botaouniversal = ircode
}


//Botão #4 para dashboard
def Comando4(){
    sendEvent(name: "status", value: "Comando4")
    def ircode =  (settings.Comando4 ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def Comando5(){
    sendEvent(name: "status", value: "Comando5")
    def ircode =  (settings.Comando5 ?: "")
    EnviaComando(ircode)    
}

//Botão #6 para dashboard
def Comando6(){
    sendEvent(name: "status", value: "Comando6")
    def ircode =  (settings.Comando6 ?: "")
    EnviaComando(ircode)    
}


//Botão #7 para dashboard
def Comando7(){
    sendEvent(name: "status", value: "Comando7")
    def ircode =  (settings.Comando7 ?: "")
    EnviaComando(ircode)
}

//Botão #8 para dashboard
def Comando8(temperature){
    sendEvent(name: "status", value: "Comando8" )
    def ircode =  (settings.Comando8 ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def Comando9(){
    sendEvent(name: "status", value: "Comando9" )
    def ircode =  (settings.Comando9 ?: "")
    EnviaComando(ircode)
}

//Botão #10 para dashboard
def Comando10(){
    sendEvent(name: "status", value: "Comando10" )
    def ircode =  (settings.Comando10 ?: "")
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


    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.cId + "&state=" + buttonnumber + "&rcId=" + state.rcId                                                                                                        
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



