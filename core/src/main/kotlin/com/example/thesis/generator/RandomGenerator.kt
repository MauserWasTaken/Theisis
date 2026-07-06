package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {

    fun generate(width: Int, height: Int): TileMap {

        val map = TileMap(width, height)

        for (y in 0 until height) {
            for (x in 0 until width) {

                map[x, y] =
                    if (Random.nextFloat() < 0.35f)
                        TileType.WALL
                    else
                        TileType.FLOOR

            }
        }

        return map
    }

}
