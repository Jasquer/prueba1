package com.example.prueba1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class NumeroActivity extends AppCompatActivity {
    private EditText txt_RangoMenor;
    private EditText txt_RangoMayor;
    private Button btn_GenerarAleatorio;
    private Button btn_regresar;
    private TextView txv_ResultadoAleatorio;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_numero);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_RangoMenor = findViewById(R.id.editText_RangoMenor);
        txt_RangoMayor = findViewById(R.id.editText_RangoMayor);
        btn_GenerarAleatorio = findViewById(R.id.button_GenerarAleatorio);
        txv_ResultadoAleatorio = findViewById(R.id.textView_ResultadoAleatorio);
        btn_regresar =findViewById(R.id.button_regresar);
        btn_regresar.setOnClickListener(view -> finish());

        btn_GenerarAleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarNumeroAleatorio();
            }
        });
    }

    private void generarNumeroAleatorio() {

        String rangoMenorString = txt_RangoMenor.getText().toString().trim();
        String rangoMayorString = txt_RangoMayor.getText().toString().trim();


        if (rangoMenorString.isEmpty()) {
            txt_RangoMenor.setError("Por favor, ingresa un número para el rango menor.");
            return;
        }

        if (rangoMayorString.isEmpty()) {
            txt_RangoMayor.setError("Por favor, ingresa un número para el rango mayor.");
            return;
        }


        try {
            int rangoMenor = Integer.parseInt(rangoMenorString);
            int rangoMayor = Integer.parseInt(rangoMayorString);


            if (rangoMenor >= rangoMayor) {
                Toast.makeText(this, "El rango mayor debe ser mayor que el rango menor.", Toast.LENGTH_SHORT).show();
                return;
            }


            Random random = new Random();
            int numeroAleatorio = random.nextInt((rangoMayor - rangoMenor) + 1) + rangoMenor;


            txv_ResultadoAleatorio.setText("Número aleatorio: " + numeroAleatorio);

        } catch (NumberFormatException e) {

            Toast.makeText(this, "Por favor, ingresa números válidos.", Toast.LENGTH_SHORT).show();
        }
    }
}