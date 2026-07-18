package com.example.thesis.generator

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.data.SavedBarrel
import com.example.thesis.data.SavedEnemy
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {


    fun generate(
        width:Int,
        height:Int
    ):LevelData {


        val map =
            TileMap(
                width,
                height
            )


        val wallBuilder =
            WallBuilder()

        wallBuilder.createBorderWall(
            map,
            thickness = 2
        )

        val wallSize = 4


        for(y in 2 until height-wallSize step wallSize){

            for(x in 2 until width-wallSize step wallSize){


                val makeWall =
                    Random.nextFloat() < 0.35f



                if(makeWall){

                    wallBuilder.createWall(
                        map,
                        x,
                        y,
                        wallSize,
                        wallSize
                    )

                }

            }

        }




        /*
            Find walkable tiles
        */

        val floorTiles =
            mutableListOf<Pair<Int,Int>>()



        for(y in 0 until height){

            for(x in 0 until width){


                if(map[x,y] == TileType.FLOOR){

                    floorTiles.add(
                        x to y
                    )

                }

            }
        }





        /*
            Generate doors
        */

        val doors =
            generateDoors(
                width,
                height,
                map
            )



        for(door in doors){

            map[
                door.x,
                door.y
            ] =
                TileType.DOOR

        }



        floorTiles.removeAll(
            doors.map {
                it.x to it.y
            }
        )





        /*
            Spawn entities
        */

        floorTiles.shuffle()



        val playerSpawn =
            floorTiles.first()



        val enemies =
            floorTiles
                .drop(1)
                .take(5)
                .map {

                    SavedEnemy(
                        x = it.first,
                        y = it.second,
                        hp = 1
                    )

                }
                .toMutableList()



        val barrels =
            floorTiles
                .drop(6)
                .take(10)
                .map {

                    SavedBarrel(
                        x = it.first,
                        y = it.second
                    )

                }
                .toMutableList()



        return LevelData(

            map = map,

            playerSpawn = playerSpawn,

            enemies = enemies,

            barrels = barrels,

            potions = mutableListOf(),

            doors = doors

        )

    }
}





private fun generateDoors(
    width:Int,
    height:Int,
    map:TileMap
):MutableList<DoorData>{


    val possible =
        mutableListOf<Pair<Int,Int>>()



    val borderThickness = 2



    // TOP border door
    for(x in borderThickness until width-borderThickness){

        if(map[x,borderThickness] == TileType.FLOOR){

            possible.add(
                x to borderThickness-1
            )

        }
    }



    // BOTTOM border door
    for(x in borderThickness until width-borderThickness){

        if(map[x,height-borderThickness-1] == TileType.FLOOR){

            possible.add(
                x to height-borderThickness
            )

        }
    }



    // LEFT border door
    for(y in borderThickness until height-borderThickness){

        if(map[borderThickness,y] == TileType.FLOOR){

            possible.add(
                borderThickness-1 to y
            )

        }
    }



    // RIGHT border door
    for(y in borderThickness until height-borderThickness){

        if(map[width-borderThickness-1,y] == TileType.FLOOR){

            possible.add(
                width-borderThickness to y
            )

        }
    }



    possible.shuffle()



    return possible
        .take(2)
        .mapIndexed { index, pos ->

            DoorData(
                id = index,
                x = pos.first,
                y = pos.second
            )

        }
        .toMutableList()
}


