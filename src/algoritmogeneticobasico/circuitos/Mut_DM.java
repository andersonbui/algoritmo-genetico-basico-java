/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.Mutacion;
import static algoritmogeneticobasico.circuitos.Cru_PMX.extraerSubArbol;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author yesmi
 */
public class Mut_DM extends Mutacion {

    @Override
    public Cromosoma mutar(Cromosoma cromosoma) {
        char charSeparador = '#';

        int numSalidas = ((Individuo) cromosoma).getTablaV().numSalidas;
        int numEntradas = ((Individuo) cromosoma).getTablaV().numEntradas;
        int longitudMax = ((Individuo) cromosoma).getTablaV().getLongitudMaxima();
        ArrayList<Character>[] listaChar_1 = new ArrayList[numSalidas];
        for (int i = 0; i < numSalidas; i++) {
            listaChar_1[i] = new ArrayList<>();
        }
        int contadorSalidas = 0;

        //individuo
        Individuo individuo = (Individuo) cromosoma;
        char[] vector = individuo.getVector();
        for (int j = 0; j < vector.length && contadorSalidas < numSalidas; j++) {
            if (vector[j] == charSeparador) {
                contadorSalidas++;
                continue;
            }
            listaChar_1[contadorSalidas].add(vector[j]);
        }

//        System.out.println("lista1: " + Arrays.toString(listaChar_1));
//        System.out.println("lista2: " + Arrays.toString(listaChar_2));
        Random rand = new Random();
        ArrayList<Character> listaTotal_1 = new ArrayList<>();
        for (int i = 0; i < numSalidas; i++) {
            ArrayList<Character> listaspequenia = listaChar_1[i];

            int posicion1 = rand.nextInt(listaspequenia.size());

//            System.out.println("posicion_1: "+posicion1);
//            System.out.println("lista_1 completa: "+listaspeq_1);
            ArrayList<Character> subArbol1 = new ArrayList<>();
            posicion1 = extraerSubArbol(listaspequenia, subArbol1, posicion1);
//            System.out.println("lista_1 incompleta: "+listaspeq_1);
//            System.out.println("sublista_1: "+subArbol1);
//
//            System.out.println("posicion_2: "+posicion2);
//            System.out.println("lista_2 completa: "+listaspeq_2);
            //------------------------------------------------------------------------------------------
//            ArrayList<Character>[] listas = new ArrayList[numSalidas];
            ArrayList<Character> subArbolTemp = new ArrayList<>();
            char charTerminal = '-';
            char charFuncional = '$';
            subArbolTemp = new ArrayList<>();
            //todo incian con un miniarbol de la raiz y dos hojas, no más.
            subArbolTemp.add(charTerminal);
            //cracion aleatorio del individuo
            while (rand.nextInt(100) < 90 && subArbolTemp.size() < longitudMax)//70 % de probabilidad que se siga produciendo nodos
            {
                int posicionCadena = rand.nextInt(subArbolTemp.size());//posicion dentro del arbol
                //si es un caracter terminal, entonces, reemplazar por un nuevo arbol
                if (subArbolTemp.get(posicionCadena).equals(charTerminal)) {
                    subArbolTemp.remove(posicionCadena);
                    subArbolTemp.add(posicionCadena, charTerminal);
                    subArbolTemp.add(posicionCadena + 1, charTerminal);
                    subArbolTemp.add(posicionCadena + 2, charFuncional);
                }
            }

            for (int t = 0; t < subArbolTemp.size(); t++) {
                //asignar una representacion respectiva a cada elemento funcional y terminal
                if (subArbolTemp.get(t) == charFuncional) {
                    subArbolTemp.set(t, (char) (rand.nextInt(4) + 49));//numeros enpiezan en el codigo 49
                } else if (subArbolTemp.get(t) == charTerminal) {
                    subArbolTemp.set(t, (char) (rand.nextInt(numEntradas) + 97));//letras minusculas enpiezan en el codigo 97
                }
            }

            //-------------------------------------------------------------------------------------------
            listaspequenia.addAll(posicion1, subArbolTemp);

//            System.out.println("lista_1 resultado: "+listaspequenia);
            listaTotal_1.addAll(listaspequenia);
            listaTotal_1.add(charSeparador);
        }

        //vectores que contienen todo el arbol completo de los hijos generados
        char[] vectorResultado_1 = new char[listaTotal_1.size()];

        for (int p = 0; p < listaTotal_1.size(); p++) {
            vectorResultado_1[p] = listaTotal_1.get(p);
        }
        Cromosoma descendiente = new Individuo(vectorResultado_1, ((Individuo) cromosoma).getTablaV());

//        System.out.println("descendiente_1: "+Arrays.toString(vectorResultado_1));
        return descendiente;
    }

}
