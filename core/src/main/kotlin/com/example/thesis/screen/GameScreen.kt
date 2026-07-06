package com.example.thesis.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.ScreenUtils
import com.example.thesis.entity.Player
import com.example.thesis.generator.RandomGenerator
import com.example.thesis.render.MapRenderer
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.example.thesis.entity.EnemyGhost
import com.example.thesis.input.PlayerInput
import com.example.thesis.world.GameWorld
import com.example.thesis.world.TileType

class GameScreen : Screen {

    //camera variables
    private val camera = OrthographicCamera()
    private val generator = RandomGenerator()
    private val renderer = MapRenderer()

    //player variables
    private lateinit var player: Player
    private lateinit var world: GameWorld
    private val input = PlayerInput()
    private var moveTimer = 0f
    private val moveDelay = 0.15f // seconds between moves

    //player sprite
    private val batch = SpriteBatch()
    private val playerTexture = Texture("thesis/Tiles/tile_0087.png")

    //enemy ghost sprite
    private val enemies = mutableListOf<EnemyGhost>()
    private val enemyTexture = Texture("thesis/Tiles/tile_0108.png")

    private val viewport: Viewport = FitViewport(50f * 16f, 50f * 16f, camera)

    override fun show() {

        val data = generator.generate(50, 50)

        world = GameWorld(data)

        renderer.build(data)

        val (px, py) = world.findSpawn()
        player = Player(px, py)

        world.spawnEnemies(5)
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f)

        handleInput(delta)

        viewport.apply()
        camera.update()

        renderer.render(camera)

        batch.projectionMatrix = camera.combined
        batch.begin()

        // player
        batch.draw(
            playerTexture,
            player.x * 16f,
            player.y * 16f
        )

        for (enemy in world.enemies) {
            batch.draw(enemyTexture, enemy.x * 16f, enemy.y * 16f)
        }

        batch.end()
    }

    private fun handleInput(delta: Float) {

        moveTimer -= delta

        if (moveTimer > 0f) return

        val (dx, dy) = input.movement()

        if (dx == 0 && dy == 0) return

        val newX = player.x + dx
        val newY = player.y + dy

        if (world.canWalk(newX, newY)) {
            player.move(dx, dy)
        }

        moveTimer = moveDelay
    }

    override fun dispose() {
        renderer.dispose()

        batch.dispose()
        playerTexture.dispose()
        enemyTexture.dispose()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }


    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
}
