package com.example.appentrega.repository;

import com.example.appentrega.CallbackFirestore;
import com.example.appentrega.model.Persona;

import java.util.ArrayList;

public interface DatosRepository {
    public void crear (Persona persona, CallbackFirestore callback);
    public void leeTodos( ArrayList<Persona> personas, CallbackFirestore callback);

}
