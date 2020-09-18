package org.xapps.apps.mangaxdatabase.views.bindings

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.models.Demography


class DemographyComboBox : MaterialAutoCompleteTextView {

    var selectedItem: Demography? = null

    constructor(context: Context) : this(context, null)

    constructor(arg0: Context, arg1: AttributeSet?) : super(arg0, arg1)

    constructor(arg0: Context, arg1: AttributeSet, arg2: Int) : super(arg0, arg1, arg2)

}

object DemographyComboBoxBindings {

    @JvmStatic
    @BindingAdapter("valueAttrChanged")
    fun setListener(view: DemographyComboBox, listener: InverseBindingListener?) {
        listener?.let {
            view.setOnItemClickListener { parent, _, position, _ ->
                view.selectedItem = parent.adapter.getItem(position) as Demography
                listener.onChange()
            }
        }
    }

//    @JvmStatic
//    @BindingAdapter(value = ["value"])
//    fun setValue(view: BindingAutoCompleteTextView, demography: Demography) {
//        view.selectedItem = demography
//        view.setText(demography.toString(), false)
//    }
//
//    @JvmStatic
//    @InverseBindingAdapter(attribute = "value")
//    fun getValue(view: BindingAutoCompleteTextView): Demography? {
//        return view.selectedItem
//    }

    @JvmStatic
    @get:InverseBindingAdapter(attribute = "value")
    @set:BindingAdapter("value")
    var DemographyComboBox.selectedValue: Demography?
        get() {
            return selectedItem
        }
        set(value) {
            selectedItem = value
            value?.let {
                setText(value.toString(), false)
            }
        }

    @JvmStatic
    @BindingAdapter("entries")
    fun setEntries(view: DemographyComboBox, entries: List<Demography>?) {
        entries?.let {
            val arrayAdapter = ArrayAdapter(view.context, R.layout.item_combobox_simple, entries)
            arrayAdapter.setDropDownViewResource(R.layout.item_combobox_dropdown_simple)
            view.setAdapter(arrayAdapter)
        }
    }

}
