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

    var stunTimer = 0f

    var hp = 1


    fun takeDamage(amount: Int): Boolean {

        hp -= amount

        return hp <= 0
    }


    fun stun(duration: Float) {
        stunTimer = duration
    }


    fun isStunned(): Boolean {
        return stunTimer > 0f
    }


    fun updateStun(delta: Float) {
        if (stunTimer > 0f) {
            stunTimer -= delta
        }
    }


    abstract override fun update(delta: Float, world: GameWorld)
}
