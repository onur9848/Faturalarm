package com.senerunosoft.faturalarm.views;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.senerunosoft.faturalarm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;

        binding.faturaHesaplaButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, FaturaActivity.class);
            startActivity(intent);
        });

        binding.requestListButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, RequestListMainActivity.class);
            startActivity(intent);
        });
    }
}
