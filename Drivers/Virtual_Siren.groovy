
/**
 *  Virtual Siren
 *	Device Driver for Hubitat Elevation
 *   	V0.1
 *
 *	Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *	in compliance with the License. You may obtain a copy of the License at:
 *
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *	for the specific language governing permissions and limitations under the License.
 */


metadata {
    definition(name: "Virtual Siren", namespace: "at9", author: "at9", importUrl: "https://raw.githubusercontent.com/at-9/hubitat/master/Drivers/Virtual_Siren.groovy") {
        capability "Actuator"
        capability "Alarm"


            }
}
preferences {
    section("Time") {
        input "resetsec", "number", title: "Reset time in Seconds", defaultValue: "30", required: true

    }
}

def both() {
    
       sendEvent(name: "alarm", value: "both")

       runIn(state.delay,"off",[overwrite: true])


}

void siren(){
       
       sendEvent(name: "alarm", value: "siren")

       runIn(state.delay,"off",[overwrite: true])
}

void strobe(){
       
       sendEvent(name: "alarm", value: "strobe")

       runIn(state.delay,"off",[overwrite: true])
}
void delay(){
       
    state.delay= 1 * resetsec as int
}
def off(){
       
    sendEvent(name: "alarm", value: "off")
} 
def updated(){
       
    delay()
}
def installed(){
       state.delay = 30

}
