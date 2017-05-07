package com.mitchellmccluskey.android.game.states
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.mitchellmccluskey.android.game.ScalaGame
import com.mitchellmccluskey.android.game.sprites.{Bird, Tube}

class PlayState(val gameStateManager: GameStateManager) extends State {

    import PlayState._

    private val bird: Bird = new Bird(50, 300)
    private val background: Texture = new Texture("background.png")
    private val ground: Texture = new Texture("ground.png")
    private val groundPosition1: Vector2 = new Vector2(camera.position.x - (camera.viewportWidth / 2), GroundYOffset)
    private val groundPosition2: Vector2 = new Vector2(camera.position.x - (camera.viewportWidth / 2) + ground.getWidth, GroundYOffset)
    private val tubes: Seq[Tube] = (1 to TubeCount).map(counter => new Tube(counter * (TubeSpacing + Tube.TubeWidth)))

    camera.setToOrtho(false, ScalaGame.Width / 2, ScalaGame.Height / 2)

    override def handleInput(): Unit = {
        if (Gdx.input.justTouched) {
            bird.jump()
        }
    }

    override def update(deltaTime: Float): Unit = {
        handleInput()
        updateGround()
        bird.update(deltaTime)
        camera.position.x = bird.position.x + 80
        tubes.foreach { tube =>
            if (camera.position.x - (camera.viewportWidth / 2) > tube.topTubePosition.x + tube.topTube.getWidth) {
                tube.reposition(tube.topTubePosition.x + ((Tube.TubeWidth + TubeSpacing) * TubeCount))
            }

            if (tube.collides(bird.bounds)) {
                gameStateManager.set(new PlayState(gameStateManager))
            }
        }
        if (bird.position.y <= ground.getHeight + GroundYOffset) {
            gameStateManager.set(new PlayState(gameStateManager))
        }
        camera.update()
    }

    override def render(spriteBatch: SpriteBatch): Unit = {
        spriteBatch.setProjectionMatrix(camera.combined)
        spriteBatch.begin()
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0)
        spriteBatch.draw(bird.getTexture, bird.position.x, bird.position.y)
        tubes.foreach { tube =>
            spriteBatch.draw(tube.topTube, tube.topTubePosition.x, tube.topTubePosition.y)
            spriteBatch.draw(tube.bottomTube, tube.bottomTubePosition.x, tube.bottomTubePosition.y)
        }
        spriteBatch.draw(ground, groundPosition1.x, groundPosition1.y)
        spriteBatch.draw(ground, groundPosition2.x, groundPosition2.y)
        spriteBatch.end()
    }

    override def dispose(): Unit = {
        background.dispose()
        bird.dispose()
        ground.dispose()
        tubes.foreach(_.dispose())
    }

    private def updateGround(): Unit = {
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition1.x + ground.getWidth) {
            groundPosition1.add(ground.getWidth * 2, 0)
        }
        if (camera.position.x - (camera.viewportWidth / 2) > groundPosition2.x + ground.getWidth) {
            groundPosition2.add(ground.getWidth * 2, 0)
        }
    }
}

object PlayState {
    private val TubeSpacing: Int = 125
    private val TubeCount: Int = 4
    private val GroundYOffset: Int = -50
}
