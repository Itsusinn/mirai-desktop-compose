package io.gi.it.mirai.desktop

import io.gi.it.mirai.desktop.event.LoginEvent
import io.gi.it.mirai.desktop.event.eventloop.consumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.SilentLogger
import kotlin.coroutines.CoroutineContext

object MiraiApp:CoroutineScope{
   private val BotList = ArrayList<Bot>()
   private val botInstance by lazy { BotList.firstOrNull() }

   init {
      consumer<LoginEvent> {

      }
   }

   private fun doLoginBot(account:String, password:String) = launch {
      val newBot = Bot(account.toLong(),password){
         fileBasedDeviceInfo("device.json")
         networkLoggerSupplier = { SilentLogger }
      }
      newBot.login()
      BotList + newBot
   }

   override val coroutineContext: CoroutineContext
      get() = GlobalScope.coroutineContext
}

