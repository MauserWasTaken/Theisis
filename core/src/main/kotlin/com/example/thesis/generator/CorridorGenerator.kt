package com.example.thesis.generator

import com.example.thesis.world.TileType


class CorridorGenerator {


    fun connectRooms(
        context: GenerationContext,
        rooms: List<Room>
    ){

        for(i in 0 until rooms.size - 1){

            val a = rooms[i]
            val b = rooms[i+1]


            carveHorizontal(
                context,
                a.centerX,
                b.centerX,
                a.centerY
            )


            carveVertical(
                context,
                a.centerY,
                b.centerY,
                b.centerX
            )
        }
    }



    private fun carveHorizontal(
        context: GenerationContext,
        x1:Int,
        x2:Int,
        y:Int
    ){

        val min = minOf(x1,x2)
        val max = maxOf(x1,x2)

        for(x in min..max){
            for(offset in 0..1){
                context.map[x,y+offset] = TileType.FLOOR
            }
        }
    }



    private fun carveVertical(
        context: GenerationContext,
        y1:Int,
        y2:Int,
        x:Int
    ){

        val min = minOf(y1,y2)
        val max = maxOf(y1,y2)

        for(y in min..max){
            for(offset in 0..1){
                context.map[x+offset,y] = TileType.FLOOR
            }
        }
    }
}
