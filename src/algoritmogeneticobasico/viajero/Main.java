package algoritmogeneticobasico.viajero;

import algoritmogeneticobasico.AlgoritmoGeneticoBasico;
import algoritmogeneticobasico.ConfiguracionAbstracta;
import java.util.ArrayList;

/**
 * @author yesmi
 */
public class Main {
    public void main(String[] args) {
        int maxCorridas = 0;
        int numeroMaxGeneraciones = 200;
        int numCiudades = 8;
        int tamPoblacion = 90;
        ArrayList<Arista> listAristas = new ArrayList<>();
        inicialiar(listAristas);
        ConfiguracionAbstracta config = new Configuracion(numeroMaxGeneraciones,numCiudades, tamPoblacion, listAristas);
        
        AlgoritmoGeneticoBasico algoritmo = new AlgoritmoGeneticoBasico(new Cru_PMX(), new Mut_DM(), new Selec_Sorteo(), config);
        algoritmo.run();
    }

    private static void inicialiar(ArrayList<Arista> listAristas) {
        listAristas.add(new Arista(1, 2, 8));
        listAristas.add(new Arista(1, 3, 2));
        listAristas.add(new Arista(1, 4, 12));
        listAristas.add(new Arista(1, 5, 2));
        listAristas.add(new Arista(1, 6, 2));
        listAristas.add(new Arista(1, 7, 21));
        listAristas.add(new Arista(1, 8, 2));
        listAristas.add(new Arista(2, 3, 1));
        listAristas.add(new Arista(2, 4, 5));
        listAristas.add(new Arista(2, 5, 4));
        listAristas.add(new Arista(2, 6, 3));
        listAristas.add(new Arista(2, 7, 5));
        listAristas.add(new Arista(2, 8, 4));
        listAristas.add(new Arista(3, 4, 24));
        listAristas.add(new Arista(3, 5, 7));
        listAristas.add(new Arista(3, 6, 43));
        listAristas.add(new Arista(3, 7, 7));
        listAristas.add(new Arista(3, 8, 48));
        listAristas.add(new Arista(4, 5, 13));
        listAristas.add(new Arista(4, 6, 54));
        listAristas.add(new Arista(4, 7, 13));
        listAristas.add(new Arista(4, 8, 13));
        listAristas.add(new Arista(5, 6, 3));
        listAristas.add(new Arista(5, 7, 24));
        listAristas.add(new Arista(5, 8, 5));
        listAristas.add(new Arista(6, 7, 42));
        listAristas.add(new Arista(6, 8, 64));
        listAristas.add(new Arista(7, 8, 13));
    }

}
