import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport.scalaJSUseMainModuleInitializer

enablePlugins(ScalaJSPlugin)

name := "mawr"

scalaVersion := "2.13.4"

scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0"

libraryDependencies += "dk.mzw.scalashading" %%% "scala-shading" % "0.1.0"

libraryDependencies += "dk.mzw.scalasprites" %%% "scala-sprites" % "0.1.0"

scalaJSUseMainModuleInitializer := true

mainClass in Compile := Some("Mawr")
