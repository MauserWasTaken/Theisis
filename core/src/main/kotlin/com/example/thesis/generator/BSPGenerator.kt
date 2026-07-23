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

        debugNode(
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

        val minSize = 10


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
                    5,
                    node.height - 5
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
                    5,
                    node.width - 5
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

    }

}
