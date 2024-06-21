package com.example.proiect.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.proiect.R;
import com.example.proiect.database.AppDatabase;
import com.example.proiect.database.ConversionHistory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class HistoryActivity extends AppCompatActivity {
    private ListView lvHistory;
    private AppDatabase db;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);
        db = AppDatabase.getDatabase(getApplicationContext());
        userId = getIntent().getIntExtra("userId", -1);

        loadHistory();
    }

    private void loadHistory() {
        new Thread(() -> {
            List<ConversionHistory> historyList = db.conversionHistoryDao().getHistoryForUser(userId);
            runOnUiThread(() -> {
                List<String> displayList = historyList.stream()
                        .map(history -> {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                            return String.format("%s: %s -> %s (%s)",
                                    history.conversionType, history.inputValue, history.outputValue, sdf.format(history.timestamp));
                        })
                        .collect(Collectors.toList());

                ArrayAdapter<String> adapter = new ArrayAdapter<>(HistoryActivity.this,
                        android.R.layout.simple_list_item_1, displayList);
                lvHistory.setAdapter(adapter);
            });
        }).start();
    }
}
