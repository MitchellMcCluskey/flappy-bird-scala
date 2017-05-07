package com.mitchellmccluskey.android.game.states

import java.util.{Stack => JStack}

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GameStateManager {

    private val states: JStack[State] = new JStack[State]()

    def push(state: State): State = states.push(state)

    def pop(): Unit = states.pop.dispose()

    def set(state: State): Unit = {
        states.pop.dispose()
        states.push(state)
    }

    def update(deltaTime: Float): Unit = states.peek().update(deltaTime)

    def render(spriteBatch: SpriteBatch): Unit = states.peek().render(spriteBatch)
}
