/**
 *  MolSmart GW3 Driver - IR(HEX) para TV  e SOM. Usando HEX PRONTO v.2
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
 *            --- Driver para GW3 - IR - para TV --- Usando HEX PRONTO
 *              V.2.0   - Versão com Driver sem precissar do APP
 *
 */
metadata {
  definition (name: "MolSmart - GW3 - IR(HEX) - TV e SOM", namespace: "TRATO", author: "VH", vid: "generic-contact") {
    capability "TV"  
    capability "SamsungTV"
    capability "Switch"  
    capability "Actuator"
    capability "PushableButton"
	capability "Variable"      
  
	attribute "channel", "number"
	attribute "volume", "number"
	attribute "movieMode", "string"
	attribute "power", "string"
	attribute "sound", "string"
	attribute "picture", "string"  
    

       command "CodigoHEXmute", [
            [name: "HEXcode", type: "STRING", description: "MUTE = COLAR O CODIGO HEX"]
        ]          
        command "CodigoHEXsource", [
            [name: "HEXcode", type: "STRING", description: "SOURCE = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXback", [
            [name: "HEXcode", type: "STRING", description: "BACK = COLAR O CODIGO HEX"]
        ]         
        command "CodigoHEXmenu", [
            [name: "HEXcode", type: "STRING", description: "MENU = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXhdmi1", [
            [name: "HEXcode", type: "STRING", description: "HDMI1 = COLAR O CODIGO HEX"]
        ]      
   	    command "CodigoHEXhdmi2" , [
            [name: "HEXcode", type: "STRING", description: "HDMI2 = COLAR O CODIGO HEX"]
        ]       
        command "CodigoHEXup", [
            [name: "HEXcode", type: "STRING", description: "UP = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXdown", [
            [name: "HEXcode", type: "STRING", description: "DOWN = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXright", [
            [name: "HEXcode", type: "STRING", description: "RIGHT = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXleft", [
            [name: "HEXcode", type: "STRING", description: "LEFT = COLAR O CODIGO HEX"]
        ]      
   	    command "CodigoHEXconfirm", [
            [name: "HEXcode", type: "STRING", description: "CONFIRM = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXexit"   , [
            [name: "HEXcode", type: "STRING", description: "EXIT = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXhome", [
            [name: "HEXcode", type: "STRING", description: "HOME = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXchannelUp", [
            [name: "HEXcode", type: "STRING", description: "CHANUP = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXchannelDown", [
            [name: "HEXcode", type: "STRING", description: "CHANDOWN = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXvolumeUp", [
            [name: "HEXcode", type: "STRING", description: "VOLUP = COLAR O CODIGO HEX"]
        ]      
   	    command "CodigoHEXvolumeDown", [
            [name: "HEXcode", type: "STRING", description: "VOLDOWN = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum0" , [
            [name: "HEXcode", type: "STRING", description: "NUM0 = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXnum1", [
            [name: "HEXcode", type: "STRING", description: "NUM1 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum2", [
            [name: "HEXcode", type: "STRING", description: "NUM2 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum3", [
            [name: "HEXcode", type: "STRING", description: "NUM3 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum4", [
            [name: "HEXcode", type: "STRING", description: "NUM4 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum5", [
            [name: "HEXcode", type: "STRING", description: "NUM5 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum6", [
            [name: "HEXcode", type: "STRING", description: "NUM6 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum7", [
            [name: "HEXcode", type: "STRING", description: "NUM7 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum8", [
            [name: "HEXcode", type: "STRING", description: "NUM8 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXnum9", [
            [name: "HEXcode", type: "STRING", description: "NUM9 = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXbtnextra1" , [
            [name: "HEXcode", type: "STRING", description: "EXTRA1 = COLAR O CODIGO HEX"]
        ]      
    	command "CodigoHEXbtnextra2"  , [
            [name: "HEXcode", type: "STRING", description: "EXTRA2 = COLAR O CODIGO HEX"]
        ]           
    	command "CodigoHEXbtnextra3"  , [
            [name: "HEXcode", type: "STRING", description: "EXTRA3 = COLAR O CODIGO HEX"]
        ]        
        command "CodigoHEXappnetflix" , [
            [name: "HEXcode", type: "STRING", description: "NETFLIX = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXappamazon", [
            [name: "HEXcode", type: "STRING", description: "AMAZON = COLAR O CODIGO HEX"]
        ]      
        command "CodigoHEXappyoutube"  , [
            [name: "HEXcode", type: "STRING", description: "YOUTUBE = COLAR O CODIGO HEX"]
        ]  
        command "CodigoHEXON"  , [
            [name: "HEXcode", type: "STRING", description: "ON = COLAR O CODIGO HEX"]
        ]  
        command "CodigoHEXOFF"  , [
            [name: "HEXcode", type: "STRING", description: "OFF = COLAR O CODIGO HEX"]
        ]          

  }
      
      


}

  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
        input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true
	    input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true
	    input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", required: true        
        input name: "repeatSendHEX", title:"Repeat for SendHex", type: "string", defaultValue: "1"   // REPEAT SEND PRONTO HEX
         
      
      
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
   
    sendEvent(name:"numberOfButtons", value:45)    
    log.debug "updated()"

}

//Get Device info and set as state to use during driver.
def AtualizaDadosGW3() {
    state.currentip = settings.molIPAddress
    state.serialNum = settings.serialNum
    state.verifyCode = settings.verifyCode
    state.channel = settings.channel
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.channel 

}

def CodigoHEXON(final String HEXcode){
    info "CodigoHEXON(${HEXcode})"
    state.OnIRsend  = HEXcode
}

def CodigoHEXOFF(final String HEXcode){
    info "CodigoHEXOFF(${HEXcode})"
    state.OFFIRsend  = HEXcode
}

def CodigoHEXmute(final String HEXcode){
    info "CodigoHEXmute(${HEXcode})"
    state.muteIRsend  = HEXcode
}


def CodigoHEXsource(final String HEXcode){
    info "CodigoHEXsource(${HEXcode})"
    state.sourceIRsend  = HEXcode
}


def CodigoHEXback(final String HEXcode){
    info "CodigoHEXback(${HEXcode})"
    state.backIRsend  = HEXcode
}


def CodigoHEXmenu(final String HEXcode){
    info "CodigoHEXmenu(${HEXcode})"
    state.menuIRsend  = HEXcode
}


def CodigoHEXhdmi1(final String HEXcode){
    info "CodigoHEXhdmi1(${HEXcode})"
    state.hdmi1IRsend  = HEXcode
}


def CodigoHEXhdmi2(final String HEXcode){
    info "CodigoHEXhdmi2(${HEXcode})"
    state.hdmi2IRsend  = HEXcode
}


def CodigoHEXup(final String HEXcode){
    info "CodigoHEXup(${HEXcode})"
    state.upIRsend  = HEXcode
}


def CodigoHEXdown(final String HEXcode){
    info "CodigoHEXdown(${HEXcode})"
    state.downIRsend  = HEXcode
}


def CodigoHEXright(final String HEXcode){
    info "CodigoHEXright(${HEXcode})"
    state.rightIRsend  = HEXcode
}

def CodigoHEXleft(final String HEXcode){
    info "CodigoHEXleft(${HEXcode})"
    state.leftIRsend  = HEXcode
}

def CodigoHEXconfirm(final String HEXcode){
    info "CodigoHEXconfirm(${HEXcode})"
    state.confirmIRsend  = HEXcode
}


def CodigoHEXexit(final String HEXcode){
    info "CodigoHEXexit(${HEXcode})"
    state.exitIRsend  = HEXcode
}


def CodigoHEXhome(final String HEXcode){
    info "CodigoHEXhome(${HEXcode})"
    state.homeIRsend  = HEXcode
}


def CodigoHEXchannelUp(final String HEXcode){
    info "CodigoHEXchannelUp(${HEXcode})"
    state.ChanUpIRsend  = HEXcode
}


def CodigoHEXchannelDown(final String HEXcode){
    info "CodigoHEXchannelDown(${HEXcode})"
    state.ChanDownIRsend  = HEXcode
}


def CodigoHEXvolumeUp(final String HEXcode){
    info "CodigoHEXvolumeUp(${HEXcode})"
    state.VolUpIRsend  = HEXcode
}


def CodigoHEXvolumeDown(final String HEXcode){
    info "CodigoHEXvolumeDown(${HEXcode})"
    state.VolDownIRsend  = HEXcode
}


def CodigoHEXnum0(final String HEXcode){
    info "CodigoHEXnum0(${HEXcode})"
    state.num0IRsend  = HEXcode
}


def CodigoHEXnum1(final String HEXcode){
    info "CodigoHEXnum1(${HEXcode})"
    state.num1IRsend  = HEXcode
}


def CodigoHEXnum2(final String HEXcode){
    info "CodigoHEXnum2(${HEXcode})"
    state.num2IRsend  = HEXcode
}


def CodigoHEXnum3(final String HEXcode){
    info "CodigoHEXnum3(${HEXcode})"
    state.num3IRsend  = HEXcode
}

def CodigoHEXnum4(final String HEXcode){
    info "CodigoHEXnum4(${HEXcode})"
    state.num4IRsend  = HEXcode
}

def CodigoHEXnum5(final String HEXcode){
    info "CodigoHEXnum5(${HEXcode})"
    state.num5IRsend  = HEXcode
}

def CodigoHEXnum6(final String HEXcode){
    info "CodigoHEXnum6(${HEXcode})"
    state.num6IRsend  = HEXcode
}

def CodigoHEXnum7(final String HEXcode){
    info "CodigoHEXnum7(${HEXcode})"
    state.num7IRsend  = HEXcode
}

def CodigoHEXnum8(final String HEXcode){
    info "CodigoHEXnum8(${HEXcode})"
    state.num8IRsend  = HEXcode
}

def CodigoHEXnum9(final String HEXcode){
    info "CodigoHEXnum9(${HEXcode})"
    state.num9IRsend  = HEXcode
}

def CodigoHEXbtnextra1(final String HEXcode){
    info "CodigoHEXbtnextra1(${HEXcode})"
    state.btnextra1IRsend  = HEXcode
}

def CodigoHEXbtnextra2(final String HEXcode){
    info "CodigoHEXbtnextra2(${HEXcode})"
    state.btnextra2IRsend  = HEXcode
}

def CodigoHEXbtnextra3(final String HEXcode){
    info "CodigoHEXbtnextra3(${HEXcode})"
    state.btnextra3IRsend  = HEXcode
}

def CodigoHEXappnetflix(final String HEXcode){
    info "CodigoHEXappnetflix(${HEXcode})"
    state.netflixIRsend  = HEXcode
}

def CodigoHEXappamazon(final String HEXcode){
    info "CodigoHEXappamazon(${HEXcode})"
    state.amazonIRsend  = HEXcode
}

def CodigoHEXappyoutube(final String HEXcode){
    info "CodigoHEXappyoutube(${HEXcode})"
    state.youtubeIRsend  = HEXcode
}





//Basico on e off para Switch 
def on() {
     sendEvent(name: "switch", value: "on", isStateChange: true)
     def ircode =  state.OnIRsend    
     EnviaComando(ircode)

}

def off() {
     sendEvent(name: "switch", value: "off", isStateChange: true)
     def ircode =  state.OFFIRsend    
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
    def ircode =  state.muteIRsend
    EnviaComando(ircode)    
}


//Botão #3 para dashboard
def source(){
	sendEvent(name: "action", value: "source")
    def ircode =  state.sourceIRsend
    EnviaComando(ircode)    
}

//Botão #4 para dashboard
def back(){
	sendEvent(name: "action", value: "back")
    def ircode = state.backIRsend
    EnviaComando(ircode)    
}

//Botão #5 para dashboard
def menu(){
	sendEvent(name: "action", value: "menu")
    def ircode =  state.menuIRsend
    EnviaComando(ircode)    
}


//Botão #6 para dashboard
def hdmi1(){
    sendEvent(name: "input", value: "hdmi1")
    def ircode =   state.hdmi1IRsend
    EnviaComando(ircode)
}

//Botão #7 para dashboard
def hdmi2(){
    sendEvent(name: "input", value: "hdmi2")
    def ircode =  state.hdmi2IRsend
    EnviaComando(ircode)
}



//Botão #8 para dashboard
def left(){
    sendEvent(name: "action", value: "left")
    def ircode =   state.leftIRsend
    EnviaComando(ircode)
}

//Botão #9 para dashboard
def right(){
    sendEvent(name: "action", value: "right")
     def ircode =  state.rightIRsend
    EnviaComando(ircode)
}



//Botão #10 para dashboard
def up(){
    sendEvent(name: "action", value: "up")
    def ircode =  state.upIRsend
    EnviaComando(ircode)
}

//Botão #11 para dashboard
def down(){
    sendEvent(name: "action", value: "down")
    def ircode =  state.downIRsend
    EnviaComando(ircode)
}

//Botão #12 para dashboard
def confirm(){
    sendEvent(name: "action", value: "confirm")
    def ircode =  state.confirmIRsend
    EnviaComando(ircode)
}


//Botão #13 para dashboard
def exit(){
	sendEvent(name: "action", value: "exit")
    def ircode =  state.exitIRsend
    EnviaComando(ircode)    
}




//Botão #14 para dashboard
def home(){
    sendEvent(name: "action", value: "home")
    def ircode =  state.homeIRsend
    EnviaComando(ircode)
}



//Botão #18 para dashboard
def channelUp(){
	sendEvent(name: "channel", value: "chup")
   def ircode =   state.ChanUpIRsend
    EnviaComando(ircode)    
}

//Botão #19 para dashboard
def channelDown(){
	sendEvent(name: "channel", value: "chdown")
    def ircode =  state.ChanDownIRsend
    EnviaComando(ircode)    
}

//Botão #21 para dashboard
def volumeUp(){
	sendEvent(name: "volume", value: "volup")
    def ircode = state.VolUpIRsend
    EnviaComando(ircode)    
}

//Botão #22 para dashboard
def volumeDown(){
	sendEvent(name: "volume", value: "voldown")
    def ircode = state.VolDownIRsend
    EnviaComando(ircode)    
}


//Botão #23 para dashboard
def num0(){
    sendEvent(name: "action", value: "num0")
    def ircode =  state.num0IRsend
    EnviaComando(ircode)
}

//Botão #24 para dashboard
def num1(){
    sendEvent(name: "action", value: "num1")
   def ircode =  state.num1IRsend
    EnviaComando(ircode)
}

//Botão #25 para dashboard
def num2(){
    sendEvent(name: "action", value: "num2")
    def ircode =  state.num2IRsend
    EnviaComando(ircode)
}


//Botão #26 para dashboard
def num3(){
    sendEvent(name: "action", value: "num3")
    def ircode =  state.num3IRsend
    EnviaComando(ircode)
}

//Botão #27 para dashboard
def num4(){
    sendEvent(name: "action", value: "num4")
    def ircode =  state.num4IRsend
    EnviaComando(ircode)
}

//Botão #28 para dashboard
def num5(){
    sendEvent(name: "action", value: "num5")
    def ircode =   state.num5IRsend
    EnviaComando(ircode)
}

//Botão #29 para dashboard
def num6(){
    sendEvent(name: "action", value: "num6")
    def ircode =  state.num6IRsend
    EnviaComando(ircode)
}


//Botão #30 para dashboard
def num7(){
    sendEvent(name: "action", value: "num7")
    def ircode =  state.num7IRsend
    EnviaComando(ircode)
}

//Botão #31 para dashboard
def num8(){
    sendEvent(name: "action", value: "num8")
    def ircode =  state.num8IRsend
    EnviaComando(ircode)
}

//Botão #32 para dashboard
def num9(){
    sendEvent(name: "action", value: "num9")
    def ircode = state.num9IRsend
    EnviaComando(ircode)
}

//Botão #33 para dashboard
def btnextra1(){
    sendEvent(name: "action", value: "confirm")
    def ircode =  state.btnextra1IRsend
    EnviaComando(ircode)
}

//Botão #34 para dashboard
def btnextra2(){
    sendEvent(name: "action", value: "btnextra2")
    def ircode =  state.btnextra2IRsend
    EnviaComando(ircode)
}

//Botão #35 para dashboard
def btnextra3(){
    sendEvent(name: "action", value: "btnextra3")
    def ircode =  state.btnextra3IRsend
    EnviaComando(ircode)
}

//Botão #38 para dashboard
def appAmazonPrime(){
    sendEvent(name: "input", value: "amazon")
    def ircode =   state.amazonIRsend
    EnviaComando(ircode)
}

//Botão #39 para dashboard
def appyoutube(){
    sendEvent(name: "input", value: "youtube")
   def ircode =  state.youtubeIRsend
    EnviaComando(ircode)
}


//Botão #40 para dashboard
def appnetflix(){
    sendEvent(name: "input", value: "netflix")
    def ircode =  state.netflixIRsend
    EnviaComando(ircode)
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


