package org.xapps.apps.mangaxdatabase.views.bindings

import android.annotation.SuppressLint
import android.view.View
import androidx.databinding.BindingAdapter
import me.zhanghai.android.materialratingbar.MaterialRatingBar


object MaterialRatingBarBindings {

    @SuppressLint("ClickableViewAccessibility")
    @JvmStatic
    @BindingAdapter("onFocusChanged")
    fun setListener(view: MaterialRatingBar, listener: View.OnTouchListener?) {
        view.setOnTouchListener(listener)
    }

}