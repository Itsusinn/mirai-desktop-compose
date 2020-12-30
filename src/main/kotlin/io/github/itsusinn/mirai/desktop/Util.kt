package io.github.itsusinn.mirai.desktop

import java.awt.Image
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO

fun imageFormURL(url:String):BufferedImage= ImageIO.read(URL(url))
