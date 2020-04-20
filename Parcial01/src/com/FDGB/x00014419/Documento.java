package com.FDGB.x00014419;

public class Documento{
    private String nombre = "\0";
    private String numero = "\0";

    //Constructor
    public Documento(String nombre, String numero){
        this.nombre = nombre;
        this.numero = numero;
    }

    //Setters & Getters
    public String getNombre(){
        return nombre;
    }
    public String getNumero(){
        return numero;
    }
}
