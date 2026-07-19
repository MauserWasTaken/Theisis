package com.example.thesis.generator

import com.example.thesis.world.TileMap
import com.example.thesis.world.TileType
import com.example.thesis.world.WallTile

class WallBuilder {


    fun createWall(
        map: TileMap,
        startX:Int,
        startY:Int,
        width:Int,
        height:Int
    ){

        for(y in 0 until height){
            for(x in 0 until width){


                val px = startX + x
                val py = startY + y


                map[px,py] = TileType.WALL


                val variant =
                    when {


                        // TOP (visually highest row)
                        y == height-1 && x == 0 ->
                            WallTile.TOP_RIGHT


                        y == height-1 && x == width-1 ->
                            WallTile.TOP_LEFT


                        y == height-1 ->
                            WallTile.TOP



                        // BOTTOM EDGE
                        y == 1 && x == 0 ->
                            WallTile.BOTTOM_RIGHT


                        y == 1 && x == width-1 ->
                            WallTile.BOTTOM_LEFT


                        y == 1 ->
                            WallTile.BOTTOM



                        // SIDES
                        x == 0 ->
                            WallTile.RIGHT

                        x == width-1 ->
                            WallTile.LEFT



                        else ->
                            WallTile.INSIDE

                    }


                map.setWallVariant(
                    px,
                    py,
                    variant
                )
            }
        }


            // brick base at bottom
        for(x in 0 until width){

            val variant =
                when(x){

                    0 ->
                        WallTile.BRICK_LEFT


                    width-1 ->
                        WallTile.BRICK_RIGHT


                    else ->
                        WallTile.BRICK
                }


            map.setWallVariant(
                startX+x,
                startY,
                variant
            )

        }

    }

    fun createBorderWall(
        map: TileMap,
        thickness: Int
    ) {

        val width = map.width
        val height = map.height


        // TOP border
        for (y in 0 until thickness) {
            for (x in 0 until width) {

                map[x, y] = TileType.WALL

                map.setWallVariant(
                    x,
                    y,
                    if (y == thickness - 1)
                        WallTile.TOP
                    else
                        WallTile.INSIDE
                )
            }
        }


        // BOTTOM border
        for (y in height - thickness until height) {
            for (x in 0 until width) {

                map[x, y] = TileType.WALL

                map.setWallVariant(
                    x,
                    y,
                    if (y == height - thickness)
                        WallTile.BRICK
                    else
                        WallTile.BOTTOM
                )
            }
        }


        // LEFT BORDER
        for (y in thickness until height - thickness) {

            for (x in 0 until thickness) {

                map[x, y] = TileType.WALL

                map.setWallVariant(
                    x,
                    y,
                    if (x == thickness - 1)
                        WallTile.LEFT
                    else
                        WallTile.INSIDE
                )
            }
        }


        // RIGHT BORDER
        for (y in thickness until height - thickness) {

            for (x in width - thickness until width) {

                map[x, y] = TileType.WALL

                map.setWallVariant(
                    x,
                    y,
                    if (x == width - thickness)
                        WallTile.RIGHT
                    else
                        WallTile.INSIDE
                )
            }
        }


        /*
            Override the four transition corners.

            Top-left

            0 0
            0 1 2 2
            0 13

        */

        map.setWallVariant(
            thickness - 1,
            thickness - 1,
            WallTile.BORDER_BOTTOM_LEFT
        )

        // left side of bottom-left corner
        map.setWallVariant(
            thickness -2,
            thickness -1,
            WallTile.INSIDE
        )


        /*
            Top-right

               0 0
            2 2 3 0
               15 0
        */

        map.setWallVariant(
            width - thickness,
            thickness - 1,
            WallTile.BORDER_BOTTOM_RIGHT
        )

        map.setWallVariant(
            width - thickness + 1,
            thickness - 1,
            WallTile.INSIDE
        )
        map.setWallVariant(
            0,
            height - thickness,
            WallTile.INSIDE
        )
        map.setWallVariant(
            0,
            height - thickness +1,
            WallTile.INSIDE
        )


        /*
            Bottom-left

            0 13
            25 14
            0 0
        */

        map.setWallVariant(
            thickness - 1,
            height - thickness +1,
            WallTile.BORDER_TOP_LEFT
        )
        map.setWallVariant(
            thickness - 1,
            height - thickness,
            WallTile.LEFT
        )

        /*
            Bottom-right

            14 27
            15 0
            0 0
        */

        map.setWallVariant(
            width - thickness,
            height - thickness + 1,
            WallTile.BORDER_TOP_RIGHT
        )
        map.setWallVariant(
            width - thickness,
            height - thickness ,
            WallTile.RIGHT
        )
        map.setWallVariant(
            width - thickness,
            height - thickness ,
            WallTile.RIGHT
        )
        map.setWallVariant(
            width - 1,
            height - thickness,
            WallTile.INSIDE
        )

        map.setWallVariant(
            width - 1,
            height - thickness +1,
            WallTile.INSIDE
        )
    }
    }
