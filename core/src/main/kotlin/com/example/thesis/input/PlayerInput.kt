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

    fun attackDirection(): Pair<Int,Int>? {


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            return 0 to 1


        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            return 0 to -1


        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            return -1 to 0


        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            return 1 to 0


        return null
    }

}
