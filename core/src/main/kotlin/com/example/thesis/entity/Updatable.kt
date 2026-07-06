package com.example.thesis.entity

import com.example.thesis.world.GameWorld

interface Updatable {
    fun update(delta: Float, world: GameWorld)
}
