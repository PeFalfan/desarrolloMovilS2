package com.example.appeventos;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EventsAdminActivity extends AppCompatActivity {
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_admin);

        referencias();
        eventos();
    }

    private void referencias() {
        currentUsername = getIntent().getStringExtra("username");

        if (currentUsername == null) {
            Toast.makeText(this, "No se recibió el nombre de usuario", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad si no se recibió el nombre de usuario
        }
    }

    private void eventos() {

    }
}


