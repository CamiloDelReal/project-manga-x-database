package org.xapps.apps.mangaxdatabase.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_listing.view.*
import org.xapps.apps.mangaxdatabase.R
import org.xapps.apps.mangaxdatabase.databinding.ItemListingBinding
import org.xapps.apps.mangaxdatabase.services.models.ReferenceItemListing


class ListingAdapter : RecyclerView.Adapter<ListingAdapter.ListingViewHolder>() {

    var references: ArrayList<ReferenceItemListing> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        val inflatedView: ItemListingBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_listing, parent, false)
        return ListingViewHolder(inflatedView)
    }

    fun add(references: List<ReferenceItemListing>) {
        this.references.addAll(references)
        notifyDataSetChanged()
    }

    fun replaceDevices(references: List<ReferenceItemListing>) {
        this.references.clear()
        this.references.addAll(references)
        notifyDataSetChanged()
    }

    fun clear() {
        this.references.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount() = references.size

    override fun onBindViewHolder(holder: ListingViewHolder, index: Int) {
        holder.bindInfo(references[index])
    }

    class ListingViewHolder(
        private val itemBinding: ItemListingBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private lateinit var reference: ReferenceItemListing

        init {
            itemBinding.root.btnItem.setOnClickListener {
                Log.i("AppLogger", "List item clicked")
            }
        }

        fun bindInfo(reference: ReferenceItemListing) {
            this.reference = reference
            itemBinding.referenceWrapper = reference
        }
    }
}