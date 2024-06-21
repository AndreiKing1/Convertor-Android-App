package com.example.proiect.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.example.proiect.R;
import com.example.proiect.database.AppDatabase;

public class ConversionTypeActivity extends AppCompatActivity {
    private Spinner spinnerConversionCategory;
    private Button btnSelectCategory, btnViewHistory, btnBackToMain;
    private AppDatabase db;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_type);

        spinnerConversionCategory = findViewById(R.id.spinnerConversionCategory);
        btnSelectCategory = findViewById(R.id.btnSelectCategory);
        btnViewHistory = findViewById(R.id.btnViewHistory);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        db = AppDatabase.getDatabase(getApplicationContext());
        userId = getIntent().getIntExtra("userId", -1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionCategory.setAdapter(adapter);

        btnSelectCategory.setOnClickListener(v -> {
            String category = spinnerConversionCategory.getSelectedItem().toString().toLowerCase();
            Intent intent = new Intent(ConversionTypeActivity.this, ConversionActivity.class);
            intent.putExtra("userId", userId);
            intent.putExtra("conversionType", category);
            startActivity(intent);
        });

        btnViewHistory.setOnClickListener(v -> viewHistory());
        btnBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(ConversionTypeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void viewHistory() {
        Intent intent = new Intent(ConversionTypeActivity.this, HistoryActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
