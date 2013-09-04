package com.benkolera

import java.util.Map.Entry
import java.util.jar.JarFile
import scala.collection.JavaConversions._

object ManifestInfo {

  def forClass[A]( clazz:Class[A] ):Map[String,String] = {
    val out =
      for {
        url  <- Option(clazz.getProtectionDomain.getCodeSource.getLocation)
        file <- Option(url.getFile) if file.endsWith(".jar")
      } yield {
        val attrs = new JarFile(file).getManifest.getMainAttributes.entrySet.toSet
        attrs.foldLeft( Map[String,String]() )(
          (map,entry) => map + (entry.getKey.toString -> entry.getValue.toString)
        )
      }
    out.getOrElse( Map() )
  }

}
