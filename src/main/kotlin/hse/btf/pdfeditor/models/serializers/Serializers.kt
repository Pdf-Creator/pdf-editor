package hse.btf.pdfeditor.models.serializers

import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.CharArraySerializer
import kotlinx.serialization.builtins.IntArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object StringPropertySerializer : KSerializer<StringProperty> {
    private val delegateSerializer = CharArraySerializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor = SerialDescriptor("String", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: StringProperty) {
        val stringData = value.get().toCharArray()
        encoder.encodeSerializableValue(delegateSerializer, stringData)
    }

    override fun deserialize(decoder: Decoder): StringProperty {
        val stringData = decoder.decodeSerializableValue(delegateSerializer)
        return SimpleStringProperty(String(stringData))
    }
}

object IntegerPropertySerializer : KSerializer<IntegerProperty> {
    private val delegateSerializer = IntArraySerializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor = SerialDescriptor("Int", delegateSerializer.descriptor)

    override fun serialize(encoder: Encoder, value: IntegerProperty) {
        val intData = IntArray(value.intValue())
        encoder.encodeSerializableValue(delegateSerializer, intData)
    }

    override fun deserialize(decoder: Decoder): IntegerProperty {
        val intData = decoder.decodeSerializableValue(delegateSerializer)
        return SimpleIntegerProperty(intData[0])
    }
}