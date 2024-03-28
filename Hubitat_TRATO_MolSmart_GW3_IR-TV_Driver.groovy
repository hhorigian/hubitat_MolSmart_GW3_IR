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
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
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



