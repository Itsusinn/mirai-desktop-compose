
package io.github.itsusinn.mirai.desktop.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import io.github.itsusinn.mirai.desktop.event.CloseWindow
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import net.mamoe.mirai.Bot

private const val Name = "MainWindow"

fun MainWindow(bot:Bot? = null) = MiraiWindow(
   name = Name,
   title = "MainWindow",
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
         onClick = { publish(CloseWindow(Name),"window") },
         modifier = Modifier
            .align(Alignment.CenterHorizontally)
      ){ Text("Exit") }
   }
}