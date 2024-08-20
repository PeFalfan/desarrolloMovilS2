package com.example.appeventos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Registro extends AppCompatActivity {
    private TextInputLayout tilUserRegister, tilPassRegister, tilRespuesta;
    private Button btnRegistrado;
    private RadioGroup rdPreguntas;
    private DataBaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        referencias();
        eventos();

        DBHelper = new DataBaseHelper(this);
        DBHelper.getWritableDatabase();
    }

    private void referencias() {
        tilUserRegister = findViewById(R.id.tilUserRegister);
        tilPassRegister = findViewById(R.id.tilPassRegister);
        tilRespuesta = findViewById(R.id.tilRespuesta);
        btnRegistrado = findViewById(R.id.btnRegistrado);
        rdPreguntas = findViewById(R.id.rdPreguntas);
    }

    private void eventos() {
        btnRegistrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = tilUserRegister.getEditText().getText().toString();
                String newPassword = tilPassRegister.getEditText().getText().toString();
                String respuesta = tilRespuesta.getEditText().getText().toString();

                ValidarChar usernameCharacter = new ValidarChar(newUsername);
                ValidarChar passwordCharacter = new ValidarChar(newPassword);


                    if (!usernameCharacter.hasUppercase() || !usernameCharacter.hasLowercase() || !usernameCharacter.hasDigit() || newUsername.isEmpty()){
                        tilUserRegister.setError("el usuario debe contener al menos una letra mayúscula, una minúscula y un número.");
                    } else {
                        tilUserRegister.setError(null);
                    }
                    if (newPassword.isEmpty()|| !passwordCharacter.hasUppercase() || !passwordCharacter.hasLowercase() || !passwordCharacter.hasDigit() ) {
                        tilPassRegister.setError("La contraseña es obligatoria y debe contener una mayuscula, minuscula y un número");
                    } else {
                        tilPassRegister.setError(null);
                    }
                    if (respuesta.isEmpty()) {
                        tilRespuesta.setError("La respuesta es obligatoria");
                    } else {
                        tilRespuesta.setError(null);
                    }
//                Toast.makeText(Registro.this, tilUserRegister.getError(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Registro.this, tilPassRegister.getError(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(Registro.this, tilRespuesta.getError(), Toast.LENGTH_SHORT).show();
                    if(tilUserRegister.getError() == null  && tilPassRegister.getError() == null && tilRespuesta.getError()== null){
                        if (registerUser(newUsername, newPassword, respuesta)) {
                            Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            tilPassRegister.setError("No se pudo registrar el usuario. Intente nuevamente.");
                        }
                    }
                }
        });
    }

    private boolean registerUser(String nombreUsuario, String contrasena, String respuestaSecreta) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre_usuario", nombreUsuario);
        contentValues.put("contrasena", contrasena);
        contentValues.put("respuesta_secreta", respuestaSecreta);
        long result = db.insert("Usuarios", null, contentValues);
        db.close();
        return result != -1;
    }
}
