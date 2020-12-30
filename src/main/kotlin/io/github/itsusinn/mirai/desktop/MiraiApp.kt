package io.github.itsusinn.mirai.desktop

import io.github.itsusinn.mirai.desktop.event.LoginEvent
import io.github.itsusinn.mirai.desktop.event.OnWindowCreated
import io.github.itsusinn.mirai.desktop.event.closeWindow
import io.github.itsusinn.mirai.desktop.event.eventloop.onetimeConsumer
import io.github.itsusinn.mirai.desktop.event.eventloop.suspendConsumer
import io.github.itsusinn.mirai.desktop.view.MainWindow
import kotlinx.coroutines.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.SilentLogger
import kotlin.coroutines.CoroutineContext

object MiraiApp:CoroutineScope{
   private val BotList = ArrayList<Bot>()
   private val botInstance by lazy { BotList.first() }

   init {
      suspendConsumer<LoginEvent> {
         val event = it.body()
         launch {
            try {
               doLoginBot(event.account,event.password)
            }catch (e:Exception){
               e.printStackTrace()
               return@launch
            }
            MainWindow(botInstance)
            onetimeConsumer<OnWindowCreated>("window"){
               if (it.body().name == "MainWindow"){
                  closeWindow("LoginWindow")
               }
            }
         }
      }
   }
   private suspend fun doLoginBot(account:String, password:String)  {
      val newBot = Bot(account.toLong(),password){
         fileBasedDeviceInfo()
         networkLoggerSupplier = { SilentLogger }
      }
      newBot.login()
      BotList.add(newBot)
   }

   override val coroutineContext: CoroutineContext
      get() = GlobalScope.coroutineContext
}

