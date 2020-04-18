package com.DEAC.x00044919;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean continuar = false;
        Byte op = 0;
        while(!continuar){

            op = Byte.parseByte(JOptionPane.showInputDialog(null, Menu()));

            switch (op){
                case 1:
                    JOptionPane.showInputDialog(null, "Escribe la informacion del nuevo empleado: ");
                    break;
                case 2:
                    JOptionPane.showInputDialog(null, "Escribe la informacion del empleado a despedir: ");
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
                            throw new OutOfLimitsException("Opcion Incorrecta!");
                        }
                    }
                    catch (OutOfLimitsException exc){
                        JOptionPane.showMessageDialog(null, exc.getMessage());
                    }
            }
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
}
