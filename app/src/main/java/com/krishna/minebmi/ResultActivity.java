package com.krishna.minebmi;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.krishna.minebmi.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String sex = getIntent().getStringExtra("sex");
        String age = getIntent().getStringExtra("age");
        String weight = getIntent().getStringExtra("weight");
        String height = getIntent().getStringExtra("height");


        float finalWeight = Float.parseFloat(weight);
        float finalHeight = Float.parseFloat(height);

        float bmi = calculateBMI(finalWeight, finalHeight);
        String txt = category(bmi);


        /// display the text on the respected ids
        binding.txtScore.setText(String.format("%.2f", bmi));

        binding.category.setText(txt);

        binding.overviewTxt.setText(sex);
        binding.ageTxt.setText(age);
        binding.weightTxt.setText(weight);
        binding.heightTxt.setText(height);


    }


    private float calculateBMI(float weight, float height){
        float bmi = 0;
        float heightConverter = height/100;
        float finalHeight = heightConverter*heightConverter;
        bmi = weight/finalHeight;
        return bmi;
    }

    private String category(float bmi){
        String yourCategory = "";

        if(bmi < 18.5){
            yourCategory = "Underweight";
            binding.txtScore.setTextColor(ContextCompat.getColor(this, R.color.less));
            binding.category.setTextColor(ContextCompat.getColor(this, R.color.less));
        } else if(bmi >= 18.5 && bmi < 25){
            yourCategory = "Normal Weight";
            binding.txtScore.setTextColor(ContextCompat.getColor(this, R.color.normal));
            binding.category.setTextColor(ContextCompat.getColor(this, R.color.normal));
        } else if(bmi >= 25 && bmi < 30){
            yourCategory = "Overweight";
            binding.txtScore.setTextColor(ContextCompat.getColor(this, R.color.medium));
            binding.category.setTextColor(ContextCompat.getColor(this, R.color.medium));
        } else if(bmi >= 30){
            yourCategory = "Obesity";
            binding.txtScore.setTextColor(ContextCompat.getColor(this, R.color.high));
            binding.category.setTextColor(ContextCompat.getColor(this, R.color.high));
        }

        return yourCategory;
    }
}