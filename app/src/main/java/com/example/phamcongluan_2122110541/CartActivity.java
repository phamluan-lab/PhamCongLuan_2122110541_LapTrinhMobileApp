package com.example.phamcongluan_2122110541;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        Button btnContinueShopping = findViewById(R.id.btnContinueShopping);
        btnContinueShopping.setOnClickListener(v -> {
            // Trở lại màn hình chính (MainActivity)
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}