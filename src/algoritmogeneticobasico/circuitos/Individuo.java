package algoritmogeneticobasico.circuitos;

import algoritmogeneticobasico.Cromosoma;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author marylin
 */
public class Individuo extends Cromosoma {
    
    public static String[] funcionales = {"AND", "OR", "NAND", "NOR"};//PUEDE ELIMINARSE
    
    char[] vector;
    TablaDVerdad tablaV;

    public Individuo(TablaDVerdad tablaV) {
        super(0.0);
        this.tablaV = tablaV;
    }

    public Individuo(char[] vector, TablaDVerdad tablaV) {
        super(0.0);
        this.vector = vector;
        this.tablaV = tablaV;
    }

    @Override
    public void evaluar() {
        valor = evaluarExpresion(tablaV, this.vector);
    }

    /**
     * Evalua al individuo con respecto al numero de valores de salida que
     * coninciden con la tabla de verdad
     *
     * @param tabla tabla de verdad
     * @param vector contiene la posible solucion
     * @return vaor entero que indica cuantos valores de salida coincidienron
     * con la tabla de verdad + bonificacion por longitud del individuo
     */
    public int evaluarExpresion(TablaDVerdad tabla, char[] vector) {
        Stack pila = new Stack();
//        System.out.println("filas: " + tabla.getEstradasSalidas().length);
        int[] fila;
        int valorAcomulado = 0;
        for (int k = 0; k < tabla.getEstradasSalidas().length; k++) {
            //sacar cada fila de la tabla de verdad
            fila = tabla.getEstradasSalidas()[k];
            pila.clear(); //vaciar pila para una nueva fila de la tabla
//            System.out.println("fila: "+Arrays.toString(fila));
            //para cada fila de la tabla de verdad, evaluar el arbol de expresiones(Individuo)
            for (int i = 0; i < vector.length; i++) {
                if (((Character) vector[i]).equals('#')) {
//                    System.out.println("Se encontro una salida #\n");
                } else if (Character.isLetter(vector[i])) {
                    // getNumericValue(vector[i]) => a:10 y z:35
                    int posicion = Character.getNumericValue(vector[i]) - 10;
//                    System.out.println("valor letra[" + posicion + "]: " + fila[posicion]);
                    //ahora a:0 y z:25
                    pila.push(fila[posicion]);
                } else if (Character.isDigit(vector[i])) {
                    //saca dos valores para realizar operacion
                    int uno = (int) pila.pop();
                    int dos = (int) pila.pop();
                    int posicionOperador = Integer.parseInt(String.valueOf(vector[i]));
                    pila.push(realizarOperacion(uno, dos, posicionOperador));
//                    System.out.println("operador[" + funcionales[posicionOperador - 1] + "]");
                } else {
                    System.out.println("ERROR! HUBO UN VALOR NO PERMITIDO EN EVALUAR EXPRESION\n");
                }
            }
            //recorre todos los resultados
            int numResultado = 0;
            int numEntradas = tabla.getNumEntradas();
            //el resultado queda en la pila
            //comparar el resultado con el de la tabla
            while (numResultado < tabla.getNumSalidas()) {

                if (fila[numEntradas + numResultado++] == (int) pila.pop()) {
                    valorAcomulado++;
//                    System.out.print("iguales");
                } else {
//                    System.out.print("diferentes");
                }
            }
//            System.out.print("--");
        }
//        System.out.println("Valor: " + valorAcomulado);
        int bonificacion = 0;
        
        if (valorAcomulado == tablaV.getEstradasSalidas().length*tablaV.numSalidas) {
            /// Bonificacióon = Longitudmax - LongitudIndividuo - Numero de operadores.
            bonificacion = tablaV.getLongitudMaxima() - vector.length - contarOperadores(vector);
        }
        return valorAcomulado + bonificacion;
    }

    /**
     * Cuenta cuantas signos de operacion (1,2,3,4) hay en vector
     *
     * @param vector
     * @return
     */
    public int contarOperadores(char[] vector) {
        int contador = 0;
        for (char unChar : vector) {
            if (Character.isDigit(unChar)) {
                contador++;
            }
        }
//        System.out.println("vector: "+Arrays.toString(vector)+"-> numeros Operadores: "+contador);
        return contador;
    }

    public int realizarOperacion(int uno, int dos, int operador) {
        switch (operador) {
            case 1:
                return uno & dos;
            case 2:
                return uno | dos;
            case 3:
                return ~(uno & dos);
            case 4:
                return ~(uno | dos);
            default:
                return -1;

        }
    }

    public char[] getVector() {
        return vector;
    }

    public void setVector(char[] vector) {
        this.vector = vector;
    }

    public double getEvaluacion() {
        return valor;
    }

    public void setEvaluacion(double evaluacion) {
        this.valor = evaluacion;
    }

    @Override
    public boolean equals(Object obj) {
        char[] vec2 = ((Individuo) obj).getVector();
        for (int i = 0; i < vector.length; i++) {
            if (vec2[i] != vector[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        Individuo indiv = new Individuo(vector.clone(), tablaV);
        indiv.setEvaluacion(valor);
        return indiv;
    }

    /**
     * Compara los valores correspondientes de cada individuo, para este caso el
     * individuo con menor valor es el mejor, por lo tanto, este optiene un
     * mayor puntaje si se compara con otro de mayor valor.
     *
     * @param ind
     * @return
     */
    @Override
    public int compareTo(Cromosoma ind) {
        return (this.getValor() > ind.getValor() ? 1 : (this.getValor() < ind.getValor() ? -1 : 0));
    }

    public TablaDVerdad getTablaV() {
        return tablaV;
    }

    public void setTablaV(TablaDVerdad tablaV) {
        this.tablaV = tablaV;
    }

    @Override
    public String toString() {
        return "valor=" + valor + "vector=" + Arrays.toString(vector);
    }

}
