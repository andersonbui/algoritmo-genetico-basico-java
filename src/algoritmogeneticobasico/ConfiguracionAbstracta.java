/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico;

/**
 *
 * @author anson
 */
public abstract class ConfiguracionAbstracta {
    
    protected int numMaxGeneraciones;
    protected int tamanioPoblacion;

    public abstract Poblacion generarPoblacionInicial();

    public int getNumMaxGeneraciones() {
        return numMaxGeneraciones;
    }

    public void setNumMaxGeneraciones(int numMaxGeneraciones) {
        this.numMaxGeneraciones = numMaxGeneraciones;
    }

    public int getTamanioPoblacion() {
        return tamanioPoblacion;
    }

    public void setTamanioPoblacion(int tamanioPoblacion) {
        this.tamanioPoblacion = tamanioPoblacion;
    }
    
}
