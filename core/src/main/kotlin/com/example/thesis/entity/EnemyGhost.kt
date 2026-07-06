package com.example.thesis.entity

import com.example.thesis.world.GameWorld

class EnemyGhost(
    x: Int,
    y: Int
) : Enemy(x, y) {

    private var cooldown = 0.5f

    override fun update(delta: Float, world: GameWorld) {

        cooldown -= delta
        if (cooldown > 0f) return

        val options = world.getWalkableNeighbors(x, y)

        if (options.isNotEmpty()) {
            val (nx, ny) = options.random()
            x = nx
            y = ny
        }

        cooldown = 0.5f
    }
}
