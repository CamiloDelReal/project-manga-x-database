package org.xapps.apps.mangaxdatabase.views.bindings

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.xapps.apps.mangaxdatabase.services.models.Author
import org.xapps.apps.mangaxdatabase.services.models.Genre


object TextViewBindings {

    @JvmStatic
    @BindingAdapter("genres")
    fun joinGenres(textView: TextView, genres: List<Genre>) {
        textView.text = genres.joinToString(separator = ", ", transform = { it.value })
    }

    @JvmStatic
    @BindingAdapter("authors")
    fun joinAuthors(textView: TextView, authors: List<Author>) {
        textView.text = authors.joinToString(separator = ", ", transform = { it.name })
    }

}