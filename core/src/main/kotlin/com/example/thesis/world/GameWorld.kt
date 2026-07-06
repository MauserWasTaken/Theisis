package com.example.thesis.world

import com.example.thesis.data.LevelData
import com.example.thesis.entity.EnemyGhost
import com.example.thesis.entity.Player

class GameWorld(level: LevelData) {

    val map: TileMap = level.map

    val player: Player =
        Player(level.playerSpawn.first, level.playerSpawn.second)

    val enemies = mutableListOf<EnemyGhost>()

    private var enemyMoveTimer = 0f
    private val enemyMoveDelay = 0.5f

    init {
        for ((x, y) in level.enemySpawns) {
            enemies.add(EnemyGhost(x, y))
        }
    }

    fun updatePlayer(dx: Int, dy: Int) {

        val newX = player.x + dx
        val newY = player.y + dy

        if (canWalk(newX, newY)) {
            player.x = newX
            player.y = newY
        }
    }

    fun updateEnemies(delta: Float) {

        for (enemy in enemies) {
            enemy.update(delta, this)
        }
    }

    fun canWalk(x: Int, y: Int): Boolean {
        if (x !in 0 until map.width) return false
        if (y !in 0 until map.height) return false

        return map[x, y] == TileType.FLOOR
    }

    fun getWalkableNeighbors(x: Int, y: Int): List<Pair<Int, Int>> {

        val dirs = listOf(
            1 to 0,
            -1 to 0,
            0 to 1,
            0 to -1
        )

        val result = mutableListOf<Pair<Int, Int>>()

        for ((dx, dy) in dirs) {

            val nx = x + dx
            val ny = y + dy

            if (canWalk(nx, ny)) {
                result.add(nx to ny)
            }
        }

        return result
    }
}
