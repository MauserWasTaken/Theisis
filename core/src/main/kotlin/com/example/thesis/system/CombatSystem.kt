package com.example.thesis.system

import com.example.thesis.world.GameWorld

class CombatSystem {


    fun update(world: GameWorld) {

        val player = world.player


        // Enemy touching player
        for (enemy in world.enemies) {

            if(enemy.x == player.x &&
                enemy.y == player.y) {

                if(player.takeDamage(1)) {
                    enemy.stun(2f)
                }
            }
        }


        // Player attacking
        if(player.isAttacking()) {

            val attackPos = player.attackPosition()


            val iterator = world.enemies.iterator()

            while(iterator.hasNext()) {

                val enemy = iterator.next()


                if(enemy.x == attackPos.first &&
                    enemy.y == attackPos.second) {


                    val killed = enemy.takeDamage(1)


                    if(killed) {
                        iterator.remove()
                    }
                }
            }
        }
    }
}
