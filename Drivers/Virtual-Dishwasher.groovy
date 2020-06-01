
/**
 *  Virtual Dishwasher
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
    definition(name: "Virtual Dishwasher", namespace: "at9", author: "at9", importUrl: "https://raw.githubusercontent.com/at-9/hubitat/master/Drivers/Virtual-Dishwasher.groovy") {
        capability "Actuator"
        capability "Switch"

               
        attribute   "dishwasher", "string"
        
        command "start"
        command "unpack"
            }
}
preferences {
    section("Time") {
        input "delaymin", "number", title: "Dishwasher run time in minutes", defaultValue: "60", required: true

    }
}

def start() {
    
       sendEvent(name: "dishwasher", value: "running")
       sendEvent(name: "switch", value: "on")
       
       delay()
       runIn(state.delay,"finished",[overwrite: true])


}

def delay(){
       
    state.delay= 60 * delaymin as int
}

def finished() {
       sendEvent(name: "dishwasher", value: "needs emptying")
       sendEvent(name: "switch", value: "off")      
}

def unpack(){
       
      sendEvent(name: "dishwasher", value: "dirty")
      unschedule()    
} 

def on(){
       
    start()
} 
def off(){
       
    finished()
} 
def updated(){
       
    delay()
}
def installed(){
       state.delay = 3600
}
