package com.example.thesis.generator

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.data.SavedBarrel
import com.example.thesis.data.SavedEnemy
import com.example.thesis.world.DebugMap
import com.example.thesis.world.DebugType
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import kotlin.random.Random

class RandomGenerator {

    fun generate(
        width: Int,
        height: Int,
        seed: Int
    ): LevelData {

        val random = Random(seed)

        val map = TileMap(width, height)

        val debugMap =
            DebugMap(
                width,
                height
            )

        generateWalls(
            map,
            debugMap,
            random
        )

        val floorTiles = collectFloorTiles(map)

        val doors = generateDoors(
            map,
            random
        )

        placeDoors(map, doors)
        for(door in doors){

            debugMap.set(
                door.x,
                door.y,
                DebugType.DOOR
            )

        }

        floorTiles.removeAll(
            doors.map { it.x to it.y }
        )

        floorTiles.shuffle(random)

        val playerSpawn = floorTiles.first()
        debugMap.set(
            playerSpawn.first,
            playerSpawn.second,
            DebugType.PLAYER
        )

        val enemies = spawnEnemies(floorTiles)
        for(enemy in enemies){

            debugMap.set(
                enemy.x,
                enemy.y,
                DebugType.ENEMY
            )
        }

        val barrels = spawnBarrels(floorTiles)

        return LevelData(
            map = map,
            playerSpawn = playerSpawn,
            enemies = enemies,
            barrels = barrels,
            potions = mutableListOf(),
            doors = doors,
            debugMap = debugMap,
            seed = seed
        )
    }

    private fun generateWalls(
        map: TileMap,
        debugMap: DebugMap,
        random: Random
    )
    {
        val wallBuilder = WallBuilder()

        wallBuilder.createBorderWall(
            map,
            thickness = 2
        )

        val wallSize = 4

        for(y in 2 until map.height-wallSize step wallSize){
            for(x in 2 until map.width-wallSize step wallSize){

                if(random.nextFloat() < 0.35f){

                    wallBuilder.createWall(
                        map,
                        x,
                        y,
                        wallSize,
                        wallSize
                    )
                    for(yy in y until y + wallSize){
                        for(xx in x until x + wallSize){

                            debugMap.set(
                                xx,
                                yy,
                                DebugType.WALL
                            )

                        }
                    }
                }
            }
        }
    }

    private fun collectFloorTiles(
        map: TileMap
    ): MutableList<Pair<Int, Int>> {

        val floorTiles =
            mutableListOf<Pair<Int, Int>>()

        for (y in 0 until map.height) {
            for (x in 0 until map.width) {

                if (map[x, y] == TileType.FLOOR) {
                    floorTiles.add(x to y)
                }
            }
        }

        return floorTiles
    }

    private fun placeDoors(
        map: TileMap,
        doors: List<DoorData>
    ) {
        for (door in doors) {
            map[door.x, door.y] = TileType.DOOR
        }
    }

    private fun spawnEnemies(
        floorTiles: List<Pair<Int, Int>>
    ): MutableList<SavedEnemy> {

        return floorTiles
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
    }

    private fun spawnBarrels(
        floorTiles: List<Pair<Int, Int>>
    ): MutableList<SavedBarrel> {

        return floorTiles
            .drop(6)
            .take(10)
            .map {

                SavedBarrel(
                    x = it.first,
                    y = it.second
                )

            }
            .toMutableList()
    }

    private fun generateDoors(
        map: TileMap,
        random: Random
    ): MutableList<DoorData> {

        val width = map.width
        val height = map.height


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
        possible.shuffle(random)

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


