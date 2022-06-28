package hse.btf.pdfeditor

import hse.btf.pdfeditor.models.FormulaItem
import hse.btf.pdfeditor.models.ImageItem
import hse.btf.pdfeditor.models.TextItem
import hse.btf.pdfeditor.models.entities.FormulaEntity
import hse.btf.pdfeditor.models.entities.ImageEntity
import hse.btf.pdfeditor.models.entities.TextEntity
import hse.btf.pdfeditor.storages.EntitiesStorage.entitiesList
import hse.btf.pdfeditor.storages.ProjectDataStorage.itemsHolder
import tornadofx.bind

class EntityToItemConverter {
    init {
        itemsHolder.observableItemsList.bind(entitiesList) {
            when (it) {
                is TextEntity ->
                    TextItem().apply {
                        x.bindBidirectional(it.xProperty)
                        y.bindBidirectional(it.yProperty)
                        w.bindBidirectional(it.widthProperty)
                        h.bindBidirectional(it.heightProperty)

                        text.bindBidirectional(it.text)
//                        font.bindBidirectional(it.font)
//                        background.bindBidirectional(it.background)
//                        border.bindBidirectional(it.border)

                    }
                is ImageEntity ->
                    ImageItem().apply {
                        x.bindBidirectional(it.xProperty)
                        y.bindBidirectional(it.yProperty)
                        w.bindBidirectional(it.widthProperty)
                        h.bindBidirectional(it.heightProperty)

                        imageFileName = it.fileName
                    }
                is FormulaEntity ->
                    FormulaItem().apply {
                        x.bindBidirectional(it.xProperty)
                        y.bindBidirectional(it.yProperty)
                        w.bindBidirectional(it.widthProperty)
                        h.bindBidirectional(it.heightProperty)

                        formula.bindBidirectional(it.formulaProperty)
                    }
                else -> {
                    println("Else branch converter")
                    null
                }
            }
        }
    }
}