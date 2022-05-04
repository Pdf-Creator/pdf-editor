package hse.btf.pdfeditor

import hse.btf.pdfeditor.Singleton.itemsHolder
import hse.btf.pdfeditor.models.itemsfxml.ItemFxml
import hse.btf.pdfeditor.models.itemsfxml.TextItemFxml
import hse.btf.pdfeditor.models.itemsjava.TextItem
import javafx.collections.FXCollections
import javafx.scene.layout.Pane
import tornadofx.bind

class PdfEditorView(layout: Pane) {
    init {
        layout.children.bind(itemsHolder.observableItemsList) {
            if (it is TextItem){
                // Item --> Node
                TextItemFxml(it).root
            } else {
                throw Exception()
            }
        }
    }
}