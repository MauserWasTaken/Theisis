package com.example.thesis.render

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.entity.Player

class MapRenderer {

    private lateinit var map: TiledMap
    private lateinit var renderer: OrthogonalTiledMapRenderer

    private val floorTexture = Texture("thesis/Tiles/tile_0048.png")
    private val wallTexture  = Texture("thesis/Tiles/tile_0014.png")

    private val floorTile = StaticTiledMapTile(TextureRegion(floorTexture))
    private val wallTile  = StaticTiledMapTile(TextureRegion(wallTexture))

    private lateinit var data: TileMap

    init {
        floorTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        wallTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
    }

    fun build(data: TileMap) {
        this.data = data

        map = TiledMap()

        val layer = TiledMapTileLayer(50, 50, 16, 16)

        for (y in 0 until 50) {
            for (x in 0 until 50) {

                val cell = TiledMapTileLayer.Cell()

                if (data.get(x, y) == TileType.WALL) {
                    cell.setTile(wallTile)
                } else {
                    cell.setTile(floorTile)
                }

                layer.setCell(x, y, cell)
            }
        }

        map.layers.add(layer)

        renderer = OrthogonalTiledMapRenderer(map)
    }

    fun render(camera: OrthographicCamera) {
        renderer.setView(camera)
        renderer.render()
    }

    fun dispose() {
        renderer.dispose()
        map.dispose()

        floorTexture.dispose()
        wallTexture.dispose()
    }
}
