/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

import java.util.ArrayList;

/**
 *
 * @author anson
 */
public class prueba_extraerSubArbol {

    public static void main(String[] args) {
        char[] vector = "ba4cadb24c13a24".toCharArray();
        ArrayList<Character> subArbol = new ArrayList<>();
        ArrayList<Character> lista = new ArrayList<>();
        for (int j = 0; j < vector.length; j++) {
            lista.add(vector[j]);
        }
        int posicion = Cru_PMX.extraerSubArbol(lista, subArbol, 2);
        System.out.println("posicion final:" + posicion);
        System.out.println("subArbol:" +subArbol);
        System.out.println("lista antes:" +lista);
        lista.addAll(posicion,subArbol);
        System.out.println("lista despues:" +lista);
    }
}
