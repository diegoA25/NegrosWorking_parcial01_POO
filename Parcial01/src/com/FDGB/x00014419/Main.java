package com.FDGB.x00014419;

import javax.swing.*;
import java.util.List;

public class Main {

public static void main(String[] args) {
    boolean continuar = false;
    Byte op = 0;
    Byte opDocs = 0;
    boolean verificacion = false;
    String nombre = "\0";
    String nombreDocumentoEliminar = "\0";
    String númeroDocumento = "\0";
    String tipoDocumento = "\0";
    String puesto = "\0";
    String nombreEmpresa = "\0";
    double salario = 0.00f;
    int meses = 0;
    int extensión = 0;

    //Ingreso y manejo de excepciones de nombre de empresa
    do{
        try{
            nombreEmpresa = JOptionPane.showInputDialog(null, "Digite el nombre de la empresa");
            if(nombreEmpresa == null){
                throw new CancelButtonException("Saliendo...");
            }
            else if(nombreEmpresa.isEmpty()){
                throw new WrongCompanyNameException("Nombre invalido!");
            }
            verificacion=true;
        }
        catch (WrongCompanyNameException exc){
            JOptionPane.showMessageDialog(null,exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
        }
        catch(CancelButtonException exc){
            JOptionPane.showMessageDialog(null,exc.getMessage());
            verificacion = true;
            continuar = true;
        }
    }while(!verificacion);
    verificacion = false;

    Empresa empresa =  new Empresa(nombreEmpresa);

    while(!continuar){
        try{
            op = Byte.parseByte(JOptionPane.showInputDialog(null, menu()));
            switch (op){
                case 1:
                    try{
                        //Ingreso y manejo de excepciones de nombre
                        do{
                            nombre = JOptionPane.showInputDialog(null, "Digite el nombre de la persona");
                            if(nombre == null){
                                throw new CancelButtonException("Regresando...");
                            }
                            try{
                                if(!OnlyCharacters(nombre) || nombre.isEmpty()){
                                    throw new WrongNameException("Nombre inválido!");
                                }
                                verificacion=true;
                            }
                            catch(WrongNameException exc){
                                JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!verificacion);
                        verificacion = false;

                        //Ingreso y manejo de excepciones de puesto
                        do{
                            puesto = JOptionPane.showInputDialog(null, "Digite el puesto de la persona");
                            if(puesto == null){
                                throw new CancelButtonException("Regresando...");
                            }
                            try{
                                if(!(puesto.equalsIgnoreCase("Plaza Fija")||puesto.equalsIgnoreCase("Servicio Profesional")) || puesto.isEmpty()){
                                    throw new WrongPuestoException("Puesto inválido!");
                                }
                                verificacion=true;
                            }
                            catch(WrongPuestoException exc){
                                JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!verificacion);
                        verificacion = false;



                        //Ingreso y manejo de excepciones de salario
                        do{
                            try{
                                salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el salario"));
                                if(salario<=0){
                                    throw new WrongSalarioException("Salario inválido!");
                                }
                                verificacion=true;
                            }
                            catch (NumberFormatException exc){
                                if(exc.getMessage().equals("null")){
                                    throw new CancelButtonException("Regresando...");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número positivo",null, JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            catch (WrongSalarioException exc){
                                JOptionPane.showMessageDialog(null,exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!verificacion);
                        verificacion = false;

                        if(puesto.equalsIgnoreCase("Plaza Fija")){

                            //Ingreso y manejo de excepciones de extensión
                            do{
                                try{
                                    extensión = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la extensión"));
                                    if(extensión<0){
                                        throw new WrongExtensiónException("Extensión inválida!");
                                    }
                                    verificacion=true;
                                }
                                catch (NumberFormatException exc){
                                    if(exc.getMessage().equals("null")){
                                        throw new CancelButtonException("Regresando...");
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número positivo",null, JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                catch (WrongExtensiónException exc){
                                    JOptionPane.showMessageDialog(null,exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                }
                            }while(!verificacion);
                            verificacion = false;
                            PlazaFija empleadoPlazaFija = new PlazaFija(nombre, puesto, salario, extensión);
                            int documentosIngresados = 0;
                                do{
                                    try{
                                    opDocs = Byte.parseByte(JOptionPane.showInputDialog(null, menuDocs()));
                                    try{
                                        switch (opDocs){
                                            case 0:
                                                for(Documento a:empleadoPlazaFija.getDocumentos()){
                                                    documentosIngresados++;
                                                }
                                                if(documentosIngresados >0){
                                                    empresa.addEmpleado(empleadoPlazaFija);
                                                    JOptionPane.showMessageDialog(null,"Empleado Agregado\nRegresando al menu...");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Debe de ingresar un documento obligatoriamente...");
                                                    opDocs=1;
                                                }
                                                break;
                                            case 1:
                                                //Ingreso y manejo de excepciones de tipo de documento
                                                do{
                                                    tipoDocumento = JOptionPane.showInputDialog(null, "Digite el tipo de documento");
                                                    if(tipoDocumento == null){
                                                        throw new CancelButtonException("Regresando...");
                                                    }
                                                    try{
                                                        if(!documentosSinRepetición(empleadoPlazaFija.getDocumentos(),tipoDocumento) || !OnlyCharactersAndNumbers(tipoDocumento) || tipoDocumento.isEmpty()){
                                                            throw new WrongTypeOfDocumentException("Tipo de documento inválido!");
                                                        }
                                                        verificacion=true;
                                                    }
                                                    catch(WrongTypeOfDocumentException exc){
                                                        JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }while(!verificacion);
                                                verificacion = false;

                                                //Ingreso y manejo de excepciones de numero de documento
                                                do{
                                                    númeroDocumento = JOptionPane.showInputDialog(null, "Digite su número de "+tipoDocumento);
                                                    if(númeroDocumento == null){
                                                        throw new CancelButtonException("Regresando...");
                                                    }
                                                    try{
                                                        if(!OnlyNumbers(númeroDocumento) || númeroDocumento.isEmpty()){
                                                            throw new WrongNumberOfDocumentException("número de "+tipoDocumento+" inválido!");
                                                        }
                                                        verificacion=true;
                                                    }
                                                    catch(WrongNumberOfDocumentException exc){
                                                        JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }while(!verificacion);
                                                verificacion = false;
                                                Documento nuevoDocumento = new Documento(tipoDocumento, númeroDocumento);
                                                empleadoPlazaFija.addDocumento(nuevoDocumento);
                                                break;
                                            case 2:
                                                nombreDocumentoEliminar = JOptionPane.showInputDialog(null, "Digite el nombre del documento a eliminar");
                                                empleadoPlazaFija.removeDocumento(nombreDocumentoEliminar);
                                                break;
                                            default:
                                                try{
                                                    if(opDocs > 2 || opDocs < 0){
                                                        throw new OutOfLimitsException("Opción Inválida!, ingresar un número entre 0 y 2");
                                                    }
                                                }
                                                catch (OutOfLimitsException exc){
                                                    JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                }
                                                break;
                                        }
                                    }
                                    catch(NullPointerException exc){
                                        JOptionPane.showMessageDialog(null,"Regresando...");
                                    }
                                    catch(CancelButtonException exc){
                                        JOptionPane.showMessageDialog(null, exc.getMessage());
                                    }
                                    finally{
                                        verificacion = false;
                                    }
                                }
                                catch(NullPointerException exc){
                                    JOptionPane.showMessageDialog(null,"Regresando...");
                                }
                                catch (NumberFormatException exc){
                                    if(exc.getMessage().equals("null")){
                                        JOptionPane.showMessageDialog(null, "Regresando....");
                                        opDocs=0;
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número",null, JOptionPane.ERROR_MESSAGE);
                                        opDocs=1;
                                    }
                                }
                                finally{
                                    verificacion = false;
                                }
                            }while(opDocs!=0);
                        }
                        else if(puesto.equalsIgnoreCase("Servicio Profesional")){
                            //Ingreso y manejo de excepciones de meses
                            do{
                                try{
                                    meses = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite los meses de servicio profesional"));
                                    if(meses<=0){
                                        throw new WrongMonthsException("Meses inválidos!");
                                    }
                                    verificacion=true;
                                }
                                catch (NumberFormatException exc){
                                    if(exc.getMessage().equals("null")){
                                        throw new CancelButtonException("Regresando...");
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número positivo",null, JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                catch (WrongMonthsException exc){
                                    JOptionPane.showMessageDialog(null,exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                }
                            }while(!verificacion);
                            verificacion = false;

                            ServicioProfesional empleadoServicioProfesional = new ServicioProfesional(nombre, puesto, salario, meses);
                            int documentosIngresados = 0;
                            do{
                                try{
                                    opDocs = Byte.parseByte(JOptionPane.showInputDialog(null, menuDocs()));
                                    try{
                                        switch (opDocs){
                                            case 0:
                                                for(Documento a:empleadoServicioProfesional.getDocumentos()){
                                                    documentosIngresados++;
                                                }
                                                if(documentosIngresados >0){
                                                    empresa.addEmpleado(empleadoServicioProfesional);
                                                    JOptionPane.showMessageDialog(null,"Empleado Agregado\nRegresando al menu...");
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null,"Debe de ingresar un documento obligatoriamente...");
                                                    opDocs=1;
                                                }
                                                break;
                                            case 1:

                                                //Ingreso y manejo de excepciones de tipo de documento
                                                do{
                                                    tipoDocumento = JOptionPane.showInputDialog(null, "Digite el tipo de documento");
                                                    if(tipoDocumento == null){
                                                        throw new CancelButtonException("Regresando...");
                                                    }
                                                    try{
                                                        if(!OnlyCharactersAndNumbers(tipoDocumento) || tipoDocumento.isEmpty()){
                                                            throw new WrongTypeOfDocumentException("Tipo de documento inválido!");
                                                        }
                                                        verificacion=true;
                                                    }
                                                    catch(WrongTypeOfDocumentException exc){
                                                        JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }while(!verificacion);
                                                verificacion = false;

                                                //Ingreso y manejo de excepciones de numero de documento
                                                do{
                                                    númeroDocumento = JOptionPane.showInputDialog(null, "Digite su número de "+ tipoDocumento);
                                                    if(númeroDocumento == null){
                                                        throw new CancelButtonException("Regresando...");
                                                    }
                                                    try{
                                                        if(!OnlyNumbers(númeroDocumento) || númeroDocumento.isEmpty()){
                                                            throw new WrongNumberOfDocumentException("número de "+tipoDocumento+" inválido!");
                                                        }
                                                        verificacion=true;
                                                    }
                                                    catch(WrongNumberOfDocumentException exc){
                                                        JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }while(!verificacion);
                                                verificacion = false;
                                                Documento nuevoDocumento = new Documento(tipoDocumento, númeroDocumento);
                                                empleadoServicioProfesional.addDocumento(nuevoDocumento);
                                                break;
                                            case 2:
                                                nombreDocumentoEliminar = JOptionPane.showInputDialog(null, "Digite el nombre del documento a eliminar: ");
                                                empleadoServicioProfesional.removeDocumento(nombreDocumentoEliminar);
                                                break;
                                            default:
                                                try{
                                                    if(opDocs > 2 || opDocs < 0){
                                                        throw new OutOfLimitsException("Opción Inválida!, ingresar un número entre 0 y 2");
                                                    }
                                                }
                                                catch (OutOfLimitsException exc){
                                                    JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                                                }
                                                break;
                                        }
                                    }
                                    catch(NullPointerException exc){
                                        JOptionPane.showMessageDialog(null,"Regresando...");
                                    }
                                    catch(CancelButtonException exc){
                                        JOptionPane.showMessageDialog(null, exc.getMessage());
                                    }
                                    finally{
                                        verificacion = false;
                                    }
                                }
                                catch(NullPointerException exc){
                                    JOptionPane.showMessageDialog(null,"Regresando...");
                                }
                                catch (NumberFormatException exc){
                                    if(exc.getMessage().equals("null")){
                                        JOptionPane.showMessageDialog(null, "Regresando....");
                                        opDocs=0;
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número",null, JOptionPane.ERROR_MESSAGE);
                                        opDocs=1;
                                    }
                                }
                                finally{
                                    verificacion = false;
                                }

                            }while(opDocs!=0);
                        }
                    }
                    catch(NullPointerException exc){
                        JOptionPane.showMessageDialog(null,"Regresando...");
                    }
                    catch(CancelButtonException exc){
                        JOptionPane.showMessageDialog(null, exc.getMessage());
                    }
                    finally{
                        verificacion = false;
                    }
                    break;
                case 2:
                    try{
                        do{
                            nombre = JOptionPane.showInputDialog(null, "Digite el nombre del empleado a despedir");
                            if(nombre == null){
                                throw new CancelButtonException("Regresando...");
                            }
                            try{
                                if(!OnlyCharacters(nombre) || nombre.isEmpty()){
                                    throw new WrongNameException("Nombre inválido!");
                                }
                                verificacion=true;
                            }
                            catch(WrongNameException exc){
                                JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!verificacion);
                        verificacion = false;

                        empresa.quitEmpleado(nombre);
                    }
                    catch(NullPointerException exc){
                        JOptionPane.showMessageDialog(null,"Regresando...");
                    }
                    catch(CancelButtonException exc){
                        JOptionPane.showMessageDialog(null, exc.getMessage());
                    }
                    finally{
                        verificacion = false;
                    }
                    break;
                case 3:
                    int cont = 0;
                    boolean existe = false;
                    JOptionPane.showMessageDialog(null,"Lista de empleados de la empresa " + empresa.getNombre());
                    for(Empleado aux:empresa.getPlanilla()){
                        existe=true;
                        cont++;
                        String cadena = "\0";
                        for(Documento a:aux.getDocumentos()){
                            cadena+=a.getNombre()+": "+a.getNumero()+"\n";
                        }
                        if(aux instanceof PlazaFija){
                            JOptionPane.showMessageDialog(null,"Empleado "+cont+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: $"+aux.getSalario()+"\n"+"Extensión: "+((PlazaFija) aux).getExtensión());
                        }
                        else if(aux instanceof ServicioProfesional){
                            JOptionPane.showMessageDialog(null,"Empleado "+cont+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: $"+aux.getSalario()+"\n"+"Meses: "+((ServicioProfesional) aux).getMeses());
                        }
                    }
                    if(existe == false){
                        JOptionPane.showMessageDialog(null, "Empleados no existentes");
                    }
                    break;
                case 4:
                    int n;
                    String cadena = "\0";
                    boolean calculado = false;
                    int id = 0;
                    try{
                        do{
                            nombre = JOptionPane.showInputDialog(null, "Digite el nombre del empleado");
                            if(nombre == null){
                                throw new CancelButtonException("Regresando...");
                            }
                            try{
                                if(!OnlyCharacters(nombre) || nombre.isEmpty()){
                                    throw new WrongNameException("Nombre inválido!");
                                }
                                verificacion=true;
                            }
                            catch(WrongNameException exc){
                                JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                            }
                        }while(!verificacion);
                        verificacion = false;

                        for(Empleado aux:empresa.getPlanilla()){
                            if(aux.getNombre().equalsIgnoreCase(nombre) && calculado == false){
                                for(Documento a:aux.getDocumentos()){
                                    cadena+=a.getNombre()+": "+a.getNumero()+"\n";
                                }
                                if(aux instanceof PlazaFija){
                                    n= JOptionPane.showConfirmDialog(null,"¿Cacular el sueldo del siguiente empleado?"+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: $"+aux.getSalario()+"\n"+"Extensión: "+((PlazaFija) aux).getExtensión(),null,JOptionPane.YES_NO_OPTION);
                                    if(n == JOptionPane.YES_OPTION){
                                        calculado = true;
                                    }
                                    else{
                                    }
                                }
                                else if(aux instanceof ServicioProfesional){
                                    n = JOptionPane.showConfirmDialog(null,"¿Cacular el sueldo del siguiente empleado?"+"\n"+"Nombre: "+aux.getNombre()+"\n"+"Puesto: "+aux.getPuesto()+"\n"+cadena+"Salario: $"+aux.getSalario()+"\n"+"Meses: "+((ServicioProfesional) aux).getMeses(),null,JOptionPane.YES_NO_OPTION);
                                    if(n == JOptionPane.YES_OPTION){
                                        calculado = true;
                                    }
                                    else{
                                    }
                                }
                            }
                            if(calculado==false){
                                id++;
                            }
                            else{
                            }
                        }
                        if (calculado == true){
                            JOptionPane.showMessageDialog(null,"Sueldo del empleado:"+ CalculadoraImpuestos.calcularPago(empresa.getPlanilla().get(id)));
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Empleado no existente");
                        }
                    }
                    catch(NullPointerException exc){
                        JOptionPane.showMessageDialog(null,"Regresando...");
                    }
                    catch(CancelButtonException exc){
                        JOptionPane.showMessageDialog(null, exc.getMessage());
                    }
                    finally{
                        verificacion = false;
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, CalculadoraImpuestos.mostrarTotales());
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo....");
                    continuar = true;
                    break;
                default:
                    try{
                        if(op > 5 || op < 0){
                            throw new OutOfLimitsException("Opción Inválida!, ingresar un número entre 0 y 5");
                        }
                    }
                    catch (OutOfLimitsException exc){
                        JOptionPane.showMessageDialog(null, exc.getMessage(),null, JOptionPane.ERROR_MESSAGE);
                    }
            }
        }
        catch (NumberFormatException exc){
            if(exc.getMessage().equals("null")){
                JOptionPane.showMessageDialog(null, "Saliendo....");
                continuar = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Opción inválida!, ingresar un número",null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }

    private static String menu(){
        return "MENU\n" +
                "1. Agregar empleado\n" +
                "2. Despedir empleado\n" +
                "3. Ver lista de empleados\n" +
                "4. Calcular sueldo\n" +
                "5. Mostrar totales\n" +
                "0. Salir";
    }

    private static String menuDocs(){
        return
                "MENU\n" +
                "1. Agregar Documento\n" +
                "2. Eliminar Documento\n" +
                "0. Salir";
    }

    private static boolean OnlyCharacters(String a){
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

    private static boolean OnlyCharactersAndNumbers(String a){
        for (char aux:a.toCharArray()) {
            if(aux>64 && aux<91){
            }
            else if(aux>96 && aux<123){
            }
            else if(aux>47 && aux<58){
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static boolean OnlyNumbers(String a){
        for (char aux:a.toCharArray()) {
            if(aux>47 && aux<58){
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static boolean documentosSinRepetición(List<Documento> documentos, String nombre){
        for(Documento a: documentos){
           if(a.getNombre().equals(nombre)){
               return false;
           }
        }
        return true;
    }
}