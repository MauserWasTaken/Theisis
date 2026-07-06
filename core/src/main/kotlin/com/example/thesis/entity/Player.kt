package com.example.thesis.entity

class Player(
    var x: Int,
    var y: Int
) {
    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
}
