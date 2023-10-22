/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

import java.util.Arrays;

/**
 *
 * @author yesmi
 */
public class TablaDVerdad {

    int[][] estradasSalidas;
    int numEntradas;
    int numSalidas;
    int longitudMaxima;

    public TablaDVerdad(int[][] estradasSalidas, int numEntradas, int numSalidas,int longitudMaxima) {
        this.estradasSalidas = estradasSalidas;
        this.numEntradas = numEntradas;
        this.numSalidas = numSalidas;
        this.longitudMaxima = longitudMaxima;
    }

    @Override
    public String toString() {
        return "tablaDeVerdad{" + "estradasSalidas=" + Arrays.toString(estradasSalidas) + '}';
    }

    public int[][] getEstradasSalidas() {
        return estradasSalidas;
    }

    public void setEstradasSalidas(int[][] estradasSalidas) {
        this.estradasSalidas = estradasSalidas;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public int getNumSalidas() {
        return numSalidas;
    }

    public void setNumSalidas(int numSalidas) {
        this.numSalidas = numSalidas;
    }

    public int getLongitudMaxima() {
        return longitudMaxima;
    }

    public void setLongitudMaxima(int longitudMaxima) {
        this.longitudMaxima = longitudMaxima;
    }
    
}
