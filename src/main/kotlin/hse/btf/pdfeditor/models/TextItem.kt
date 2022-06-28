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

//    val font: ObjectProperty<Font> = SimpleObjectProperty(),
//    val background: ObjectProperty<Background> = SimpleObjectProperty(),
//    val border: ObjectProperty<Border> = SimpleObjectProperty()

    @Serializable(with = StringPropertySerializer::class)
    val fontFamily: StringProperty = SimpleStringProperty(""),

    @Serializable(with = StringPropertySerializer::class)
    val fontName: StringProperty = SimpleStringProperty("Arial"),

    @Serializable(with = IntegerPropertySerializer::class)
    val fontSize: IntegerProperty = SimpleIntegerProperty(13),

    @Serializable(with = StringPropertySerializer::class)
    val fontStyle: StringProperty = SimpleStringProperty("")
) : Item()