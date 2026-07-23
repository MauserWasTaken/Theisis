package com.example.thesis.generator

class BSPNode(

    val x: Int,
    val y: Int,

    val width: Int,
    val height: Int

) {

    var left: BSPNode? = null
    var right: BSPNode? = null

    var room: Room? = null


    fun isLeaf(): Boolean {
        return left == null && right == null
    }
}
