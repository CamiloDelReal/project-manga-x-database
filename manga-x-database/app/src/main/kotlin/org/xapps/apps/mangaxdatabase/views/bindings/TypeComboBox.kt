package org.xapps.apps.mangaxdatabase.views.bindings

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.services.models.Type


class TypeComboBox : MaterialAutoCompleteTextView {

    var selectedItem: Type? = null

    constructor(context: Context) : this(context, null)

    constructor(arg0: Context, arg1: AttributeSet?) : super(arg0, arg1)

    constructor(arg0: Context, arg1: AttributeSet, arg2: Int) : super(arg0, arg1, arg2)

}

object TypeComboBoxBindings {

    @JvmStatic
    @BindingAdapter("valueAttrChanged")
    fun setListener(view: TypeComboBox, listener: InverseBindingListener?) {
        listener?.let {
            view.setOnItemClickListener { parent, _, position, _ ->
                view.selectedItem = parent.adapter.getItem(position) as Type
                listener.onChange()
            }
        }
    }

//    @JvmStatic
//    @BindingAdapter(value = ["value"])
//    fun setValue(view: BindingAutoCompleteTextView, type: Type) {
//        view.selectedItem = type
//        view.setText(type.toString(), false)
//    }
//
//    @JvmStatic
//    @InverseBindingAdapter(attribute = "value")
//    fun getValue(view: BindingAutoCompleteTextView): Type? {
//        return view.selectedItem
//    }

    @JvmStatic
    @get:InverseBindingAdapter(attribute = "value")
    @set:BindingAdapter("value")
    var TypeComboBox.selectedValue: Type?
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
    fun setEntries(view: TypeComboBox, entries: List<Type>?) {
        entries?.let {
            val arrayAdapter = ArrayAdapter(view.context, R.layout.item_combobox_simple, entries)
            arrayAdapter.setDropDownViewResource(R.layout.item_combobox_dropdown_simple)
            view.setAdapter(arrayAdapter)
        }
    }

}
