/**
 *  MolSmart GW3-IR para TV App for Hubitat.  Usando códigos SendIR
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
 *   +++  Versão para enviar Códigos SENDIR Diretamente no botão ++++
 *   +++  Versão IR para TV. Usando SendIR

*/

definition(
    name: "MolSmart GW3-IR TV(SendIR) App",
    namespace: "TRATO",
    author: "VH",
    description: "MolSmart GW3-IR para TV(SendIR)",
    category: "Remote Controls / IR / TV",
    iconUrl: "",
    iconX2Url: "")

preferences {
    page name: "mainPage", title: "", install: true, uninstall: true
}

def mainPage() {
    dynamicPage(name: "mainPage") {
    section("MolSmart GW3 (IR) Setup Configuration") {
        input "thisName", "text", title: "Nome personalizado para o GW3", submitOnChange: true
		if(thisName) app.updateLabel("$thisName")
        input name: "molIPAddress", type: "text", title: "MolSmart GW3 IP Address", submitOnChange: true, required: true, defaultValue: "192.168.1.100" 
    	input name: "serialNum", title:"Numero de serie (Etiqueta GW3)", type: "string", required: true
	    input name: "verifyCode", title:"Verify code (Etiqueta GW3)", type: "string", required: true
	    input name: "channel", title:"Canal Infravermelho (1/2 ou 3)", type: "string", required: true        
        input name: "logEnable", type: "bool", title: "Enable debug logging", defaultValue: false
        input name: "debugOutput", type: "bool", title: "Habilitar Log", defaultValue: false        
        
        
        
    }
    }
}

def installed() {
    log.debug "installed(): Installing MolSmart App Parent App"
    initialize()
    
}

def updated() {
    log.debug "updated(): Updating MolSmart App"
    //unschedule(pollStatus)
    initialize()

}

def uninstalled() {
    unschedule()
    log.debug "uninstalled(): Uninstalling MolSmart App"
}


def initialize() {

    unschedule()
    int inputCount = 1
    

    for(int i = 1; i<=inputCount; i++) {
        def contactName = "MolGW3-IR-TV-" + Integer.toString(i) + "_${app.id}"
	    logDebug "initialize(): adding driver = " + contactName
        
        def contactDev = getChildDevice(contactName)
	    if(!contactDev) contactDev = addChildDevice("TRATO", "MolSmart GW3 - IR - TV(SendIR)", contactName, null, [name: "MolSmGW3 IR-TV " + Integer.toString(i), inputNumber: thisName])

    
    }
    
     def ipmolsmart = settings.molIPAddress
     def devices = getAllChildDevices()
     for (aDevice in devices)
    {
        
        
        //aDevice.AtualizaIP(ipmolsmart)  //coloco o ip no relay.
        logDebug "Coloco el ip en cada device = $aDevice, " + ipmolsmart
        aDevice.AtualizaDadosGW3(ipmolsmart,serialNum,verifyCode,channel)  //envia todos os dados do GW3
        
    }

    
        
}



private logDebug(msg) {
  if (settings?.debugOutput || settings?.debugOutput == null) {
    log.debug "$msg"
  }
}




