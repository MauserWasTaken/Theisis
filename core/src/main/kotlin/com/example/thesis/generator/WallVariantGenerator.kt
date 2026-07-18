package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.world.WallTile


class WallVariantGenerator {


    fun apply(map: TileMap){


        for(y in 0 until map.height){
            for(x in 0 until map.width){


                if(map[x,y] == TileType.WALL){


                    map.setWallVariant(
                        x,
                        y,
                        calculateVariant(
                            x,
                            y,
                            map
                        )
                    )

                }

            }
        }

    }



    private fun calculateVariant(
        x:Int,
        y:Int,
        map:TileMap
    ):WallTile {


        val up = isFloor(x,y-1,map)
        val down = isFloor(x,y+1,map)
        val left = isFloor(x-1,y,map)
        val right = isFloor(x+1,y,map)


        return when {


            // corners
            down && right ->
                WallTile.TOP_LEFT


            down && left ->
                WallTile.TOP_RIGHT


            up && right ->
                WallTile.BOTTOM_LEFT


            up && left ->
                WallTile.BOTTOM_RIGHT



            // edges
            down ->
                WallTile.TOP


            up ->
                WallTile.BOTTOM


            right ->
                WallTile.LEFT


            left ->
                WallTile.RIGHT



            else ->
                WallTile.INSIDE
        }
    }



    private fun isFloor(
        x:Int,
        y:Int,
        map:TileMap
    ):Boolean{


        if(
            x < 0 ||
            y < 0 ||
            x >= map.width ||
            y >= map.height
        )
            return false


        return map[x,y] == TileType.FLOOR
    }

}
