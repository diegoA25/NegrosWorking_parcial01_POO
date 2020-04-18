package com.FDGB.x00014419;

import java.util.ArrayList;
import java.util.List;

abstract class Empleado {
    protected String nombre = "\0";
    protected String puesto = "\0";
    protected List<Documento> documentos = new ArrayList<Documento>();
    protected double salario = 0.00f;

    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void addDocumento(Documento a){
    }

    public void removeDocumento(String a){
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}