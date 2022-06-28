package hse.btf.pdfeditor.serializers


import hse.btf.pdfeditor.storages.ProjectDataStorage.itemsHolder
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

private val format = Json { encodeDefaults = true }

@OptIn(ExperimentalSerializationApi::class)
fun saveToFile(fileName: String) {
    // opening vs creating file and (over)writing information to it
    // test.pdf -> test.json
    var splitedFile = fileName.split(".").toTypedArray()
    var fileDir = Path.of("src","main", "kotlin", "hse", "btf", "pdfeditor", "datasaving")
    if (Files.notExists(fileDir)) {
        Files.createDirectories(fileDir)
    }
    var filePath = Path.of(fileDir.toString(), splitedFile.get(0) + ".json")
    if (Files.notExists(filePath)) {
        Files.createFile(filePath)
    }
    val file = filePath.toAbsolutePath().toFile()
    // getting Json string
    System.out.println(filePath)
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