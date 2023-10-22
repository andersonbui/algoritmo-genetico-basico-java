/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico;

import algoritmogeneticobasico.Cromosoma;
import java.util.ArrayList;
import java.util.ArrayList;

/**
 *
 * @author ANDERSONANF
 */
public class Poblacion {

    protected ArrayList<Cromosoma> cromosomas;

    public Poblacion(ArrayList<Cromosoma> cromosomas) {
        this.cromosomas = cromosomas;
    }

    public Poblacion() {
        cromosomas = new ArrayList<Cromosoma>();
    }

    @Override
    protected Object clone() {
        Poblacion pobl = new Poblacion();
        for (Cromosoma cromosoma : cromosomas) {
            pobl.getCromosomas().add((Cromosoma) cromosoma.clone());
        }
        return pobl;
    }

    /**
     * @return @pdGenerated default getter
     */
    public java.util.ArrayList<Cromosoma> getCromosomas() {
        if (cromosomas == null) {
            cromosomas = new ArrayList<>();
        }
        return cromosomas;
    }

    /**
     * @return @pdGenerated default iterator getter
     */
    public java.util.Iterator getIteratorCromosoma() {
        if (cromosomas == null) {
            cromosomas = new ArrayList<>();
        }
        return cromosomas.iterator();
    }

    /**
     * @param newCromosomas
     * @pdGenerated default setter
     */
    public void setCromosomas(java.util.ArrayList<Cromosoma> newCromosomas) {
        removeAllCromosoma();
        for (java.util.Iterator iter = newCromosomas.iterator(); iter.hasNext();) {
            addIndividuo((Cromosoma) iter.next());
        }
    }

    /**
     * @pdGenerated default add
     * @param newCromosoma
     */
    public void addIndividuo(Cromosoma newCromosoma) {
        if (newCromosoma == null) {
            return;
        }
        if (this.cromosomas == null) {
            this.cromosomas = new ArrayList<>();
        }
        if (!this.cromosomas.contains(newCromosoma)) {
            this.cromosomas.add(newCromosoma);
        }
    }

    /**
     * @pdGenerated default remove
     * @param oldCromosoma
     */
    public void removeCromosoma(Cromosoma oldCromosoma) {
        if (oldCromosoma == null) {
            return;
        }
        if (this.cromosomas != null) {
            if (this.cromosomas.contains(oldCromosoma)) {
                this.cromosomas.remove(oldCromosoma);
            }
        }
    }

    /**
     * @pdGenerated default removeAll
     */
    public void removeAllCromosoma() {
        if (cromosomas != null) {
            cromosomas.clear();
        }
    }

    public int size() {
        if (this.cromosomas == null) {
            this.cromosomas = new ArrayList<>();
        }
        return cromosomas.size();
    }

    public double getEvaluacion() {
        double total = 0;
        for (Cromosoma cromosoma : cromosomas) {
            total += cromosoma.getValor();
        }
        return total;
    }
    
    public void evaluar() {
        for (Cromosoma cromosoma : cromosomas) {
            cromosoma.evaluar();
        }
    }
    public Cromosoma elitismo() {
        Cromosoma mejorCromosoma = null;
        if (!cromosomas.isEmpty()) {
            mejorCromosoma = cromosomas.get(0);
            for (Cromosoma cromosoma : cromosomas) {
                if (mejorCromosoma.compareTo(cromosoma) < 0) {
                    mejorCromosoma = cromosoma;
                }
            }
        }
        return mejorCromosoma;
    }

}
