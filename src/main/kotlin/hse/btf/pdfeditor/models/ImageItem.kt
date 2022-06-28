package hse.btf.pdfeditor.models

import kotlinx.serialization.Serializable

@Serializable
data class ImageItem(
    var imageFileName: String = ""
) : Item()