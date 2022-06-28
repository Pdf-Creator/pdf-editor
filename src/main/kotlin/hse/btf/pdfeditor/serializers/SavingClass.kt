package hse.btf.pdfeditor.serializers


import hse.btf.pdfeditor.storages.ProjectDataStorage.itemsHolder
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

private val format = Json { encodeDefaults = true }

@OptIn(ExperimentalSerializationApi::class)
fun saveToFile(fileName: String) {
    // opening vs creating file and (over)writing information to it
    var filePath = "./src/main/kotlin/hse/btf/pdfeditor/datasaving/"

    val fileNameWithoutExtension = fileName.split(".").toTypedArray()
    val fileNameWithSliders = fileNameWithoutExtension[fileNameWithoutExtension.size - 2]
    val fileNameWords = fileNameWithSliders.split("/")
    val fileNameSimple = fileNameWords[fileNameWords.size - 1]

    filePath += if (fileNameSimple != "") "$fileNameSimple.json" else "dataFile.json"
    val file = File(filePath)

    // getting Json string
    val json = format.encodeToString(itemsHolder.observableItemsList.toList())
    file.writeText(json)
}

fun readFromFile(fileName: String) : Boolean {
    var filePath = "./src/main/kotlin/hse/btf/pdfeditor/datasaving/"
    filePath += if (fileName != "") "$fileName.json" else "dataFile.json"
    val file = File(filePath)

    if (!file.exists()) {
        return false
    }

    val fileData = file.readText()
    itemsHolder.updateFromFileData(fileData)
    return true
}

@OptIn(ExperimentalSerializationApi::class)
fun serializeProjectsNames(projectNamesList: List<String>) {
    val jsonFile = File("./src/main/resources/users.projects/projectNamesList.json")

    val json = format.encodeToString(projectNamesList)
    jsonFile.writeText(json)
}

@OptIn(ExperimentalSerializationApi::class)
fun deserializeProjectsNames(projectNamesList: MutableList<String>) {
    val jsonFile = File("./src/main/resources/users.projects/projectNamesList.json")

    val fileData = jsonFile.readText()
    val namesList = try {
        Json.decodeFromString<List<String>>(fileData)
    } catch (e: Throwable) {
        listOf()
    }

    projectNamesList.clear()
    projectNamesList.addAll(namesList)
}