package com.FDGB.x00014419;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean continuar = false;
        Byte op = 0;
        boolean verificacion = false;
        String nombre = "\0";
        try{
            while(!continuar){
                op = Byte.parseByte(JOptionPane.showInputDialog(null, Menu()));
                switch (op){
                    case 1:
                        try{
                            JOptionPane.showMessageDialog(null, "ESCRIBA LA INFORMACION DEL NUEVO EMPLEADO ");
                            do{
                                nombre = JOptionPane.showInputDialog(null, "Digite el nombre de la persona");
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
        }
        catch(NumberFormatException exc){
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
