package com.example.thesis.world

import com.example.thesis.data.LevelData
import com.example.thesis.entity.EnemyGhost
import com.example.thesis.entity.Player

class GameWorld(level: LevelData) {

    val map: TileMap = level.map

    val player: Player =
        Player(level.playerSpawn.first, level.playerSpawn.second)

    val enemies = mutableListOf<EnemyGhost>()

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

    fun canWalk(x: Int, y: Int): Boolean {
        if (x !in 0 until map.width) return false
        if (y !in 0 until map.height) return false

        return map[x, y] == TileType.FLOOR
    }
}
