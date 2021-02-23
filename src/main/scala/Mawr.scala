import utility.{Keys, Mouse}
import dk.mzw.scalasprites.SpriteCanvas.Loader
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLCanvasElement

import scala.concurrent.ExecutionContext.Implicits.global

object Mawr {

    def main(args : Array[String]): Unit = {
        val canvas = dom.document.getElementById("spriteCanvas").asInstanceOf[HTMLCanvasElement]
        val loader = new Loader(canvas)

        val sprites = new Sprites(loader)
        val universe = new Universe(sprites)

        loader.complete.foreach { display =>
            println("loader.complete")
            val mouse = new Mouse(canvas, display.gameCoordinatesX, display.gameCoordinatesY)
            val keys = new Keys()

            // This crazy stuff is done to avoid creating and allocating a new anonymous function for each call to requestAnimationFrame
            var loopF : Double => Unit = null

            var last : Double = secondsElapsed() - 0.01
            def loop(_t : Double) : Unit = {
                val now = secondsElapsed()
                val delta = now - last
                if (delta < 1) {
                    universe.update(display.boundingBox, keys, mouse, delta)
                    universe.draw(display)
                    val clearColor = (0.3, 0.3, 0.3, 1.0)
                    display.draw(clearColor, universe.screenHeight, universe.centerX, universe.centerY)
                }
                last = now
                dom.window.requestAnimationFrame(loopF)
            }
            loopF = loop
            loop(0)
        }
    }

    def secondsElapsed() : Double = {
        scalajs.js.Dynamic.global.performance.now().asInstanceOf[Double] * 0.001
    }

}
