package com.example.thesis.data

import com.example.thesis.world.TileMap

data class LevelData(

    val map:TileMap,

    val playerSpawn:Pair<Int,Int>,

    var enemies:MutableList<SavedEnemy>,

    var barrels: MutableList<SavedBarrel>,

    val doors: MutableList<DoorData>
)
