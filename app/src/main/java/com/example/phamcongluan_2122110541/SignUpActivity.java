package com.example.phamcongluan_2122110541;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    EditText txtFullName, txtUsername, txtPassword, txtConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtFullName = findViewById(R.id.txtFullName);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String pass = txtPassword.getText().toString();
            String confirm = txtConfirmPassword.getText().toString();

            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
            } else {
                // Xử lý đăng ký tại đây (gửi API, lưu SQLite, v.v.)
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                finish(); // Quay về trang trước (đăng nhập)
            }
        });
    }
}
