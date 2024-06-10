/**
 *  MolSmart GW3 Driver - IR(SendIR) para TV  e SOM. Usando SendIR v.2
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
 *            --- Driver para GW3 - IR - para TV --- Usando SendIR PRONTO
 *              V.2.0   - Versão com Driver sem precissar do APP + Códigos salvos no driver page.
 *              V.2.1   - 10/6/2024 - Fix URL
 *
 */
metadata {
  definition (name: "MolSmart - GW3 - IR(SendIR) - TV e SOM", namespace: "TRATO", author: "VH", vid: "generic-contact") {
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
    

       command "CodigoSendIR_mute", [
            [name: "SendIRcode", type: "STRING", description: "MUTE = COLAR O CODIGO SendIR"]
        ]          
        command "CodigoSendIR_source", [
            [name: "SendIRcode", type: "STRING", description: "SOURCE = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_back", [
            [name: "SendIRcode", type: "STRING", description: "BACK = COLAR O CODIGO SendIR"]
        ]         
        command "CodigoSendIR_menu", [
            [name: "SendIRcode", type: "STRING", description: "MENU = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_hdmi1", [
            [name: "SendIRcode", type: "STRING", description: "HDMI1 = COLAR O CODIGO SendIR"]
        ]      
   	    command "CodigoSendIR_hdmi2" , [
            [name: "SendIRcode", type: "STRING", description: "HDMI2 = COLAR O CODIGO SendIR"]
        ]       
        command "CodigoSendIR_up", [
            [name: "SendIRcode", type: "STRING", description: "UP = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_down", [
            [name: "SendIRcode", type: "STRING", description: "DOWN = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_right", [
            [name: "SendIRcode", type: "STRING", description: "RIGHT = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_left", [
            [name: "SendIRcode", type: "STRING", description: "LEFT = COLAR O CODIGO SendIR"]
        ]      
   	    command "CodigoSendIR_confirm", [
            [name: "SendIRcode", type: "STRING", description: "CONFIRM = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_exit"   , [
            [name: "SendIRcode", type: "STRING", description: "EXIT = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_home", [
            [name: "SendIRcode", type: "STRING", description: "HOME = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_channelUp", [
            [name: "SendIRcode", type: "STRING", description: "CHANUP = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_channelDown", [
            [name: "SendIRcode", type: "STRING", description: "CHANDOWN = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_volumeUp", [
            [name: "SendIRcode", type: "STRING", description: "VOLUP = COLAR O CODIGO SendIR"]
        ]      
   	    command "CodigoSendIR_volumeDown", [
            [name: "SendIRcode", type: "STRING", description: "VOLDOWN = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num0" , [
            [name: "SendIRcode", type: "STRING", description: "NUM0 = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_num1", [
            [name: "SendIRcode", type: "STRING", description: "NUM1 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num2", [
            [name: "SendIRcode", type: "STRING", description: "NUM2 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num3", [
            [name: "SendIRcode", type: "STRING", description: "NUM3 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num4", [
            [name: "SendIRcode", type: "STRING", description: "NUM4 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num5", [
            [name: "SendIRcode", type: "STRING", description: "NUM5 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num6", [
            [name: "SendIRcode", type: "STRING", description: "NUM6 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num7", [
            [name: "SendIRcode", type: "STRING", description: "NUM7 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num8", [
            [name: "SendIRcode", type: "STRING", description: "NUM8 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_num9", [
            [name: "SendIRcode", type: "STRING", description: "NUM9 = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_btnextra1" , [
            [name: "SendIRcode", type: "STRING", description: "EXTRA1 = COLAR O CODIGO SendIR"]
        ]      
    	command "CodigoSendIR_btnextra2"  , [
            [name: "SendIRcode", type: "STRING", description: "EXTRA2 = COLAR O CODIGO SendIR"]
        ]           
    	command "CodigoSendIR_btnextra3"  , [
            [name: "SendIRcode", type: "STRING", description: "EXTRA3 = COLAR O CODIGO SendIR"]
        ]        
        command "CodigoSendIR_APP_NETFLIX" , [
            [name: "SendIRcode", type: "STRING", description: "NETFLIX = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_APP_AMAZON", [
            [name: "SendIRcode", type: "STRING", description: "AMAZON = COLAR O CODIGO SendIR"]
        ]      
        command "CodigoSendIR_APP_YOUTUBE"  , [
            [name: "SendIRcode", type: "STRING", description: "YOUTUBE = COLAR O CODIGO SendIR"]
        ]  
        command "CodigoSendIR_ON"  , [
            [name: "SendIRcode", type: "STRING", description: "ON = COLAR O CODIGO SendIR"]
        ]  
        command "CodigoSendIR_OFF"  , [
            [name: "SendIRcode", type: "STRING", description: "OFF = COLAR O CODIGO SendIR"]
        ]          

  }
      
      


}
    import groovy.transform.Field
    @Field static final String DRIVER = "by TRATO"
    @Field static final String USER_GUIDE = "https://github.com/hhorigian/hubitat_MolSmart_GW3_IR/blob/main/README.md"


    String fmtHelpInfo(String str) {
    String prefLink = "<a href='${USER_GUIDE}' target='_blank'>${str}<br><div style='font-size: 70%;'>${DRIVER}</div></a>"
    return "<div style='font-size: 160%; font-style: bold; padding: 2px 0px; text-align: center;'>${prefLink}</div>"
    }


  preferences {
    input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
        input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true
	    input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true
	    input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", required: true        
         
      
      
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
    AtualizaDadosGW3()  

}

//Get Device info and set as state to use during driver.
def AtualizaDadosGW3() {
    state.currentip = settings.molIPAddress
    state.serialNum = settings.serialNum
    state.verifyCode = settings.verifyCode
    state.channel = settings.channel
    log.info "Dados do GW3 atualizados: " + state.currentip + " -- " + state.serialNum + " -- " +  state.verifyCode + " -- " + state.channel 

}

def CodigoSendIR_ON(final String SendIRcode){
    info "CodigoSendIR_ON(${SendIRcode})"
    state.OnIRsend  = SendIRcode
}

def CodigoSendIR_OFF(final String SendIRcode){
    info "CodigoSendIR_OFF(${SendIRcode})"
    state.OFFIRsend  = SendIRcode
}

def CodigoSendIR_mute(final String SendIRcode){
    info "CodigoSendIR_mute(${SendIRcode})"
    state.muteIRsend  = SendIRcode
}


def CodigoSendIR_source(final String SendIRcode){
    info "CodigoSendIR_source(${SendIRcode})"
    state.sourceIRsend  = SendIRcode
}


def CodigoSendIR_back(final String SendIRcode){
    info "CodigoSendIR_back(${SendIRcode})"
    state.backIRsend  = SendIRcode
}


def CodigoSendIR_menu(final String SendIRcode){
    info "CodigoSendIR_menu(${SendIRcode})"
    state.menuIRsend  = SendIRcode
}


def CodigoSendIR_hdmi1(final String SendIRcode){
    info "CodigoSendIR_hdmi1(${SendIRcode})"
    state.hdmi1IRsend  = SendIRcode
}


def CodigoSendIR_hdmi2(final String SendIRcode){
    info "CodigoSendIR_hdmi2(${SendIRcode})"
    state.hdmi2IRsend  = SendIRcode
}


def CodigoSendIR_up(final String SendIRcode){
    info "CodigoSendIR_up(${SendIRcode})"
    state.upIRsend  = SendIRcode
}


def CodigoSendIR_down(final String SendIRcode){
    info "CodigoSendIR_down(${SendIRcode})"
    state.downIRsend  = SendIRcode
}


def CodigoSendIR_right(final String SendIRcode){
    info "CodigoSendIR_right(${SendIRcode})"
    state.rightIRsend  = SendIRcode
}

def CodigoSendIR_left(final String SendIRcode){
    info "CodigoSendIR_left(${SendIRcode})"
    state.leftIRsend  = SendIRcode
}

def CodigoSendIR_confirm(final String SendIRcode){
    info "CodigoSendIR_confirm(${SendIRcode})"
    state.confirmIRsend  = SendIRcode
}


def CodigoSendIR_exit(final String SendIRcode){
    info "CodigoSendIR_exit(${SendIRcode})"
    state.exitIRsend  = SendIRcode
}


def CodigoSendIR_home(final String SendIRcode){
    info "CodigoSendIR_home(${SendIRcode})"
    state.homeIRsend  = SendIRcode
}


def CodigoSendIR_channelUp(final String SendIRcode){
    info "CodigoSendIR_channelUp(${SendIRcode})"
    state.ChanUpIRsend  = SendIRcode
}


def CodigoSendIR_channelDown(final String SendIRcode){
    info "CodigoSendIR_channelDown(${SendIRcode})"
    state.ChanDownIRsend  = SendIRcode
}


def CodigoSendIR_volumeUp(final String SendIRcode){
    info "CodigoSendIR_volumeUp(${SendIRcode})"
    state.VolUpIRsend  = SendIRcode
}


def CodigoSendIR_volumeDown(final String SendIRcode){
    info "CodigoSendIR_volumeDown(${SendIRcode})"
    state.VolDownIRsend  = SendIRcode
}


def CodigoSendIR_num0(final String SendIRcode){
    info "CodigoSendIR_num0(${SendIRcode})"
    state.num0IRsend  = SendIRcode
}


def CodigoSendIR_num1(final String SendIRcode){
    info "CodigoSendIR_num1(${SendIRcode})"
    state.num1IRsend  = SendIRcode
}


def CodigoSendIR_num2(final String SendIRcode){
    info "CodigoSendIR_num2(${SendIRcode})"
    state.num2IRsend  = SendIRcode
}


def CodigoSendIR_num3(final String SendIRcode){
    info "CodigoSendIR_num3(${SendIRcode})"
    state.num3IRsend  = SendIRcode
}

def CodigoSendIR_num4(final String SendIRcode){
    info "CodigoSendIR_num4(${SendIRcode})"
    state.num4IRsend  = SendIRcode
}

def CodigoSendIR_num5(final String SendIRcode){
    info "CodigoSendIR_num5(${SendIRcode})"
    state.num5IRsend  = SendIRcode
}

def CodigoSendIR_num6(final String SendIRcode){
    info "CodigoSendIR_num6(${SendIRcode})"
    state.num6IRsend  = SendIRcode
}

def CodigoSendIR_num7(final String SendIRcode){
    info "CodigoSendIR_num7(${SendIRcode})"
    state.num7IRsend  = SendIRcode
}

def CodigoSendIR_num8(final String SendIRcode){
    info "CodigoSendIR_num8(${SendIRcode})"
    state.num8IRsend  = SendIRcode
}

def CodigoSendIR_num9(final String SendIRcode){
    info "CodigoSendIR_num9(${SendIRcode})"
    state.num9IRsend  = SendIRcode
}

def CodigoSendIR_btnextra1(final String SendIRcode){
    info "CodigoSendIR_btnextra1(${SendIRcode})"
    state.btnextra1IRsend  = SendIRcode
}

def CodigoSendIR_btnextra2(final String SendIRcode){
    info "CodigoSendIR_btnextra2(${SendIRcode})"
    state.btnextra2IRsend  = SendIRcode
}

def CodigoSendIR_btnextra3(final String SendIRcode){
    info "CodigoSendIR_btnextra3(${SendIRcode})"
    state.btnextra3IRsend  = SendIRcode
}

def CodigoSendIR_APP_NETFLIX(final String SendIRcode){
    info "CodigoSendIR_APP_NETFLIX(${SendIRcode})"
    state.netflixIRsend  = SendIRcode
}

def CodigoSendIR_APP_AMAZON(final String SendIRcode){
    info "CodigoSendIR_APP_AMAZON(${SendIRcode})"
    state.amazonIRsend  = SendIRcode
}

def CodigoSendIR_APP_YOUTUBE(final String SendIRcode){
    info "CodigoSendIR_APP_YOUTUBE(${SendIRcode})"
    state.youtubeIRsend  = SendIRcode
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
    
    def URI = "http://" + state.currentip + "/api/device/deviceDetails/smartHomeAutoHttpControl?serialNum=" + state.serialNum + "&verifyCode="  + state.verifyCode + "&c=" + state.channel + "&gc=" + command       
    httpPOSTExec(URI)
    log.info "Enviado...HTTP" +  URI + " commando = "   
    
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


