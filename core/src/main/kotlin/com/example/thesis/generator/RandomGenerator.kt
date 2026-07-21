package com.example.thesis.generator

import com.example.thesis.data.DoorData
import com.example.thesis.data.DoorDirection
import com.example.thesis.data.GenerationInfo
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
        settings: GenerationSettings,
        seed: Int
    ): LevelData{

        val context = GenerationContext(
            map = TileMap(
                settings.width,
                settings.height
            ),

            debugMap = DebugMap(
                settings.width,
                settings.height
            ),

            random = Random(seed)
        )

        val validator = MapValidator()

        generateWalls(
            context,
            settings
        )

        val floorTiles =
            collectFloorTiles(context)

        validator.validateFloorExists(floorTiles)

        val doors =
            generateDoors(context)

        placeDoors(
            context,
            doors
        )
        validator.validateDoors(
            context.map,
            doors.map { it.x to it.y }
        )

        floorTiles.removeAll(
            doors.map { it.x to it.y }
        )

        floorTiles.shuffle(context.random)

        val playerSpawn = floorTiles.first()

        validator.validatePositionIsWalkable(
            context.map,
            playerSpawn
        )
        context.debugMap.player(
            playerSpawn.first,
            playerSpawn.second
        )

        val enemies = spawnEnemies(floorTiles)

        validator.validateEnemies(
            context.map,
            enemies.map {
                it.x to it.y
            }
        )
        for(enemy in enemies){

            context.debugMap.enemy(
                enemy.x,
                enemy.y
            )
        }

        val barrels = spawnBarrels(floorTiles)

        return LevelData(
            map = context.map,
            playerSpawn = playerSpawn,
            enemies = enemies,
            barrels = barrels,
            potions = mutableListOf(),
            doors = doors,
            debugMap = context.debugMap,
            seed = seed,
            generationInfo = GenerationInfo(
                algorithm = "Random",
                width = context.map.width,
                height = context.map.height
            )
        )
    }

    private fun generateWalls(
        context: GenerationContext,
        settings: GenerationSettings
    ) {

        val wallBuilder = WallBuilder()

        wallBuilder.createBorderWall(
            context.map,
            thickness = settings.borderThickness
        )

        val wallSize = settings.wallSize

        for (y in 2 until context.map.height - wallSize step wallSize) {
            for (x in 2 until context.map.width - wallSize step wallSize) {

                if (context.random.nextFloat() < settings.wallChance)
                {

                    wallBuilder.createWall(
                        context.map,
                        x,
                        y,
                        wallSize,
                        wallSize
                    )

                    for (yy in y until y + wallSize) {
                        for (xx in x until x + wallSize) {

                            context.debugMap.wall(
                                xx,
                                yy
                            )
                        }
                    }
                }
            }
        }
    }

    private fun collectFloorTiles(
        context: GenerationContext
    ): MutableList<Pair<Int,Int>> {

        val floorTiles =
            mutableListOf<Pair<Int,Int>>()

        for (y in 0 until context.map.height) {
            for (x in 0 until context.map.width) {

                if (context.map[x,y] == TileType.FLOOR) {
                    floorTiles.add(x to y)
                }
            }
        }

        return floorTiles
    }

    private fun placeDoors(
        context: GenerationContext,
        doors: List<DoorData>
    ) {

        for (door in doors) {

            context.map[door.x, door.y] =
                TileType.DOOR

            context.debugMap.door(
                door.x,
                door.y
            )
        }
    }

    private fun spawnEnemies(
        floorTiles: List<Pair<Int,Int>>
    ): MutableList<SavedEnemy>{

        return floorTiles
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
        context: GenerationContext
    ): MutableList<DoorData> {

        val width = context.map.width
        val height = context.map.height


        val possible =
            mutableListOf<Triple<Int,Int,DoorDirection>>()



    val borderThickness = 2



    // TOP border door
    for(x in borderThickness until width-borderThickness){

        if(context.map[x,borderThickness] == TileType.FLOOR){

            possible.add(
                Triple(
                    x,
                    borderThickness-1,
                    DoorDirection.NORTH
                )
            )

        }
    }



    // BOTTOM border door
    for(x in borderThickness until width-borderThickness){

        if(context.map[x,height-borderThickness-1] == TileType.FLOOR){

            possible.add(
                Triple(
                    x,
                    height-borderThickness,
                    DoorDirection.SOUTH
                )
            )

        }
    }



    // LEFT border door
    for(y in borderThickness until height-borderThickness){

        if(context.map[borderThickness,y] == TileType.FLOOR){

            possible.add(
                Triple(
                    borderThickness-1,
                    y,
                    DoorDirection.WEST
                )
            )

        }
    }



    // RIGHT border door
    for(y in borderThickness until height-borderThickness){

        if(context.map[width-borderThickness-1,y] == TileType.FLOOR){

            possible.add(
                Triple(
                    width-borderThickness,
                    y,
                    DoorDirection.EAST
                )
            )

        }
    }
        possible.shuffle(context.random)

    return possible
        .take(2)
        .mapIndexed { index, pos ->

            DoorData(
                id = index,
                x = pos.first,
                y = pos.second,
                direction = pos.third
            )

        }
        .toMutableList()
}
}


