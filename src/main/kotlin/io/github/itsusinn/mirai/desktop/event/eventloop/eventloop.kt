package io.github.itsusinn.mirai.desktop.event.eventloop

import io.github.itsusinn.mirai.desktop.event.*
import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.MessageCodec
import io.vertx.kotlin.coroutines.receiveChannelHandler
import kotlin.reflect.jvm.jvmName

object EventLoop{
   private val vertx = Vertx.vertx()
   val eventBus: EventBus = vertx.eventBus()
   init {
      eventBus.registerLocalCodec()
   }
}

fun EventBus.registerLocalCodec(){
   registerDefaultCodec(StartEvent::class.java, LocalMessageCodec())
   registerDefaultCodec(OpenLoginWindow::class.java, LocalMessageCodec())
   registerDefaultCodec(LoginEvent::class.java, LocalMessageCodec())
   registerDefaultCodec(OnWindowCreated::class.java, LocalMessageCodec())
   registerDefaultCodec(CloseWindow::class.java, LocalMessageCodec())
   registerDefaultCodec(ExitApp::class.java, LocalMessageCodec())
}

inline fun <reified T> LocalMessageCodec(): MessageCodec<T, T> where T: Event
        = object : MessageCodec<T, T> {
   val name = "${T::class.jvmName}Codec"
   override fun transform(s: T): T = s
   override fun name(): String = name
   override fun systemCodecID(): Byte = -1
   override fun encodeToWire(buffer: Buffer, s: T) {
      throw NotImplementedError("No need to impl")
   }
   override fun decodeFromWire(pos: Int, buffer: Buffer): T {
      throw NotImplementedError("No need to impl")
   }
}

