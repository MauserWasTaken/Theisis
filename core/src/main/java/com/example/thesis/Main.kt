package com.example.thesis;

import com.badlogic.gdx.Game;
import com.example.thesis.screen.GameScreen;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
