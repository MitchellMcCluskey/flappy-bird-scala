package com.mitchellmccluskey.android.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Animation(texture: Texture, frameCount: Int, cycleTime: Float) {

    private val region: TextureRegion = new TextureRegion(texture)
    private val frameWidth = region.getRegionWidth / frameCount
    private val frames: Seq[TextureRegion] = {
        Seq.range(0, frameCount)
            .map(counter => new TextureRegion(region, counter * frameWidth, 0, frameWidth, region.getRegionHeight))
    }
    private val maxFrameTime: Float = cycleTime / frameCount
    private var currentFrameTime: Float = 0
    private var frame: Int = 0

    def update(deltaTime: Float): Unit = {
        currentFrameTime += deltaTime
        if (currentFrameTime > maxFrameTime) {
            frame = frame + 1
            currentFrameTime = 0
        }
        if (frame >= frameCount) {
            frame = 0
        }
    }

    def getFrame: TextureRegion = frames(frame)

}
