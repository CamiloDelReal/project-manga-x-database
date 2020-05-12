package org.xapps.apps.mangaxdatabase.services.models

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.picasso.Picasso
import org.xapps.apps.mangaxdatabase.R


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
    var chapters: Int = 0,

    @ColumnInfo(name = "evaluation")
    var evaluation: Float = 0.0f,

    @ColumnInfo(name = "demograpy_id")
    var demographyId: Long = 0,

    @ColumnInfo(name = "type_id")
    var typeId: Long = 0,

    @ColumnInfo(name = "state_id")
    var stateId: Long = 0,

    @ColumnInfo(name = "portrait_poster_id")
    var portraitPosterId: Long = 0,

    @ColumnInfo(name = "landscape_poster_id")
    var landscapePosterId: Long = 0,

    @ColumnInfo(name = "is_stored")
    var isStored: Boolean = false,

    @ColumnInfo(name = "has_been_seen")
    var hasBeenSeen: Boolean = false,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false
): BaseObservable() {

    companion object {

        @JvmStatic
        @BindingAdapter("portraitPosterId")
        fun loadPortraitPoster(imageView: AppCompatImageView, portraitPosterId: Long) {
            Picasso.get().load(R.mipmap.ffff).into(imageView)
        }

    }
}