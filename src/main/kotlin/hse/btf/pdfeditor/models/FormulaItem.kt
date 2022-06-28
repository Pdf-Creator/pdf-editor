package hse.btf.pdfeditor.models

import hse.btf.pdfeditor.serializers.StringPropertySerializer
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import kotlinx.serialization.Serializable

//@Serializable
data class FormulaItem(
    @Serializable(with = StringPropertySerializer::class)
    val formula: StringProperty = SimpleStringProperty("1")
) : Item()