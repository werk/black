import dk.mzw.scalashading.Language.{Animation, Image, R, Vec2, Vec3, rgba}
import dk.mzw.scalashading.Math
import dk.mzw.scalashading.util.Prelude
import dk.mzw.scalashading.util.Prelude.gaussianOne
import dk.mzw.scalasprites.SpriteCanvas.{Blending, Loader}

class Sprites(loader : Loader) {

    object Graphics {
        val flame : R => Image = {variance : R => x : R => y : R =>
            for {
                d <- Vec2(x, y).magnitude
                r <- gaussianOne(variance, d)
                g <- gaussianOne(variance * 0.7, d)
                b <- gaussianOne(variance * 0.6, d)
            } yield rgba(r, g, b, 1)
        }

        val grayBackground : Image = {x : R => y : R =>
            for {
                v <- Prelude.simplexNoise(Vec3(x * 5, y * 5, 40))
                i <- v * v * v * v * v * v * v * v * v * v * v
            } yield rgba(i*0.5, i * 0.5, i, 1)
        }

        val background : Image = {x : R => y : R =>
            for {
                h <- Prelude.simplexNoise(Vec3(x * 1, y * 1, 1)) * 0.1 + 0.9
                s <- Prelude.simplexNoise(Vec3(x * 10, y * 10, 20)) * 0.2 + 0.8
                v <- Prelude.simplexNoise(Vec3(x * 2, y * 2, 30)) * 0.5
            } yield Prelude.hsva(h, s, v, 1)
        }
    }

    val flameBrightImage = loader("assets/flame-bright.png") //loader(flame(0.3))
    val flameRedImage = loader("assets/flame-red.png")
    val roundFlame = loader(Graphics.flame(0.2))
    val background = loader(Graphics.background)
    val grayBackground = loader(Graphics.grayBackground)

}

object Sprites {
    import org.scalajs.dom.raw.{WebGLRenderingContext => GL}
    val deathBlending = Blending(GL.FUNC_ADD, GL.CONSTANT_COLOR, GL.ONE_MINUS_SRC_ALPHA, Some((0.4, 0.3, 0.3, 1)))
}