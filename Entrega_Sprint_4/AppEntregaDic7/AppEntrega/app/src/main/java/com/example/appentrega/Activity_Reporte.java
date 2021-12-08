package com.example.appentrega;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appentrega.model.Persona;
import com.example.appentrega.repository.DatosRepository;
import com.example.appentrega.repository.DatosRepositoryImpl;

import java.util.ArrayList;


public class Activity_Reporte extends AppCompatActivity {
    private ArrayList<Persona>personas=new ArrayList<>();
    RecyclerView recyclerView;
    DatosRepository repository=  new DatosRepositoryImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        this.setTitle("Reporte IMC");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Persona persona=(Persona) getIntent().getSerializableExtra("persona");
        personas.clear();
        Persona persona=Datos.getInstance().getPersona();
        if(persona.getEmail().contains(".es")){//solo datos usuario actual
            personas.clear();
            personas.add(persona);
            PersonaAdapter personaAdapter = new PersonaAdapter(personas);
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Reporte.this));
            recyclerView.setAdapter(personaAdapter);
            return;
        }
        else {
            repository.leeTodos( personas, new CallbackFirestore() {
                @Override
                public void onSuccess(Object object) {
                    PersonaAdapter personaAdapter = new PersonaAdapter(personas);
                    recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Reporte.this));
                    recyclerView.setAdapter(personaAdapter);
                }

                @Override
                public void onFailure(Object object) {
                    personas = new ArrayList<>();
                }
            });
        }
    }
}