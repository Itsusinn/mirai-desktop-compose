package io.gi.it.mirai.desktop.event

import net.mamoe.mirai.Bot

object StartEvent:Event

object OpenLoginWindow:Event

class LoginEvent(val account:Long, val password:String):Event

class LoginFailedEvent():Event

class OpenMainWindow(val bot: Bot):Event