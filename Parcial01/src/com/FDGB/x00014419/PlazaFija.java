package com.FDGB.x00014419;

public class PlazaFija extends Empleado{
    private int extensión =0;

    public PlazaFija(String nombre, String puesto, double salario, int extensión) {
        super(nombre, puesto, salario);
        this.extensión = extensión;
    }

    public int getExtensión() {
        return extensión;
    }

    public void setExtensión(int extensión) {
        this.extensión = extensión;
    }
}