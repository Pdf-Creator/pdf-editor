package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.itemsstand.Item
import javafx.collections.FXCollections
import javafx.collections.ObservableList

data class Holder(val observableItemsList: ObservableList<Item> = FXCollections.observableArrayList()) {
    fun replaceData(holder: Holder) {
        // clear old elems
        observableItemsList.clear()

        // adding new
        observableItemsList.addAll(holder.observableItemsList)
    }
}