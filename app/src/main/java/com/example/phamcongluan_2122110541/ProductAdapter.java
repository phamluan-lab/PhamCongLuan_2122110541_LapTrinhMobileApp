package com.example.phamcongluan_2122110541;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;
    private List<Product> originalList;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.originalList = new ArrayList<>(productList);
    }
    public void filter(String keyword) {
        productList.clear();

        if (keyword.isEmpty()) {
            productList.addAll(originalList);
        } else {
            keyword = keyword.toLowerCase();
            for (Product p : originalList) {
                if (p.name.toLowerCase().contains(keyword)) {
                    productList.add(p);
                }
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.txtName.setText(p.name);
        holder.txtPrice.setText(p.price + "₫");
        Glide.with(context).load(p.image).into(holder.imgProduct);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", p.name);
            intent.putExtra("price", p.price);
            intent.putExtra("image", p.image);
            intent.putExtra("description", p.description);
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtName, txtPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtProductPrice);
        }
    }
}
