package org.xapps.apps.mangaxdatabase.services.models

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "references")
data class Reference(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
//    @Bindable
    var title: String = "",

    @ColumnInfo(name = "synopsis")
    var synopsis: String = "",

//    @ColumnInfo(name = "date")
//    var date: Date,

    @ColumnInfo(name = "chapters")
    var chapters: Int? = null,

    @ColumnInfo(name = "evaluation")
    var evaluation: Float? = null,

    @ColumnInfo(name = "demograpy_id")
    var demographyId: Long? = null,

    @ColumnInfo(name = "type_id")
    var typeId: Long? = null,

    @ColumnInfo(name = "state_id")
    var stateId: Long? = null,

    @ColumnInfo(name = "portrait_poster_id")
    var portraitPosterId: Long? = null,

    @ColumnInfo(name = "landscape_poster_id")
    var landscapePosterId: Long? = null,

    @ColumnInfo(name = "is_stored")
    var isStored: Boolean = false,

    @ColumnInfo(name = "has_been_seen")
    var hasBeenSeen: Boolean = false,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
): BaseObservable()