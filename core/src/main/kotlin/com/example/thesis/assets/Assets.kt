package com.example.thesis.assets

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.example.thesis.world.WallTile

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

    lateinit var wallInside: Texture
    lateinit var wallTop: Texture
    lateinit var wallBottom: Texture
    lateinit var wallLeft: Texture
    lateinit var wallRight: Texture

    lateinit var wallTopLeft: Texture
    lateinit var wallTopRight: Texture
    lateinit var wallBottomLeft: Texture
    lateinit var wallBottomRight: Texture

    lateinit var wallBrickLeft: Texture
    lateinit var wallBrickRight: Texture

    lateinit var borderTopLeft: Texture
    lateinit var borderTopRight: Texture
    lateinit var borderBottomLeft: Texture
    lateinit var borderBottomRight: Texture


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


        //tall textures
        wallInside = Texture("thesis/Tiles/tile_0000.png")
        wallTop = Texture("thesis/Tiles/tile_0026.png")
        wallBottom = Texture("thesis/Tiles/tile_0002.png")
        wallLeft = Texture("thesis/Tiles/tile_0013.png")
        wallRight = Texture("thesis/Tiles/tile_0015.png")

        wallTopLeft = Texture("thesis/Tiles/tile_0005.png")
        wallTopRight = Texture("thesis/Tiles/tile_0004.png")
        wallBottomLeft = Texture("thesis/Tiles/tile_0017.png")
        wallBottomRight = Texture("thesis/Tiles/tile_0016.png")

        wallBrickLeft =
            Texture("thesis/Tiles/tile_0057.png")

        wallBrickRight =
            Texture("thesis/Tiles/tile_0059.png")

        borderTopLeft =
            Texture("thesis/Tiles/tile_0001.png")

        borderTopRight =
            Texture("thesis/Tiles/tile_0003.png")

        borderBottomLeft =
            Texture("thesis/Tiles/tile_0025.png")

        borderBottomRight =
            Texture("thesis/Tiles/tile_0027.png")



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

    fun getWallTexture(
        variant: WallTile
    ): Texture {

        return when(variant){

            WallTile.INSIDE ->
                wallInside

            WallTile.TOP ->
                wallTop

            WallTile.BOTTOM ->
                wallBottom

            WallTile.LEFT ->
                wallLeft

            WallTile.RIGHT ->
                wallRight

            WallTile.TOP_LEFT ->
                wallTopLeft

            WallTile.TOP_RIGHT ->
                wallTopRight

            WallTile.BOTTOM_LEFT ->
                wallBottomLeft

            WallTile.BOTTOM_RIGHT ->
                wallBottomRight

            WallTile.BRICK ->
                wall

            WallTile.NONE ->
                wallInside

            WallTile.BRICK_LEFT ->
                wallBrickLeft

            WallTile.BRICK_RIGHT ->
                wallBrickRight

            WallTile.BORDER_TOP_LEFT ->
                borderTopLeft

            WallTile.BORDER_TOP_RIGHT ->
                borderTopRight

            WallTile.BORDER_BOTTOM_LEFT ->
                borderBottomLeft

            WallTile.BORDER_BOTTOM_RIGHT ->
                borderBottomRight
        }
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
