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
abstract class Item {
    @Serializable(with = IntegerPropertySerializer::class)
    val x: IntegerProperty = SimpleIntegerProperty(centerX)

    @Serializable(with = IntegerPropertySerializer::class)
    val y: IntegerProperty = SimpleIntegerProperty(centerY)

    @Serializable(with = IntegerPropertySerializer::class)
    val w: IntegerProperty = SimpleIntegerProperty(defaultWidth)

    @Serializable(with = IntegerPropertySerializer::class)
    val h: IntegerProperty = SimpleIntegerProperty(defaultHeight)

    @Serializable(with = StringPropertySerializer::class)
    val color: StringProperty = SimpleStringProperty("white")

    companion object {
        // default width and height
        const val defaultWidth = 100
        const val defaultHeight = 50

        // field center
        const val centerX = 150
        const val centerY = 200
    }

    /**
     * сделать размер центра изменяемым
     * подумать над минимальным размером Item'а
     * и обработать ситуации, когда размер, например, не может быть уменьшен,
     * потому что контент внутри не позволяет этого сделать
     */
}