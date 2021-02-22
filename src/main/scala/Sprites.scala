import dk.mzw.scalashading.Language.{Animation, Image, R, Vec2, Vec3, rgba}
import dk.mzw.scalashading.Math
import dk.mzw.scalashading.util.Prelude
import dk.mzw.scalashading.util.Prelude.gaussianOne
import dk.mzw.scalasprites.SpriteCanvas.{Blending, Loader}

class Sprites(loader : Loader) {

    val flame : R => Image = {variance : R => x : R => y : R =>
        for {
            d <- Vec2(x, y).magnitude
            intensity <- gaussianOne(variance, d)
            i <- intensity
        } yield rgba(i, i * 0.9, i * 0.4, 1)
    }

    val background : R => Image = {variance : R => x : R => y : R =>
        for {
            d <- Vec2(x, y).magnitude
            intensity <- gaussianOne(variance, d)
            i <- intensity
        } yield rgba(i, i * 0.9, i * 0.4, 1)
    }

    val flameBrightImage = loader("assets/flame-bright.png") //loader(flame(0.3))
    val flameRedImage = loader("assets/flame-red.png")
    val roundFlame = loader(flame(0.2))

}

object Sprites {
    import org.scalajs.dom.raw.{WebGLRenderingContext => GL}
    val deathBlending = Blending(GL.FUNC_ADD, GL.CONSTANT_COLOR, GL.ONE_MINUS_SRC_ALPHA, Some((0.4, 0.3, 0.3, 1)))
}