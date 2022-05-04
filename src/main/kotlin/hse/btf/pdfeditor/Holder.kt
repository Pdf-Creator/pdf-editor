package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.itemsstand.Item
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import kotlinx.serialization.Serializable

@Serializable
data class Holder(val observableItemsList: ObservableList<Item> = FXCollections.observableArrayList())