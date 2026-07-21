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

    val roomIds = Array(height) {
        Array(width) {
            -1
        }
    }

    val pathIds =
        Array(height){
            Array(width){
                -1
            }
        }


    val regionIds =
        Array(height){
            Array(width){
                -1
            }
        }


    val floodValues =
        Array(height){
            Array(width){
                -1
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

    fun getRoomId(
        x:Int,
        y:Int
    ):Int {

        return roomIds[y][x]
    }


    // DEBUG HELPERS

    fun wall(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.WALL
        )
    }


    fun room(
        id:Int,
        x:Int,
        y:Int
    ){

        set(
            x,
            y,
            DebugType.ROOM
        )

        roomIds[y][x] = id
    }


    fun path(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.PATH
        )
    }


    fun player(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.PLAYER
        )
    }


    fun enemy(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.ENEMY
        )
    }


    fun barrel(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.BARREL
        )
    }


    fun door(
        x:Int,
        y:Int
    ){
        set(
            x,
            y,
            DebugType.DOOR
        )
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
