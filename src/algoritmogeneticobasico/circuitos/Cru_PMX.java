/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

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
        char charSeparador = '#';
        int tamanioTotal = 0;

        int numSalidas = ((Individuo) dosIndiv[0]).getTablaV().numSalidas;
        ArrayList<Character>[] listaChar_1 = new ArrayList[numSalidas];
        ArrayList<Character>[] listaChar_2 = new ArrayList[numSalidas];
        for (int i = 0; i < numSalidas; i++) {
            listaChar_1[i] = new ArrayList<>();
            listaChar_2[i] = new ArrayList<>();
        }
        int contadorSalidas = 0;

        //individuo 1
        Individuo individuo = (Individuo) dosIndiv[0];
        char[] vector = individuo.getVector();
        for (int j = 0; j < vector.length && contadorSalidas < numSalidas; j++) {
            if (vector[j] == charSeparador) {
                contadorSalidas++;
                continue;
            }
            listaChar_1[contadorSalidas].add(vector[j]);
        }

        //individuo 2
        contadorSalidas = 0;
        individuo = (Individuo) dosIndiv[1];
        vector = individuo.getVector();
        for (int j = 0; j < vector.length && contadorSalidas < numSalidas; j++) {
            if (vector[j] == charSeparador) {
                contadorSalidas++;
                continue;
            }
            listaChar_2[contadorSalidas].add(vector[j]);
        }
//        System.out.println("lista1: " + Arrays.toString(listaChar_1));
//        System.out.println("lista2: " + Arrays.toString(listaChar_2));

        Random rand = new Random();
        ArrayList<Character> listaTotal_1 = new ArrayList<>();
        ArrayList<Character> listaTotal_2 = new ArrayList<>();
        for (int i = 0; i < numSalidas; i++) {
            ArrayList<Character> listaspeq_1 = listaChar_1[i];
            ArrayList<Character> listaspeq_2 = listaChar_2[i];

            int posicion1 = rand.nextInt(listaspeq_1.size());
            int posicion2 = rand.nextInt(listaspeq_2.size());

//            System.out.println("posicion_1: "+posicion1);
//            System.out.println("lista_1 completa: "+listaspeq_1);
            ArrayList<Character> subArbol1 = new ArrayList<>();
            posicion1 = extraerSubArbol(listaspeq_1, subArbol1, posicion1);
//            System.out.println("lista_1 incompleta: "+listaspeq_1);
//            System.out.println("sublista_1: "+subArbol1);
//
//            System.out.println("posicion_2: "+posicion2);
//            System.out.println("lista_2 completa: "+listaspeq_2);
            ArrayList<Character> subArbol2 = new ArrayList<>();
            posicion2 = extraerSubArbol(listaspeq_2, subArbol2, posicion2);
//            System.out.println("lista_2 incompleta: "+listaspeq_2);
//            System.out.println("sublista_2: "+subArbol2);

            listaspeq_1.addAll(posicion1, subArbol2);
            listaspeq_2.addAll(posicion2, subArbol1);

//            System.out.println("lista_1 resultado: "+listaspeq_1);
//            System.out.println("lista_2 resultado: "+listaspeq_2);
            listaTotal_1.addAll(listaspeq_1);
            listaTotal_1.add(charSeparador);
            listaTotal_2.addAll(listaspeq_2);
            listaTotal_2.add(charSeparador);
        }

        Cromosoma[] desendientes = new Cromosoma[2];
        //vectores que contienen todo el arbol completo de los hijos generados
        char[] vectorResultado_1 = new char[listaTotal_1.size()];
        char[] vectorResultado_2 = new char[listaTotal_2.size()];

        for (int p = 0; p < listaTotal_1.size(); p++) {
            vectorResultado_1[p] = listaTotal_1.get(p);
        }
        for (int p = 0; p < listaTotal_2.size(); p++) {
            vectorResultado_2[p] = listaTotal_2.get(p);
        }
        desendientes[0] = new Individuo(vectorResultado_1, ((Individuo) dosIndiv[0]).getTablaV());
        desendientes[1] = new Individuo(vectorResultado_2, ((Individuo) dosIndiv[1]).getTablaV());

//        System.out.println("descendiente_1: "+Arrays.toString(vectorResultado_1));
//        System.out.println("descendiente_2: "+Arrays.toString(vectorResultado_2));
        return desendientes;
    }

    public static int extraerSubArbol(ArrayList<Character> vector1, ArrayList<Character> subArbol, int posicion1) {
        int contador = 1;
        do {
            char carcter = vector1.remove(posicion1--);
            if (Character.isDigit(carcter)) {
                contador++;
            } else {
                contador--;
            }
            subArbol.add(0, carcter);
        } while (contador > 0);
        return posicion1 + 1;
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
                if ((temp[0] == pasada[0] && temp[1] == pasada[1])
                        || (temp[0] == pasada[1] && temp[1] == pasada[0])) {
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
