package org.xapps.apps.mangaxdatabase.services.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class ReferenceItemListing (
    @Embedded
    val reference: Reference,

    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    val type: Type,

    @Relation(
        parentColumn = "demograpy_id",
        entityColumn = "id"
    )
    val demography: Demography,

    @Relation(
        parentColumn = "reference_id",
        entityColumn = "genre_id",
        associateBy = Junction(ReferenceGenre::class)
    )
    val genres: List<Genre>,

    @Relation(
        parentColumn = "reference_id",
        entityColumn = "author_id",
        associateBy = Junction(ReferenceAuthor::class)
    )
    val authors: List<Author>
)