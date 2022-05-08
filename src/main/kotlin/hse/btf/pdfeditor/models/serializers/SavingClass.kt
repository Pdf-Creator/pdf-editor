package hse.btf.pdfeditor.models.serializers

import hse.btf.pdfeditor.Holder
import hse.btf.pdfeditor.Singleton.itemsHolder
import hse.btf.pdfeditor.models.itemsstand.Item
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private val format = Json { encodeDefaults = true }

fun saveToFile(fileName: String?): Unit {
    val json = format.encodeToString(itemsHolder.observableItemsList.toList())
    val filePath = fileName ?: ("./src/main/kotlin/hse/btf/pdfeditor/datasaving" + "dataFile.json")

    // opening vs creating file and (over)writing information to it
    val file = File(filePath)
    file.writeText(json)
}

fun readFromFile(fileName: String?): Unit {
    val filePath = fileName ?: ("./src/main/kotlin/hse/btf/pdfeditor/datasaving" + "dataFile.json")
    val file = File(filePath)

    val fileData = file.readText()
    val holder = Json.decodeFromString<List<Item>>(fileData)

    // updating data
    itemsHolder.observableItemsList.clear()
    itemsHolder.observableItemsList.addAll(holder)
}