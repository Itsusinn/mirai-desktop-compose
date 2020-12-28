package io.github.itsusinn.mirai.desktop.event.eventloop

import io.github.itsusinn.mirai.desktop.event.Event
import io.github.itsusinn.mirai.desktop.event.eventloop.EventLoop.eventBus
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message
import io.vertx.core.eventbus.MessageConsumer
import io.vertx.core.eventbus.MessageProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object DefaultCoroutineScope:CoroutineScope{
   override val coroutineContext: CoroutineContext
      get() = GlobalScope.coroutineContext
}

inline fun <reified T> publish(
   message:T,
   address:String = "default"
): EventBus where T: Event {
   return eventBus.publish(address, message)
}

inline fun <reified T> send(
   message:T,
   address:String = "default"
): EventBus where T: Event {
   return eventBus.send(address, message)
}

inline fun <reified T> publisher(
   address: String = "default")
: MessageProducer<T> where T: Event {
   return eventBus.publisher<T>(address)
}

//A constant consumer with handler
inline fun <reified T> consumer(
   address: String = "default",
   noinline handler: (Message<T>) -> Unit
): MessageConsumer<T> where T: Event {
   return eventBus.consumer<T>(address){
      if (it.body() is T) handler(it)
   }
}

//A constant consumer with handler
inline fun <reified T> suspendingConsumer(
   address: String = "default",
   noinline handler: suspend (Message<T>) -> Unit
): MessageConsumer<T> where T: Event {
   return eventBus.consumer<T>(address){
      DefaultCoroutineScope.launch {
         if (it.body() is T) handler(it)
      }
   }
}


//A constant consumer
inline fun <reified T> consumer(
   address: String = "default",
): MessageConsumer<T> where T: Event {
   return eventBus.consumer<T>(address)
}
//A auto-unregistered consumer with handler
inline fun <reified T> consume(
   address: String = "default",
   noinline handler: (Message<T>) -> Unit
): MessageConsumer<T> where T: Event {
   val consumer = consumer<T>()
   return eventBus.consumer<T>(address){
      if (it.body() is T) {
         handler(it)
         consumer.unregister()
      }
   }
}