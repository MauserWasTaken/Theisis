package com.example.thesis.world

import com.example.thesis.entity.EnemyGhost

class GameWorld(
    val map: TileMap
) {

    val enemies = mutableListOf<EnemyGhost>()

    fun canWalk(x: Int, y: Int): Boolean {
        if (x !in 0 until map.width) return false
        if (y !in 0 until map.height) return false

        return map[x, y] == TileType.FLOOR
    }

    fun findSpawn(): Pair<Int, Int> {
        return getSpawnableTiles().random()
    }

    fun spawnEnemies(count: Int) {

        val tiles = getSpawnableTiles().shuffled()

        val spawnCount = minOf(count, tiles.size)

        repeat(spawnCount) { i ->
            val (x, y) = tiles[i]
            enemies.add(EnemyGhost(x, y))
        }
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
