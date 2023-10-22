package algoritmogeneticobasico.viajero;

import algoritmogeneticobasico.AlgoritmoGeneticoBasico;
import algoritmogeneticobasico.Cromosoma;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yesmi
 */
public class Individuo extends Cromosoma {

    int[] vector;
    ArrayList<Arista> listaAristas;

    public Individuo(ArrayList<Arista> listaAristas) {
        super(0.0);
        this.listaAristas = listaAristas;
    }

    public Individuo(int[] vector, ArrayList<Arista> listaAristas) {
        super(0.0);
        this.vector = vector;
        this.listaAristas = listaAristas;
    }

    @Override
    public void evaluar() {
        int anterior = 0;
        valor = 0;
        for (int i = 0; i < vector.length; i++) {
            if (i != 0) {
                valor += buscarIndividuos(anterior, vector[i]).getPeso();
            }
            anterior = vector[i];
        }
    }

    public Arista buscarIndividuos(int ciudad1, int ciudad2) {
        {
            for (Arista ind : listaAristas) {
                if ((ind.ciudad1 == ciudad1 && ind.ciudad2 == ciudad2) || (ind.ciudad1 == ciudad2 && ind.ciudad2 == ciudad1)) {
                    return ind;
                }
            }
            return null;
        }
    }

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }

    public double getEvaluacion() {
        return valor;
    }

    public void setEvaluacion(double evaluacion) {
        this.valor = evaluacion;
    }

    public ArrayList<Arista> getListaAristas() {
        return listaAristas;
    }

    public void setListaAristas(ArrayList<Arista> listaAristas) {
        this.listaAristas = listaAristas;
    }

    @Override
    public boolean equals(Object obj) {
        int[] vec2 = ((Individuo) obj).getVector();
        for (int i = 0; i < vector.length; i++) {
            if (vec2[i] != vector[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object clone() {
        Individuo indiv = new Individuo(vector.clone(), listaAristas);
        indiv.setEvaluacion(valor);
        return indiv;
    }

    /**
     * Compara los valores correspondientes de cada individuo, para este caso el individuo
     * con menor valor es el mejor, por lo tanto, este optiene un mayor puntaje si se 
     * compara con otro de mayor valor.
     * @param ind
     * @return 
     */
    @Override
    public int compareTo(Cromosoma ind) {
        return (this.getValor() < ind.getValor() ? 1 : (this.getValor() > ind.getValor()? -1:0)); 
    }

//    @Override
//    public String toString() {
//        return "Individuo{" + "vector=" + Arrays.toString(vector) + "; valor=" + valor +'}';
//    }
    @Override
    public String toString() {
        return "valor;" + valor + ' ';
    }

}
