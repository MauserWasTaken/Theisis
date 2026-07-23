package com.example.thesis.generator

import com.example.thesis.world.TileType

class RoomGenerator {

    fun carveRoom(
        context: GenerationContext,
        room: Room
    ) {

        for (y in room.y + 1 until room.y + room.height - 1) {
            for (x in room.x + 1 until room.x + room.width - 1) {

                context.map[x, y] = TileType.FLOOR

                context.debugMap.room(
                    room.id,
                    x,
                    y
                )
            }
        }
    }
}
