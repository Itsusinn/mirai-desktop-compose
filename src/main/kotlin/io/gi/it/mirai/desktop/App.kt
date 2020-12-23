package io.gi.it.mirai.desktop

import io.gi.it.mirai.desktop.event.OpenLoginWindow
import io.gi.it.mirai.desktop.event.StartEvent
import io.gi.it.mirai.desktop.event.eventloop.consumer
import io.gi.it.mirai.desktop.event.eventloop.publish
import io.gi.it.mirai.desktop.view.LoginWindow

object App{
   @JvmStatic fun main(args: Array<String>){
      consumer<OpenLoginWindow> {
         LoginWindow()
      }
      publish(StartEvent)
      publish(OpenLoginWindow)
   }
}

