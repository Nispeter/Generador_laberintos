/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

/**
 *  Proxy que modifica las variables nesesarias desde el panelBotonesSur hacia el laberinto
 * @author Nisp
 */
public class Modificador {
    
    private Laberinto l;
    private Movil m;
    public int modActual;

    /**
     * Constructor de Modificador que establece las propiedades de la clase.
     * @param l
     * @param m 
     */
    public Modificador(Laberinto l,Movil m){ 
        this.l=l;this.m = m;
    }

    /**
     * Modificador, cambia el valor de los tiles y por ende la pared del tile
     * @param i valor del tile especifico
     */
    public void cambiar(int i){
        l.list.get(i).setVal(modActual);
    } 

    /**
     *  Selecciona  que modificacion se le aplicara a los tiles
     * @param val
     */
    public void modificarTiles(int val){
        switch (val){
            case 0:
                modActual = 0;
                break;
            case 1:
                modActual = 1;
                break;
            case 2:
                modActual = 2;
                break;
        }
    }
    
    /**
     * metodo que randimiza el laberinto desde el panelBotonesSur
     */
    public void randomizar(){
        l = Laberinto.getInstancia();
        l.setAll(100,100,10,10);    
        m.setXY(625,625);
        m.setPosicion(0);
        m.resetRebotes();
        l.crearLaberinto();
        m.creaRuta();
        
    }
    
    /**
     * Cuenta los rebotes del movil
     * @return 
     */
    public int contarRebotes(){
        return m.getRebotes();
    }
    
    /**
     * Metodo auxiliar para poner el puntero del movil en modificador
     * @param m 
     */
    public void setMovil(Movil m){
        this.m=m;
    }
}
