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
            String fullName = txtFullName.getText().toString().trim();
            String username = txtUsername.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String confirmPassword = txtConfirmPassword.getText().toString().trim();

            if (fullName.isEmpty()) {
                txtFullName.setError("Vui lòng nhập họ tên");
                txtFullName.requestFocus();
                return;
            }

            if (username.isEmpty()) {
                txtUsername.setError("Vui lòng nhập tên tài khoản");
                txtUsername.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                txtPassword.setError("Vui lòng nhập mật khẩu");
                txtPassword.requestFocus();
                return;
            }

            if (password.length() < 6) {
                txtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
                txtPassword.requestFocus();
                return;
            }

            if (!password.equals(confirmPassword)) {
                txtConfirmPassword.setError("Mật khẩu không khớp");
                txtConfirmPassword.requestFocus();
                return;
            }

            // Nếu tất cả hợp lệ
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // TODO: Gửi dữ liệu lên server hoặc lưu SQLite
            finish(); // quay về màn hình trước (login)
        });
    }
}
