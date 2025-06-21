package com.example.phamcongluan_2122110541;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText objUsername = findViewById(R.id.txtUsername);
                EditText objPass = findViewById(R.id.txtPass);

                String txtUsername = objUsername.getText().toString();
                String txtPass = objPass.getText().toString();

                if (txtUsername.equals("900") && txtPass.equals("123")) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
                } else {
                    Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}