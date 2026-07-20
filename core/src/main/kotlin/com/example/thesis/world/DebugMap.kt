package com.example.thesis.world

class DebugMap(
    val width: Int,
    val height: Int
) {

    val values = Array(height) {
        Array(width) {
            DebugType.NONE
        }
    }


    fun set(
        x:Int,
        y:Int,
        value:DebugType
    ){

        if(isInside(x,y)){
            values[y][x] = value
        }
    }


    fun get(
        x:Int,
        y:Int
    ):DebugType {

        return values[y][x]
    }


    private fun isInside(
        x:Int,
        y:Int
    ):Boolean {

        return x >= 0 &&
            y >= 0 &&
            x < width &&
            y < height
    }
}
