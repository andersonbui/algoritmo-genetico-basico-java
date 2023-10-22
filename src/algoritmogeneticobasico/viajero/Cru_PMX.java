/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.viajero;

import algoritmogeneticobasico.Cruce;
import algoritmogeneticobasico.Cromosoma;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author yesmi
 */
public class Cru_PMX extends Cruce {

    @Override
    public Cromosoma[] cruzar(Cromosoma[] dosIndiv) {
//        System.out.println("--inicio cruce--\n");
        int tamanioRistra = 4;
        Random rand = new Random();
        int tamanio = ((Individuo) dosIndiv[0]).getVector().length;
        //vectores originales
        int[][] vectoresOrigin = new int[2][];
        vectoresOrigin[0] = ((Individuo) dosIndiv[0]).getVector();
        vectoresOrigin[1] = ((Individuo) dosIndiv[1]).getVector();
        //numeros aleatorios para la seleccion de los dos limites de las ristras
//        int inicioRistra = 1;
        int inicioRistra = rand.nextInt(tamanio - tamanioRistra);
//        int inicioRistra = 1;
//        System.out.println("inicioRistras: " + inicioRistra + "\n");
        int[][] vectores = new int[2][tamanio];
        //almacen de los rangos escogidos
        int[][] rangos = new int[2][tamanioRistra];
        rangos[0] = new int[tamanioRistra];
        rangos[1] = new int[tamanioRistra];
        //Inicializar vectores resultado

        for (int i = 0; i < tamanio; i++) {
            vectores[0][i] = -1;
            vectores[1][i] = -1;
        }

        //sacar vector de cruce
        for (int i = inicioRistra; i < inicioRistra + tamanioRistra; i++) {
            rangos[0][i - inicioRistra] = vectoresOrigin[0][i];
            rangos[1][i - inicioRistra] = vectoresOrigin[1][i];
            vectores[1][i] = vectoresOrigin[0][i];
            vectores[0][i] = vectoresOrigin[1][i];
        }
        rellenarVector(vectores[0], vectoresOrigin[0], rangos);
        rellenarVector(vectores[1], vectoresOrigin[1], rangos);
        Cromosoma[] cromos = new Cromosoma[2];
        cromos[0] = new Individuo(vectores[0], ((Individuo) dosIndiv[0]).getListaAristas());
        cromos[1] = new Individuo(vectores[1], ((Individuo) dosIndiv[1]).getListaAristas());
//        System.out.println("--termino cruce--\n");
        return cromos;
    }

    /**
     * se utiliza para rellenar de forma adecada las casillas fuera del rango de
     * intercambio
     *
     * @param vectores
     * @param vectorOrigin
     * @param vecIntercambio
     */
    public void rellenarVector(int[] vectores, int[] vectorOrigin, int[][] vecIntercambio) {
        for (int i = 0; i < vectores.length; i++) {
            if (vectores[i] == -1) {
                vectores[i] = vectorOrigin[i];
            } else {
                continue;
            }
            //busca los cruces para el valor actual de vectores[0][i] dentro de los posibles intercambios en "rangos"
            ArrayList<int[]> listCr = buscarCruce(vectores[i], vecIntercambio);
            ArrayList<int[]> pasadas = new ArrayList();
            while (!listCr.isEmpty()) {
                int[] vec = listCr.remove(0);
                vectores[i] = -1;
                //puede caer en un ciclo infinito
                if (yaExisteNumEnLista(vec[1], vectores)) {
                    if (listCr.isEmpty()) {
                        pasadas.add(vec);
                        listCr = buscarCruce(vec[1], vecIntercambio);
                        quitarPasadas(listCr, pasadas);
                    }
                } else {
                    vectores[i] = vec[1];
                    listCr.clear();
                    pasadas.clear();
                    break;
                }
            }
        }
    }

    public void quitarPasadas(ArrayList<int[]> lista, ArrayList<int[]> pasadas) {
        int[] temp;
        for (int[] pasada : pasadas) {
            for (int i = 0; i < lista.size(); i++) {
                temp = lista.get(i);
                if((temp[0] == pasada[0] && temp[1] == pasada[1]) ||
                    (temp[0] == pasada[1] && temp[1] == pasada[0])){
                    lista.remove(i);
                }
            }
        }
    }

    public ArrayList<int[]> buscarCruce(int numDeCruce, int[][] listaC) {
        int[] crec;
        ArrayList<int[]> listaCruces = new ArrayList<>();
        for (int i = 0; i < listaC[0].length; i++) {
            if (listaC[0][i] == numDeCruce) {
                crec = new int[2];
                crec[0] = listaC[0][i];
                crec[1] = listaC[1][i];
                listaCruces.add(crec);
            } else if (listaC[1][i] == numDeCruce) {
                crec = new int[2];
                crec[0] = listaC[1][i];
                crec[1] = listaC[0][i];
                listaCruces.add(crec);
            }
        }
        return listaCruces;
    }

    public boolean yaExisteNumEnLista(int num, int[] listaNum) {
        for (int i = 0; i < listaNum.length; i++) {
            if (listaNum[i] == num) {
                return true;
            }
        }
        return false;
    }
}
