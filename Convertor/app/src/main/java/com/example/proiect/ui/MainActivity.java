package com.example.proiect.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.proiect.database.AppDatabase;
import com.example.proiect.database.User;
import com.example.proiect.R;

public class MainActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister, btnContinueWithoutAccount;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnContinueWithoutAccount = findViewById(R.id.btnContinueWithoutAccount);

        db = AppDatabase.getDatabase(getApplicationContext());

        btnLogin.setOnClickListener(v -> login());
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        btnContinueWithoutAccount.setOnClickListener(v -> continueWithoutAccount());
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        new Thread(() -> {
            User user = db.userDao().getUser(username, password);
            runOnUiThread(() -> {
                if (user != null) {
                    Intent intent = new Intent(MainActivity.this, ConversionTypeActivity.class);
                    intent.putExtra("userId", user.id);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void continueWithoutAccount() {
        Intent intent = new Intent(MainActivity.this, ConversionTypeActivity.class);
        startActivity(intent);
    }
}
