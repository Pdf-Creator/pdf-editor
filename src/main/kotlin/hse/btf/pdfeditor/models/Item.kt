package hse.btf.pdfeditor.models

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty

// внешний прямоугольник, в который обрамлен контент
abstract class Item {
    /**
     * сделать размер центра изменяемым
     * подумать над минимальным размером Item'а
     * и обработать ситуации, когда размер, например, не может быть уменьшен,
     * потому что контент внутри не позволяет этого сделать
     */
    val x: SimpleIntegerProperty = SimpleIntegerProperty(centerX)
    val y: SimpleIntegerProperty = SimpleIntegerProperty(centerY)
    val w: SimpleIntegerProperty = SimpleIntegerProperty(defaultWidth)
    val h: SimpleIntegerProperty = SimpleIntegerProperty(defaultHeight)
    val color: SimpleStringProperty = SimpleStringProperty("white")

    companion object {
        // default width and height
        const val defaultWidth = 100
        const val defaultHeight = 50

        // field center
        const val centerX = 150
        const val centerY = 200
    }
}