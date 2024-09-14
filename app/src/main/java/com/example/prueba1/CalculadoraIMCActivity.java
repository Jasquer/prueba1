package com.example.prueba1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculadoraIMCActivity extends AppCompatActivity {
    private EditText txt_Peso;
    private EditText txt_Altura;
    private Button btn_CalcularIMC;
    private TextView txv_ResultadoIMC;
    private Button btn_Regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora_imcactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_Peso = findViewById(R.id.editText_Peso);
        txt_Altura = findViewById(R.id.editText_Altura);
        btn_CalcularIMC = findViewById(R.id.button_CalcularIMC);
        txv_ResultadoIMC = findViewById(R.id.textView_ResultadoIMC);
        btn_CalcularIMC.setOnClickListener(view -> calcularIMC());
        btn_Regresar = findViewById(R.id.button_menu);
        btn_Regresar.setOnClickListener(view -> finish());
    }

    private void calcularIMC() {
        String pesoString = txt_Peso.getText().toString().trim();
        String alturaString = txt_Altura.getText().toString().trim();
        if (pesoString.isEmpty()) {
            txt_Peso.setError("Por favor, ingresa tu peso.");
            return;
        }
        if (alturaString.isEmpty()) {
            txt_Altura.setError("Por favor, ingresa tu altura.");
            return;
        }
        try {
            float peso = Float.parseFloat(pesoString);
            float altura = Float.parseFloat(alturaString);
            if (peso <= 0) {
                txt_Peso.setError("El peso debe ser mayor a cero.");
                return;
            }
            if (altura <= 0) {
                txt_Altura.setError("La altura debe ser mayor a cero.");
                return;
            }

            float imc = peso / (altura * altura);

            String clasificacion;
            if (imc < 18.5) {
                clasificacion = "Estás en bajo peso.";
            } else if (imc >= 18.5 && imc <= 24.9) {
                clasificacion = "Tienes un peso normal.";
            } else if (imc >= 25 && imc <= 29.9) {
                clasificacion = "Estás en sobrepeso.";
            } else {
                clasificacion = "Estás en obesidad.";
            }

            txv_ResultadoIMC.setText("Tu IMC es: " + String.format("%.2f", imc) + "\n" + clasificacion);

        } catch (NumberFormatException e) {
            txv_ResultadoIMC.setText("Por favor, ingresa valores numéricos válidos.");
        }
    }
}
