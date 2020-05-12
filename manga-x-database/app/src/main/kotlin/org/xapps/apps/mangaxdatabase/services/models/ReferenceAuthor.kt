package org.xapps.apps.mangaxdatabase.services.models

import androidx.room.*


@Entity(
    tableName = "references_authors",
    primaryKeys = ["reference_id", "author_id"],
    indices = [Index("reference_id"), Index("author_id")]
)
data class ReferenceAuthor(
    @ColumnInfo(name = "reference_id")
    @ForeignKey(
        entity = Reference::class,
        parentColumns = ["id"],
        childColumns = ["reference_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val referenceId: Long,

    @ColumnInfo(name = "author_id")
    @ForeignKey(
        entity = Author::class,
        parentColumns = ["id"],
        childColumns = ["author_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )
    val authorId: Long
)