/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.Poblacion;

/**
 *
 * @author anson
 */
public class pruebas_generarIndividuo {
    public static void main(String[] args) {
        Configuracion poblac = new Configuracion(1, 5, 15, null);
        poblac.generarIndividuo(3, 2);
    }
}
