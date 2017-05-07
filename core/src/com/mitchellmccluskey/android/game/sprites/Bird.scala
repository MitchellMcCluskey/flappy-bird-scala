package com.mitchellmccluskey.android.game.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Rectangle, Vector3}

class Bird(initialXPosition: Int, initialYPosition: Int) {

    import Bird._

    private val texture: Texture = new Texture("bird-animation.png")
    private val animation: Animation = new Animation(texture, 3, 0.5f)
    val position: Vector3 = new Vector3(initialXPosition, initialYPosition, 0)
    val bounds: Rectangle = new Rectangle(initialXPosition, initialYPosition, texture.getWidth / 3, texture.getHeight)
    private val velocity: Vector3 = new Vector3(0, 0, 0)
    private val flap: Sound = Gdx.audio.newSound(Gdx.files.internal("flap.ogg"))

    def update(deltaTime: Float): Unit = {
        animation.update(deltaTime)
        if (position.y > 0) {
            velocity.add(0, Gravity, 0)
        }
        velocity.scl(deltaTime)
        position.add(Movement * deltaTime, velocity.y, 0)
        velocity.scl(1 / deltaTime)
        if (position.y < 0) {
            position.y = 0
        }
        bounds.setPosition(position.x, position.y)
    }

    def getTexture: TextureRegion = animation.getFrame

    def jump(): Unit = {
        velocity.y = 250
        flap.play(0.5f)
    }

    def dispose(): Unit = {
        texture.dispose()
        flap.dispose()
    }
}

object Bird {
    private val Gravity: Int = -15
    private val Movement: Int = 100
}
