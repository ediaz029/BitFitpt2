package com.example.bitfit_pt1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit_pt1.databinding.ActivityMainBinding
import com.example.bitfit_pt1.MealDao
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow




class MainActivity : AppCompatActivity() {
    private lateinit var mealsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val meals = mutableListOf<MealEntity>()
    private lateinit var meal: MealEntity
    private lateinit var addMealButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mealsRecyclerView = findViewById(R.id.meals)

        val mealAdapter = MealAdapter(this, meals)
        mealsRecyclerView.adapter = mealAdapter

        mealsRecyclerView.layoutManager = LinearLayoutManager(this)

        val database = AppDatabase.getInstance(this)

        lifecycleScope.launch {
            val mealList = database.mealDao().getAll().collect {mealList ->


                    meals.clear()
                    meals.addAll(mealList)

                    mealAdapter.notifyDataSetChanged()


                }
            }



        val intent = Intent(this, DetailActivity::class.java)

        val addMealButton = findViewById<Button>(R.id.addMealButton)

        addMealButton.setOnClickListener {
            startActivity(intent)
        }


    }
}