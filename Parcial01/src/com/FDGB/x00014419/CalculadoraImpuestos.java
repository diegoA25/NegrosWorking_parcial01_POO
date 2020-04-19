package com.FDGB.x00014419;

public final class CalculadoraImpuestos {
    private static double totalRenta = 0.00f;
    private static double totalISSS = 0.00f;
    private static double totalAFP = 0.00f;

    private CalculadoraImpuestos(){
    }

    //funcion sin excepciones
    //si se repite el calculo de un empleado es como que si se le volviera a pagar
    public static double calcularPago(Empleado a){
        //Servicio profesional
        if(a instanceof ServicioProfesional){
            double renta = 0.1f * a.getSalario();
            totalRenta += renta;
            return a.getSalario() - renta;
        }
        //plaza fija
        else{
            double afp = 0.0625f * a.getSalario();
            double isss = 0.03 * a.getSalario();
            double restante = a.getSalario() - afp - isss;
            double renta = 0;
            if(restante > 0.01 && restante < 472.00){
                renta = 0;
            }
            else if(restante > 472.01 && restante < 895.24){
                renta = 0.1 * (restante - 472) +17.67;
            }
            else if(restante > 895.25 && restante < 2038.10){
                renta = 0.2 * (restante - 895.24) +60;
            }
            else if(restante > 2038.10){
                renta = 0.3 * (restante - 2038.10) +288.57;
            }
            totalISSS += isss;
            totalAFP += afp;
            totalRenta += renta;
            return restante - renta;
        }
    }


    public static String mostrarTotales(){
        return "Totales \n" +
                "Total de ISSS: $" + totalISSS +"\n" +
                "Total de AFP: $" + totalAFP + "\n" +
                "Total de Renta: $" + totalRenta;
    }
}