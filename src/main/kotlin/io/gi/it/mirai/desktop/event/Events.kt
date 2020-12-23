package io.gi.it.mirai.desktop.event

object StartEvent:Event

object OpenLoginWindow:Event

class LoginEvent(
   val account:Long,
   val password:String
   ):Event