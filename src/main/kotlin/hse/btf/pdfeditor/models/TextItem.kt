package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.IntegerPropertySerializer
import hse.btf.pdfeditor.serializers.StringPropertySerializer
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
    val fontSize: IntegerProperty = SimpleIntegerProperty(13),

    @Serializable(with = StringPropertySerializer::class)
    val style: StringProperty = SimpleStringProperty("type text here"),

    //val font: Font = Font()
) : Item()