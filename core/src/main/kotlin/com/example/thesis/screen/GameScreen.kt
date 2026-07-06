package com.example.thesis.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.example.thesis.generator.RandomGenerator
import com.example.thesis.input.PlayerInput
import com.example.thesis.render.MapRenderer
import com.example.thesis.world.GameWorld
import com.example.thesis.assets.Assets

class GameScreen : Screen {

    // CAMERA
    private val camera = OrthographicCamera()
    private val viewport: Viewport = FitViewport(50f * 16f, 50f * 16f, camera)

    // WORLD
    private lateinit var world: GameWorld
    private val generator = RandomGenerator()

    // INPUT
    private val input = PlayerInput()
    private var moveTimer = 0f
    private val moveDelay = 0.15f

    // RENDERING
    private val renderer = MapRenderer()
    private val batch = SpriteBatch()

    override fun show() {

        val data = generator.generate(50, 50)

        world = GameWorld(data)

        renderer.build(data)

        world.spawnEnemies(5)
    }

    override fun render(delta: Float) {

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f)

        handleInput(delta)

        viewport.apply()
        camera.update()

        // MAP
        renderer.render(camera)

        // ENTITIES
        batch.projectionMatrix = camera.combined
        batch.begin()

        batch.draw(
            Assets.player,
            world.player.x * 16f,
            world.player.y * 16f
        )

        for (enemy in world.enemies) {
            batch.draw(
                Assets.enemy,
                enemy.x * 16f,
                enemy.y * 16f
            )
        }

        batch.end()
    }

    private fun handleInput(delta: Float) {

        moveTimer -= delta
        if (moveTimer > 0f) return

        val (dx, dy) = input.movement()
        if (dx == 0 && dy == 0) return

        world.updatePlayer(dx, dy)

        moveTimer = moveDelay
    }

    override fun dispose() {

        renderer.dispose()
        batch.dispose()

        Assets.dispose()

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
}
