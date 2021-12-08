package com.example.appentrega.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Persona implements Serializable  {
    private String email;
    private float edad;
    private float peso;
    private float estatura;
    private String genero;
    private float imc;
    private float basal;
    private Date fecha=new Date();
    private boolean expanded;
    private String tipo;
    private String nota;
    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Persona() {}

    public Persona(String email, float edad, float peso, float estatura, String genero, float imc, float basal, String nota) {
        this.email = email;
        this.edad = edad;
        this.peso = peso;
        this.estatura = estatura;
        this.genero = genero;
        this.imc = imc;
        this.basal = basal;
        this.fecha = new Date();
        this.tipo=email.contains(".es")?"Estudiante":"BienestarUn";
        this.nota=nota;
        this.expanded = false;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    public float getBasal() {
        return basal;
    }

    public void setBasal(float basal) {
        this.basal = basal;
    }

    public Date getFecha() {
        return fecha;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "email='" + email + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", estatura=" + estatura +
                ", genero='" + genero + '\'' +
                ", imc=" + imc +
                ", basal=" + basal +
                ", fecha=" + fecha +
                ", expanded=" + expanded +
                ", tipo='" + tipo + '\'' +
                ", nota='" + nota +
                '}';
    }
    public Map<String, Object> getMap(){
        Map<String,Object> mapa= new HashMap<>();
        mapa.put("email", email);
        mapa.put("edad" , edad );
        mapa.put("peso" , peso );
        mapa.put("estatura", estatura );
        mapa.put("genero" , genero );
        mapa.put("imc", imc );
        mapa.put("basal" , basal );
        mapa.put("fecha" , fecha );
        mapa.put("expanded" , expanded );
        mapa.put("tipo" , tipo  );
        mapa.put("nota" , nota);
        return mapa;

    }


}
