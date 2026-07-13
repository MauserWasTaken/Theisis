package com.example.thesis.assets

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureRegion

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

    lateinit var sword: Texture
        private set

    lateinit var swordRegion: TextureRegion
        private set


    lateinit var weaponSlot: Texture
        private set

    lateinit var door: Texture
        private set

    lateinit var barrel: Texture
        private set

    lateinit var potion: Texture
        private set


    fun load() {

        player = Texture("thesis/Tiles/tile_0087.png")
        playerHp = Texture("thesis/Tiles/tile_0102.png")

        enemy = Texture("thesis/Tiles/tile_0108.png")
        floor = Texture("thesis/Tiles/tile_0048.png")
        wall = Texture("thesis/Tiles/tile_0014.png")
        sword = Texture("thesis/Tiles/tile_0104.png")
        door = Texture("thesis/Tiles/tile_0021.png")
        barrel = Texture("thesis/Tiles/tile_0082.png")
        potion = Texture("thesis/Tiles/tile_0127.png")

        swordRegion = TextureRegion(sword)


        player.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        playerHp.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        enemy.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        floor.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        wall.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        barrel.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        potion.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        sword.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)

        val pixmap = Pixmap(
            1,
            1,
            Pixmap.Format.RGBA8888
        )

        pixmap.setColor(0f,0f,0f,1f)
        pixmap.fill()

        uiBackground = Texture(pixmap)

        val pixmap2 = Pixmap(
            1,
            1,
            Pixmap.Format.RGBA8888
        )

        pixmap2.setColor(0.5804f, 0.5412f, 0.4078f, 1f)
        pixmap2.fill()

        weaponSlot = Texture(pixmap2)

        pixmap.dispose()
        pixmap2.dispose()

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
