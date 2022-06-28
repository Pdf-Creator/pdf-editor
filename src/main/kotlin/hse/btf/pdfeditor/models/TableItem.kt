package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.IntegerPropertySerializer
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import kotlinx.serialization.Serializable

@Serializable
data class TableItem (
    @Serializable(with = IntegerPropertySerializer::class)
    val rows: IntegerProperty = SimpleIntegerProperty(2),

    @Serializable(with = IntegerPropertySerializer::class)
    val cols: IntegerProperty = SimpleIntegerProperty(2)
)