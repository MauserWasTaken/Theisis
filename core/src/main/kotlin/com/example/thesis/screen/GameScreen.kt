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
import com.example.thesis.system.CombatSystem
import com.badlogic.gdx.Gdx
import com.example.thesis.ui.UiRenderer
import com.example.thesis.world.LevelManager

class GameScreen : Screen {

    // CAMERA
    private val camera = OrthographicCamera()
    private val uiHeight = 64f

    private val worldWidth = 50f * 16f
    private val worldHeight = 50f * 16f

    private val viewport: Viewport = FitViewport(
        worldWidth,
        worldHeight,
        camera
    )

    // WORLD
    private lateinit var world: GameWorld
    private val combatSystem = CombatSystem()
    private val tileSize = 16f
    private val levelManager = LevelManager()
    private var doorCooldown = 0f

    // INPUT
    private val input = PlayerInput()
    private var moveTimer = 0f
    private val moveDelay = 0.15f

    // RENDERING
    private val renderer = MapRenderer()
    private val batch = SpriteBatch()
    private val uiRenderer = UiRenderer(batch)

    override fun show() {

        camera.setToOrtho(false)


        val level = levelManager.current()


        world = GameWorld(level)

        renderer.build(level.map)
    }

    override fun render(delta: Float) {

        ScreenUtils.clear(0.1f, 0.1f, 0.1f, 1f)

        handleInput(delta)

        viewport.apply()
        camera.update()

        world.player.update(delta)

        doorCooldown -= delta


        val door = world.getPlayerDoor()

        if(door != null && doorCooldown <= 0f) {

            world =
                levelManager.changeLevel(
                    world,
                    door
                )

            renderer.build(world.map)

            doorCooldown = 0.5f

            return
        }

        world.updateEnemies(delta)

        combatSystem.update(world)

        if(world.player.isDead()) {
            Gdx.app.exit()
            return
        }

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

        if(world.player.isAttacking()) {

            val(posX,posY)=world.player.attackPosition()


            batch.draw(
                Assets.swordRegion,
                posX * 16f,
                posY * 16f,
                8f,
                8f,
                16f,
                16f,
                1f,
                1f,
                world.player.attackRotation()
            )
        }

        for (enemy in world.enemies) {
            batch.draw(
                Assets.enemy,
                enemy.x * 16f,
                enemy.y * 16f
            )
        }

        batch.end()

        // UI CAMERA
        uiRenderer.render(world)
    }

    private fun handleInput(delta: Float) {


        moveTimer -= delta


        if(moveTimer <= 0) {

            val(dx,dy)=input.movement()

            if(dx !=0 || dy !=0){

                world.player.setDirection(dx,dy)

                world.updatePlayer(dx,dy)

                moveTimer = moveDelay
            }
        }


        val attackDirection = input.attackDirection()

        if(attackDirection != null) {

            world.player.attack(
                attackDirection.first,
                attackDirection.second
            )
        }
    }

    override fun dispose() {

        renderer.dispose()
        batch.dispose()

        Assets.dispose()

    }


    override fun resize(width: Int, height: Int) {

        viewport.update(
            width,
            height - uiHeight.toInt(),
            true
        )

        viewport.screenY = uiHeight.toInt()
        viewport.screenHeight = height - uiHeight.toInt()

        uiRenderer.resize(width, height)
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
}
