package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.IntegerPropertySerializer
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import kotlinx.serialization.Serializable

@Serializable
data class ImageItem (
    @Serializable(with = IntegerPropertySerializer::class)
    val width: IntegerProperty = SimpleIntegerProperty(2),

    @Serializable(with = IntegerPropertySerializer::class)
    val height: IntegerProperty = SimpleIntegerProperty(2)
)