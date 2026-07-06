package com.example.thesis.generator

import com.example.thesis.data.LevelData
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {

    fun generate(width: Int, height: Int): LevelData {

        val map = TileMap(width, height)

        val floorTiles = mutableListOf<Pair<Int, Int>>()

        for (y in 0 until height) {
            for (x in 0 until width) {

                val isFloor = Random.nextFloat() >= 0.35f

                map[x, y] =
                    if (isFloor) TileType.FLOOR else TileType.WALL

                if (isFloor) {
                    floorTiles.add(x to y)
                }
            }
        }

        floorTiles.shuffle()

        val playerSpawn = floorTiles.first()
        val enemySpawns = floorTiles.drop(1).take(5)

        return LevelData(
            map = map,
            playerSpawn = playerSpawn,
            enemySpawns = enemySpawns
        )
    }
}
