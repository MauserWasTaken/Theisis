package com.example.thesis.assets

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap

object Assets {

    lateinit var player: Texture
        private set

    lateinit var playerHp: Texture
        private set

    lateinit var enemy: Texture
        private set

    lateinit var floor: Texture
        private set

    lateinit var wall: Texture
        private set

    lateinit var uiBackground: Texture
        private set


    fun load() {

        player = Texture("thesis/Tiles/tile_0087.png")
        playerHp = Texture("thesis/Tiles/tile_0102.png")

        enemy  = Texture("thesis/Tiles/tile_0108.png")
        floor  = Texture("thesis/Tiles/tile_0048.png")
        wall   = Texture("thesis/Tiles/tile_0014.png")


        player.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        playerHp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        enemy.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        floor.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        wall.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        val pixmap = Pixmap(
            1,
            1,
            Pixmap.Format.RGBA8888
        )

        pixmap.setColor(0f,0f,0f,1f)
        pixmap.fill()

        uiBackground = Texture(pixmap)

        pixmap.dispose()

    }


    fun dispose() {

        player.dispose()
        playerHp.dispose()

        enemy.dispose()
        floor.dispose()
        wall.dispose()
        uiBackground.dispose()
    }
}
