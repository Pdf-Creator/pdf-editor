package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.DoublePropertySerializer
import hse.btf.pdfeditor.serializers.IntegerPropertySerializer
import hse.btf.pdfeditor.serializers.StringPropertySerializer
import javafx.beans.property.DoubleProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
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

    @Serializable(with = StringPropertySerializer::class)
    val color: StringProperty = SimpleStringProperty("white")

    /**
     * сделать размер центра изменяемым
     * подумать над минимальным размером Item'а
     * и обработать ситуации, когда размер, например, не может быть уменьшен,
     * потому что контент внутри не позволяет этого сделать
     */
}