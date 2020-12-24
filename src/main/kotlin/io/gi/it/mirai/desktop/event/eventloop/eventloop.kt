package io.gi.it.mirai.desktop.event.eventloop

import io.gi.it.mirai.desktop.event.Event
import io.gi.it.mirai.desktop.event.LoginEvent
import io.gi.it.mirai.desktop.event.OpenLoginWindow
import io.gi.it.mirai.desktop.event.StartEvent
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.MessageCodec
import kotlin.reflect.jvm.jvmName

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

inline fun <reified T> LocalMessageCodec(): MessageCodec<T, T> where T: Event = object : MessageCodec<T, T> {
   val name by lazy { "${T::class.jvmName}Codec" }
   override fun transform(s: T): T = s
   override fun name(): String = name
   override fun systemCodecID(): Byte = -1
   override fun encodeToWire(buffer: Buffer?, s: T) { throw NotImplementedError("No need to impl") }
   override fun decodeFromWire(pos: Int, buffer: Buffer?): T { throw NotImplementedError("No need to impl") }
}

