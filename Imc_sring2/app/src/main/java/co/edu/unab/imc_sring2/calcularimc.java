package co.edu.unab.imc_sring2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class calcularimc extends AppCompatActivity {
    EditText ET_Peso;
    EditText ET_Altura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcularimc);
        ET_Peso= (EditText) findViewById(R.id.Peso);
        ET_Altura= (EditText) findViewById(R.id.Altura);
        Button botonvolver=(Button) findViewById(R.id.volver);
        botonvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calcularimc.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
     public void CalcularOnClick (View v){
        try{
        double peso= Double.parseDouble(ET_Peso.getText().toString());
        double altura=Double.parseDouble(ET_Altura.getText().toString());
        Intent i = new Intent(this,Resultado.class);
            i.putExtra("peso",peso);
            i.putExtra("altura",altura);
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this,"Error en el ingreso de datos", Toast.LENGTH_SHORT).show();
     }
}
}