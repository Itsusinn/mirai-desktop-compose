package io.gi.it.mirai.desktop.view

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun LoginWindow() = Window(
//  title = "登录",
   size = IntSize(800,600),
   undecorated = true,
) {
   var account by remember { mutableStateOf("") }
   var password by remember { mutableStateOf("") }
   MaterialTheme {
      Box(
         modifier = Modifier.fillMaxSize()
//            .background(color = Color(151,136,166,88))
            .padding(10.dp)
      ){
         Column(
            Modifier.fillMaxSize(),
            Arrangement.spacedBy(5.dp),
         ) {
            Image(
               bitmap = imageResource("44892049_p0.jpg"), // ImageBitmap
               modifier = Modifier
                  .size(400.dp,400.dp)
                  .align(Alignment.CenterHorizontally)
            )
            TextField(
               value = account,
               onValueChange = { account = it },
               label = { Text("Your Account") },
               singleLine = true,
               modifier = Modifier
                  .align(Alignment.CenterHorizontally)
            )
            TextField(
               value = password,
               onValueChange = { password = it },
               label = { Text("Your Password") },
               singleLine = true,
               modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Button(
               onClick = {
                  AppManager.focusedWindow?.close()
               },
               modifier = Modifier
                  .align(Alignment.CenterHorizontally)
                  .size(200.dp,60.dp)
            ) {
               Text("Login")
            }
         }
      }

   }
}