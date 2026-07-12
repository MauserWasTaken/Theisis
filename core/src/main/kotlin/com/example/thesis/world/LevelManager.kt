package com.example.thesis.world

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.generator.RandomGenerator


class LevelManager {

    private val generator = RandomGenerator()

    private val levels = mutableMapOf<Int, LevelData>()

    var currentLevel = 0


    fun loadLevel(number:Int):LevelData {

        return levels.getOrPut(number) {

            generator.generate(
                50,
                40
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


    fun current():LevelData {

        return loadLevel(currentLevel)
    }
}
