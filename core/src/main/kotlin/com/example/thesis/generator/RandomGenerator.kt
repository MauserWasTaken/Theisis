package com.example.thesis.generator

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.data.SavedEnemy
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {

    fun generate(width: Int, height: Int): LevelData {

        val map = TileMap(width, height)

        val floorTiles = mutableListOf<Pair<Int, Int>>()


        // Generate map
        for (y in 0 until height) {
            for (x in 0 until width) {

                val isFloor = Random.nextFloat() >= 0.35f

                map[x, y] =
                    if (isFloor) TileType.FLOOR
                    else TileType.WALL


                if (isFloor) {
                    floorTiles.add(x to y)
                }
            }
        }


        // Generate doors
        val doors = generateDoors(width,height,map)


        for(door in doors) {

            map[door.x,door.y] = TileType.DOOR

        }


        // Remove doors from possible spawn locations
        floorTiles.removeAll(
            doors.map { it.x to it.y }
        )


        floorTiles.shuffle()


        // Player spawn
        val playerSpawn = floorTiles.first()


        // Enemy spawn
        val enemies = floorTiles
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



        return LevelData(
            map = map,
            playerSpawn = playerSpawn,
            enemies = enemies,
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

            if(map[x,1] == TileType.FLOOR)
                possible.add(x to 0)


            if(map[x,height-2] == TileType.FLOOR)
                possible.add(x to height-1)
        }


        for(y in 1 until height-1){

            if(map[1,y] == TileType.FLOOR)
                possible.add(0 to y)


            if(map[width-2,y] == TileType.FLOOR)
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
