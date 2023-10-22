package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.AlgoritmoGeneticoBasico;
import algoritmogeneticobasico.ConfiguracionAbstracta;

/**
 * @author yesmi
 */
public class Main {

    public static void main(String[] args) {
//        int maxCorridas = 0;
        int numeroMaxGeneraciones = 100;
        int longitudMaximaPermitida = 500;
        int tamPoblacion = 500;
        TablaDVerdad tablaDeVerdad = inicialiar(longitudMaximaPermitida);

        ConfiguracionAbstracta config = new Configuracion(numeroMaxGeneraciones, tamPoblacion, longitudMaximaPermitida, tablaDeVerdad);

        AlgoritmoGeneticoBasico algoritmo = new AlgoritmoGeneticoBasico(new Cru_PMX(), new Mut_DM(), new Selec_Sorteo(), config);
        algoritmo.run();
    }

    public static TablaDVerdad inicialiar(int longitudMax) {

        int[][] tabla = {
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 1, 0, 0},
            {0, 0, 1, 1, 1},
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 1},
            {0, 1, 1, 0, 0},
            {0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 1, 1},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 0, 0, 0},
            {1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 4, 1, longitudMax);
        return tablaDeVerdad;
    }

    public static TablaDVerdad inicialiar1(int longitudMax) {

        int[][] tabla = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 3, 2, longitudMax);
        return tablaDeVerdad;
    }

    public static TablaDVerdad inicialiar4(int longitudMax) {

        int[][] tabla = {
            {0, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 0},
            {1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 3, 1, longitudMax);
        return tablaDeVerdad;
    }

    public static TablaDVerdad inicialiar2(int longitudMax) {

        int[][] tabla = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}};

        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 2, 1, longitudMax);
        return tablaDeVerdad;
    }
}
