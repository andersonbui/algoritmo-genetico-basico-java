/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogeneticobasico.circuitos;

/**
 *
 * @author anson
 */
public class pruebas_EvaluarExpresion {

    public void main1(String[] args) {
        String cadena = "ab1#ab2#";
        char[] vector;
//        vector = {'a', 'b', '1', '#', 'a', 'b', '2', '#'};
        vector = cadena.toCharArray();
        Individuo ind = new Individuo(vector, inicialiar1());
        ind.evaluarExpresion(inicialiar1(), vector);
    }

    public static TablaDVerdad inicialiar1() {

        int[][] tabla = {
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {1, 0, 1, 1},
            {1, 1, 1, 1}};

        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 2, 2,10);
        return tablaDeVerdad;
    }

    //sumador completo
    public static void main(String[] args) {
        String cadena = "caa1aa1ac1b332ca3ac13a3c1c44cb2c1baa34c23ba2424cb32aa2c22aa2aac2c4c222aa31ab2b1b31ca22aa3aba12bc21233ac2bab214cbaa4b2a1bc44ba1cc2a1123a41ac3bca4443bb1a4bab3b21cbb22331abc1ccb32a11221cb2c32bcac24bb44ac1aca33bba141ab2bc21cb1b321324bccabbbabbb4bab44443412224caa41c4ac3b144a41ccba1b423ca33b2a2cbcb4aac21c2bb4a21bbcaa1314ca4ab3b3ab441cb1a1c3baa32c1142bc33a3442bb3224ccca43341ca3bac343b4c3aacc24accb22141cab42ab41214acb312123ac2ac2bcb1aa424b4aa1c4cc21ac2c3c343bb3cb2142aa33a4cbc1cb211abb343ba1c1a1bb143131#cb2bc1ba14cc32cc44a21b2#";
//        String cadena = "abb3cc311aa3bcc311abc11aa3bb3c11222#abb3c11aa3bc11ab122#";
        char[] vector;
//        vector = {'a', 'b', '1', '#', 'a', 'b', '2', '#'};
        vector = cadena.toCharArray();
        Individuo ind = new Individuo(vector, inicialiar2());
        System.out.println("resultao: "+ind.evaluarExpresion(inicialiar2(), vector));
    }

    public static TablaDVerdad inicialiar2() {
        int[][] tabla = {
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1},
            {0, 1, 0, 0, 1},
            {0, 1, 1, 1, 0},
            {1, 0, 0, 0, 1},
            {1, 0, 1, 1, 0},
            {1, 1, 0, 1, 0},
            {1, 1, 1, 1, 1}};
        TablaDVerdad tablaDeVerdad = new TablaDVerdad(tabla, 3, 2,10);
        return tablaDeVerdad;
    }
}
