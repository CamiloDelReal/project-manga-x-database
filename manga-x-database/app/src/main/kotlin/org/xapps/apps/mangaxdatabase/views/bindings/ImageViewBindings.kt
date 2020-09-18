package org.xapps.apps.mangaxdatabase.views.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import org.xapps.apps.mangaxdatabase.R


object ImageViewBindings {

    @JvmStatic
    @BindingAdapter("portraitPosterId")
    fun loadPortraitPoster(imageView: ImageView, portraitPosterId: Long) {
        Picasso.get().load(R.mipmap.ffff).into(imageView)
    }

}