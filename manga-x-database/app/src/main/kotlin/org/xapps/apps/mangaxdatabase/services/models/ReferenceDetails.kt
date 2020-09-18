package org.xapps.apps.mangaxdatabase.services.models

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


data class ReferenceDetails(
    @Embedded
    val reference: Reference = Reference(),

    @Relation(
        parentColumn = "type_id",
        entityColumn = "id"
    )
    @Bindable
    var type: Type? = null,

    @Relation(
        parentColumn = "demograpy_id",
        entityColumn = "id"
    )
    @Bindable
    var demography: Demography? = null,

    @Relation(
        parentColumn = "state_id",
        entityColumn = "id"
    )
    @Bindable
    var state: State? = null,

    @Relation(
        parentColumn = "reference_id",
        entityColumn = "genre_id",
        associateBy = Junction(ReferenceGenre::class)
    )
    @Bindable
    var genres: List<Genre>? = null,

    @Relation(
        parentColumn = "reference_id",
        entityColumn = "author_id",
        associateBy = Junction(ReferenceAuthor::class)
    )
    var authors: List<Author>? = null,

    @Relation(
        parentColumn = "portrait_poster_id",
        entityColumn = "id"
    )
    var portraitPoster: PortraitPoster? = null,

    @Relation(
        parentColumn = "landscape_poster_id",
        entityColumn = "id"
    )
    var landscapePoster: LandscapePoster? = null
): BaseObservable()