package com.example.thesis.world

import com.example.thesis.entity.EnemyGhost
import com.example.thesis.entity.Player

class GameWorld(
    val map: TileMap
) {

    val player: Player
    val enemies = mutableListOf<EnemyGhost>()

    init {
        val spawn = findSpawnInternal()
        player = Player(spawn.first, spawn.second)
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

    fun spawnEnemies(count: Int) {

        val tiles = getSpawnableTiles().shuffled()
        val spawnCount = minOf(count, tiles.size)

        repeat(spawnCount) { i ->
            val (x, y) = tiles[i]
            enemies.add(EnemyGhost(x, y))
        }
    }

    fun findSpawn(): Pair<Int, Int> {
        return getSpawnableTiles().random()
    }

    // ---------------- PRIVATE HELPERS ----------------

    private fun findSpawnInternal(): Pair<Int, Int> {
        return getSpawnableTiles().random()
    }

    private fun getSpawnableTiles(): List<Pair<Int, Int>> {

        val list = mutableListOf<Pair<Int, Int>>()

        for (y in 0 until map.height) {
            for (x in 0 until map.width) {
                if (map[x, y] == TileType.FLOOR) {
                    list.add(x to y)
                }
            }
        }

        return list
    }
}
