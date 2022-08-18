/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import java.util.Random;

/**
 *clase que contiene distintos metodos generadores de numeros aleatoreos
 * @author Nisp
 */
public class Generadores {
    private static Random rnd;

    public Generadores(){
        
    }

    /**
     *genera un Angulo aleatorio 
     * @return
     */
    public static  double genererarAnguloRandom(){
        rnd = new Random();
        double n = rnd.nextDouble()%0.2;
        return n;
    }

    /**
     * genera un numero random entre 0 y max
     * @param max
     * @return
     */
    public static int generarRandom(int max){
        rnd = new Random();
        int n = rnd.nextInt(max +1);
        return n;
    }
    
    /**
     * genera un numero aleatoreo entre min y max
     * @param min
     * @param max
     * @return 
     */
    public static int generarRandomEntre(int min,int max){
        rnd = new Random();
        int n = (int)(Math.random()*(max - min +1) + min);
        return n;
    }

    /**
     *  genera el angulo especifico calculado para retroceder el movil si choca con una pared
     * @param an
     * @return
     */
    public static double generarAnguloVuelta(double an){
        double anguloVuelta = 0;
        double ang = an;
        Random r = new Random();
        anguloVuelta = -0.45 + (0.9) * r.nextDouble();
        anguloVuelta = ang + anguloVuelta + 1;
        return anguloVuelta;
    }
}

