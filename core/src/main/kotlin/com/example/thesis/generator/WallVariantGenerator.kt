package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.world.WallVariant


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
    ):WallVariant {


        val up = isFloor(x,y-1,map)
        val down = isFloor(x,y+1,map)
        val left = isFloor(x-1,y,map)
        val right = isFloor(x+1,y,map)


        return when {


            // corners
            down && right ->
                WallVariant.TOP_LEFT


            down && left ->
                WallVariant.TOP_RIGHT


            up && right ->
                WallVariant.BOTTOM_LEFT


            up && left ->
                WallVariant.BOTTOM_RIGHT



            // edges
            down ->
                WallVariant.TOP


            up ->
                WallVariant.BOTTOM


            right ->
                WallVariant.LEFT


            left ->
                WallVariant.RIGHT



            else ->
                WallVariant.INSIDE
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
