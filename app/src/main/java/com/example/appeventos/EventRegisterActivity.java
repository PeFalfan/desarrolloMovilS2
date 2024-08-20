package com.example.appeventos;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appeventos.models.EventModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class EventRegisterActivity extends AppCompatActivity {

    TextView selectedDate;
//    MaterialToolbar toolbar;
    Button saveEventBtn;
    TextInputEditText titleInput;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        titleInput = findViewById(R.id.txt_title_input);
        selectedDate = findViewById(R.id.txt_selected_date);
//        toolbar = findViewById(R.id.materialToolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Registro de evento");
        db = new DataBaseHelper(this);
        db.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        selectedDate.setText(Objects.requireNonNull(extras).getString("selectedDate"));

        saveEventBtn = findViewById(R.id.btn_save_event);

        saveEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!titleInput.getText().toString().isEmpty()) {
                    Toast.makeText(EventRegisterActivity.this, titleInput.getText(), Toast.LENGTH_SHORT).show();

                    db.createEvent(titleInput.getText().toString(), selectedDate.getText().toString());

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}