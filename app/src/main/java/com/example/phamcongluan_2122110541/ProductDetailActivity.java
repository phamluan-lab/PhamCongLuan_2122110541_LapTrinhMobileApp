package com.example.phamcongluan_2122110541;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgDetail;
    TextView txtName, txtPrice, txtDescription;
    Button btnAddToCart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các view
        imgDetail = findViewById(R.id.imgDetail);
        txtName = findViewById(R.id.txtDetailName);
        txtPrice = findViewById(R.id.txtDetailPrice);
        txtDescription = findViewById(R.id.txtDetailDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Lấy dữ liệu từ Intent
        String name = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String image = getIntent().getStringExtra("image");
        String description = getIntent().getStringExtra("description");

        // Gán dữ liệu vào view
        txtName.setText(name);
        txtPrice.setText(price + "₫");
        txtDescription.setText(description);
        Glide.with(this).load(image).into(imgDetail);

        // Thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            Product product = (Product) getIntent().getSerializableExtra("product");
            if (product != null) {
                CartItem item = new CartItem();
                item.id = product.id;
                item.name = product.name;
                item.price = (int) Float.parseFloat(product.price);
                item.image = product.image;
                item.quantity = 1;

                CartManager manager = new CartManager(this);
                manager.addToCart(item);

                Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Không có sản phẩm!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

