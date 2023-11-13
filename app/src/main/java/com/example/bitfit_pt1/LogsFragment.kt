package com.example.bitfit_pt1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class LogsFragment : Fragment() {
    private val meals = mutableListOf<MealEntity>()
    private lateinit var mealsRecyclerView: RecyclerView
    private lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logs, container, false)

        val layoutManager = LinearLayoutManager(requireContext())
        mealsRecyclerView = view.findViewById(R.id.meals_recyclerview)
        mealsRecyclerView.layoutManager = layoutManager
        mealsRecyclerView.setHasFixedSize(true)
        mealAdapter = MealAdapter(requireContext(), meals)
        mealsRecyclerView.adapter = mealAdapter

        val database = AppDatabase.getInstance(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            database.mealDao().getAll().collect { mealList ->
                meals.clear()
                meals.addAll(mealList)
                mealAdapter.notifyDataSetChanged()
            }
        }

        val intent = Intent(requireActivity(), DetailActivity::class.java)

        val addMealButton = view.findViewById<Button>(R.id.addMealButton)

        addMealButton.setOnClickListener {
            startActivity(intent)
        }

        return view
    }
}
