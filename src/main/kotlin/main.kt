import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun main() = Window(
   size = IntSize(1600,1200),
   undecorated = true ,
) {
   var text by remember { mutableStateOf("Hello, World!") }
   MaterialTheme {
      Column {
         Button(
            modifier = Modifier.main(),
            onClick = {
               text = "Hello, Desktop!"
               loginWindow()
            }) {
            Text(text)
         }
         Button(
            onClick = {
               AppManager.exit()
            }
         ){
            Text("Exit")
         }
      }
   }
}

fun Modifier.main():Modifier{
   size(400.dp,80.dp)
   return this
}

fun loginWindow() = Window(title = "登录",size = IntSize(800,600)) {
   var text by remember { mutableStateOf("Hello, World!") }
   var account by remember { mutableStateOf("YourAccount") }
   var password by remember { mutableStateOf("YourPassword") }
   MaterialTheme {
      Column {
         Button(onClick = {
            text = "Hello, Desktop!"
         }) {
            Text(text)
         }
         Text(account)
         Text(password)
      }
   }
}