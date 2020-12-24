
package io.gi.it.mirai.desktop.view

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import net.mamoe.mirai.Bot

fun MainWindow(bot:Bot) = MiraiWindow(
   size = IntSize(1600,1200),
   undecorated = true,
) {
   var text by remember { mutableStateOf("Hello, World!") }

   Column(
      modifier = Modifier.fillMaxSize().padding(5.dp)
   ) {

      Button(
         onClick = { text = "Hello, Desktop!" },
         modifier = Modifier.align(Alignment.CenterHorizontally)
      ) { Text(text) }

      Button(
         onClick = {
            AppManager.focusedWindow?.close()
         },
         modifier = Modifier
            .align(Alignment.CenterHorizontally)
      ){ Text("Exit") }
   }
}