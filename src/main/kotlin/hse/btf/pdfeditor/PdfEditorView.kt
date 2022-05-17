package hse.btf.pdfeditor

import hse.btf.pdfeditor.Singleton.itemsHolder
import hse.btf.pdfeditor.models.itemsfxml.TextItemFxml
import hse.btf.pdfeditor.models.itemsstand.TextItem
import javafx.scene.layout.Pane
import tornadofx.bind

class PdfEditorView(layout: Pane) {
    init {
        layout.children.bind(itemsHolder.observableItemsList) {
            when (it) {
                is TextItem -> TextItemFxml(it)
            }.root
        }
    }
}