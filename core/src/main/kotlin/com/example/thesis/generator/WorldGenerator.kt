package com.example.thesis.generator

import com.example.thesis.world.TileMap

interface WorldGenerator {

    fun generate(
        width:Int,
        height:Int
    ): TileMap

}
