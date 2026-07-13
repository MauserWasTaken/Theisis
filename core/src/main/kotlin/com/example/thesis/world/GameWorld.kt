package com.example.thesis.world

import com.example.thesis.data.DoorData
import com.example.thesis.data.LevelData
import com.example.thesis.entity.Enemy
import com.example.thesis.entity.EnemyGhost
import com.example.thesis.entity.Player
import com.example.thesis.data.SavedEnemy
import com.example.thesis.data.SavedBarrel
import com.example.thesis.entity.Barrel
import com.example.thesis.entity.Potion
import com.example.thesis.data.SavedPotion


class GameWorld(
    val level: LevelData
) {

    val map: TileMap = level.map

    val player = Player(
        level.playerSpawn.first,
        level.playerSpawn.second
    )

    val enemies = mutableListOf<Enemy>()

    val barrels = mutableListOf<Barrel>()

    val potions = mutableListOf<Potion>()

    init {

        for(enemy in level.enemies) {

            val ghost = EnemyGhost(
                enemy.x,
                enemy.y
            )

            ghost.hp = enemy.hp

            enemies.add(ghost)
        }

        for(barrel in level.barrels){

            barrels.add(
                Barrel(
                    barrel.x,
                    barrel.y
                )
            )
        }

        for(potion in level.potions){

            potions.add(
                Potion(
                    potion.x,
                    potion.y
                )
            )
        }
    }

    fun updatePlayer(dx: Int, dy: Int) {

        val newX = player.x + dx
        val newY = player.y + dy

        if (canWalk(newX, newY)) {
            player.x = newX
            player.y = newY
        }
    }

    fun updateEnemies(delta: Float) {

        for (enemy in enemies) {
            enemy.update(delta, this)
        }
    }

    fun canWalk(x:Int,y:Int):Boolean {

        if(x !in 0 until map.width) return false
        if(y !in 0 until map.height) return false


        if(barrels.any {
                it.x == x && it.y == y
            })
        {
            return false
        }


        return map[x,y] == TileType.FLOOR ||
            map[x,y] == TileType.DOOR
    }

    fun getWalkableNeighbors(x: Int, y: Int): List<Pair<Int, Int>> {

        val dirs = listOf(
            1 to 0,
            -1 to 0,
            0 to 1,
            0 to -1
        )

        val result = mutableListOf<Pair<Int, Int>>()

        for ((dx, dy) in dirs) {

            val nx = x + dx
            val ny = y + dy

            if (canWalk(nx, ny)) {
                result.add(nx to ny)
            }
        }

        return result
    }

    fun getPlayerDoor(): DoorData? {


        return level.doors.firstOrNull {

            it.x == player.x &&
                it.y == player.y

        }
    }

    fun getSpawnPositionNearDoor(door: DoorData): Pair<Int, Int> {

        val directions = listOf(
            0 to 1,   // up
            0 to -1,  // down
            1 to 0,   // right
            -1 to 0   // left
        )


        for ((dx, dy) in directions) {

            val x = door.x + dx
            val y = door.y + dy

            if (canWalk(x, y) && map[x, y] != TileType.DOOR) {
                return x to y
            }
        }


        // fallback (should not happen)
        return door.x to door.y
    }

    fun saveState(){

        level.enemies.clear()

        for(enemy in enemies){

            level.enemies.add(
                SavedEnemy(
                    enemy.x,
                    enemy.y,
                    enemy.hp
                )
            )
        }


        level.barrels.clear()

        for(barrel in barrels){

            level.barrels.add(
                SavedBarrel(
                    barrel.x,
                    barrel.y
                )
            )
        }

        level.potions.clear()

        for(potion in potions){

            level.potions.add(
                SavedPotion(
                    potion.x,
                    potion.y
                )
            )
        }
    }

    fun pickupPotion(){

        val iterator = potions.iterator()

        while(iterator.hasNext()){

            val potion = iterator.next()

            if(
                potion.x == player.x &&
                potion.y == player.y
            ){

                //player.heal(3)

                iterator.remove()

            }
        }
    }
}
