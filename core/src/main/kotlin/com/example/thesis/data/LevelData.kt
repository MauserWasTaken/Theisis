package com.example.thesis.data

import com.example.thesis.world.DebugMap
import com.example.thesis.world.TileMap

data class LevelData(

    val map: TileMap,

    val playerSpawn: Pair<Int,Int>,

    var enemies: MutableList<SavedEnemy>,

    var barrels: MutableList<SavedBarrel>,

    var potions: MutableList<SavedPotion>,

    val doors: MutableList<DoorData>,

    val debugMap: DebugMap,

    val seed: Int,

    val generationInfo: GenerationInfo
)
