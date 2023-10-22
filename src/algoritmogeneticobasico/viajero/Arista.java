/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.viajero;

/**
 *
 * @author yesmi
 */
public class Arista {

    int ciudad1;
    int ciudad2;
    float peso;

    public Arista(int ciudad1, int ciudad2, float peso) {
        this.ciudad1 = ciudad1;
        this.ciudad2 = ciudad2;
        this.peso = peso;
    }

    public int getCiudad1() {
        return ciudad1;
    }

    public void setCiudad1(int ciudad1) {
        this.ciudad1 = ciudad1;
    }

    public int getCiudad2() {
        return ciudad2;
    }

    public void setCiudad2(int ciudad2) {
        this.ciudad2 = ciudad2;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Arista{" + "ciudad1=" + ciudad1 + ", ciudad2=" + ciudad2 + ", peso=" + peso + '}';
    }

}
