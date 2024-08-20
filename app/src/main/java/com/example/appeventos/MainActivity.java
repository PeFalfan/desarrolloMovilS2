package com.example.appeventos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout tilUser, tilPass;
    private Button btnIngresar, btnRegistrar;
    private TextView tvRecuperacion;
    private DataBaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper= new DataBaseHelper(this);
        DBHelper.getReadableDatabase();

        referencias();
        eventos();

    }

    private void referencias() {
        tilUser = findViewById(R.id.tilUser);
        tilPass = findViewById(R.id.tilPass);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        tvRecuperacion = findViewById(R.id.tvRecuperacion);
    }

    private void eventos() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_usuario = tilUser.getEditText().getText().toString();
                String contrasena = tilPass.getEditText().getText().toString();

                if (DBHelper.checkUserCredentials(nombre_usuario, contrasena)) {
                    Intent intent = new Intent(MainActivity.this, EventsAdminActivity.class);
                    intent.putExtra("username", nombre_usuario); // Pasar el nombre de usuario
                    startActivity(intent);
                } else {
                    tilPass.setError("Nombre de usuario o contraseña incorrectos");
                }

            }

        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);
            }

        });

        tvRecuperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

    }
}

