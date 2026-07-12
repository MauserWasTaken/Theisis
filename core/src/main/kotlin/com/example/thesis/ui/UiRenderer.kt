package com.example.thesis.ui

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.example.thesis.assets.Assets
import com.example.thesis.world.GameWorld

class UiRenderer(
    private val batch: SpriteBatch
) {

    private val camera = OrthographicCamera()

    private val viewport: Viewport = FitViewport(
        800f,
        600f,
        camera
    )

    private val barHeight = 64f

    private val tileSize = 16f

    fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }


    fun render(world: GameWorld) {

        viewport.apply()

        batch.projectionMatrix = camera.combined

        batch.begin()


        // UI bar
        batch.draw(
            Assets.uiBackground,
            0f,
            600f - barHeight,
            800f,
            barHeight
        )


        // HP icons
        for(i in 0 until world.player.hp) {

            batch.draw(
                Assets.playerHp,
                20f + i * 20f,
                600f - 48f,
                16f,
                16f
            )
        }

        // weapon slot background

        batch.draw(
            Assets.weaponSlot,
            400f - tileSize,
            600f - 48f,
            tileSize,
            tileSize
        )


        // equipped weapon

        batch.draw(
            Assets.sword,
            400f - tileSize,
            600f - 48f,
            tileSize,
            tileSize
        )


        batch.end()
    }
}
