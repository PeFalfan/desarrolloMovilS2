package com.example.appeventos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventsAdminActivity extends AppCompatActivity {
    private Button goEventBtn, deleteAllBtn;
    private DataBaseHelper DBHelper;
    private String currentUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_admin);

        referencias();
        eventos();
    }

    private void referencias() {
        goEventBtn = findViewById(R.id.goEventBtn);
        deleteAllBtn = findViewById(R.id.deleteAllBtn);
        DBHelper = new DataBaseHelper(this);

        currentUsername = getIntent().getStringExtra("username");

        if (currentUsername == null) {
            Toast.makeText(this, "No se recibió el nombre de usuario", Toast.LENGTH_SHORT).show();
            finish(); // Cerrar la actividad si no se recibió el nombre de usuario
            return;
        }
    }

    private void eventos() {


        goEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoHome = new Intent(EventsAdminActivity.this, EventsSavingActivity.class);
                intentGoHome.putExtra("username", currentUsername); // Pasar el nombre de usuario
                EventsAdminActivity.this.startActivity(intentGoHome);
            }

        });


        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userId = DBHelper.getUserId(currentUsername);
                if (userId == -1) {
                    Toast.makeText(EventsAdminActivity.this, "Usuario no encontrado.", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AlertDialog.Builder(EventsAdminActivity.this)
                        .setTitle("Confirmar Eliminación")
                        .setMessage("¿Está seguro de que desea eliminar su cuenta y todos sus eventos?")
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (DBHelper.deleteUserAndEvents(userId)) {
                                    Toast.makeText(EventsAdminActivity.this, "Cuenta y eventos eliminados correctamente.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(EventsAdminActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(EventsAdminActivity.this, "No se pudo eliminar la cuenta.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }
}


