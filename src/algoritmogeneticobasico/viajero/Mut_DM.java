/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.viajero;

import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.Mutacion;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yesmi
 */
public class Mut_DM extends Mutacion {

    @Override
    public Cromosoma mutar(Cromosoma cromosoma) {
//        System.out.println("--inicio mutacion--\n");
        Individuo indi = ((Individuo) cromosoma);
        int[] vector = indi.getVector();

        Random rand = new Random();
        //tamanio de subristra
        int tamanioSubristra = rand.nextInt(vector.length / 2);
//        int tamanioSubristra = 4;
        int inicioSubristra = rand.nextInt(vector.length - tamanioSubristra);
//        int inicioSubristra = 1;
        int posicionTraslado = rand.nextInt(vector.length - tamanioSubristra);
//        int posicionTraslado = 3;

        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<Integer> subristra = new ArrayList<>();
        for (int i = 0; i < vector.length; i++) {
            if (i < inicioSubristra || i >= inicioSubristra + tamanioSubristra) {
                lista.add(vector[i]);
            } else {
                subristra.add(vector[i]);
            }
        }
        lista.addAll(posicionTraslado, subristra);
        for (int i = 0; i < vector.length; i++) {
            vector[i]=lista.get(i);
        }
//        System.out.println("--termino mutacion--\n");
        return indi;
    }

}
