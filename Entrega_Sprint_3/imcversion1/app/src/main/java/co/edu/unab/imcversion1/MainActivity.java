package co.edu.unab.imcversion1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nombre1, edad;
    Button actividadDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //casteo
        nombre1=(EditText)findViewById(R.id.editText);
        edad=(EditText)findViewById(R.id.editText2);
        actividadDatos=(Button)findViewById(R.id.actividadDatos);
        //metodo capturar nombre
        actividadDatos.setOnClickListener(v -> {

            String nom = nombre1.getText().toString();
            String edad1 = edad.getText().toString();

            if(nom.length() == 0){
                Toast.makeText(MainActivity.this,"Debes ingresar un nombre", Toast.LENGTH_SHORT).show();

            }
            if(edad1.length() == 0){
                Toast.makeText(MainActivity.this,"Debes ingresar una edad", Toast.LENGTH_SHORT).show();
            }
            if(nom.length() != 0 && edad1.length() != 0){
                Toast.makeText(MainActivity.this,"¡Felicidades! estas a un paso de conocer tu IMC", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,Calcularimc.class);
                intent.putExtra("dato", nombre1.getText().toString());
                startActivity(intent);
            }

        });
    }
}