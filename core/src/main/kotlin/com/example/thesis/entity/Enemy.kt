package com.example.thesis.entity

import com.example.thesis.world.GameWorld

abstract class Enemy(
    var x: Int,
    var y: Int
) : Updatable {

    abstract override fun update(delta: Float, world: GameWorld)
}
