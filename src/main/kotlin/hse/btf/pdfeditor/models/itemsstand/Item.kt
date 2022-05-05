package hse.btf.pdfeditor.models.itemsstand

import hse.btf.pdfeditor.models.serializers.IntegerPropertySerializer
import hse.btf.pdfeditor.models.serializers.StringPropertySerializer
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import kotlinx.serialization.Serializable

// внешний прямоугольник, в который обрамлен контент
@Serializable
sealed class Item {
    @Serializable(with = IntegerPropertySerializer::class)
    val x: IntegerProperty = SimpleIntegerProperty(150)

    @Serializable(with = IntegerPropertySerializer::class)
    val y: IntegerProperty = SimpleIntegerProperty(200)

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