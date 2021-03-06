package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.Item
import hse.btf.pdfeditor.storages.ProjectDataStorage
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

data class Holder(val observableItemsList: ObservableList<Item> = FXCollections.observableArrayList()) {
    fun updateFromFileData(fileData: String) {
        val itemsList = Json.decodeFromString<List<Item>>(fileData)

        // updating data
        ProjectDataStorage.itemsHolder.observableItemsList.clear()
        ProjectDataStorage.itemsHolder.observableItemsList.addAll(itemsList)
    }
}