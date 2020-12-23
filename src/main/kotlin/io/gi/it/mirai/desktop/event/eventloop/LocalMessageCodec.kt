package io.gi.it.mirai.desktop.event.eventloop

import io.gi.it.mirai.desktop.event.Event
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import kotlin.reflect.jvm.jvmName

inline fun <reified T> LocalMessageCodec(): MessageCodec<T, T> where T: Event = object : MessageCodec<T, T> {
   val name = "${T::class.jvmName}Codec"
   override fun transform(s: T): T = s
   override fun name(): String = name
   override fun systemCodecID(): Byte = -1
   override fun encodeToWire(buffer: Buffer?, s: T) {
      throw NotImplementedError("No need to impl")
   }
   override fun decodeFromWire(pos: Int, buffer: Buffer?): T {
      throw NotImplementedError("No need to impl")
   }
}