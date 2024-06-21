package com.example.proiect.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.proiect.database.AppDatabase;
import com.example.proiect.database.User;
import com.example.proiect.R;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnRegister;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        db = AppDatabase.getDatabase(getApplicationContext());

        btnRegister.setOnClickListener(v -> register());
    }

    private void register() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (validatePassword(password)) {
            new Thread(() -> {
                User user = new User();
                user.username = username;
                user.password = password;
                db.userDao().insert(user);

                runOnUiThread(() -> {
                    Toast.makeText(RegisterActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                });
            }).start();
        } else {
            Toast.makeText(this, "Password must be at least 8 characters long and contain at least one letter, one number, and one special character.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        return pattern.matcher(password).matches();
    }
}
