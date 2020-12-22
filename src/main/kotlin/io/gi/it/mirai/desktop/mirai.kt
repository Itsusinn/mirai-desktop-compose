package io.gi.it.mirai.desktop

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.utils.SilentLogger
import kotlin.coroutines.CoroutineContext

object MiraiApp:CoroutineScope{
   private val BotList = ArrayList<Bot>()
   val bot by lazy { BotList[0] }

   fun loginBot(account:String, password:String) = launch {
      val newBot = Bot(account.toLong(),password){
         // 覆盖默认的配置
         fileBasedDeviceInfo("device.json") // 使用 "device.json" 保存设备信息
         networkLoggerSupplier = { SilentLogger } // 禁用网络层输出
      }
      newBot.login()
      BotList + newBot
   }

   override val coroutineContext: CoroutineContext
      get() = GlobalScope.coroutineContext
}

