package com.mitchellmccluskey.android.game.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State {

    protected val gameStateManager: GameStateManager
    protected val camera: OrthographicCamera = new OrthographicCamera
    protected var mouse: Vector3 = _

    def handleInput(): Unit

    def update(deltaTime: Float): Unit

    def render(spriteBatch: SpriteBatch): Unit

    def dispose(): Unit
}
