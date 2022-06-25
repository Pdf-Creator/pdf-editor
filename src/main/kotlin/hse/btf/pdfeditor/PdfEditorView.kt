package hse.btf.pdfeditor

import hse.btf.pdfeditor.utils.Singleton.itemsHolder
import hse.btf.pdfeditor.models.TableItem
import hse.btf.pdfeditor.models.TextItem
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.Node
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import tornadofx.bind

class PdfEditorView(layout: Pane) {
    companion object {
        @JvmField
        var lastAddedNode: Node? = null

        @JvmField
        var lastAddedAnchorPane: AnchorPane? = null
    }

    init {
        layout.children.bind(itemsHolder.observableItemsList) {
            when (it) {
                is TextItem -> {
                    // text
                    lastAddedNode = TextArea().apply {
                        // connection with TextItem
                        textProperty().bindBidirectional(it.text)
                        it.fontSize.bindBidirectional(SimpleDoubleProperty(font.size))

                        // TODO: сконнектить style -- color, shrift, ...
//                    styleProperty().bindBidirectional(it.style)
//                    println("Font: $font")
                        //fontProperty().bindBidirectional(it.font)
                    }

                    // textBox
                    lastAddedAnchorPane = AnchorPane().apply {
                        // position
                        layoutXProperty().bindBidirectional(it.x)
                        layoutYProperty().bindBidirectional(it.y)

                        prefWidthProperty().bindBidirectional(it.w)
                        prefHeightProperty().bindBidirectional(it.h)
                    }

                    lastAddedNode
                }
                is TableItem -> TableView<String>().apply {
//                    columns
//                    TableColumn
                    // сделать свои columns

                    // position
                    layoutXProperty().bindBidirectional(it.x)
                    layoutYProperty().bindBidirectional(it.y)

                    prefWidthProperty().bindBidirectional(it.w)
                    prefHeightProperty().bindBidirectional(it.h)

                    lastAddedNode = this
                }
            }
        }
    }
}