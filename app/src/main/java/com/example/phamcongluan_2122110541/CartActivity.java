package com.example.phamcongluan_2122110541;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    private TextView tvProductName, tvProductPrice, tvQuantity, tvTotal;
    private Button btnIncrease, btnDecrease, btnContinue, btnCheckout;
    private int quantity = 1;
    private int unitPrice = 7990000; // Ví dụ giá 1 sản phẩm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        // Ánh xạ view
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvTotal = findViewById(R.id.tvTotal);

        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnContinue = findViewById(R.id.btnContinue);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Khởi tạo
        updateTotal();

        btnIncrease.setOnClickListener(v -> {
            quantity++;
            updateTotal();
        });

        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateTotal();
            }
        });

        btnContinue.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

    }


    private void updateTotal() {
        tvQuantity.setText(String.valueOf(quantity));
        int total = unitPrice * quantity;
        tvTotal.setText("Tổng tiền: " + formatPrice(total) + "đ");
    }

    private String formatPrice(int price) {
        return String.format("%,d", price).replace(",", ".");
    }

}
