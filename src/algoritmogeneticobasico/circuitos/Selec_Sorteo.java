package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.viajero.*;
import algoritmogeneticobasico.Cromosoma;
import algoritmogeneticobasico.Poblacion;
import algoritmogeneticobasico.Seleccion;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author yesmi
 */
public class Selec_Sorteo extends Seleccion {

    @Override
    public Cromosoma[] seleccionar(Poblacion poblacion) {
        Cromosoma[] cromos = null;
        ArrayList<Cromosoma> listaCrom = poblacion.getCromosomas();
        Random rand = new Random();
        //numeros aleatorios para la seleccion de los dos candidatos a padre
        int tamanio = listaCrom.size();
        if (tamanio > 1) {
//        System.out.println("tamanio:" + tamanio + "\n");
            cromos = new Cromosoma[2];
            int uno = Math.abs(rand.nextInt(tamanio));
            tamanio--;
            int dos = Math.abs(rand.nextInt(tamanio));
            Cromosoma crmUno = null;
            Cromosoma crmDos = null;
            //extraer los dos cromosomas candidatos
            crmUno = listaCrom.remove(uno);
            crmDos = listaCrom.remove(dos);
            //el candidato con menor valor sera el padre
            if (crmUno.compareTo(crmDos) > 0) {
                cromos[0] = crmUno;
                listaCrom.add(crmUno);
            } else {
                cromos[0] = crmDos;
                listaCrom.add(crmDos);
            }

            tamanio = listaCrom.size();
            //numeros aleatorios para la seleccion de los dos candidatos a madre
//        System.out.println("tamanio:" + tamanio + "- uno:" + uno + "- dos:" + dos + "\n");
            if (tamanio > 1) {
                uno = Math.abs(rand.nextInt(tamanio));
                tamanio--;
                dos = Math.abs(rand.nextInt(tamanio));
                //extraer los dos cromosomas candidatos
                crmUno = listaCrom.remove(uno);
                crmDos = listaCrom.remove(dos);

                //el candidato con menor valor sera el madre
                if (crmUno.compareTo(crmDos) > 0) {
                    cromos[1] = crmUno;
                    listaCrom.add(crmUno);
                } else {
                    cromos[1] = crmDos;
                    listaCrom.add(crmDos);
                }
            }else{
                //remueve el ultimo
                cromos[1] = listaCrom.get(0);
            }
        }
        return cromos;
    }
}
