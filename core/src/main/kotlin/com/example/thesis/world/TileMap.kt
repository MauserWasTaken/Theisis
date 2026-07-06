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

    operator fun get(x: Int, y: Int): TileType {
        return tiles[y][x]
    }

    operator fun set(x: Int, y: Int, value: TileType) {
        tiles[y][x] = value
    }

}
