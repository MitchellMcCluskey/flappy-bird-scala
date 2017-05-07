package com.mitchellmccluskey.android.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.{Rectangle, Vector2}

import scala.util.Random

class Tube(xPosition: Float) {

    import Tube._

    val topTube: Texture = new Texture("top-tube.png")
    val bottomTube: Texture = new Texture("bottom-tube.png")
    val topTubePosition: Vector2 = new Vector2(xPosition, getRandomPosition)
    val bottomTubePosition: Vector2 = new Vector2(xPosition, topTubePosition.y - TubeGap - bottomTube.getHeight)
    private val boundsTop: Rectangle = new Rectangle(topTubePosition.x, topTubePosition.y, topTube.getWidth, topTube.getHeight)
    private val boundsBottom: Rectangle = new Rectangle(bottomTubePosition.x, bottomTubePosition.y, bottomTube.getWidth, bottomTube.getHeight)

    private def getRandomPosition: Int = new Random().nextInt(Fluctuation) + TubeGap + LowestOpening

    def reposition(xPosition: Float): Unit = {
        topTubePosition.set(xPosition, getRandomPosition)
        bottomTubePosition.set(xPosition, topTubePosition.y - TubeGap - bottomTube.getHeight)
        boundsTop.setPosition(topTubePosition.x, topTubePosition.y)
        boundsBottom.setPosition(bottomTubePosition.x, bottomTubePosition.y)
    }

    def collides(player: Rectangle): Boolean = player.overlaps(boundsTop) || player.overlaps(boundsBottom)

    def dispose(): Unit = {
        topTube.dispose()
        bottomTube.dispose()
    }
}

object Tube {
    private val Fluctuation: Int = 130
    private val TubeGap: Int = 100
    private val LowestOpening: Int = 120
    val TubeWidth: Int = 52
}
