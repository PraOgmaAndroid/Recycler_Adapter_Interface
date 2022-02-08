package com.ogmaconceptions.recycler_adapter_interface.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ogmaconceptions.recycler_adapter_interface.databinding.ListingLayoutBinding
import com.ogmaconceptions.recycler_adapter_interface.interfaces.CountHandle

class ListingAdapter(counterHandel: CountHandle): RecyclerView.Adapter<ListingAdapter.ListingHolder>() {

    private val textList = ArrayList<String>()

    private var counterHandler: CountHandle = counterHandel

    inner class ListingHolder(var listBinding: ListingLayoutBinding): RecyclerView.ViewHolder(listBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingHolder {
        return ListingHolder(ListingLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListingHolder, position: Int) {
        with(holder){
            with(listBinding){
                tvText.text = textList[position]

                imgDelete.setOnClickListener {
                    try{
                        removeItem(textList.indexOf(textList[adapterPosition]))
                    }catch (excp: ArrayIndexOutOfBoundsException){
                        Log.e("Exception","$excp")
                    }

                }

            }
        }
    }

    override fun getItemCount(): Int {
        return textList.size
    }

    fun addItem(item: String) {
        textList.add(item)
        //Log.e("PRint","${textList.size}")
        counterHandler.displayTotalCount(textList.size)
        notifyItemInserted(textList.size)
    }

    fun removeItem(position: Int) {
        textList.removeAt(position)
        //Log.e("AfterRemoveTotal","${textList.size}")
        counterHandler.displayTotalCount(textList.size)
        notifyItemRemoved(position)
    }

}