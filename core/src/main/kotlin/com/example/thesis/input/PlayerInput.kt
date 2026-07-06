package com.example.thesis.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class PlayerInput {

    fun movement(): Pair<Int, Int> {

        var dx = 0
        var dy = 0

        if (Gdx.input.isKeyPressed(Input.Keys.W)) dy++
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) dy--

        if (Gdx.input.isKeyPressed(Input.Keys.A)) dx--
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) dx++

        return Pair(dx, dy)
    }
}
