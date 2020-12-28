package io.github.itsusinn.mirai.desktop

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.AppWindow
import io.github.itsusinn.mirai.desktop.event.*
import io.github.itsusinn.mirai.desktop.event.eventloop.consumer
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import io.github.itsusinn.mirai.desktop.view.LoginWindow
import kotlinx.coroutines.runBlocking

object App{
   private val windows = HashMap<String,AppWindow>()
   private val miraiApp = MiraiApp
   init {
      consumer<OnWindowCreated>("window"){

         val event = it.body()
         try {
            addWindow(event.name,event.window)
         }catch (e:Exception){
            e.printStackTrace()
         }
      }
      consumer<CloseWindow>("window"){
         val event = it.body()
         closeWindow(event.name)
      }
      consumer<ExitApp>("window"){
         AppManager.exit()
      }
   }

   private fun closeWindow(name:String){
      var exist = false
      for (window in windows) {
         if (window.key == name) {
            window.value.close()
            exist = true
            break
         }
      }
      if (exist) windows.remove(name)
   }

   private fun addWindow(name: String,window: AppWindow){
      if (windows.containsKey(name)) {
         window.close()
         throw IllegalArgumentException("Duplicated name")
      }
      windows[name] = window
   }

   @JvmStatic fun main(args: Array<String>){
      consumer<OpenLoginWindow> {
         LoginWindow()
      }
      publish(StartEvent)
      publish(OpenLoginWindow)
   }
}

