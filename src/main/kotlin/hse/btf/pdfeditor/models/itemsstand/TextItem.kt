package hse.btf.pdfeditor.models.itemsstand

import hse.btf.pdfeditor.models.serializers.IntegerPropertySerializer
import hse.btf.pdfeditor.models.serializers.StringPropertySerializer
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import kotlinx.serialization.Serializable

@Serializable
data class TextItem(
    @Serializable(with = StringPropertySerializer::class)
    val text: StringProperty = SimpleStringProperty("type text here"),

    @Serializable(with = IntegerPropertySerializer::class)
    val fontSize: IntegerProperty = SimpleIntegerProperty(13)
) : Item()