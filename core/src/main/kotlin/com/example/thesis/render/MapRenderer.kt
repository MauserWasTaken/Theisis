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
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.example.thesis.world.DebugMap
import com.example.thesis.debug.DebugConfig
import com.example.thesis.world.DebugType


class MapRenderer {

    private lateinit var map: TiledMap
    private lateinit var renderer: OrthogonalTiledMapRenderer

    private val debugFont =
        BitmapFont()

    private lateinit var debugMap: DebugMap

    private val floorTile =
        StaticTiledMapTile(TextureRegion(Assets.floor))

    private val barrelTile =
        StaticTiledMapTile(TextureRegion(Assets.barrel))

    private val doorTile =
        StaticTiledMapTile(TextureRegion(Assets.door))


    fun build(
        data: TileMap,
        debug: DebugMap
    )
    {

        debugMap = debug


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

                        val wallVariant =
                            data.getWallTexture(x,y)


                        val wallTexture =
                            Assets.getWallTexture(
                                wallVariant
                            )


                        cell.setTile(
                            StaticTiledMapTile(
                                TextureRegion(wallTexture)
                            )
                        )

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

    fun renderDebug(
        camera: OrthographicCamera
    ){

        if(!DebugConfig.enabled)
            return


        val batch =
            renderer.batch


        batch.begin()


        for(y in 0 until debugMap.height){

            for(x in 0 until debugMap.width){

                val debug =
                    debugMap.get(x,y)


                val text =
                    when(debug){

                        DebugType.WALL ->
                            "W"

                        DebugType.ROOM ->
                            "R"

                        DebugType.PATH ->
                            "."

                        DebugType.PLAYER ->
                            "P"

                        DebugType.ENEMY ->
                            "E"

                        DebugType.BARREL ->
                            "B"

                        DebugType.DOOR ->
                            "D"

                        DebugType.NONE ->
                            ""
                    }


                if(text.isNotEmpty()){

                    debugFont.draw(
                        batch,
                        text,
                        x * 16f,
                        y * 16f
                    )

                }
            }
        }


        batch.end()
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
