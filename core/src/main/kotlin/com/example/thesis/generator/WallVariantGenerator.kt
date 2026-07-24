package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.world.WallTile

class WallVariantGenerator {


    fun generate(
        map: TileMap
    ){

        for(y in 0 until map.height){
            for(x in 0 until map.width){


                if(map[x,y] != TileType.WALL)
                    continue


                val variant =
                    calculateVariant(
                        map,
                        x,
                        y
                    )


                map.setWallVariant(
                    x,
                    y,
                    variant
                )
            }
        }
    }



    private fun calculateVariant(
        map: TileMap,
        x:Int,
        y:Int
    ):WallTile {


        val up =
            map.isWall(x,y+1)

        val down =
            map.isWall(x,y-1)

        val left =
            map.isWall(x-1,y)

        val right =
            map.isWall(x+1,y)



        return when {


            !down ->
                WallTile.BOTTOM


            !up ->
                WallTile.TOP


            !left ->
                WallTile.RIGHT


            !right ->
                WallTile.LEFT


            else ->
                WallTile.INSIDE
        }
    }
}
