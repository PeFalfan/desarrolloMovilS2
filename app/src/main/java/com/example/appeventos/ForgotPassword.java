package com.example.appeventos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassword extends AppCompatActivity {
    private TextInputLayout tilUser, tilSecurityAnswer, tilNewPassword;
    private Button btnResetPassword;
    private DataBaseHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tilUser = findViewById(R.id.tilUser);
        tilSecurityAnswer = findViewById(R.id.tilSecurityAnswer);
        tilNewPassword = findViewById(R.id.tilNewPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        DBHelper = new DataBaseHelper(this);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tilUser.getEditText().getText().toString();
                String securityAnswer = tilSecurityAnswer.getEditText().getText().toString();
                String newPassword = tilNewPassword.getEditText().getText().toString();

                if (DBHelper.checkSecurityAnswer(username, securityAnswer)) {
                    if (DBHelper.updatePassword(username, newPassword)) {
                        Toast.makeText(ForgotPassword.this, "Contraseña actualizada correctamente", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        tilNewPassword.setError("No se pudo actualizar la contraseña. Intente nuevamente.");
                    }
                } else {
                    tilSecurityAnswer.setError("Respuesta incorrecta a la pregunta secreta.");
                }
            }
        });
    }
}