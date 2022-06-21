package hse.btf.pdfeditor

import hse.btf.pdfeditor.utils.Singleton.itemsHolder
import hse.btf.pdfeditor.models.TableItem
import hse.btf.pdfeditor.models.TextItem
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.Node
import javafx.scene.control.TableView
import javafx.scene.control.TextArea
import javafx.scene.layout.Pane
import tornadofx.bind

class PdfEditorView(layout: Pane) {
    companion object {
        @JvmField
        var lastAddedNode: Node? = null
    }

    init {
        layout.children.bind(itemsHolder.observableItemsList) {
            when (it) {
                is TextItem -> TextArea().apply {
                    // connection with TextItem
                    textProperty().bindBidirectional(it.text)
                    it.fontSize.bindBidirectional(SimpleDoubleProperty(font.size))

//                    styleProperty().bindBidirectional(it.style)
//                    println("Font: $font")
                    //fontProperty().bindBidirectional(it.font)

                    // position
                    layoutXProperty().bindBidirectional(it.x)
                    layoutYProperty().bindBidirectional(it.y)

                    prefWidthProperty().bindBidirectional(it.w)
                    prefHeightProperty().bindBidirectional(it.h)

                    lastAddedNode = this
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