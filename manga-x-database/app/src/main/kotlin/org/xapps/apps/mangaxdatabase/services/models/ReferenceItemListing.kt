package org.xapps.apps.mangaxdatabase.services.models

import com.google.android.material.textview.MaterialTextView
import androidx.databinding.BindingAdapter
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
) {


    companion object {

        @JvmStatic
        @BindingAdapter("genres")
        fun joinGenres(textView: MaterialTextView, genres: List<Genre>) {
            textView.text = genres.joinToString(separator = ", ", transform = { it.value })
        }

        @JvmStatic
        @BindingAdapter("authors")
        fun joinAuthors(textView: MaterialTextView, authors: List<Author>) {
            textView.text = authors.joinToString(separator = ", ", transform = { it.name })
        }

    }

}