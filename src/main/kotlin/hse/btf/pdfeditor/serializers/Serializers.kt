package hse.btf.pdfeditor.serializers

import javafx.beans.property.DoubleProperty
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StringPropertySerializer : KSerializer<StringProperty> {
    private val delegateSerializer = String.serializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: StringProperty) {
        encoder.encodeSerializableValue(delegateSerializer, value.get())
    }

    override fun deserialize(decoder: Decoder): StringProperty {
        val stringData = decoder.decodeSerializableValue(delegateSerializer)
        return SimpleStringProperty(stringData)
    }
}

object IntegerPropertySerializer : KSerializer<IntegerProperty> {
    private val delegateSerializer = Int.serializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: IntegerProperty) {
        encoder.encodeSerializableValue(delegateSerializer, value.get())
    }

    override fun deserialize(decoder: Decoder): IntegerProperty {
        val intData = decoder.decodeSerializableValue(delegateSerializer)
        return SimpleIntegerProperty(intData)
    }
}

object DoublePropertySerializer : KSerializer<DoubleProperty> {
    private val delegateSerializer = Double.serializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor = delegateSerializer.descriptor

    override fun serialize(encoder: Encoder, value: DoubleProperty) {
        encoder.encodeSerializableValue(delegateSerializer, value.get())
    }

    override fun deserialize(decoder: Decoder): DoubleProperty {
        val doubleData = decoder.decodeSerializableValue(delegateSerializer)
        return SimpleDoubleProperty(doubleData)
    }
}