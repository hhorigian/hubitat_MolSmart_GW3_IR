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
 *        V.2.0 30/5/2024 - Versão Driver sem APP. 
 *        V.2.1 10/6/2024 - Fix Update State variables  
 *        V.2.2 13/6/2024 - Fix Update State variables  
 *        V.2.3 24/7/2024 - Added buttons 1 and 2 codes  
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
      
        command "Botao1"      
        command "Botao2"      
        command "Botao3"      
        command "Botao4"
        command "Botao5"   
        command "Botao6"
        command "Botao7"
        command "Botao8"  
        command "Botao9"
    	command "Botao10"
          
  }
    
    
  }

    import groovy.transform.Field
    @Field static final String DRIVER = "by TRATO"
    @Field static final String USER_GUIDE = "https://github.com/hhorigian/hubitat_MolSmart_GW3_IR/tree/main/Universal"


    String fmtHelpInfo(String str) {
    String prefLink = "<a href='${USER_GUIDE}' target='_blank'>${str}<br><div style='font-size: 70%;'>${DRIVER}</div></a>"
    return "<div style='font-size: 160%; font-style: bold; padding: 2px 0px; text-align: center;'>${prefLink}</div>"
    }


  preferences {
      input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true
	    input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true
    	input name: "cId", title:"Control ID (pego no idoor)", type: "string", required: true  
        input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
        input name: "debugOutput", type: "bool", title: "Habilitar Log", defaultValue: false           

	  //help guide
        input name: "UserGuide", type: "hidden", title: fmtHelpInfo("Manual do Driver") 

	input name: "BotaoOn", title:"ComandoOn-Botão(1)", type: "string" , defaultValue: "1"
	input name: "BotaoOff", title:"BotaoOff-Botão(2)", type: "string" , defaultValue: "2"
    input name: "Botao3", title:"Botao3-Botão(3)", type: "string" , defaultValue: "3"
	input name: "Botao4", title:"Botao4-Botão(4)", type: "string" , defaultValue: "4" 	
    input name: "Botao5", title:"Botao5-Botão(5)", type: "string" , defaultValue: "5" 
    input name: "Botao6", title:"Botao6-Botão(6)", type: "string" , defaultValue: "6"
    input name: "Botao7", title:"Botao7-Botão(7)", type: "string" , defaultValue: "7"
	input name: "Botao8", title:"Botao8-Botão(8)", type: "string" , defaultValue: "8"
	input name: "Botao9", title:"Botao9-Botão(9)", type: "string" , defaultValue: "9"
	input name: "Botao10", title:"Botao10-Botão(10)", type: "string" , defaultValue: "10"
         
       
  }   
  

def initialized()
{
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
    AtualizaDadosGW3()    
    
}

def AtualizaDadosGW3() {
    state.currentip = settings.molIPAddress
    state.serialNum = settings.serialNum
    state.verifyCode = settings.verifyCode
    state.cId = settings.cId
    state.rcId = 61
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.cId + " -- " +  state.rcId
}

def on() {
    sendEvent(name: "status", value: "on", descriptionText: "Universal Remote Set to on", isStateChange: true)
     def ircode =  (settings.BotaoOn ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "status", value: "off", descriptionText: "Universal Remote Set to off", isStateChange: true)
     def ircode =  (settings.BotaoOff ?: "")    
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
        case 1: Botao1(); break
        case 2: Botao2(); break
        case 3: Botao3(); break
		case 4 : Botao4(); break
		case 5 : Botao5(); break
        case 6 : Botao6(); break
        case 7 : Botao7(); break
        case 8 : Botao8(); break                
        case 9 : Botao9(); break    
        case 10 : Botao10(); break            
		default:
			logDebug("push: Botão inválido.")
			break
	}
}
		
//Botão #1 para dashboard
def Botao1(){
    sendEvent(name: "status", value: "Botao1")
    def ircode =  "1"
    EnviaComando(ircode)    
    state.botaouniversal = ircode
}

//Botão #1 para dashboard
def Botao2(){
    sendEvent(name: "status", value: "Botao2")
    def ircode =  "2"
    EnviaComando(ircode)    
    state.botaouniversal = ircode
}

//Botão #3 para dashboard
def Botao3(){
    sendEvent(name: "status", value: "Botao3")
    def ircode =  "3"
    EnviaComando(ircode)    
    state.botaouniversal = ircode
}


//Botão #4 para dashboard
def Botao4(){
    sendEvent(name: "status", value: "Botao4")
    def ircode =  (settings.Botao4 ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def Botao5(){
    sendEvent(name: "status", value: "Botao5")
    def ircode =  (settings.Botao5 ?: "")
    EnviaComando(ircode)    
}

//Botão #6 para dashboard
def Botao6(){
    sendEvent(name: "status", value: "Botao6")
    def ircode =  (settings.Botao6 ?: "")
    EnviaComando(ircode)    
}


//Botão #7 para dashboard
def Botao7(){
    sendEvent(name: "status", value: "Botao7")
    def ircode =  (settings.Botao7 ?: "")
    EnviaComando(ircode)
}

//Botão #8 para dashboard
def Botao8(temperature){
    sendEvent(name: "status", value: "Botao8" )
    def ircode =  (settings.Botao8 ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def Botao9(){
    sendEvent(name: "status", value: "Botao9" )
    def ircode =  (settings.Botao9 ?: "")
    EnviaComando(ircode)
}

//Botão #10 para dashboard
def Botao10(){
    sendEvent(name: "status", value: "Botao10" )
    def ircode =  (settings.Botao10 ?: "")
    EnviaComando(ircode)
}



def EnviaComando(buttonnumber) {


    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.cId + "&state=" + buttonnumber + "&rcId=" + state.rcId                                                                                                        
    httpPOSTExec(URI)
    log.info "Comando Enviad HTTP = " +  URI + "+ commando = " + buttonnumber
    
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




