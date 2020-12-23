package io.gi.it.mirai.desktop.event.eventloop

import io.gi.it.mirai.desktop.event.eventloop.EventLoop.eventBus
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.eventbus.MessageProducer

fun publish(message:Any,address:String = "default"): EventBus {
   return eventBus.publish(address, message)
}

fun send(message:Any,address:String = "default"): EventBus {
   return eventBus.send(address, message)
}

inline fun <reified T> publisher(
   address: String = "default")
: MessageProducer<T> {
   return eventBus.publisher<T>(address)
}

inline fun <reified T> consumer(
   address: String = "default",
   noinline handler: (Message<T>) -> Unit
): MessageConsumer<T> {
   return eventBus.consumer<T>(address){ if (it.body() is T) handler(it) }
}