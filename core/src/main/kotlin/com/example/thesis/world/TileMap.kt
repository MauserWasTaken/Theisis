package com.example.thesis.world

class TileMap(
    val width: Int,
    val height: Int
) {

    val tiles = Array(height){
        Array(width){
            TileType.WALL
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

    fun isInside(x:Int, y:Int):Boolean {
        return x >= 0 &&
            y >= 0 &&
            x < width &&
            y < height
    }



    fun getNeighbour(
        x:Int,
        y:Int,
        dx:Int,
        dy:Int
    ):TileType? {

        val nx = x + dx
        val ny = y + dy

        if(!isInside(nx,ny))
            return null

        return this[nx,ny]
    }

    fun isWall(x:Int,y:Int):Boolean {

        if(!isInside(x,y))
            return true

        return this[x,y] == TileType.WALL
    }

    fun isFloor(x: Int, y: Int): Boolean {
        return isInside(x, y) && this[x, y] == TileType.FLOOR
    }
}
