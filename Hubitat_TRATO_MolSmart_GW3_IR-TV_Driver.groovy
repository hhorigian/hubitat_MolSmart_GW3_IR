/**
 *  MolSmart GW3 Driver - IR para TV
 *
 *  Copyright 2024 VH 
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by/**
 *  MolSmart GW3 Driver - IR para TV
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
 *            --- Driver para GW3 - IR - para TV ---
 *            V.2. com botões para o dashboard. Cada comando tem um numero de botão para incluir no dashboard. 31/3/2024 
 *
 */
metadata {
  definition (name: "MolSmart GW3 - IR - TV", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
	input name: "OnIRsend", title:"On-Sendir", type: "string"
	input name: "OffIRsend", title:"Off-Sendir", type: "string"
    input name: "muteIRsend", title:"Mute-Sendir(2)", type: "string"  
	input name: "sourceIRsend", title:"Source-Sendir(3)", type: "string"	
    input name: "backIRsend", title:"Back-Sendir(4)", type: "string"  
    input name: "menuIRsend", title:"Menu-Sendir(5)", type: "string"	
    input name: "hdmi1IRsend", title:"Hdmi1-Sendir(6)", type: "string"
	input name: "hdmi2IRsend", title:"Hdmi2-Sendir(7)", type: "string"
	input name: "upIRsend", title:"Up-Sendir(8)", type: "string"
	input name: "downIRsend", title:"Down-Sendir(9)", type: "string"
	input name: "rightIRsend", title:"Right-Sendir(10)", type: "string"
	input name: "leftIRsend", title:"Left-Sendir(11)", type: "string"
	input name: "confirmIRsend", title:"Confirm-Sendir(12)", type: "string"      
    input name: "exitIRsend", title:"Exit-Sendir(13)", type: "string"  
    input name: "homeIRsend", title:"Home-Sendir(14)", type: "string"  
    input name: "ChanUpIRsend", title:"Channel Up-Sendir(18)", type: "string"
 	input name: "ChanDownIRsend", title:"Channel Down-Sendir(19)", type: "string"
 	input name: "VolUpIRsend", title:"Volume Up-Sendir(21)", type: "string"
 	input name: "VolDownIRsend", title:"Volume Down-Sendir(22)", type: "string"
	input name: "num0IRsend", title:"Num0-Sendir(23)", type: "string"
    input name: "num1IRsend", title:"Num1-Sendir(24)", type: "string"
	input name: "num2IRsend", title:"Num2-Sendir(25)", type: "string"
	input name: "num3IRsend", title:"Num3-Sendir(26)", type: "string"
	input name: "num4IRsend", title:"Num4-Sendir(27)", type: "string"
	input name: "num5IRsend", title:"Num5-Sendir(28)", type: "string"
	input name: "num6IRsend", title:"Num6-Sendir(29)", type: "string"
	input name: "num7IRsend", title:"Num7-Sendir(30)", type: "string"
	input name: "num8IRsend", title:"Num8-Sendir(31)", type: "string"
	input name: "num9IRsend", title:"Num9-Sendir(32)", type: "string"
    input name: "btnextra1IRsend", title:"Botonextra1-Sendir(33)", type: "string"  
    input name: "btnextra2IRsend", title:"Botonextra2-Sendir(34)", type: "string"        
    input name: "btnextra3IRsend", title:"Botonextra2-Sendir(35)", type: "string"     
    input name: "netflixIRsend", title:"Netflix-Sendir(38)", type: "string"
	input name: "amazonIRsend", title:"Amazon Prime-Sendir(39)", type: "string" 
    input name: "youtubeIRsend", title:"Youtube-Sendir(40)", type: "string"             
         
      
      
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
     // def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,21,22,21,22,64,22,64,22,21,22,21,22,64,22,21,22,64,22,64,22,21,22,21,22,64,22,64,22,21,22,1820"
     def ircode =  (settings.OnIRsend ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "switch", value: "off", isStateChange: true)
     //def ircode = "38000,1,1,170,170,20,63,20,63,20,63,20,20,20,20,20,20,20,20,20,20,20,63,20,63,20,63,20,20,20,20,20,20,20,20,20,20,20,20,20,63,20,20,20,20,20,20,20,20,20,20,20,20,20,63,20,20,20,63,20,63,20,63,20,63,20,63,20,63,20,1798"
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
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.muteIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #3 para dashboard
def source(){
	sendEvent(name: "action", value: "source")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.sourceIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def back(){
	sendEvent(name: "action", value: "back")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.backIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def menu(){
	sendEvent(name: "action", value: "menu")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.menuIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #6 para dashboard
def hdmi1(){
    sendEvent(name: "input", value: "hdmi1")
    //def ircode = "38000,1,1,173,173,21,65,21,65,21,65,21,21,21,21,21,21,21,21,21,21,21,65,21,65,21,65,21,21,21,21,21,21,21,21,21,21,21,65,21,21,21,21,21,65,21,21,21,65,21,65,21,65,21,21,21,65,21,65,21,21,21,65,21,21,21,21,21,21,21,1832"
    def ircode =  (settings.hdmi1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #7 para dashboard
def hdmi2(){
    sendEvent(name: "input", value: "hdmi2")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.hdmi2IRsend ?: "")
    EnviaComando(ircode)
}



//Botão #8 para dashboard
def left(){
    sendEvent(name: "action", value: "left")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def right(){
    sendEvent(name: "action", value: "right")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}



//Botão #10 para dashboard
def up(){
    sendEvent(name: "action", value: "up")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.upIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def down(){
    sendEvent(name: "action", value: "down")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.hdmi1 ?: "")
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def confirm(){
    sendEvent(name: "action", value: "confirm")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.confirmIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #13 para dashboard
def exit(){
	sendEvent(name: "action", value: "exit")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.exitIRsend ?: "")
    EnviaComando(ircode)    
}




//Botão #14 para dashboard
def home(){
    sendEvent(name: "action", value: "home")
    //def ircode = "38000,1,1,173,173,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,22,22,22,22,65,22,65,22,65,22,65,22,22,22,22,22,65,22,65,22,22,22,22,22,22,22,22,22,65,22,1787"
    def ircode =  (settings.homeIRsend ?: "")
    EnviaComando(ircode)
}



//Botão #18 para dashboard
def channelUp(){
	sendEvent(name: "channel", value: "chup")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.ChanUpIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #19 para dashboard
def channelDown(){
	sendEvent(name: "channel", value: "chdown")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.ChanDownIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #21 para dashboard
def volumeUp(){
	sendEvent(name: "volume", value: "volup")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.VolUpIRsend ?: "")
    EnviaComando(ircode)    
}

//Botão #22 para dashboard
def volumeDown(){
	sendEvent(name: "volume", value: "voldown")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.VolDownIRsend ?: "")
    EnviaComando(ircode)    
}


//Botão #23 para dashboard
def num0(){
    sendEvent(name: "action", value: "num0")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num0IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #24 para dashboard
def num1(){
    sendEvent(name: "action", value: "num1")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #25 para dashboard
def num2(){
    sendEvent(name: "action", value: "num2")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num2IRsend ?: "")
    EnviaComando(ircode)
}


//Botão #26 para dashboard
def num3(){
    sendEvent(name: "action", value: "num3")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num3IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #27 para dashboard
def num4(){
    sendEvent(name: "action", value: "num4")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num4IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #28 para dashboard
def num5(){
    sendEvent(name: "action", value: "num5")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num5IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #29 para dashboard
def num6(){
    sendEvent(name: "action", value: "num6")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num6IRsend ?: "")
    EnviaComando(ircode)
}


//Botão #30 para dashboard
def num7(){
    sendEvent(name: "action", value: "num7")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num7IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #31 para dashboard
def num8(){
    sendEvent(name: "action", value: "num8")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num8IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #32 para dashboard
def num9(){
    sendEvent(name: "action", value: "num9")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num9IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #33 para dashboard
def btnextra1(){
    sendEvent(name: "action", value: "confirm")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #34 para dashboard
def btnextra2(){
    sendEvent(name: "action", value: "btnextra2")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra2IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #35 para dashboard
def btnextra3(){
    sendEvent(name: "action", value: "btnextra3")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra3IRsend ?: "")
    EnviaComando(ircode)
}

//Botão #38 para dashboard
def appAmazonPrime(){
    sendEvent(name: "input", value: "amazon")
//  def ircode = "38000,1,1,173,173,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,65,22,22,22,65,22,65,22,65,22,65,22,65,22,65,22,22,22,65,22,22,22,22,22,22,22,22,22,1787"
    def ircode =  (settings.amazonIRsend ?: "")
    EnviaComando(ircode)
}

//Botão #39 para dashboard
def appyoutube(){
    sendEvent(name: "input", value: "youtube")
//  def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.youtubeIRsend ?: "")
    EnviaComando(ircode)
}


//Botão #40 para dashboard
def appnetflix(){
    sendEvent(name: "input", value: "netflix")
//  def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
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
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.channel + "&gc=" + command       
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



 applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 *
 *            --- Driver para GW3 - IR - para TV ---
 *
 *
 */
metadata {
  definition (name: "MolSmart GW3 - IR - TV", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "Switch"  
    capability "Actuator"
    capability "TV"  
    capability "SamsungTV"
  
      
      
	attribute "channel", "number"
	attribute "volume", "number"
	attribute "movieMode", "string"
	attribute "power", "string"
	attribute "sound", "string"
	attribute "picture", "string"  
    

        command "volup"
   	    command "voldown"
        command "mute"      
    	command "chup"
        command "chdown"
        command "hdmi1"
   	    command "hdmi2"
    	command "netflix"
        command "amazon"
        command "youtube"      
   	    command "up"
    	command "down"
    	command "right"
    	command "left"
    	command "confirm"
        command "home"
        command "exit"      
    	command "num1"
    	command "num2"
    	command "num3"
    	command "num4"
    	command "num5"
    	command "num6"
    	command "num7"
    	command "num8"
    	command "num9"
    	command "num0" 
    	command "btnextra1" 
    	command "btnextra2"       
    	command "btnextra3"         
      
          
        //command "sendCodeData", [[name: "code*", type: "STRING"]]
    
            
  }
      
  }

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
	input name: "OnIRsend", title:"On - IR", type: "string"
	input name: "OffIRsend", title:"Off - IR", type: "string"
 	input name: "ChanUpIRsend", title:"Channel Up - IR", type: "string"
 	input name: "ChanDownIRsend", title:"Channel Down - IR", type: "string"
 	input name: "VolUpIRsend", title:"Vol Up - IR", type: "string"
 	input name: "VolDownIRsend", title:"Vol Down - IR", type: "string"
	input name: "hdmi1IRsend", title:"Hdmi1 - IR", type: "string"
	input name: "hdmi2IRsend", title:"Hdmi2 - IR", type: "string"
	input name: "netflixIRsend", title:"Netflix - IR", type: "string"
	input name: "amazonIRsend", title:"Amazon Prime - IR", type: "string" 
    input name: "youtubeIRsend", title:"Youtube - IR", type: "string"       
	input name: "upIRsend", title:"up - IR", type: "string"
	input name: "downIRsend", title:"down -  IR", type: "string"
	input name: "rightIRsend", title:"right - IR", type: "string"
	input name: "leftIRsend", title:"left - IR", type: "string"
	input name: "confirmIRsend", title:"Confirm - IR", type: "string"
	input name: "num1IRsend", title:"num1 - IR", type: "string"
	input name: "num2IRsend", title:"num2 - IR", type: "string"
	input name: "num3IRsend", title:"num3 - IR", type: "string"
	input name: "num4IRsend", title:"num4 - IR", type: "string"
	input name: "num5IRsend", title:"num5 - IR", type: "string"
	input name: "num6IRsend", title:"num6 - IR", type: "string"
	input name: "num7IRsend", title:"num7 - IR", type: "string"
	input name: "num8IRsend", title:"num8 - IR", type: "string"
	input name: "num9IRsend", title:"num9 - IR", type: "string"
	input name: "num0IRsend", title:"num0 - IR", type: "string"
    input name: "muteIRsend", title:"mute - IR", type: "string"  
    input name: "homeIRsend", title:"home - IR", type: "string"  
    input name: "exitIRsend", title:"exit - IR", type: "string"  
    input name: "btnextra1IRsend", title:"botonextra1 - IR", type: "string"  
    input name: "btnextra2IRsend", title:"botonextra2 - IR", type: "string"        
    input name: "btnextra3IRsend", title:"botonextra2 - IR", type: "string"              
      
      
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
   
    //sendEvent(name:"numberOfButtons", value:3)    
    log.debug "updated()"
    
}



def on() {
     sendEvent(name: "switch", value: "on", isStateChange: true)
     // def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,21,22,21,22,64,22,64,22,21,22,21,22,64,22,21,22,64,22,64,22,21,22,21,22,64,22,64,22,21,22,1820"
     def ircode =  (settings.OnIRsend ?: "")
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "switch", value: "off", isStateChange: true)
     //def ircode = "38000,1,1,170,170,20,63,20,63,20,63,20,20,20,20,20,20,20,20,20,20,20,63,20,63,20,63,20,20,20,20,20,20,20,20,20,20,20,20,20,63,20,20,20,20,20,20,20,20,20,20,20,20,20,63,20,20,20,63,20,63,20,63,20,63,20,63,20,63,20,1798"
     def ircode =  (settings.OffIRsend ?: "")    
     EnviaComando(ircode)
         
}

def exit(){
	sendEvent(name: "action", value: "exit")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.exitIRsend ?: "")
    EnviaComando(ircode)    
}


def chup(){
	sendEvent(name: "channel", value: "chup")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.ChanUpIRsend ?: "")
    EnviaComando(ircode)    
}

def chdown(){
	sendEvent(name: "channel", value: "chdown")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.ChanDownIRsend ?: "")
    EnviaComando(ircode)    
}

def volup(){
	sendEvent(name: "volume", value: "volup")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.VolUpIRsend ?: "")
    EnviaComando(ircode)    
}

def voldown(){
	sendEvent(name: "volume", value: "voldown")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.VolDownIRsend ?: "")
    EnviaComando(ircode)    
}

def mute(){
	sendEvent(name: "volume", value: "mute")
//  def ircode = "38000,1,1,172,172,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,21,22,64,22,64,22,64,22,64,22,1820"
    def ircode =  (settings.muteIRsend ?: "")
    EnviaComando(ircode)    
}


def netflix(){
    sendEvent(name: "input", value: "netflix")
//  def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.netflixIRsend ?: "")
    EnviaComando(ircode)
}

def youtube(){
    sendEvent(name: "input", value: "youtube")
//  def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.youtubeIRsend ?: "")
    EnviaComando(ircode)
}

def amazon(){
    sendEvent(name: "input", value: "amazon")
//  def ircode = "38000,1,1,173,173,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,65,22,22,22,65,22,65,22,65,22,65,22,65,22,65,22,22,22,65,22,22,22,22,22,22,22,22,22,1787"
    def ircode =  (settings.amazonIRsend ?: "")
    EnviaComando(ircode)
}


def hdmi1(){
    sendEvent(name: "input", value: "hdmi1")
    //def ircode = "38000,1,1,173,173,21,65,21,65,21,65,21,21,21,21,21,21,21,21,21,21,21,65,21,65,21,65,21,21,21,21,21,21,21,21,21,21,21,65,21,21,21,21,21,65,21,21,21,65,21,65,21,65,21,21,21,65,21,65,21,21,21,65,21,21,21,21,21,21,21,1832"
    def ircode =  (settings.hdmi1IRsend ?: "")
    EnviaComando(ircode)
}


def hdmi2(){
    sendEvent(name: "input", value: "hdmi2")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.hdmi2IRsend ?: "")
    EnviaComando(ircode)
}


def home(){
    sendEvent(name: "action", value: "home")
    //def ircode = "38000,1,1,173,173,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,65,22,65,22,22,22,22,22,22,22,22,22,22,22,65,22,22,22,22,22,65,22,65,22,65,22,65,22,22,22,22,22,65,22,65,22,22,22,22,22,22,22,22,22,65,22,1787"
    def ircode =  (settings.homeIRsend ?: "")
    EnviaComando(ircode)
}


def confirm(){
    sendEvent(name: "action", value: "confirm")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.confirmIRsend ?: "")
    EnviaComando(ircode)
}

def up(){
    sendEvent(name: "action", value: "up")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.upIRsend ?: "")
    EnviaComando(ircode)
}

def down(){
    sendEvent(name: "action", value: "down")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.hdmi1 ?: "")
    EnviaComando(ircode)
}


def right(){
    sendEvent(name: "action", value: "right")
    //def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

def left(){
    sendEvent(name: "action", value: "left")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

def num0(){
    sendEvent(name: "action", value: "num0")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num0IRsend ?: "")
    EnviaComando(ircode)
}

def num1(){
    sendEvent(name: "action", value: "num1")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num1IRsend ?: "")
    EnviaComando(ircode)
}

def num2(){
    sendEvent(name: "action", value: "num2")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num2IRsend ?: "")
    EnviaComando(ircode)
}

def num3(){
    sendEvent(name: "action", value: "num3")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num3IRsend ?: "")
    EnviaComando(ircode)
}

def num4(){
    sendEvent(name: "action", value: "num4")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num4IRsend ?: "")
    EnviaComando(ircode)
}

def num5(){
    sendEvent(name: "action", value: "num5")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num5IRsend ?: "")
    EnviaComando(ircode)
}

def num6(){
    sendEvent(name: "action", value: "num6")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num6IRsend ?: "")
    EnviaComando(ircode)
}

def num7(){
    sendEvent(name: "action", value: "num7")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num7IRsend ?: "")
    EnviaComando(ircode)
}

def num8(){
    sendEvent(name: "action", value: "num8")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num8IRsend ?: "")
    EnviaComando(ircode)
}

def num9(){
    sendEvent(name: "action", value: "num9")
//    def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.num9IRsend ?: "")
    EnviaComando(ircode)
}


def btnextra1(){
    sendEvent(name: "action", value: "confirm")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra1IRsend ?: "")
    EnviaComando(ircode)
}

def btnextra2(){
    sendEvent(name: "action", value: "btnextra2")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra2IRsend ?: "")
    EnviaComando(ircode)
}

def btnextra3(){
    sendEvent(name: "action", value: "btnextra3")
//   def ircode = "38000,1,1,172,171,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,65,21,65,21,22,21,22,21,22,21,22,21,1673"
    def ircode =  (settings.btnextra3IRsend ?: "")
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
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&cId=" + state.channel + "&gc=" + command       
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



