package com.example.thesis.entity

import com.example.thesis.world.GameWorld

abstract class Enemy(
    override var x: Int,
    override var y: Int
) : Movable, Updatable {

    var state = EnemyState.PATROL

    var previousX = x
    var previousY = y

    var cooldown = 0.5f

    abstract override fun update(delta: Float, world: GameWorld)
}
