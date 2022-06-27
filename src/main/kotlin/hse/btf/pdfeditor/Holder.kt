package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.Item
import hse.btf.pdfeditor.models.PaperEntity
import hse.btf.pdfeditor.utils.DataStorage
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

data class Holder(val observableItemsList: ObservableList<Item> = FXCollections.observableArrayList()) {
    fun updateFromFileData(fileData: String) {
        val itemsList = Json.decodeFromString<List<Item>>(fileData)

        // updating data
        DataStorage.itemsHolder.observableItemsList.clear()
        DataStorage.itemsHolder.observableItemsList.addAll(itemsList)
    }
}