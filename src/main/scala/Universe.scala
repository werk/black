import utility.{Keys, Mouse}
import dk.mzw.scalasprites.SpriteCanvas

class Universe(sprites : Sprites) {

    var velocityX : Double = 0
    var velocityY : Double = 0

    val screenHeight : Double = 5
    var centerX : Double = 0
    var centerY : Double = 0

    def update(boundingBox : SpriteCanvas.BoundingBox, keys : Keys,  mouse : Mouse, delta : Double) = {
        velocityX += keys.factor(Keys.leftArrow, Keys.rightArrow) * delta * 0.1
        velocityY += keys.factor(Keys.downArrow, Keys.upArrow) * delta * 0.1
        centerX += velocityX
        centerY += velocityY
    }

    def draw(display : SpriteCanvas.Display) = {

        display.add(
            image = sprites.grayBackground,
            x = centerX,
            y = centerY,
            width = screenHeight * 3,
            height = screenHeight * 3,
            imageX = centerX * 0.1,
            imageY = centerY * 0.1,
        )

        display.add(
            image = sprites.background,
            x = centerX,
            y = centerY,
            width = screenHeight * 3,
            height = screenHeight * 3,
            imageX = centerX,
            imageY = centerY,
            blending = SpriteCanvas.Blending.additive,
        )

        display.add(
            image = sprites.roundFlame,
            x = centerX,
            y = centerY,
            blending = SpriteCanvas.Blending.additive
        )
    }

}
