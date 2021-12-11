package com.example.appentrega;

import com.example.appentrega.model.Persona;

public class Datos  {
    private Persona persona=new Persona();
    private static final Datos instance=new Datos();
    private Datos(){}
    public static Datos getInstance() {
        return instance;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public void setMail(String mail){
        this.persona.setEmail(mail);
    }
    public String getMail(){
        return this.persona.getEmail();
    }
}

