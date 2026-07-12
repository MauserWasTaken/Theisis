package com.example.thesis.entity

class Player(
    override var x: Int,
    override var y: Int
) : Movable {

    var maxHp = 3
    var hp = maxHp

    var invincibleTimer = 0f

    var attackID = 0


    // Current facing direction
    var dirX = 0
    var dirY = 1


    // Attack direction
    private var attackDirX = 0
    private var attackDirY = 1


    // Attack timer
    var attackTimer = 0f
    private val attackDuration = 0.15f


    fun update(delta: Float) {

        if (invincibleTimer > 0) {
            invincibleTimer -= delta
        }

        if (attackTimer > 0) {
            attackTimer -= delta
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


    fun attack(dx:Int, dy:Int) {

        if(attackTimer <= 0) {

            attackDirX = dx
            attackDirY = dy

            attackTimer = attackDuration

            attackID++
        }
    }


    fun isAttacking(): Boolean {
        return attackTimer > 0
    }


    fun attackPosition(): Pair<Int, Int> {

        return Pair(
            x + attackDirX,
            y + attackDirY
        )
    }


    fun setDirection(dx: Int, dy: Int) {

        if(dx != 0 || dy != 0) {
            dirX = dx
            dirY = dy
        }
    }

    fun attackRotation(): Float {

        return when {
            attackDirY > 0 -> 0f      // up
            attackDirY < 0 -> 180f    // down
            attackDirX > 0 -> 270f    // right
            attackDirX < 0 -> 90f     // left
            else -> 0f
        }
    }


    fun isDead(): Boolean {
        return hp <= 0
    }
}
