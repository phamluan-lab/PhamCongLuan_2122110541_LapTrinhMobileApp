package com.example.phamcongluan_2122110541;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "cart_prefs";
    private static final String CART_KEY = "cart_items";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public List<CartItem> getCartItems() {
        String json = sharedPreferences.getString(CART_KEY, "");
        Type type = new TypeToken<List<CartItem>>() {}.getType();
        List<CartItem> items = gson.fromJson(json, type);
        return items != null ? items : new ArrayList<>();
    }

    public void addToCart(CartItem newItem) {
        List<CartItem> cart = getCartItems();
        boolean found = false;

        for (CartItem item : cart) {
            if (item.id == newItem.id) {
                item.quantity += newItem.quantity;
                found = true;
                break;
            }
        }

        if (!found) cart.add(newItem);

        String json = gson.toJson(cart);
        sharedPreferences.edit().putString(CART_KEY, json).apply();
    }

    public int calculateTotal() {
        int total = 0;
        List<CartItem> cart = getCartItems();
        for (CartItem item : cart) {
            total += item.price * item.quantity;
        }
        return total;
    }
}
