package com.example.thesis.entity

class Player(
    override var x: Int,
    override var y: Int
) : Movable {

    var maxHp = 5
    var hp = maxHp

    var invincibleTimer = 0f

    fun update(delta: Float) {
        if (invincibleTimer > 0) {
            invincibleTimer -= delta
        }
    }

    fun canTakeDamage(): Boolean {
        return invincibleTimer <= 0
    }

    fun takeDamage(amount: Int): Boolean {

        if (!canTakeDamage()) {
            return false
        }

        hp -= amount

        invincibleTimer = 2f

        return true
    }


    fun isDead(): Boolean {
        return hp <= 0
    }
}
