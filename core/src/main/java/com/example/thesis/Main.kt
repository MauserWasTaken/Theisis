package com.example.thesis;

import com.badlogic.gdx.Game;
import com.example.thesis.screen.GameScreen;

import com.example.thesis.assets.Assets;

class Main : Game() {

    override fun create() {
        Assets.load()
        setScreen(GameScreen())
    }
}
