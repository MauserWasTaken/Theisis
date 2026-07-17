package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType


class WallBuilder {


    fun apply(
        map: TileMap,
        thickness: Int = 3
    ) {


        val originalWalls =
            mutableListOf<Pair<Int,Int>>()


        // Save current walls
        for(y in 0 until map.height){
            for(x in 0 until map.width){

                if(map[x,y] == TileType.WALL){

                    originalWalls.add(
                        x to y
                    )

                }

            }
        }



        val radius = thickness / 2



        // Expand every wall
        for(wall in originalWalls){


            val centerX = wall.first
            val centerY = wall.second


            for(y in centerY-radius .. centerY+radius){
                for(x in centerX-radius .. centerX+radius){


                    if(
                        x >= 0 &&
                        y >= 0 &&
                        x < map.width &&
                        y < map.height
                    ){

                        map[x,y] =
                            TileType.WALL

                    }

                }
            }

        }

    }

}
