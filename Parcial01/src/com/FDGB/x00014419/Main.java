package com.FDGB.x00014419;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean continuar = false;
        Byte op = 0, opdocs = 0;
        boolean verificacion = false;
        String nombre = "\0", puesto = " ", enterprise = " ", number = " ", document = " ", eliminardocs = " ", tipodocument = " ";
        double salario = 0.00;
        int months = 0, extensions = 0;

        enterprise = JOptionPane.showInputDialog(null, "Digite el nombre de la empresa: ");
        Empresa empresa =  new Empresa(enterprise);

        try{
            while(!continuar){
                try{
                    op = Byte.parseByte(JOptionPane.showInputDialog(null, Menu()));
                    switch (op){
                        case 1:
                            try{
                                JOptionPane.showMessageDialog(null, "ESCRIBA LA INFORMACION DEL NUEVO EMPLEADO ");
                                do{
                                    nombre = JOptionPane.showInputDialog(null, "Digite el nombre de la persona: ");
                                    puesto = JOptionPane.showInputDialog(null, "Digite el puesto de su empleado: ");
                                    salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el salario: "));
                                    if(puesto.equalsIgnoreCase("Plaza Fija")){
                                        extensions = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la extension: "));
                                        PlazaFija square = new PlazaFija(nombre, puesto, salario, extensions);
                                        do {
                                            opdocs = Byte.parseByte(JOptionPane.showInputDialog(null, "1. Agregar Documento. \n 2. Eliminar Documento. \n 3. Salir. \n Opcion: "));
                                            if (opdocs == 1) {
                                                tipodocument = JOptionPane.showInputDialog(null, "Digite tipo de documento (Dui, Nit, Licencia): ");
                                                if(tipodocument.equalsIgnoreCase("Dui")){
                                                    document = JOptionPane.showInputDialog(null, "DUI: ");
                                                }
                                                else if(tipodocument.equalsIgnoreCase("Nit")){
                                                    document = JOptionPane.showInputDialog(null, "NIT: ");
                                                }
                                                else if(tipodocument.equalsIgnoreCase("Licencia")){
                                                    document = JOptionPane.showInputDialog(null, "Licencia: ");
                                                }
                                                number = JOptionPane.showInputDialog(null, "Digite su numero: ");
                                                Documento docs = new Documento(document, number);
                                                square.addDocumento(docs);

                                            } else if (opdocs == 2) {
                                                eliminardocs = JOptionPane.showInputDialog(null, "Digite el nombre del documento a eliminar: ");
                                                square.removeDocumento(eliminardocs);
                                            } else if (opdocs == 3) {
                                                JOptionPane.showMessageDialog(null, "Regresando....");
                                            }
                                        }while(opdocs!=3);
                                        empresa.addEmpleado(square);
                                    }
                                    else if(puesto.equalsIgnoreCase("Servicio Profesional")){
                                        months = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la cantidad de meses de contrato: "));
                                        ServicioProfesional job = new ServicioProfesional(nombre, puesto, salario, months);
                                        do {
                                            opdocs = Byte.parseByte(JOptionPane.showInputDialog(null, "1. Agregar Documento. \n 2. Eliminar Documento. \n 3. Salir. \n Opcion: "));
                                            if (opdocs == 1) {
                                                tipodocument = JOptionPane.showInputDialog(null, "Digite tipo de documento (Dui, Nit, Licencia): ");
                                                if(tipodocument.equalsIgnoreCase("Dui")){
                                                    document = JOptionPane.showInputDialog(null, "DUI: ");
                                                }
                                                else if(tipodocument.equalsIgnoreCase("Nit")){
                                                    document = JOptionPane.showInputDialog(null, "NIT: ");
                                                }
                                                else if(tipodocument.equalsIgnoreCase("Licencia")){
                                                    document = JOptionPane.showInputDialog(null, "Licencia: ");
                                                }
                                                number = JOptionPane.showInputDialog(null, "Digite su numero: ");
                                                Documento docs = new Documento(document, number);
                                                job.addDocumento(docs);

                                            } else if (opdocs == 2) {
                                                eliminardocs = JOptionPane.showInputDialog(null, "Digite el nombre del documento a eliminar: ");
                                                job.removeDocumento(eliminardocs);
                                            } else if (opdocs == 3) {
                                                JOptionPane.showMessageDialog(null, "Regresando....");
                                            }
                                        }while(opdocs!=3);
                                        empresa.addEmpleado(job);
                                    }
                                    try{
                                        if(!verificarNombre(nombre)){
                                            throw new WrongNameException("Nombre inválido!");
                                        }
                                        JOptionPane.showMessageDialog(null,"Funciona");
                                        verificacion=true;
                                    }
                                    catch(WrongNameException exc){
                                        JOptionPane.showMessageDialog(null, exc.getMessage());
                                    }
                                }while(!verificacion);
                            }
                            catch(NullPointerException exc){
                                JOptionPane.showMessageDialog(null,"Regresando...");
                            }
                            finally{
                                verificacion = false;
                            }
                            break;
                        case 2:
                            JOptionPane.showInputDialog(null, "Escribe la información del empleado a despedir: ");
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Lista de empleados: ");
                            break;
                        case 4:
                            JOptionPane.showMessageDialog(null, "Calculando sueldo: ");
                            break;
                        case 5:
                            JOptionPane.showMessageDialog(null, "Mostrando totales: ");
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Saliendo....");
                            continuar = true;
                            break;
                        default:
                            try{
                                if(op > 5 || op < 0){
                                    throw new OutOfLimitsException("Opción Incorrecta!");
                                }
                            }
                            catch (OutOfLimitsException exc){
                                JOptionPane.showMessageDialog(null, exc.getMessage());
                            }
                    }
                }
                catch (NumberFormatException exc){
                    JOptionPane.showMessageDialog(null, "Incorrecto! por favor ingrese un numero.");
                }

            }
        }
        catch(NullPointerException exc){
            JOptionPane.showMessageDialog(null,"Saliendo...");
        }
        finally {
        }
    }

    private static String Menu(){
        return "MENU\n" +
                "1. Agregar empleado\n" +
                "2. Despedir empleado\n" +
                "3. Ver lista de empleados\n" +
                "4. Calcular sueldo\n" +
                "5. Mostrar totales\n" +
                "0. Salir";
    }

    public static boolean verificarNombre(String a){
        for (char aux:a.toCharArray()) {
            if(aux>64 && aux<91){
            }
            else if(aux>96 && aux<123){
            }
            else{
                return false;
            }
        }
        return true;
    }


}
