package org.xapps.apps.mangaxdatabase.views.bindings

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
    var pendingSelection: List<Genre>? = null

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

            if (childCount > 0) {
                for (i in 0 until childCount)
                    (getChildAt(i) as Chip).isChecked = false

                newValues.forEach {
                    val chip = findViewWithTag<Chip>(it)
                    chip.isChecked = true
                }
            } else {
                pendingSelection = newValues
            }
        }

    @JvmStatic
    @BindingAdapter(value = ["entries", "checkable"], requireAll = false)
    fun setEntries(view: GenreChipGroup, entries: List<Genre>?, checkable: Boolean = false) {
        Log.i("AppLogger", "Chip entries")
        view.removeAllViews()
        entries?.let {
            val layout = if (checkable)
                R.layout.item_chip_choice
            else
                R.layout.item_chip
            entries.forEach {
                val chip = LayoutInflater.from(view.context).inflate(layout, view, false) as Chip
                chip.text = it.value
                chip.tag = it
                if(checkable) {
                    chip.setOnCheckedChangeListener { _, _ ->
                        view.listener?.apply {
                            onChange()
                        }
                    }
                    if (view.pendingSelection != null && view.pendingSelection!!.contains(it))
                        chip.isChecked = true
                }
                view.addView(chip)
            }
        }
//        view.pendingSelection = null
    }
}