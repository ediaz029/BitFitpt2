package com.example.bitfit_pt1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ArticleAdapter"

class MealAdapter(private val context: Context, private val meals: List<MealEntity>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_meal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val nameTextView = itemView.findViewById<TextView>(R.id.nameTV)
        private val calTextView = itemView.findViewById<TextView>(R.id.calTV)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(meal: MealEntity) {
            nameTextView.text = meal.name
            calTextView.text = meal.calories
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }
    }
    }