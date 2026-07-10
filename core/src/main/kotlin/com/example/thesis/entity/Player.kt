package com.example.thesis.entity

class Player(
    override var x: Int,
    override var y: Int
) : Movable {

    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}
