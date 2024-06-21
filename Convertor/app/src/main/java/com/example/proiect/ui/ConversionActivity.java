package com.example.proiect.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.proiect.database.AppDatabase;
import com.example.proiect.database.ConversionHistory;
import com.example.proiect.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConversionActivity extends AppCompatActivity {
    private EditText etInputValue;
    private Spinner spinnerConversionType;
    private TextView tvOutputValue;
    private Button btnConvert, btnViewHistory, btnBackToConversionTypes;
    private AppDatabase db;
    private int userId;
    private String conversionCategory;
    private List<ConversionHistory> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        etInputValue = findViewById(R.id.etInputValue);
        spinnerConversionType = findViewById(R.id.spinnerConversionType);
        tvOutputValue = findViewById(R.id.tvOutputValue);
        btnConvert = findViewById(R.id.btnConvert);
        btnViewHistory = findViewById(R.id.btnViewHistory);
        btnBackToConversionTypes = findViewById(R.id.btnBackToConversionTypes);

        db = AppDatabase.getDatabase(getApplicationContext());

        userId = getIntent().getIntExtra("userId", -1);
        conversionCategory = getIntent().getStringExtra("conversionType");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                getArrayResourceForCategory(conversionCategory), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConversionType.setAdapter(adapter);

        btnConvert.setOnClickListener(v -> convert());
        btnViewHistory.setOnClickListener(v -> viewHistory());
        btnBackToConversionTypes.setOnClickListener(v -> {
            Intent intent = new Intent(ConversionActivity.this, ConversionTypeActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
            finish();
        });
    }

    private int getArrayResourceForCategory(String category) {
        switch (category) {
            case "length":
                return R.array.length_conversion_types;
            case "weight":
                return R.array.weight_conversion_types;
            case "volume":
                return R.array.volume_conversion_types;
            case "speed":
                return R.array.speed_conversion_types;
            case "energy":
                return R.array.energy_conversion_types;
            case "pressure":
                return R.array.pressure_conversion_types;
            case "area":
                return R.array.area_conversion_types;
            case "data":
                return R.array.data_conversion_types;
            default:
                return R.array.length_conversion_types; // Default fallback
        }
    }

    private void convert() {
        String inputValue = etInputValue.getText().toString();
        String conversionType = spinnerConversionType.getSelectedItem().toString();
        double input = Double.parseDouble(inputValue);
        double output = 0;

        switch (conversionType) {
            case "Celsius to Fahrenheit":
                output = (input * 9/5) + 32;
                break;
            case "Fahrenheit to Celsius":
                output = (input - 32) * 5/9;
                break;
            case "Metri to Centimetri":
                output = input * 100;
                break;
            case "Centimetri to Metri":
                output = input / 100;
                break;
            case "Metri to Milimetri":
                output = input * 1000;
                break;
            case "Milimetri to Metri":
                output = input / 1000;
                break;
            case "Inci to Centimetri":
                output = input * 2.54;
                break;
            case "Centimetri to Inci":
                output = input / 2.54;
                break;
            case "Picioare to Metri":
                output = input * 0.3048;
                break;
            case "Metri to Picioare":
                output = input / 0.3048;
                break;
            case "Yarzi to Metri":
                output = input * 0.9144;
                break;
            case "Metri to Yarzi":
                output = input / 0.9144;
                break;
            case "Mile to Kilometri":
                output = input * 1.60934;
                break;
            case "Kilometri to Mile":
                output = input / 1.60934;
                break;
            case "Kilograme to Livre":
                output = input * 2.20462;
                break;
            case "Livre to Kilograme":
                output = input / 2.20462;
                break;
            case "Uncii to Grame":
                output = input * 28.3495;
                break;
            case "Grame to Uncii":
                output = input / 28.3495;
                break;
            case "Galoni to Litri":
                output = input * 3.78541;
                break;
            case "Litri to Galoni":
                output = input / 3.78541;
                break;
            case "Pinte to Mililitri":
                output = input * 473.176;
                break;
            case "Mililitri to Pinte":
                output = input / 473.176;
                break;
            case "Metri pe secundă to Kilometri pe oră":
                output = input * 3.6;
                break;
            case "Kilometri pe oră to Metri pe secundă":
                output = input / 3.6;
                break;
            case "Metri pe secundă to Mile pe oră":
                output = input * 2.23694;
                break;
            case "Mile pe oră to Metri pe secundă":
                output = input / 2.23694;
                break;
            case "Kilometri pe oră to Mile pe oră":
                output = input * 0.621371;
                break;
            case "Mile pe oră to Kilometri pe oră":
                output = input / 0.621371;
                break;
            case "Jouli to Calorii":
                output = input * 0.239006;
                break;
            case "Calorii to Jouli":
                output = input / 0.239006;
                break;
            case "Pascali to Bari":
                output = input / 100000;
                break;
            case "Bari to Pascali":
                output = input * 100000;
                break;
            case "Pascali to Atmosfere":
                output = input / 101325;
                break;
            case "Atmosfere to Pascali":
                output = input * 101325;
                break;
            case "Milimetri coloană de mercur to Pascali":
                output = input * 133.322;
                break;
            case "Pascali to Milimetri coloană de mercur":
                output = input / 133.322;
                break;
            case "Acri to Metri pătrați":
                output = input * 4046.86;
                break;
            case "Metri pătrați to Acri":
                output = input / 4046.86;
                break;
            case "Hectare to Metri pătrați":
                output = input * 10000;
                break;
            case "Metri pătrați to Hectare":
                output = input / 10000;
                break;
            case "Biti to Bytes":
                output = input / 8;
                break;
            case "Bytes to Biti":
                output = input * 8;
                break;
            case "Bytes to KB":
                output = input / 1024;
                break;
            case "KB to Bytes":
                output = input * 1024;
                break;
            case "Bytes to MB":
                output = input / (1024 * 1024);
                break;
            case "MB to Bytes":
                output = input * 1024 * 1024;
                break;
            case "Bytes to GB":
                output = input / (1024 * 1024 * 1024);
                break;
            case "GB to Bytes":
                output = input * (1024 * 1024 * 1024);
                break;
            case "KB to MB":
                output = input / 1024;
                break;
            case "MB to KB":
                output = input * 1024;
                break;
            case "KB to GB":
                output = input / (1024 * 1024);
                break;
            case "GB to KB":
                output = input * (1024 * 1024);
                break;
            case "MB to GB":
                output = input / 1024;
                break;
            case "GB to MB":
                output = input * 1024;
                break;
            case "KB to TB":
                output = input / (1024 * 1024 * 1024);
                break;
            case "TB to KB":
                output = input * (1024 * 1024 * 1024);
                break;
            case "MB to TB":
                output = input / (1024 * 1024);
                break;
            case "TB to MB":
                output = input * (1024 * 1024);
                break;
            case "GB to TB":
                output = input / 1024;
                break;
            case "TB to GB":
                output = input * 1024;
                break;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.############");
        tvOutputValue.setText(decimalFormat.format(output));

        if (userId != -1) {
            ConversionHistory history = new ConversionHistory();
            history.userId = userId;
            history.conversionType = conversionType;
            history.inputValue = inputValue;
            history.outputValue = decimalFormat.format(output);
            history.timestamp = System.currentTimeMillis();

            historyList.add(history);
            new Thread(() -> db.conversionHistoryDao().insert(history)).start();
        }
    }

    private void viewHistory() {
        Intent intent = new Intent(ConversionActivity.this, HistoryActivity.class);
        intent.putExtra("userId", userId);
        intent.putParcelableArrayListExtra("historyList", new ArrayList<>(historyList));
        startActivity(intent);
    }
}
