package com.example.phamcongluan_2122110541;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView tvTotal;
    private Button btnCheckout, btnContinue;

    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.rvCart);
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnContinue = findViewById(R.id.btnContinue);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Quản lý giỏ hàng
        cartManager = new CartManager(this);
        List<CartItem> cartItems = cartManager.getCartItems();

        // Adapter xử lý hiển thị
        cartAdapter.setOnCartChangeListener(updatedCart -> {
            int total = 0;
            for (CartItem item : updatedCart) {
                total += item.price * item.quantity;
            }
            updateTotal(total); // ✅ Gọi đúng với int
        });

        recyclerView.setAdapter(cartAdapter);

        updateTotal(cartManager.calculateTotal());

        btnContinue.setOnClickListener(v -> finish()); // Quay về trang trước
        btnCheckout.setOnClickListener(v -> {
            // TODO: xử lý thanh toán
        });
    }

    // Cập nhật tổng tiền khi thay đổi
    private void updateTotal(int total) {
        tvTotal.setText("Tổng tiền: " + formatPrice(total) + "đ");
    }

    // Format tiền theo kiểu 1.000.000
    private String formatPrice(int price) {
        return String.format("%,d", price).replace(",", ".");
    }
}
