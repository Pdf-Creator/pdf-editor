package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.TextEntity
import hse.btf.pdfeditor.models.TextItem
import hse.btf.pdfeditor.utils.DataStorage.entitiesList
import hse.btf.pdfeditor.utils.DataStorage.itemsHolder
import tornadofx.bind

class EntityToItemConverter {
//    companion object {
//        @JvmField
//        var lastAddedNode: Node? = null
//
//        @JvmField
//        var lastAddedAnchorPane: AnchorPane? = null
//    }
//
//    init {
//        layout.children.bind(itemsHolder.observableItemsList) {
//            when (it) {
//                is TextItem -> {
//                    // text
//                    lastAddedNode = TextArea().apply {
//                        // connection with TextItem
//                        textProperty().bindBidirectional(it.text)
//                        it.fontSize.bindBidirectional(SimpleDoubleProperty(font.size))
//
//                        // TODO: сконнектить style -- color, shrift, ...
////                    styleProperty().bindBidirectional(it.style)
////                    println("Font: $font")
//                        //fontProperty().bindBidirectional(it.font)
//                    }
//
//                    // textBox
//                    lastAddedAnchorPane = AnchorPane().apply {
//                        // position
//                        layoutXProperty().bindBidirectional(it.x)
//                        layoutYProperty().bindBidirectional(it.y)
//
//                        prefWidthProperty().bindBidirectional(it.w)
//                        prefHeightProperty().bindBidirectional(it.h)
//                    }
//
//                    lastAddedNode
//                }
//                is TableItem -> TableView<String>().apply {
////                    columns
////                    TableColumn
//                    // сделать свои columns
//
//                    // position
//                    layoutXProperty().bindBidirectional(it.x)
//                    layoutYProperty().bindBidirectional(it.y)
//
//                    prefWidthProperty().bindBidirectional(it.w)
//                    prefHeightProperty().bindBidirectional(it.h)
//
//                    lastAddedNode = this
//                }
//            }
//        }
//    }

    init {
        itemsHolder.observableItemsList.bind(entitiesList) {
            when (it) {
                is TextEntity ->
                    TextItem().apply {
                        text.bindBidirectional(it.text)
                        x.bindBidirectional(it.xProperty)
                        y.bindBidirectional(it.yProperty)
                        w.bindBidirectional(it.widthProperty)
                        h.bindBidirectional(it.heightProperty)
                    }
                else -> {
                    println("Else branch converter")
                    null
                }
            }
        }
    }
}