/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.viajero;

import algoritmogeneticobasico.ConfiguracionAbstracta;
import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.Poblacion;
import java.util.ArrayList;
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
    int numCiudades;
    ArrayList<Arista> listaAristas;

    /**
     *
     * @param numMaxGeneraciones
     * @param numCiudades
     * @param tamanioPoblacion
     * @param listaAristas
     *
     */
    public Configuracion(int numMaxGeneraciones,int numCiudades, int tamanioPoblacion, ArrayList<Arista> listaAristas) {
        this.numMaxGeneraciones = numMaxGeneraciones;
        this.tamanioPoblacion = tamanioPoblacion;
        this.numCiudades = numCiudades;
        this.listaAristas = listaAristas;
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
        int[] vect = new int[numCiudades];
        Individuo ind = new Individuo(listaAristas);
        
        do {
            rellenar(vect);
            ind.setVector(vect);
        } while (!existeCaminos(vect) || yaExiste(poblacion,ind));
        return ind;
    }

    public boolean yaExiste(Poblacion poblacion,Individuo indi) {
        for(Cromosoma cromo:poblacion.getCromosomas()){
            if(((Individuo)cromo).equals(indi)){
                return true;
            }
        }
        return false;
    }

    /**
     * comprueba que el camino, creado por vect, exista
     *
     * @param vect
     * @return
     */
    public boolean existeCaminos(int[] vect) {
        int anterior = 0;
        //camino normal en el vector
        for (int i = 0; i < vect.length; i++) {
            if (i != 0) {
                for (int k = 0; k < listaAristas.size(); k++) {
                    if (((listaAristas.get(k).ciudad1 == anterior && listaAristas.get(k).ciudad2 == vect[i])
                            || (listaAristas.get(k).ciudad2 == anterior && listaAristas.get(k).ciudad1 == vect[i]))) {
                        return true;
                    }
                }
            }
            anterior = vect[i];
        }
        //ultimo tramo, que es el primer vertice aon el ultimo
        int ultimo = vect.length - 1;
        for (int k = 0; k < listaAristas.size(); k++) {
            if (((listaAristas.get(k).ciudad1 == vect[0] && listaAristas.get(k).ciudad2 == vect[ultimo])
                    || (listaAristas.get(k).ciudad2 == vect[ultimo] && listaAristas.get(k).ciudad1 == vect[0]))) {
                return true;
            }
        }
        return false;
    }

    /**
     * rellenar el vector supuesto camino solucion
     *
     * @param vector
     */
    public void rellenar(int[] vector) {
        ArrayList<Integer> lista = new ArrayList();
        for (int i = 1; i <= numCiudades; i++) {
            lista.add(i);
        }
        int i = 0;
        while (!lista.isEmpty()) {
            Random rand = new Random();
            vector[i] = lista.remove(rand.nextInt(lista.size()));
            i++;
        }
    }
    
    
}
