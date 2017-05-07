package com.mitchellmccluskey.android.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mitchellmccluskey.android.game.states.{GameStateManager, MenuState}

class ScalaGame extends ApplicationAdapter {

    private[game] var gameStateManager: GameStateManager = _
    private[game] var spriteBatch: SpriteBatch = _
    private[game] var music: Music = _

    override def create(): Unit = {
        spriteBatch = new SpriteBatch
        gameStateManager = new GameStateManager
        Gdx.gl.glClearColor(1, 0, 0, 1)
        gameStateManager.push(new MenuState(gameStateManager))
        playMusic()
    }

    override def render(): Unit = {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager.update(Gdx.graphics.getDeltaTime)
        gameStateManager.render(spriteBatch)
    }

    private def playMusic(): Unit = {
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"))
        music.setLooping(true)
        music.setVolume(0.1f)
        music.play()
    }

    override def dispose(): Unit = {
        super.dispose()
        music.dispose()
    }
}

object ScalaGame {
    val Width: Int = 480
    val Height: Int = 800
    val Scale: Float = 0.5f
    val Title: String = "Scala Game"
}
