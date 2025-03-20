package com.krishna.minebmi;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.krishna.minebmi.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    String sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.maleButton.setOnClickListener(v -> {
            binding.femaleButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.unselected)));
            binding.maleButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.selected)));
            sex = "Male";
        });

        binding.femaleButton.setOnClickListener(v -> {
            binding.maleButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.unselected)));
            binding.femaleButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.selected)));
            sex = "Female";
        });


        binding.calculateButton.setOnClickListener(v -> {
            if(!binding.ageInput.getText().toString().isEmpty() && !Objects.equals(sex, "") && !binding.weightInput.getText().toString().isEmpty() && !binding.heightInput.getText().toString().isEmpty()){
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("sex", sex);
                intent.putExtra("age", binding.ageInput.getText().toString());
                intent.putExtra("weight", binding.weightInput.getText().toString());
                intent.putExtra("height", binding.heightInput.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please giving the information completely", Toast.LENGTH_SHORT).show();
            }
        });
    }
}