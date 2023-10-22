/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogeneticobasico;

/**
 *
 * @author yesmi
 */
public abstract class  Cromosoma {
    protected double valor;

    public Cromosoma(double valor) {
        this.valor = valor;
    }

    public abstract void evaluar();
    
    public abstract int compareTo(Cromosoma ind);

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
        
    @Override
    public abstract Object clone();    
}
