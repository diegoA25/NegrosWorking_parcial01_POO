package com.FDGB.x00014419;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;

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
        documentos.add(a);
    }

    public void removeDocumento(String a){
        int cont =0;
        boolean aux2 = false;
        for(Documento aux: documentos ){
            if(aux.getNombre().equals(a)){
                aux2 = true;
            }
            if(!aux2){
                cont++;
            }
        }
        if(!aux2){
            JOptionPane.showMessageDialog(null, "Documento inexistente!");
        }
        else{
            documentos.remove(cont);
            JOptionPane.showMessageDialog(null, "Documento Eliminado");
        }
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}