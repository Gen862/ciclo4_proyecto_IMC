package co.edu.unab.imcversion1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Calcularimc extends AppCompatActivity {

    TextView tv1;
    EditText ET_Peso;
    EditText ET_Altura;
    ImageView botonIr, botonVolver;
    //private Spinner spinner1;
    //private String[] unidades= {"Kilográmos", "Libras"};
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcularimc);

        //casteo
        ET_Peso= findViewById(R.id.Peso);
        ET_Altura= findViewById(R.id.Altura);
        botonIr= findViewById(R.id.botonCalcular);
        botonVolver= findViewById(R.id.volver);
        tv1 = (TextView)findViewById(R.id.saludo);
       //spinner1 = findViewById(R.id.spinner1);

        String dato = getIntent().getStringExtra("dato");
        tv1.setText("Bienvenido, "+dato);

        //ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,unidades);
        //spinner1.setAdapter(adaptador);
    }

    public void BotonVolver(View view){
        botonVolver.setOnClickListener(v -> {
            Intent intent = new Intent(Calcularimc.this,MainActivity.class);
            startActivity(intent);
        });
    }

    public void CalcularImc(View v){

        double peso= Double.parseDouble(ET_Peso.getText().toString());
        double altura=Double.parseDouble(ET_Altura.getText().toString());

        botonIr.setOnClickListener(view -> {
            if(peso == 0){
                Toast.makeText(Calcularimc.this,"Debes ingresar un peso", Toast.LENGTH_LONG).show();
            }
            if(altura == 0){
                Toast.makeText(Calcularimc.this,"Debes ingresar una altura válida", Toast.LENGTH_LONG).show();
            }
            if(peso != 0 && altura != 0){
                Intent i = new Intent(Calcularimc.this,Resultado.class);
                i.putExtra("peso",peso);
                i.putExtra("altura",altura);
                startActivity(i);
            }
        });
    }
}