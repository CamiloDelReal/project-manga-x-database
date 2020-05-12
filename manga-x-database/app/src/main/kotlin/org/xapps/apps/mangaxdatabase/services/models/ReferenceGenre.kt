package org.xapps.apps.mangaxdatabase.services.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    tableName = "references_genres",
    primaryKeys = ["reference_id", "genre_id"],
    indices = [Index("reference_id"), Index("genre_id")]
)
data class ReferenceGenre(
    @ColumnInfo(name = "reference_id")
    @ForeignKey(
        entity = Reference::class,
        parentColumns = ["id"],
        childColumns = ["reference_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val referenceId: Long,

    @ColumnInfo(name = "genre_id")
    @ForeignKey(
        entity = Genre::class,
        parentColumns = ["id"],
        childColumns = ["genre_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val genreId: Long
)