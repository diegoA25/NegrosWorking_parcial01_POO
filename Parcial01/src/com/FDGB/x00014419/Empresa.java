package com.FDGB.x00014419;

import java.util.ArrayList;
import java.util.List;

public class Empresa{
    private String nombre;
    private List<Empleado> planilla = new ArrayList<Empleado>();

    //Constructor
    public Empresa(String nombre){
        this.nombre = nombre;
    }

    //Setters & Getters
    public String getNombre(){
        return nombre;
    }
    public List<Empleado> getPlanilla(){
        return planilla;
    }

    //Metodos
    public void addEmpleado(Empleado empleado){
        planilla.add(empleado);
    }
    public void quitEmpleado(String nombre){
        for (Empleado aux: planilla) {
            if(aux.getNombre().equals(nombre)){
                planilla.remove(aux);
            }
        }

    }
}
