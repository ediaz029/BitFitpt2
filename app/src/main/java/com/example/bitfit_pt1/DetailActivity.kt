package com.example.bitfit_pt1;

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var enterFoodET: EditText
    private lateinit var enterCalET: EditText
    private lateinit var recordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        enterFoodET = findViewById(R.id.enterFood)
        enterCalET = findViewById(R.id.enterCal)
        recordButton = findViewById(R.id.recordButton)

        recordButton.setOnClickListener {
            val food = enterFoodET.text.toString()
            val calories = enterCalET.text.toString()

            if (food.isNotEmpty() && calories.isNotEmpty()) {

                val mealEntity = MealEntity(name = food, calories = calories)

                lifecycleScope.launch(IO) {
                    // Get a reference to your Room database
                    val mealDao = (application as MealApplication).db.mealDao()

                    // Insert the single MealEntity item
                    mealDao.insert(mealEntity)
                }
            }
        }
    }
}






