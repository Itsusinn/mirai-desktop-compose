package io.gi.it.mirai.desktop.event.eventloop

import io.gi.it.mirai.desktop.event.LoginEvent
import io.gi.it.mirai.desktop.event.OpenLoginWindow
import io.gi.it.mirai.desktop.event.StartEvent
import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus

object EventLoop{
   private val vertx = Vertx.vertx()
   val eventBus: EventBus = vertx.eventBus()
   init {
      eventBus.registerLocalCodec()
   }
}

fun EventBus.registerLocalCodec(){
   this
      .registerDefaultCodec(StartEvent::class.java, LocalMessageCodec<StartEvent>())
      .registerDefaultCodec(OpenLoginWindow::class.java, LocalMessageCodec<OpenLoginWindow>())
      .registerDefaultCodec(LoginEvent::class.java, LocalMessageCodec<LoginEvent>())

}

