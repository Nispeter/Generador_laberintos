
package sistema;

import auxiliar.Generadores;
import geometricas.Angular;
import geometricas.RellenaConPuntos;
import java.awt.*;

/**
 *
 * Clase que establece la ruta que tomara el movil para atravezar el laberinto.
 * tiene la facultad de ser creada y modificarce cuando el movil que la creo choca contra una pared
 */
public class Ruta {
    private Polygon p;
    private int x,y,xf,yf;

    /**
     * constructor de la ruta
     * @param x posicion relativa al movil
     * @param y
     * @param xf posicion de la meta
     * @param yf
     */
    public Ruta(int x,int y,int xf, int yf){
       this.x=x;this.y=y;this.xf=xf;this.yf=yf;
       p = new Polygon();
       creaRuta();
    }
    private void creaRuta(){
        Polygon esqueleto = new Polygon();
        Point aux = Angular.generaPunto(new Point(x,y), 0, 0.5);
        esqueleto.addPoint(aux.x,aux.y);
        p.addPoint(esqueleto.xpoints[0],esqueleto.ypoints[0]);
        for (int i = 1; i < esqueleto.npoints; i++) {
            RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[i-1],esqueleto.ypoints[i-1]), new Point(esqueleto.xpoints[i],esqueleto.ypoints[i]), p);
            p.addPoint(esqueleto.xpoints[i],esqueleto.ypoints[i]);
        }
        RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[esqueleto.npoints-1],esqueleto.ypoints[esqueleto.npoints-1]),
        new Point(esqueleto.xpoints[0],esqueleto.ypoints[0]), p);
        RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[0],esqueleto.ypoints[0]),new Point(xf+25,yf+25),p);
    }

    /**
     * metodo que cambia la ruta a la posicion donde se encuentre el mouse
     * @param x posicion relativa al movil
     * @param y posicion relativa al movil
     * @param ang angulo del movil
     */
    public void cambiarRuta(int x,int y,double ang){
        this.x=x;this.y=y;
        p = new Polygon();
        Polygon esqueleto = new Polygon();
        Point aux = Angular.generaPunto(new Point(x,y), 0, ang);
        esqueleto.addPoint(aux.x,aux.y);
        p.addPoint(esqueleto.xpoints[0],esqueleto.ypoints[0]);
        for (int i = 1; i < esqueleto.npoints; i++) {
            RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[i-1],esqueleto.ypoints[i-1]), new Point(esqueleto.xpoints[i],esqueleto.ypoints[i]), p);
            p.addPoint(esqueleto.xpoints[i],esqueleto.ypoints[i]);
        }
        RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[esqueleto.npoints-1],esqueleto.ypoints[esqueleto.npoints-1]),
        new Point(esqueleto.xpoints[0],esqueleto.ypoints[0]), p);
        Point auxB = Angular.generaPunto(new Point(x,y), 50+Generadores.generarRandom(80), Generadores.generarAnguloVuelta(ang));
        RellenaConPuntos.nuevaLinea(new Point(esqueleto.xpoints[0],esqueleto.ypoints[0]),auxB,p);
        //Point auxC = Angular.generaPunto(auxB, 10, Generadores.generarAnguloVuelta(ang));
        //RellenaConPuntos.nuevaLinea(auxB,auxC,p);
        RellenaConPuntos.nuevaLinea(auxB,new Point(xf+25,yf+25),p);
    }

    /**
     *
     * @param i posicion relativa en la ruta
     * @return el punto donde se encuentra el movil respecto de la ruta
     */
    public Point getPunto(int i){
        Point r=null;
        if(i<p.npoints)r=new Point(p.xpoints[i],p.ypoints[i]);
        return r;
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g){
        g.setColor(new Color(125,23,163));
        g.drawPolyline(p.xpoints,p.ypoints,p.npoints);
    }

    /**
     *
     * @return retorna la cantidad maxima de puntos que tiene la ruta
     */
    public int maxPuntos(){
        int aux = p.npoints;
        return aux;
    }
}
