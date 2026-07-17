package com.example.thesis.generator

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.data.SavedBarrel
import com.example.thesis.data.SavedEnemy
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {
        fun generate(width: Int, height: Int): LevelData {


            val map = TileMap(width, height)

            val floorTiles =
                mutableListOf<Pair<Int,Int>>()



            val wallSize = 3

            for(y in 0 until height step wallSize){
                for(x in 0 until width step wallSize){

                    val makeWall =
                        Random.nextFloat() < 0.35f


                    for(yy in y until y + wallSize){
                        for(xx in x until x + wallSize){

                            if(
                                xx < width &&
                                yy < height
                            ){

                                if(makeWall){
                                    map[xx,yy] = TileType.WALL
                                }
                                else{
                                    map[xx,yy] = TileType.FLOOR
                                }

                            }
                        }
                    }

                }
            }


            // Make walls thicker
            WallBuilder()
                .apply(
                    map,
                    thickness = 3
                )


            // Calculate wall textures
            WallVariantGenerator()
                .apply(map)


            // Find actual walkable tiles
            for(y in 0 until height){
                for(x in 0 until width){

                    if(map[x,y] == TileType.FLOOR){
                        floorTiles.add(x to y)
                    }

                }
            }



            // Generate doors
            val doors =
                generateDoors(
                    width,
                    height,
                    map
                )


            for(door in doors){

                map[door.x,door.y] =
                    TileType.DOOR

            }



            floorTiles.removeAll(
                doors.map {
                    it.x to it.y
                }
            )



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



    private fun generateDoors(
        width:Int,
        height:Int,
        map:TileMap
    ): MutableList<DoorData> {


        val possible = mutableListOf<Pair<Int,Int>>()


        for(x in 1 until width-1){

            if(map[x,3] == TileType.FLOOR)
                possible.add(x to 0)


            if(map[x,height-4]== TileType.FLOOR)
                possible.add(x to height-1)
        }


        for(y in 1 until height-1){

            if(map[3,y] == TileType.FLOOR)
                possible.add(0 to y)


            if(map[width-4,y] == TileType.FLOOR)
                possible.add(width-1 to y)
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
}
