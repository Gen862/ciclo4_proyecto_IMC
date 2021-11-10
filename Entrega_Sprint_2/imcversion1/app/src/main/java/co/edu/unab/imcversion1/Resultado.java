package co.edu.unab.imcversion1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Resultado extends AppCompatActivity {
    EditText ET_Resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ET_Resultado =(EditText) findViewById(R.id.Resultado);
        Intent i=getIntent();
        double altura=i.getDoubleExtra("altura",0);
        double peso=i.getDoubleExtra("peso",0);
        double imc=(peso/Math.pow(altura,2));
        ET_Resultado.setText(String.valueOf(imc));

        Button botonvolver=(Button) findViewById(R.id.volver);
        botonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resultado.this,calcularimc.class);
                startActivity(intent);
            }
        });
    }
}