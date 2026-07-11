package com.example.thesis.system

import com.example.thesis.world.GameWorld

class CombatSystem {


    fun update(world: GameWorld) {

        val player = world.player


        for (enemy in world.enemies) {


            if (enemy.x == player.x &&
                enemy.y == player.y) {


                if (player.takeDamage(1)) {

                    enemy.stun(2f)
                }
            }
        }
    }
}
