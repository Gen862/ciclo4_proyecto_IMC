package co.edu.unab.imcversion1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Resultado extends AppCompatActivity {
    EditText ET_Resultado;
    Button consejos1, botonvolver, volver_inicio,guardar;
    ArrayList<String> arrayList;
    EditText Descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        ET_Resultado= findViewById(R.id.Resultado);
        guardar=findViewById(R.id.guardar_imc);
        Descripcion= findViewById(R.id.descripcion_peso);

        Intent i;
        i = getIntent();
        double altura = i.getDoubleExtra("altura",0);
        double peso = i.getDoubleExtra("peso",0);
        double imc = (peso/(altura*altura));
        ET_Resultado.setText(String.format("%.2f",imc));
            if(imc<18.5)
                Descripcion.setText("Bajo Peso");
            else
            if(imc>=18.5 && imc<=24.9)
                Descripcion.setText("Peso Normal");
            else
            if(imc>=25 && imc<=29)
                Descripcion.setText("Sobrepeso");
            else
            if(imc>=30 && imc<=34.9)
                Descripcion.setText("Obesidad Tipo 1");
            else
            if(imc>=35 && imc<=39.9)
                Descripcion.setText("Obesidad Tipo 2");
            else
            if(imc>=40 && imc<=49)
                Descripcion.setText("Obesidad Tipo 3");
            else
            if(imc>50)
                Descripcion.setText("Obesidad Tipo 4");

        ListView Listaregistros = (ListView) findViewById(R.id.registros);
        arrayList = new ArrayList<>();
        ArrayAdapter<String>adaptador= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        Listaregistros.setAdapter(adaptador);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add(ET_Resultado.getText().toString());
                adaptador.notifyDataSetChanged();
            }
        });

        botonvolver= findViewById(R.id.volver);
        botonvolver.setOnClickListener(v -> {
            Intent intent = new Intent(Resultado.this,Calcularimc.class);
            startActivity(intent);
        });

        volver_inicio= findViewById(R.id.volver_inicio);
        volver_inicio.setOnClickListener(v -> {
            Intent intent = new Intent(Resultado.this,MainActivity.class);
            startActivity(intent);
        });

        consejos1 = findViewById(R.id.consejos);
        consejos1.setOnClickListener(v -> {
            Intent intent = new Intent(Resultado.this,MainActivity.class);
            startActivity(intent);
        });

    }


}