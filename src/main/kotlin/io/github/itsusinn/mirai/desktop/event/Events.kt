package io.github.itsusinn.mirai.desktop.event

import androidx.compose.desktop.AppWindow
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import net.mamoe.mirai.Bot

interface Event

object StartEvent: Event

object OpenLoginWindow: Event

class LoginEvent(val account:String, val password:String): Event

class LoginFailedEvent(): Event

class OpenMainWindow(val bot: Bot): Event

class OnWindowCreated(val name:String, val window:AppWindow):Event

class CloseWindow(val name:String):Event
fun closeWindow(name: String){
   publish(CloseWindow(name),"window")
}

object ExitApp:Event
fun exitApp(){
   publish(ExitApp,"window")
}