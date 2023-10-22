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
public class prueba_mutar {

    public static void main(String[] args) {
        Mut_DM mut = new Mut_DM();
        Cromosoma indivi = new Individuo("ba4cadb24c13a24#bcc4abc21d1d124#".toCharArray(), inicialiar());
        System.out.println("mutado: " + mut.mutar(indivi));
    }

    public static TablaDVerdad inicialiar() {
        int[][] tabla = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 3, 2, 10);
        return tablaDeVerdad;
    }
}
