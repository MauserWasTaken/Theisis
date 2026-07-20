package com.example.thesis.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input

class DebugController {

    var debugEnabled = false
        private set




    val isDebugEnabled: Boolean
        get() = debugEnabled

    fun update(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.F1)){

            debugEnabled = !debugEnabled

        }
    }
}
