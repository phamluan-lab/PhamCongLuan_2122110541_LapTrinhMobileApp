package com.example.phamcongluan_2122110541;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import android.text.Editable;
import android.text.TextWatcher;



import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import org.json.JSONArray;
import org.json.JSONObject;

import org.json.JSONException;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private ProductAdapter productAdapter;


    private String url = "https://api.escuelajs.co/api/v1/products";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getData();
        EditText edtSearch = findViewById(R.id.edtSearch);
        edtSearch.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (productAdapter != null) {
                    productAdapter.filter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        // ✅ Đặt trong onCreate
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_cart) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
                return true;
            }

            // Xử lý mục khác nếu có
            return false;
        });

    }

    // Xử lý click sản phẩm
    public void goToProductDetail(View view) {
 Intent intent = new Intent(this, ProductDetailActivity.class);
     startActivity(intent);
   }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://fakestoreapi.com/products";

        stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        List<Product> productList = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            Product product = new Product();
                            product.id = obj.getInt("id");
                            product.name = obj.getString("title");
                            product.price = obj.getString("price");
                            product.image = obj.getString("image");
                            product.description = obj.getString("description");

                            productList.add(product);
                        }

                        RecyclerView recyclerView = findViewById(R.id.rvProducts);
                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


                        productAdapter = new ProductAdapter(this, productList);
                        recyclerView.setAdapter(productAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Toast.makeText(this, "Lỗi khi lấy dữ liệu", Toast.LENGTH_SHORT).show();
                });

        queue.add(stringRequest);
    }

}
