package com.kemalurekli.cryptoprices.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kemalurekli.cryptoprices.R
import com.kemalurekli.cryptoprices.databinding.RowLayoutBinding
import com.kemalurekli.cryptoprices.model.PricesModel


class RecyclerViewAdapter (val priceList: ArrayList<PricesModel>)
    :RecyclerView.Adapter<RecyclerViewAdapter.RowLayoutDesign>() {

    class RowLayoutDesign(val rowLayoutBinding: RowLayoutBinding)
        :RecyclerView.ViewHolder(rowLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowLayoutDesign {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowLayoutBinding = RowLayoutBinding.inflate(layoutInflater, parent, false)
        return RowLayoutDesign(rowLayoutBinding)
    }

    override fun onBindViewHolder(holder: RowLayoutDesign, position: Int) {
        val crypto = priceList[position]

        holder.rowLayoutBinding.apply {
            tvCurrency.text = crypto.currency
            tvPrice.text = crypto.price
            //Picasso.get().load(kitap.book_image_url).into(kitapImageView)
        }
    }

    override fun getItemCount(): Int {
        return priceList.size
    }


    fun updatePriceList(newCountryList: List<PricesModel>) {
        priceList.clear()
        priceList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}