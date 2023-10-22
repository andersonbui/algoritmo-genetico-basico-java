/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.Cromosoma;

/**
 *
 * @author anson
 */
public class pruebas_cruze {
    public static void main(String[] args) {
        Cru_PMX cruze = new Cru_PMX();
        Cromosoma[] dosIndiv = new Individuo[2];
        dosIndiv[0] = new Individuo("ba4cadb24c13a24#bcc4abc21d1d124#".toCharArray(), inicialiar2());
        dosIndiv[1] = new Individuo("cb3dd24#cba32abcac22334#".toCharArray(), inicialiar2());
        cruze.cruzar(dosIndiv);
    }
    
    public static TablaDVerdad inicialiar2() {
        int[][] tabla = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 3, 2,10);
        return tablaDeVerdad;
    }
}
