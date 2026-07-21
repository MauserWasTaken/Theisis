package com.example.thesis.generator

import com.example.thesis.world.DebugMap
import com.example.thesis.world.TileMap
import kotlin.random.Random

data class GenerationContext(

    val map: TileMap,

    val debugMap: DebugMap,

    val random: Random

)
