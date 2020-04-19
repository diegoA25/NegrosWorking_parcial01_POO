package com.FDGB.x00014419;

import javax.swing.*;
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
        boolean despedido = false;
        int cont = 0;
        int n = 0;
        for (Empleado aux: planilla) {
            if(aux.getNombre().equals(nombre)){
                String cadena="\0";
                for(Documento a:aux.getDocumentos()){
                    cadena+=a.getNombre()+": "+a.getNumero()+"\n";
                }
                if(aux instanceof PlazaFija){
                    n= JOptionPane.showConfirmDialog(null,"¿Quieres despedir el siguiente empleado?"+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: "+aux.getSalario()+"\n"+"Extensión: "+((PlazaFija) aux).getExtensión(),null,JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        despedido = true;
                        break;
                    }
                    else{
                    }
                }
                else if(aux instanceof ServicioProfesional){
                    n= JOptionPane.showConfirmDialog(null,"¿Quieres despedir el siguiente empleado?"+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: "+aux.getSalario()+"\n"+"Meses: "+((ServicioProfesional) aux).getMeses(),null,JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        despedido = true;
                        break;
                    }
                    else{
                    }
                }

            }
            cont++;
        }
        if(despedido == true){
            planilla.remove(cont);
            JOptionPane.showMessageDialog(null,"Empleado despedido");
        }
        else{
            JOptionPane.showMessageDialog(null,"Empleado no existente");
        }
    }
}
