package com.mitchellmccluskey.android.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mitchellmccluskey.android.game.ScalaGame

class MenuState(val gameStateManager: GameStateManager) extends State {

    private val background: Texture = new Texture("background.png")
    private val playButton: Texture = new Texture("play-button.png")

    override def handleInput(): Unit = {
        if (Gdx.input.justTouched) {
            gameStateManager.set(new PlayState(gameStateManager))
        }
    }

    override def update(deltaTime: Float): Unit = handleInput()

    override def render(spriteBatch: SpriteBatch): Unit = {
        spriteBatch.begin()
        spriteBatch.draw(background, 0, 0, ScalaGame.Width, ScalaGame.Height)
        spriteBatch.draw(playButton, (ScalaGame.Width / 2) - (playButton.getWidth / 2), ScalaGame.Height / 2)
        spriteBatch.end()
    }

    override def dispose(): Unit = {
        background.dispose()
        playButton.dispose()
    }
}
