package com.example.sportnews

import android.content.Context
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sportnews.SportNewsAdapter.MyViewHolder
import com.example.sportnews.model.SportNewsModel
import com.squareup.picasso.Picasso

class SportNewsAdapter(private val headLinesList: ArrayList<SportNewsModel>,
                       private val context: Context,
                       private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
        val rootView = getLayoutInflater(viewGroup.context).inflate(R.layout.sport_headline_row, viewGroup, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
       return headLinesList.size
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val sportHeadline = headLinesList[position]

        val headlineTextView = viewHolder.headlineTextView
        val categoryTextView = viewHolder.categoryTextView
        val dateTextView = viewHolder.dateTextView
        val blurbTextView = viewHolder.blurbTextView
        val imageView = viewHolder.imageView

        headlineTextView.text = headLinesList[position].Headline
        categoryTextView.text = headLinesList[position].Category
        dateTextView.text = headLinesList[position].DateCreated
        blurbTextView.text = headLinesList[position].Blurb


        Picasso.with(context)
            .load(headLinesList[position].ImageUrlLocal + headLinesList[position].SmallImageName)
            .into(viewHolder.imageView)


        viewHolder.itemView.setOnClickListener { itemClickListener.onItemClicked(sportHeadline) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(model: SportNewsModel)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var headlineTextView: TextView = itemView.findViewById(R.id.headline)
        internal var categoryTextView: TextView = itemView.findViewById(R.id.category)
        internal var dateTextView: TextView = itemView.findViewById(R.id.date)
        internal var blurbTextView: TextView = itemView.findViewById(R.id.blurb_text)
        internal var imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}