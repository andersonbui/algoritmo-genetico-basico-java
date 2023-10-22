/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package versionesClases;

import algoritmogeneticobasico.circuitos.*;
import algoritmogeneticobasico.ConfiguracionAbstracta;
import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.Poblacion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author ANDERSONANF
 */
public class Configuracion_v1 extends ConfiguracionAbstracta {

    /**
     * generar supuesta solucion para cada individuo de la lista
     *
     * @return
     */
    int longitudMaximaPermitida;
    TablaDVerdad tablaDeVerdad;

    /**
     *
     * @param numMaxGeneraciones
     * @param longitudMaximaPermitida
     * @param tamanioPoblacion
     * @param tablaDeVerdad
     *
     */
    public Configuracion_v1(int numMaxGeneraciones, int tamanioPoblacion, int longitudMaximaPermitida, TablaDVerdad tablaDeVerdad) {
        this.numMaxGeneraciones = numMaxGeneraciones;
        this.tamanioPoblacion = tamanioPoblacion;
        this.longitudMaximaPermitida = longitudMaximaPermitida;
        this.tablaDeVerdad = tablaDeVerdad;
    }

    /**
     *
     * @return
     */
    @Override
    public Poblacion generarPoblacionInicial() {
        Poblacion poblacion = new Poblacion();
        while (poblacion.size() < tamanioPoblacion) {
            Cromosoma ind = generarIndividuo(poblacion);
            poblacion.addIndividuo(ind);
        }
        return poblacion;
    }

    /**
     * crear un individuo con su supuesta solucion de recorrido
     *
     * @return
     */
    Cromosoma generarIndividuo(Poblacion poblacion) {
        char[] vect;
        Individuo ind = new Individuo(tablaDeVerdad);
        vect = generarIndividuo(tablaDeVerdad.getNumSalidas(), tablaDeVerdad.getNumEntradas());
        ind.setVector(vect);
        return ind;
    }

    public boolean yaExiste(Poblacion poblacion, Individuo indi) {
        for (Cromosoma cromo : poblacion.getCromosomas()) {
            if (((Individuo) cromo).equals(indi)) {
                return true;
            }
        }
        return false;
    }

    /**
     * rellenar el vector supuesto camino solucion
     *
     * @param numSalidas
     * @param vector
     */
    
    public char[] generarIndividuo(int numSalidas, int numEntradas) {
        ArrayList<Character>[] lista = new ArrayList[numSalidas];
        char charTerminal = '-';
        char charFuncional = '$';
        int tamanioTotal = 0;
        char[] vector;
        Random rand = new Random();
        for (int i = 0; i < numSalidas; i++) {
            lista[i] = new ArrayList<>();
            //todo incian con un miniarbol de la raiz y dos hojas, no más.
            lista[i].add(charTerminal);
            //cracion aleatorio del individuo
            while (rand.nextInt(100) < 99 && lista[i].size() < longitudMaximaPermitida)//70 % de probabilidad que se siga produciendo nodos
            {
                int posicionCadena = rand.nextInt(lista[i].size());//posicion dentro del arbol
                //si es un caracter terminal, entonces, reemplazar por un nuevo arbol
                if (lista[i].get(posicionCadena).equals(charTerminal)) {
                    lista[i].remove(posicionCadena);
                    lista[i].add(posicionCadena, charTerminal);
                    lista[i].add(posicionCadena + 1, charTerminal);
                    lista[i].add(posicionCadena + 2, charFuncional);
                }
            }
//            System.out.println("[" + i + "]cadena: ");
//            for (char unchar : lista[i]) {
//                System.out.print(unchar);
//            }
//            System.out.println("");
            tamanioTotal += lista[i].size();
        }
        vector = new char[tamanioTotal + numSalidas];
        int k = 0;
//        System.out.print("\n\"");
        for (int i = 0; i < numSalidas; i++) {
            for (char w : lista[i]) {
                //asignar una representacion respectiva a cada elemento funcional y terminal
                if (w == charFuncional) {
                    vector[k++] = (char) (rand.nextInt(4) + 49);//cuatro opearaciones
                } else if (w == charTerminal) {
                    vector[k++] = (char) (rand.nextInt(numEntradas) + 97); //todas las posibles combinaciones
                } else {
                    vector[k++] = w;
                }
//                System.out.print(vector[k-1]);
            }
            vector[k++] = '#';
//            System.out.print(vector[k-1]);
        }
//        System.out.println("\"\nvector completo: " + Arrays.toString(vector));
        return vector;
    }

}
