package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.StringPropertySerializer
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.layout.Background
import javafx.scene.layout.Border
import javafx.scene.text.Font
import kotlinx.serialization.Serializable

//@Serializable
data class TextItem(
    @Serializable(with = StringPropertySerializer::class)
    val text: StringProperty = SimpleStringProperty("type text here"),

    val font: ObjectProperty<Font> = SimpleObjectProperty(),
    val background: ObjectProperty<Background> = SimpleObjectProperty(),
    val border: ObjectProperty<Border> = SimpleObjectProperty()

//    @Serializable(with = IntegerPropertySerializer::class)
//    val fontSize: IntegerProperty = SimpleIntegerProperty(13),
//
//    @Serializable(with = StringPropertySerializer::class)
//    val style: StringProperty = SimpleStringProperty("type text here"),
) : Item()