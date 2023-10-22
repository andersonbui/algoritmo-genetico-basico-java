/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.viajero.Individuo;
import algoritmogeneticobasico.viajero.Mut_DM;
import java.util.Arrays;

/**
 *
 * @author ANDERSONANF
 */
public class Pruebas_Mut_DM {

    public static void main(String[] args) {
        Mut_DM cru = new Mut_DM();
        int[] vector1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] vector2 = {3, 7, 5, 1, 6, 8, 2, 4};
        Cromosoma[] vecIndi = new Individuo[2];
        vecIndi[0] = new Individuo(vector1, null);
        vecIndi[1] = new Individuo(vector2, null);
        System.out.println("vecIndi 0: "+vecIndi[0].toString());
        System.out.println("vecIndi 1: "+vecIndi[1].toString());
        Cromosoma vecIndi2 = cru.mutar(vecIndi[0]);
        System.out.println(vecIndi2);
    }
}
