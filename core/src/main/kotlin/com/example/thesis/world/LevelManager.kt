package com.example.thesis.world

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.generator.RandomGenerator
import kotlin.random.Random


class LevelManager {
    val seed = Random.nextInt()

    private val levels = mutableMapOf<Int, LevelData>()

    var currentLevel = 0


    fun loadLevel(number:Int):LevelData {

        return levels.getOrPut(number) {

            RandomGenerator().generate(
                50,
                40,
                //seed = seed
                seed = 12345
            )

        }
    }


    fun enterDoor(door:DoorData):LevelData {


        // already connected
        if(door.connectedLevel != -1) {

            currentLevel = door.connectedLevel

            return loadLevel(currentLevel)
        }


        // create new level
        val oldLevel = currentLevel

        currentLevel++


        val newLevel = loadLevel(currentLevel)


        // connect same door id
        val targetDoor =
            newLevel.doors[door.id]


        door.connectedLevel = currentLevel
        door.connectedDoor = targetDoor.id


        targetDoor.connectedLevel = oldLevel
        targetDoor.connectedDoor = door.id


        return newLevel
    }


    fun getReturnDoor(level:LevelData, doorId:Int):DoorData {

        return level.doors[doorId]
    }

    fun changeLevel(
        currentWorld: GameWorld,
        door: DoorData
    ): GameWorld {

        // save old level
        currentWorld.saveState()


        // load destination
        val newLevel = enterDoor(door)


        val newWorld = GameWorld(newLevel)


        // find connected door
        val targetDoor =
            newLevel.doors[door.connectedDoor]


        // spawn next to it
        val spawn =
            newWorld.getSpawnPositionNearDoor(targetDoor)


        newWorld.player.x = spawn.first
        newWorld.player.y = spawn.second


        return newWorld
    }


    fun current():LevelData {

        return loadLevel(currentLevel)
    }
}
