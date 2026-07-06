package com.example.thesis.data

import com.example.thesis.world.TileMap

data class LevelData(
    val map: TileMap,
    val playerSpawn: Pair<Int, Int>,
    val enemySpawns: List<Pair<Int, Int>>,
    //val exitSpawn: Pair<Int, Int>
)
