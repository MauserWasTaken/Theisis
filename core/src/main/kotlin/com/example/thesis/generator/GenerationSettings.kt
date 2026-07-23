package com.example.thesis.generator

data class GenerationSettings(

    val width: Int,
    val height: Int,

    val wallSize: Int = 4,

    val borderThickness: Int = 2,

    val wallChance: Float = 0.35f,

    val enemyCount: Int = 5,
    val barrelCount: Int = 10,
    val doorCount: Int = 2,

    val roomPadding: Int = 2,
    val minRoomSize: Int = 5

)
