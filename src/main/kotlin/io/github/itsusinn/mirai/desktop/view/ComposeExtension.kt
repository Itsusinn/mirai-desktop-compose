package io.github.itsusinn.mirai.desktop.view

import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.WindowEvents
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.MenuBar
import io.github.itsusinn.mirai.desktop.event.OnWindowCreated
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import java.awt.image.BufferedImage
import javax.swing.SwingUtilities

fun MiraiWindow(
   name:String,
   title: String = "JetpackDesktopWindow",
   size: IntSize = IntSize(800, 600),
   location: IntOffset = IntOffset.Zero,
   centered: Boolean = true,
   icon: BufferedImage? = null,
   menuBar: MenuBar? = null,
   undecorated: Boolean = false,
   events: WindowEvents = WindowEvents(),
   onDismissRequest: (() -> Unit)? = null,
   content: @Composable () -> Unit = emptyContent()
){
   SwingUtilities.invokeLater {
      AppWindow(
         title = title,
         size = size,
         location = location,
         centered = centered,
         icon = icon,
         menuBar = menuBar,
         undecorated = undecorated,
         events = events,
         onDismissRequest = onDismissRequest
      ).also { window ->
         publish(OnWindowCreated(name,window),"window")
      }.show {
         content()
      }
   }
}