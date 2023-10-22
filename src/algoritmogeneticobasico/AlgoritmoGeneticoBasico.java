/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author yesmi
 */
public class AlgoritmoGeneticoBasico {

    Cruce cruce;
    Mutacion mutacion;
    Seleccion seleccion;
    protected Poblacion poblacion;
    ConfiguracionAbstracta configuracion;

    public AlgoritmoGeneticoBasico(Cruce cruce, Mutacion mutacion, Seleccion seleccion, ConfiguracionAbstracta configuracion) {
        this.cruce = cruce;
        this.mutacion = mutacion;
        this.seleccion = seleccion;
        this.configuracion = configuracion;
    }

    /**
     * implementacion de algoritmo genetico basico de forma generica
     */
    public void run() {

        Scanner scn = new Scanner(System.in);
        //Generacion de la poblacion inicial
        poblacion = configuracion.generarPoblacionInicial();
        //evalua cada individuo de la poblacion
        poblacion.evaluar();

        int contGeneraciones = configuracion.getNumMaxGeneraciones();

        ArrayList<Cromosoma> nuevaPobla;
        Poblacion vieja;
        while (contGeneraciones != 0) {
            contGeneraciones--;
            //Aplicacion de elitismo, el mejor individuo de la vieja generacion se pasa a la nueva
            Cromosoma crom = poblacion.elitismo();
            System.out.println("generacion[" + contGeneraciones + "]->mejor: " + crom.toString());
//            System.out.println("individuo: "+crom.);
            nuevaPobla = new ArrayList();
            //crom.evaluar();
            nuevaPobla.add(crom);
            vieja = (Poblacion) poblacion.clone();
            int contadorPoblacion = poblacion.cromosomas.size() / 2;
            for (int i = 0; i < contadorPoblacion; i++) {
                Cromosoma[] individuoss = seleccion.seleccionar(poblacion);
                Cromosoma[] dosDescendientes = cruce.cruzar(individuoss);
                dosDescendientes[0] = mutacion.mutar(dosDescendientes[0]);
                dosDescendientes[1] = mutacion.mutar(dosDescendientes[1]);
                for (Cromosoma individuo : dosDescendientes) {
                    individuo.evaluar();
                    nuevaPobla.add(individuo);
                }
//                System.out.println("["+i+"]individuo: " + Arrays.toString(dosDescendientes));
//                System.out.println("digite:\n");
            }
            //--idea comparar poblaciones antes de reemplazar --------------------------------------------------------
            Poblacion nuevaPoblac = new Poblacion(nuevaPobla);
            double evalNueva = nuevaPoblac.getEvaluacion();
            double evalVieja = vieja.getEvaluacion();
                this.poblacion = nuevaPoblac;
        }
    }

}
