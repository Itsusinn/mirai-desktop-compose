package io.github.itsusinn.mirai.desktop.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import io.github.itsusinn.mirai.desktop.event.ExitApp
import io.github.itsusinn.mirai.desktop.event.LoginEvent
import io.github.itsusinn.mirai.desktop.event.closeWindow
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import io.github.itsusinn.mirai.desktop.event.exitApp

private const val Name = "LoginWindow"

fun LoginWindow() = MiraiWindow(
   name = Name,
   title = "登录",
   size = IntSize(900,700),
   undecorated = true,
) {
   var account by remember { mutableStateOf("1802657638") }
   var password by remember { mutableStateOf("") }
   Box(
      modifier = Modifier.fillMaxSize()
         .background(color = Color(151,136,166,88))
         .padding(10.dp)
   ){
      Column(
         Modifier.fillMaxSize(),
         Arrangement.spacedBy(10.dp),
      ) {
         Image(
            bitmap = imageResource("login2.png"),
            modifier = Modifier.align(Alignment.CenterHorizontally)
               .size(400.dp,400.dp)
         )
         TextField(
            value = account,
            onValueChange = { account = it },
            label = { Text("Your Account") },
            singleLine = true,
            modifier = Modifier.align(Alignment.CenterHorizontally)
         )
         TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Your Password") },
            singleLine = true,
            modifier = Modifier.align(Alignment.CenterHorizontally)
         )
         Spacer(modifier = Modifier.size(15.dp))
         Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
         ) {
            Button(
               modifier = Modifier.size(90.dp,60.dp),
               onClick = { publish(LoginEvent(account,password)) },
            ) { Text("Login") }
            Spacer(modifier = Modifier.size(60.dp))
            Button(
               modifier = Modifier.size(90.dp,60.dp),
               onClick = { closeWindow(Name) },
            ){ Text("Cancel") }
            Spacer(modifier = Modifier.size(60.dp))
            Button(
               modifier = Modifier.size(90.dp,60.dp),
               onClick = { exitApp() },
            ){ Text("Close") }

         }
      }
   }
}
