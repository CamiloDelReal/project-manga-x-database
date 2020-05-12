package org.xapps.apps.mangaxdatabase.views.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.models.Genre


class GenreChipGroup : ChipGroup {

    var listener: InverseBindingListener? = null

    constructor(context: Context) : this(context, null)

    constructor(arg0: Context, arg1: AttributeSet?) : super(arg0, arg1)

    constructor(arg0: Context, arg1: AttributeSet, arg2: Int) : super(arg0, arg1, arg2)

}

object GenreChipGroupBindings {

    @JvmStatic
    @BindingAdapter("valuesAttrChanged")
    fun setListener(view: GenreChipGroup, listener: InverseBindingListener?) {
        view.listener = listener
    }

    @JvmStatic
    @get:InverseBindingAdapter(attribute = "values")
    @set:BindingAdapter("values")
    var GenreChipGroup.selectecdValues: List<Genre>?
        get() {
            val items = ArrayList<Genre>()
            for (i in 0 until childCount) {
                val chip = getChildAt(i) as Chip
                if (chip.isChecked)
                    items.add(chip.tag as Genre)
            }
            return items
        }
        set(values) {
            val newValues = values ?: ArrayList()
            for (i in 0 until childCount)
                (getChildAt(i) as Chip).isChecked = false
            newValues.forEach {
                val chip = findViewWithTag<Chip>(it)
                chip.isChecked = true
            }
        }

    @JvmStatic
    @BindingAdapter("entries")
    fun setEntries(view: GenreChipGroup, entries: List<Genre>?) {
        entries?.let {
            entries.forEach {
                val chip = LayoutInflater.from(view.context)
                    .inflate(R.layout.item_chip_choice, view, false) as Chip
                chip.text = it.value
                chip.tag = it
                chip.setOnCheckedChangeListener { _, _ ->
                    view.listener?.apply {
                        onChange()
                    }
                }
                view.addView(chip)
            }
        }
    }
}