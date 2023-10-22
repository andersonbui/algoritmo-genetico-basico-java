/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

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
public class Configuracion extends ConfiguracionAbstracta {

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
    public Configuracion(int numMaxGeneraciones, int tamanioPoblacion, int longitudMaximaPermitida, TablaDVerdad tablaDeVerdad) {
        this.numMaxGeneraciones = numMaxGeneraciones;
        this.tamanioPoblacion = tamanioPoblacion;
        this.longitudMaximaPermitida = longitudMaximaPermitida;
        this.tablaDeVerdad = tablaDeVerdad;
    }

    /**
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
     * @param numEntradas
     * @return 
     */
    public char[] generarIndividuo(int numSalidas, int numEntradas) {
        ArrayList<Character>[] lista = new ArrayList[numSalidas];
        char[] vector;
        int k = 0;
        vector = new char[generarSubArbol(lista, numSalidas, numEntradas)];
        
        for (int i = 0; i < numSalidas; i++) {
            for (char w : lista[i]) {
                vector[k++] = w;
            }
            vector[k++] = '#';
        }
        return vector;
    }
    /**
     * 
     * @param listas arreglo de listas ya creada de caracteres de tamanio igual a numSalidas
     * @param numSalidas
     * @param numEntradas
     * @return numero total elementos en las listas
     */
    public int generarSubArbol(ArrayList<Character>[] listas ,int numSalidas, int numEntradas){
        char charTerminal = '-';
        char charFuncional = '$';
        int tamanioTotal = 0;
        Random rand = new Random();
        for (int i = 0; i < numSalidas; i++) {
            listas[i] = new ArrayList<>();
            //todo incian con un miniarbol de la raiz y dos hojas, no más.
            listas[i].add(charTerminal);
            //cracion aleatorio del individuo
            while (rand.nextInt(100) < 99 && listas[i].size() < longitudMaximaPermitida)//90 % de probabilidad que se siga produciendo nodos
            {
                int posicionCadena = rand.nextInt(listas[i].size());//posicion dentro del arbol
                //si es un caracter terminal, entonces, reemplazar por un nuevo arbol
                if (listas[i].get(posicionCadena).equals(charTerminal)) {
                    listas[i].remove(posicionCadena);
                    listas[i].add(posicionCadena, charTerminal);
                    listas[i].add(posicionCadena + 1, charTerminal);
                    listas[i].add(posicionCadena + 2, charFuncional);
                }
            }
//            System.out.println("[" + i + "]cadena: ");
//            for (char unchar : lista[i]) {
//                System.out.print(unchar);
//            }
//            System.out.println("");
            tamanioTotal += listas[i].size();
        }
        
        for (int i = 0; i < numSalidas; i++) {
            for (int t = 0; t < listas[i].size(); t++) {
                //asignar una representacion respectiva a cada elemento funcional y terminal
                if (listas[i].get(t) == charFuncional) {
                    listas[i].set(t, (char) (rand.nextInt(4) + 49));//numeros enpiezan en el codigo 49
                } else if (listas[i].get(t) == charTerminal) {
                    listas[i].set(t, (char) (rand.nextInt(numEntradas) + 97));//letras minusculas enpiezan en el codigo 97
                }
            }
        }
        return tamanioTotal + numSalidas;
    }

}
