package io.github.itsusinn.mirai.desktop.view

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import io.github.itsusinn.mirai.desktop.event.CloseWindow
import io.github.itsusinn.mirai.desktop.event.closeWindow
import io.github.itsusinn.mirai.desktop.event.eventloop.publish
import io.github.itsusinn.mirai.desktop.imageFormURL
import net.mamoe.mirai.Bot
import java.awt.image.BufferedImage
import kotlin.math.abs
import kotlin.math.roundToInt

fun MainWindow(bot:Bot) = MiraiWindow(
   name = "MainWindow",
   title = "MainWindow",
   size = IntSize(1600,1200),
   undecorated = true,
   //icon = imageFormURL(bot.selfQQ.avatarUrl)
) {
   var text by remember { mutableStateOf("Hello, ${bot.nick}") }
   val scaffoldState = rememberScaffoldState()
   // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
   val sharpEdgePercent = -50f
   val roundEdgePercent = 45f
// Start with sharp edges
   val animatedProgress = animatedFloat(sharpEdgePercent)
// animation value to animate shape
   val progress = animatedProgress.value.roundToInt()

// When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
// This allows for a smooth transition between cut corners and round corners.
   val fabShape = if (progress < 0) {
      CutCornerShape(abs(progress))
   } else if (progress == roundEdgePercent.toInt()) {
      CircleShape
   } else {
      RoundedCornerShape(progress)
   }
// lambda to call to trigger shape animation
   val changeShape = {
      val target = animatedProgress.targetValue
      val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
      animatedProgress.animateTo(
         targetValue = nextTarget,
         anim = TweenSpec(durationMillis = 600)
      )
   }

   Scaffold(
      scaffoldState = scaffoldState,
      drawerShape = MaterialTheme.shapes.small,
      drawerElevation = 40.dp,
      drawerContent = {
         Column {
            Text("1")
            Text("2")
         }
      },
      topBar = {
         TopAppBar(
            title = { Text(bot.nick) },
            navigationIcon = {
               IconButton(
                  onClick = {
                     scaffoldState.drawerState.open()
                  }
               ) {
                  Icon(Icons.Filled.Menu)
               }
            }
         )
      },
      bottomBar = {
      },
      floatingActionButtonPosition = FabPosition.End,
      floatingActionButton = {
         ExtendedFloatingActionButton(
            text = { Text("Inc") },
            onClick = { /* fab click handler */ }
         )
      },
      bodyContent = { innerPadding ->
         Column(
            modifier = Modifier.fillMaxSize().padding(15.dp)
         ) {
            Button(
               onClick = { text = "Hello, Desktop!" },
               modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { Text(text) }
            Button(
               onClick = { closeWindow("MainWindow") },
               modifier = Modifier.align(Alignment.CenterHorizontally)
            ){ Text("Exit") }
         }
      }
   )

}