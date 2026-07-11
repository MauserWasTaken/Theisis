package com.example.thesis.entity

import com.example.thesis.world.GameWorld
import kotlin.math.abs

class EnemyGhost(
    x: Int,
    y: Int
) : Enemy(x, y) {


    override fun update(delta: Float, world: GameWorld) {

        updateStun(delta)

        if (isStunned()) {
            return
        }

        cooldown -= delta

        if (cooldown > 0) return


        if (distanceToPlayer(world) <= 10) {
            state = EnemyState.CHASE
        }
        else {
            state = EnemyState.PATROL
        }


        when(state) {

            EnemyState.PATROL -> patrol(world)

            EnemyState.CHASE -> chase(world)

        }


        cooldown = 0.5f
    }


    private fun patrol(world: GameWorld) {

        var options = world.getWalkableNeighbors(x, y)

        // Prefer tiles that are not the previous position
        val betterOptions = options.filter {
            it.first != previousX ||
                it.second != previousY
        }

        if (betterOptions.isNotEmpty()) {
            options = betterOptions
        }


        if (options.isNotEmpty()) {

            previousX = x
            previousY = y

            val (nx, ny) = options.random()

            x = nx
            y = ny
        }
    }


    private fun chase(world: GameWorld) {

        val player = world.player


        val dx = when {
            player.x > x -> 1
            player.x < x -> -1
            else -> 0
        }


        val dy = when {
            player.y > y -> 1
            player.y < y -> -1
            else -> 0
        }


        val newX = x + dx
        val newY = y + dy


        if(world.canWalk(newX,newY)) {

            previousX = x
            previousY = y

            x = newX
            y = newY
        }
    }


    private fun distanceToPlayer(world: GameWorld): Int {

        return abs(world.player.x - x) +
            abs(world.player.y - y)

    }
}
