package org.xapps.apps.mangaxdatabase.services.models

import androidx.room.Embedded
import androidx.room.Relation


data class ReferenceItemSummary (
    @Embedded
    val reference: Reference,

    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val type: Type
)