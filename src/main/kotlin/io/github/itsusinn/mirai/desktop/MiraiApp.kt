package io.github.itsusinn.mirai.desktop

import io.github.itsusinn.mirai.desktop.event.LoginEvent
import io.github.itsusinn.mirai.desktop.event.eventloop.consumer
import io.github.itsusinn.mirai.desktop.event.eventloop.suspendingConsumer
import io.github.itsusinn.mirai.desktop.view.MainWindow
import io.vertx.kotlin.coroutines.awaitBlocking
import kotlinx.coroutines.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.SilentLogger
import kotlin.coroutines.CoroutineContext

object MiraiApp:CoroutineScope{
   private val BotList = ArrayList<Bot>()
   private val botInstance by lazy { BotList.firstOrNull() }

   init {
      suspendingConsumer<LoginEvent> {
         val event = it.body()
         try {
            doLoginBot(event.account,event.password)
         }catch (e:Exception){
            e.printStackTrace()
            return@suspendingConsumer
         }
         MainWindow(botInstance)
      }
   }
   private suspend fun doLoginBot(account:String, password:String)  {
      val newBot = Bot(account.toLong(),password){
         fileBasedDeviceInfo()
         networkLoggerSupplier = { SilentLogger }
      }
      newBot.login()
      BotList + newBot
   }

   override val coroutineContext: CoroutineContext
      get() = GlobalScope.coroutineContext
}

