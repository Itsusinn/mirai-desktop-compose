package io.gi.it.mirai.desktop

import io.vertx.core.Vertx
import io.vertx.core.eventbus.EventBus
import io.vertx.core.eventbus.Message

object App{
   private val vertx = Vertx.vertx()
   val eventBus: EventBus = vertx.eventBus()

}
fun publish(address:String = "default", message:Any){
   App.eventBus.publish(address, message)
}
fun <T> publisher(address: String = "default") {
   App.eventBus.publisher<T>(address)
}
fun <T> consumer(address: String = "default",handler: (Message<T>) -> Unit){
   App.eventBus.consumer<T>(address,handler)
}

