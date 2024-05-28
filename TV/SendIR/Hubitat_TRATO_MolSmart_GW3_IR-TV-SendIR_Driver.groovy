/**
 *  MolSmart GW3 Driver - IR para TV. Usando SendIR
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
 *            --- Driver para GW3 - IR - para TV --- Usando códigos SendIR
 *            V.2. com botões para o dashboard. Cada comando tem um numero de botão para incluir no dashboard. 31/3/2024 
 *            
 *
 */
metadata {
  definition (name: "MolSmart - GW3 - IR(SendIR) - TV", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "Switch"  
    capability "Actuator"
    capability "TV"  
    capability "SamsungTV"
    capability "PushableButton"
	capability "Variable"      
  
      
      
	attribute "channel", "number"
	attribute "volume", "number"
	attribute "movieMode", "string"
	attribute "power", "string"
	attribute "sound", "string"
	attribute "picture", "string"  
    


        command "mute"      
        command "source"
        command "back"   
        command "menu"
        command "hdmi1"
   	    command "hdmi2"  
        command "up"
    	command "down"
    	command "right"
    	command "left"
   	    command "confirm"
        command "exit"   
        command "home"
        command "channelUp"
        command "channelDown"
        command "volumeUp"
   	    command "volumeDown"
    	command "num0" 
        command "num1"
    	command "num2"
    	command "num3"
    	command "num4"
    	command "num5"
    	command "num6"
    	command "num7"
    	command "num8"
    	command "num9"
        command "btnextra1" 
    	command "btnextra2"       
    	command "btnextra3"    
        command "appnetflix" 
        command "appamazon"
        command "appyoutube"  
          
  }
      
  }

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
	input name: "OnIRsend", title:"On-Sendir", type: "textarea"
	input name: "OffIRsend", title:"Off-Sendir", type: "textarea"
    input name: "muteIRsend", title:"Mute-Sendir(2)", type: "textarea"  
	input name: "sourceIRsend", title:"Source-Sendir(3)", type: "textarea"	
    input name: "backIRsend", title:"Back-Sendir(4)", type: "textarea"  
    input name: "menuIRsend", title:"Menu-Sendir(5)", type: "textarea"	
    input name: "hdmi1IRsend", title:"Hdmi1-Sendir(6)", type: "textarea"
	input name: "hdmi2IRsend", title:"Hdmi2-Sendir(7)", type: "textarea"
	input name: "upIRsend", title:"Up-Sendir(8)", type: "textarea"
	input name: "downIRsend", title:"Down-Sendir(9)", type: "textarea"
	input name: "rightIRsend", title:"Right-Sendir(10)", type: "textarea"
	input name: "leftIRsend", title:"Left-Sendir(11)", type: "textarea"
	input name: "confirmIRsend", title:"Confirm-Sendir(12)", type: "textarea"      
    input name: "exitIRsend", title:"Exit-Sendir(13)", type: "textarea"  
    input name: "homeIRsend", title:"Home-Sendir(14)", type: "textarea"  
    input name: "ChanUpIRsend", title:"Channel Up-Sendir(18)", type: "textarea"
 	input name: "ChanDownIRsend", title:"Channel Down-Sendir(19)", type: "textarea"
 	input name: "VolUpIRsend", title:"Volume Up-Sendir(21)", type: "textarea"
 	input name: "VolDownIRsend", title:"Volume Down-Sendir(22)", type: "textarea"
	input name: "num0IRsend", title:"Num0-Sendir(23)", type: "textarea"
    input name: "num1IRsend", title:"Num1-Sendir(24)", type: "textarea"
	input name: "num2IRsend", title:"Num2-Sendir(25)", type: "textarea"
	input name: "num3IRsend", title:"Num3-Sendir(26)", type: "textarea"
	input name: "num4IRsend", title:"Num4-Sendir(27)", type: "textarea"
	input name: "num5IRsend", title:"Num5-Sendir(28)", type: "textarea"
	input name: "num6IRsend", title:"Num6-Sendir(29)", type: "textarea"
	input name: "num7IRsend", title:"Num7-Sendir(30)", type: "textarea"
	input name: "num8IRsend", title:"Num8-Sendir(31)", type: "textarea"
	input name: "num9IRsend", title:"Num9-Sendir(32)", type: "string"
    input name: "btnextra1IRsend", title:"Botonextra1-Sendir(33)", type: "textarea"  
    input name: "btnextra2IRsend", title:"Botonextra2-Sendir(34)", type: "textarea"        
    input name: "btnextra3IRsend", title:"Botonextra2-Sendir(35)", type: "textarea"     
    input name: "netflixIRsend", title:"Netflix-Sendir(38)", type: "textarea"
	input name: "amazonIRsend", title:"Amazon Prime-Sendir(39)", type: "textarea" 
    input name: "youtubeIRsend", title:"Youtube-Sendir(40)", type: "textarea"             
         
      
      
  }   
  

def initialized()
{
    state.currentip = ""  
    log.debug "initialized()"
    
}

def installed()
{
   

    //sendEvent(name:"numberOfButtons", value:3)   
    //sendEvent(name: "status", value: "stop")   
    log.debug "installed()"
    
}

def updated()
{
   
    sendEvent(name:"numberOfButtons", value:45)    
    log.debug "updated()"
    
}


//Basico on e off para Switch 
def on() {
     sendEvent(name: "switch", value: "on", isStateChange: true)
     def ircode =  (settings.OnIRsend ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "switch", value: "off", isStateChange: true)
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
		case 38: appAmazonPrime(); break
		case 39: appYouTube(); break
		case 40: appNetflix(); break    
		default:
			logDebug("push: Botão inválido.")
			break
	}
}

//Botão #2 para dashboard
def mute(){
	sendEvent(name: "volume", value: "mute")
    def ircode =  (settings.muteIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #3 para dashboard
def source(){
	sendEvent(name: "action", value: "source")
    def ircode =  (settings.sourceIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def back(){
	sendEvent(name: "action", value: "back")
    def ircode =  (settings.backIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def menu(){
	sendEvent(name: "action", value: "menu")
    def ircode =  (settings.menuIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #6 para dashboard
def hdmi1(){
    sendEvent(name: "input", value: "hdmi1")
    def ircode =  (settings.hdmi1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #7 para dashboard
def hdmi2(){
    sendEvent(name: "input", value: "hdmi2")
    def ircode =  (settings.hdmi2IRsend ?: "")
    EnviaComando(ircode)
}



//Botão #8 para dashboard
def left(){
    sendEvent(name: "action", value: "left")
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def right(){
    sendEvent(name: "action", value: "right")
     def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}



//Botão #10 para dashboard
def up(){
    sendEvent(name: "action", value: "up")
    def ircode =  (settings.upIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def down(){
    sendEvent(name: "action", value: "down")
    def ircode =  (settings.hdmi1 ?: "")
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def confirm(){
    sendEvent(name: "action", value: "confirm")
    def ircode =  (settings.confirmIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #13 para dashboard
def exit(){
	sendEvent(name: "action", value: "exit")
    def ircode =  (settings.exitIRsend ?: "")
    EnviaComando(ircode)    
}




//Botão #14 para dashboard
def home(){
    sendEvent(name: "action", value: "home")
    def ircode =  (settings.homeIRsend ?: "")
    EnviaComando(ircode)
}



//Botão #18 para dashboard
def channelUp(){
	sendEvent(name: "channel", value: "chup")
   def ircode =  (settings.ChanUpIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #19 para dashboard
def channelDown(){
	sendEvent(name: "channel", value: "chdown")
    def ircode =  (settings.ChanDownIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #21 para dashboard
def volumeUp(){
	sendEvent(name: "volume", value: "volup")
    def ircode =  (settings.VolUpIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #22 para dashboard
def volumeDown(){
	sendEvent(name: "volume", value: "voldown")
    def ircode =  (settings.VolDownIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #23 para dashboard
def num0(){
    sendEvent(name: "action", value: "num0")
    def ircode =  (settings.num0IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #24 para dashboard
def num1(){
    sendEvent(name: "action", value: "num1")
   def ircode =  (settings.num1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #25 para dashboard
def num2(){
    sendEvent(name: "action", value: "num2")
    def ircode =  (settings.num2IRsend ?: "")
    EnviaComando(ircode)
}


//Botão #26 para dashboard
def num3(){
    sendEvent(name: "action", value: "num3")
    def ircode =  (settings.num3IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #27 para dashboard
def num4(){
    sendEvent(name: "action", value: "num4")
    def ircode =  (settings.num4IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #28 para dashboard
def num5(){
    sendEvent(name: "action", value: "num5")
    def ircode =  (settings.num5IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #29 para dashboard
def num6(){
    sendEvent(name: "action", value: "num6")
    def ircode =  (settings.num6IRsend ?: "")
    EnviaComando(ircode)
}


//Botão #30 para dashboard
def num7(){
    sendEvent(name: "action", value: "num7")
    def ircode =  (settings.num7IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #31 para dashboard
def num8(){
    sendEvent(name: "action", value: "num8")
    def ircode =  (settings.num8IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #32 para dashboard
def num9(){
    sendEvent(name: "action", value: "num9")
    def ircode =  (settings.num9IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #33 para dashboard
def btnextra1(){
    sendEvent(name: "action", value: "confirm")
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #34 para dashboard
def btnextra2(){
    sendEvent(name: "action", value: "btnextra2")
    def ircode =  (settings.btnextra2IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #35 para dashboard
def btnextra3(){
    sendEvent(name: "action", value: "btnextra3")
    def ircode =  (settings.btnextra3IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #38 para dashboard
def appAmazonPrime(){
    sendEvent(name: "input", value: "amazon")
    def ircode =  (settings.amazonIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #39 para dashboard
def appyoutube(){
    sendEvent(name: "input", value: "youtube")
   def ircode =  (settings.youtubeIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #40 para dashboard
def appnetflix(){
    sendEvent(name: "input", value: "netflix")
    def ircode =  (settings.netflixIRsend ?: "")
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
    
    def URI = "https://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.channel + "&gc=" + command       
    httpPOSTExec(URI)
    log.info "HTTP" +  URI + "commando = "
    
    
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

void logDebug(String msg) {
    if ((Boolean)settings.logDebug != false) {
        log.debug "${drvThis}: ${msg}"
    }
}

