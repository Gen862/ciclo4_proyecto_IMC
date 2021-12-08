package com.example.appentrega.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appentrega.CallbackFirestore;
import com.example.appentrega.model.Persona;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;


public class    DatosRepositoryImpl implements DatosRepository{
    final static String COLLECTION = "datosIMC";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public void crear(Persona persona, CallbackFirestore callback) {
        db.collection(COLLECTION)
                .document(persona.getEmail())
                .set(persona.getMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(persona);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e);
                    }
                });
    }
    @Override
    public void leeTodos(ArrayList<Persona> personas, CallbackFirestore callback) {
        db.collection(COLLECTION)
        .get()
        .addOnCompleteListener((Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot doc : task.getResult().getDocuments()) {
                    Persona persona = new Persona();
                    persona.setEmail(doc.get("email") == null ? "" : doc.get("email").toString());
                    persona.setNota(doc.get("nota") == null ? "" : doc.get("nota").toString());
                    persona.setGenero(doc.get("genero") == null ? "" : doc.get("genero").toString());
                    persona.setEstatura(doc.get("estatura") == null ? 0F : ((Double) doc.get("estatura")).floatValue());
                    persona.setEdad(doc.get("edad") == null ? 0F : ((Double) doc.get("edad")).floatValue());
                    persona.setPeso(doc.get("peso") == null ? 0F : ((Double) doc.get("peso")).floatValue());
                    persona.setImc(doc.get("imc") == null ? 0F : ((Double) doc.get("imc")).floatValue());
                    persona.setBasal(doc.get("basal") == null ? 0F : ((Double) doc.get("basal")).floatValue());
                    Log.d("@OJO en lista:", persona.toString());
                    personas.add(persona);
                }
                callback.onSuccess(personas);
            } else {
                callback.onFailure(null);
            }
        });
    }
}
