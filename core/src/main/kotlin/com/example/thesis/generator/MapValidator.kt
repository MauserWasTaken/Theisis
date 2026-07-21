package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType

class MapValidator {


    fun validateFloorExists(
        floorTiles: List<Pair<Int,Int>>
    ){

        require(floorTiles.isNotEmpty()) {
            "Generated map has no floor tiles"
        }

    }



    fun validatePositionIsWalkable(
        map: TileMap,
        position: Pair<Int,Int>
    ){

        require(
            map[position.first,position.second] == TileType.FLOOR ||
                map[position.first,position.second] == TileType.DOOR
        ){
            "Spawn position is not walkable: $position"
        }

    }



    fun validateEnemies(
        map: TileMap,
        enemies: List<Pair<Int,Int>>
    ){

        for(enemy in enemies){

            require(
                map[enemy.first, enemy.second] == TileType.FLOOR
            ){
                "Enemy spawned inside wall: $enemy"
            }

        }

    }



    fun validateDoors(
        map: TileMap,
        doors: List<Pair<Int,Int>>
    ){

        for(door in doors){

            val x = door.first
            val y = door.second


            require(
                map[x,y] == TileType.DOOR
            ){
                "Door not placed correctly: $door"
            }


            val connected =
                listOf(
                    x+1 to y,
                    x-1 to y,
                    x to y+1,
                    x to y-1
                )
                    .any {

                        map.isInside(it.first,it.second) &&
                            map[it.first,it.second] == TileType.FLOOR

                    }


            require(connected){
                "Door has no connection to floor: $door"
            }
        }
    }

    fun validateConnected(
        map:TileMap,
        start:Pair<Int,Int>
    )
    {

    }
}
