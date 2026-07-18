package com.example.thesis.world

class TileMap(
    val width: Int,
    val height: Int
) {

    val tiles = Array(height) {
        Array(width) {
            TileType.FLOOR
        }
    }


    val wallTextures = Array(height){
        Array(width){
            WallTile.NONE
        }
    }


    operator fun get(x:Int,y:Int):TileType {
        return tiles[y][x]
    }


    operator fun set(
        x:Int,
        y:Int,
        value:TileType
    ){
        tiles[y][x] = value
    }


    fun setWallVariant(
        x:Int,
        y:Int,
        variant:WallTile
    ){
        wallTextures[y][x] = variant
    }


    fun getWallTexture(
        x:Int,
        y:Int
    ):WallTile{
        return wallTextures[y][x]
    }
}
