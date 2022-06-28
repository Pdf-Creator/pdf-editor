package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.DoublePropertySerializer
import hse.btf.pdfeditor.serializers.IntegerPropertySerializer
import javafx.beans.property.DoubleProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import kotlinx.serialization.Serializable

@Serializable
sealed class Item {
    @Serializable(with = DoublePropertySerializer::class)
    val x: DoubleProperty = SimpleDoubleProperty(150.0)

    @Serializable(with = DoublePropertySerializer::class)
    val y: DoubleProperty = SimpleDoubleProperty(200.0)

    @Serializable(with = IntegerPropertySerializer::class)
    val w: IntegerProperty = SimpleIntegerProperty(100)

    @Serializable(with = IntegerPropertySerializer::class)
    val h: IntegerProperty = SimpleIntegerProperty(50)
}