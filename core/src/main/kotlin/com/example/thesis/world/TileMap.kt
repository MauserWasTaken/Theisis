package com.example.thesis.world

class TileMap(
    val width: Int,
    val height: Int
) {

    val tiles = Array(height) {
        Array(width) {
            TileType.WALL
        }
    }


    val wallVariants = Array(height){
        Array(width){
            WallVariant.INSIDE
        }
    }


    operator fun get(x: Int, y: Int): TileType {
        return tiles[y][x]
    }

    operator fun set(x: Int, y: Int, value: TileType) {
        tiles[y][x] = value
    }


    fun setWallVariant(
        x:Int,
        y:Int,
        variant:WallVariant
    ){
        wallVariants[y][x] = variant
    }


    fun getWallVariant(
        x:Int,
        y:Int
    ):WallVariant {
        return wallVariants[y][x]
    }
}
