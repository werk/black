import utility.Mouse
import dk.mzw.scalasprites.SpriteCanvas

class Universe(sprites : Sprites) {

    def update(boundingBox : SpriteCanvas.BoundingBox, mouse : Mouse, delta : Double) = {

    }

    def draw(display : SpriteCanvas.Display) = {
        display.add(sprites.roundFlame, 0, 0, blending = SpriteCanvas.Blending.additive)
    }

}
