package com.example.thesis.render

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile
import com.badlogic.gdx.graphics.OrthographicCamera
import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType

import com.example.thesis.assets.Assets


class MapRenderer {

    private lateinit var map: TiledMap
    private lateinit var renderer: OrthogonalTiledMapRenderer

    private val floorTile =
        StaticTiledMapTile(TextureRegion(Assets.floor))

    private val barrelTile =
        StaticTiledMapTile(TextureRegion(Assets.barrel))

    private val doorTile =
        StaticTiledMapTile(TextureRegion(Assets.door))


    fun build(data: TileMap) {

        map = TiledMap()


        val layer = TiledMapTileLayer(
            data.width,
            data.height,
            16,
            16
        )


        for(y in 0 until data.height){
            for(x in 0 until data.width){


                val cell =
                    TiledMapTileLayer.Cell()


                when(data[x,y]) {


                    TileType.WALL -> {


                        val variant =
                            data.getWallVariant(x,y)


                        val wallTile =
                            StaticTiledMapTile(
                                TextureRegion(
                                    Assets.getWallTexture(
                                        variant
                                    )
                                )
                            )


                        cell.setTile(wallTile)

                    }



                    TileType.DOOR -> {

                        cell.setTile(doorTile)

                    }



                    TileType.BARREL -> {

                        cell.setTile(barrelTile)

                    }



                    else -> {

                        cell.setTile(floorTile)

                    }

                }


                layer.setCell(
                    x,
                    y,
                    cell
                )
            }
        }


        map.layers.add(layer)


        renderer =
            OrthogonalTiledMapRenderer(map)
    }


    fun render(camera:OrthographicCamera){

        renderer.setView(camera)
        renderer.render()

    }


    fun dispose(){

        renderer.dispose()
        map.dispose()

    }

}
