package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.world.WallTile

class BrickGenerator {

    fun generate(
        map: TileMap
    ){

        for(y in 1 until map.height){

            for(x in 0 until map.width){

                val texture =
                    map.getWallTexture(x,y)


                when(texture){

                    WallTile.BOTTOM -> {

                        map[x, y - 1] = TileType.WALL

                        map.setWallVariant(
                            x,
                            y - 1,
                            WallTile.BRICK
                        )
                    }


                    WallTile.BOTTOM_LEFT -> {

                        map[x, y - 1] = TileType.WALL

                        map.setWallVariant(
                            x,
                            y - 1,
                            WallTile.BRICK_RIGHT
                        )
                    }


                    WallTile.BOTTOM_RIGHT -> {

                        map[x, y - 1] = TileType.WALL

                        map.setWallVariant(
                            x,
                            y - 1,
                            WallTile.BRICK_LEFT
                        )
                    }


                    else -> {}
                }
            }
        }
    }
}
