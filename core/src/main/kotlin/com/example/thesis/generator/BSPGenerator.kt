package com.example.thesis.generator

class BSPGenerator {


    fun generate(
        context: GenerationContext
    ): List<Room> {


        val root = BSPNode(
            0,
            0,
            context.map.width,
            context.map.height
        )


        split(
            root,
            context
        )

        val rooms = mutableListOf<Room>()


        createRooms(
            root,
            rooms,
            context
        )


        return rooms
    }

    private fun split(
        node: BSPNode,
        context: GenerationContext
    ) {

        val minSize = 16


        if(
            node.width <= minSize ||
            node.height <= minSize
        ){
            return
        }


        val splitHorizontal =
            if(node.width > node.height)
                false
            else
                true


        if(splitHorizontal){

            val split =
                context.random.nextInt(
                    node.height / 3,
                    node.height * 2 / 3
                )


            node.left = BSPNode(
                node.x,
                node.y,
                node.width,
                split
            )


            node.right = BSPNode(
                node.x,
                node.y + split,
                node.width,
                node.height - split
            )

        }
        else {

            val split =
                context.random.nextInt(
                    node.width / 3,
                    node.width * 2 / 3
                )


            node.left = BSPNode(
                node.x,
                node.y,
                split,
                node.height
            )


            node.right = BSPNode(
                node.x + split,
                node.y,
                node.width - split,
                node.height
            )
        }


        split(node.left!!, context)
        split(node.right!!, context)
    }

    private fun debugNode(
        node: BSPNode,
        context: GenerationContext
    ) {

        if(node.isLeaf()){

            for(y in node.y until node.y + node.height){

                for(x in node.x until node.x + node.width){

                    context.debugMap.path(
                        x,
                        y
                    )
                }
            }

            return
        }


        node.left?.let {
            debugNode(
                it,
                context
            )
        }


        node.right?.let {
            debugNode(
                it,
                context
            )
        }
    }
    private fun createRooms(
        node: BSPNode,
        rooms: MutableList<Room>,
        context: GenerationContext
    ) {

        if (node.isLeaf()) {

            val padding = 2
            val minRoomSize = 8

            val maxRoomWidth = node.width - padding * 2
            val maxRoomHeight = node.height - padding * 2

            // leaf is too small
            if (
                maxRoomWidth < minRoomSize ||
                maxRoomHeight < minRoomSize
            ) {
                return
            }

            val roomWidth =
                if (maxRoomWidth == minRoomSize) minRoomSize
                else context.random.nextInt(minRoomSize, maxRoomWidth + 1)

            val roomHeight =
                if (maxRoomHeight == minRoomSize) minRoomSize
                else context.random.nextInt(minRoomSize, maxRoomHeight + 1)

            val xOffsetMax = node.width - roomWidth
            val yOffsetMax = node.height - roomHeight

            val roomX = node.x +
                if (xOffsetMax <= 1) 0
                else context.random.nextInt(1, xOffsetMax)

            val roomY = node.y +
                if (yOffsetMax <= 1) 0
                else context.random.nextInt(1, yOffsetMax)

            val room = Room(
                id = rooms.size,
                x = roomX,
                y = roomY,
                width = roomWidth,
                height = roomHeight
            )

            node.room = room
            rooms.add(room)

            println(
                "Room ${room.id}: ${room.x},${room.y} ${room.width}x${room.height}"
            )

            RoomGenerator().carveRoom(context, room)
            return
        }

        node.left?.let { createRooms(it, rooms, context) }
        node.right?.let { createRooms(it, rooms, context) }
    }

}
