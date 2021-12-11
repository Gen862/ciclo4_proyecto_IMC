package com.example.appentrega;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appentrega.model.Persona;
import com.example.appentrega.repository.DatosRepository;
import com.example.appentrega.repository.DatosRepositoryImpl;

import java.util.Date;

public class Activity_Datos extends AppCompatActivity {
    private Persona persona=new Persona();
    public DatosRepository repository;
    Button mBtnMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        this.setTitle("Calculo IMC");
        Button calculo = (Button) findViewById(R.id.calcular);
        Button reporte=(Button) findViewById(R.id.reporte);
        Button concejo=(Button) findViewById(R.id.concejos);
        EditText estatura = (EditText) findViewById(R.id.estatura);
        EditText edad = (EditText) findViewById(R.id.edad);
        EditText peso = (EditText) findViewById(R.id.peso);
        TextView basal = (TextView) findViewById(R.id.basal);
        TextView imc = (TextView) findViewById(R.id.imc);
        TextView Tx_tipopeso = (TextView) findViewById(R.id.tipodepeso);
        RadioButton radioButton = (RadioButton) findViewById(R.id.radio_masculino);
        mBtnMaps = (Button) findViewById(R.id.Mapa);
        repository = new DatosRepositoryImpl();

        calculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(estatura.getText().toString().isEmpty() ||
                    peso.getText().toString().isEmpty() ||
                    edad.getText().toString().isEmpty() ){
                    Toast.makeText(Activity_Datos.this,"Datos invalidor",Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean genero = radioButton.isChecked();
                float fPeso = Float.parseFloat(peso.getText().toString());
                float fEstatura =  Float.parseFloat(estatura.getText().toString());
                float fEdad =  Float.parseFloat(edad.getText().toString());
                if(fEstatura<1F || fPeso<1F || fEdad<1F){
                    Toast.makeText(Activity_Datos.this,"Valor(es) en cero",Toast.LENGTH_SHORT).show();
                    return;
                }
                float fEstaturaimc = fEstatura / 100F;
                float basal_1 = 10F * fPeso;
                float basal_2 = 6.25F * fEstatura;
                float basal_3 = 5F * fEdad;

                if (genero) {
                    basal_3 = basal_3 + 5F;
                } else {
                    basal_3 = basal_3 - 161F;
                }

                float basal_calculo = basal_1 + basal_2 - basal_3;
                basal.setText("Su metabolismo basal es:" + basal_calculo);
                float imc_calculo = 0;
                if (fEstatura > 0) imc_calculo = fPeso / (fEstaturaimc * fEstaturaimc);
                imc.setText(String.format("%.1f",imc_calculo));
                if (imc_calculo < 20)
                    Tx_tipopeso.setText("Estás en : Bajo Peso");
                else if (imc_calculo >= 20 && imc_calculo < 25)
                    Tx_tipopeso.setText("Estás en: Peso Normal");
                else if (imc_calculo >= 25 && imc_calculo <= 29.9)
                    Tx_tipopeso.setText("Estás en:Sobrepeso");
                else if (imc_calculo >= 30 && imc_calculo <= 34.9)
                    Tx_tipopeso.setText("Estás en:Obesidad grado 1");
                else if (imc_calculo >= 35 && imc_calculo <= 40)
                    Tx_tipopeso.setText("Estás en: Obesidad grado 2");
                else if (imc_calculo > 40)
                    Tx_tipopeso.setText("Estás en: Obesidad Morbidad o grado 3");

                    persona=Datos.getInstance().getPersona();
                if (persona.getEmail() != null) {
                    persona.setEstatura(fEstatura);
                    persona.setEdad(fEdad);
                    persona.setPeso(fPeso);
                    persona.setBasal(basal_calculo);
                    persona.setImc(imc_calculo);
                    persona.setGenero(genero ? "Masculino" : "Femenino");
                    persona.setNota(Tx_tipopeso.getText() + "");
                    Datos.getInstance().setPersona(persona);
                    repository.crear(persona, new CallbackFirestore() {
                        @Override
                        public void onSuccess(Object object) {
                            Log.d("@OJO lo Creo:", ((Persona) object).toString());
                        }

                        @Override
                        public void onFailure(Object object) {
                            Log.d("@OJO", "No lo creo");
                        }
                    });
                }
            }
        });

       reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Activity_Datos.this, Activity_Reporte.class);
                    startActivity(intent);
            }
        });

        concejo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentc = new Intent (Activity_Datos.this,Activity_Concejos.class);
                intentc.putExtra("estado",Tx_tipopeso.getText().toString());
                startActivity(intentc);
                finish();
            }
        });
        mBtnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Datos.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}