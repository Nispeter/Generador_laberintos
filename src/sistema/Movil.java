
package sistema;
import geometricas.Angular;
import java.awt.Graphics;
import java.awt.*;

/**
 * clase Movil es un automata que recorre el laberinto chocando con paredes
 * Es un poligono que se puede mover y recorre una ruta
 */
class Movil {

    private int x,y,posicion;
    private double ang,escala=1.0;
    private int scale;
    private Polygon p,p2,p3;
    private Ruta r;
    private Laberinto l;
    private int rebotes;
    
    /**
     * Construccion del Movil que pone los parametros de este
     * @param x posicion en x 
     * @param y posicion en y
     * @param l puntero al laberinto 
     * @param labscale escala del laberinto
     */
    public Movil(int x, int y,Laberinto l, int labscale){
        this.x = x;this.y = y;this.l=l;scale = labscale;
        ang =0.0;escala=0.5;rebotes = 0;
        this.creaRuta();
        generaForma();
    }
    
    /**
     * retorna la cantidad de rebotes
     * @return rebores
     */
    public int getRebotes(){
        return rebotes;
    }
    public void resetRebotes(){
        rebotes = 0;
    }
    public void creaRuta(){
        r = new Ruta(x,y,l.getMeta().x,l.getMeta().y);
    }
    public void setXY(int x,int y){
        this.x=x;this.y=y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setPosicion(int pos){
        posicion = pos;
    }
    public void paint(Graphics g){
        if(r!=null)r.paint(g);
        g.setColor(new Color(34,240,150));
        if(p!=null)g.fillPolygon(p);
        g.setColor(new Color(15,163,98));
        if(p2!=null)g.fillPolygon(p2);
    }
    
    /**
     * genera la forma del movil
     */
    private void generaForma(){
        p = new Polygon();
        Point aux = Angular.generaPunto(new Point(x,y), 40.0*escala, 0+ang);
        p.addPoint(aux.x,aux.y);
        aux = Angular.generaPunto(new Point(x,y), 40.0*escala, 0.75+ang);
        p.addPoint(aux.x,aux.y);
        aux = Angular.generaPunto(new Point(x,y), 40.0*escala, 1.25+ang);
        p.addPoint(aux.x,aux.y);
        /*aux = Angular.generaPunto(new Point(x,y), 40.0*escala, 1.75+ang);
        p.addPoint(aux.x,aux.y);*/
        
        p2 = new Polygon();
         aux = Angular.generaPunto(new Point(x,y), 25.0*escala, 0+ang);
        p2.addPoint(aux.x,aux.y);
        aux = Angular.generaPunto(new Point(x,y), 25.0*escala, 0.75+ang);
        p2.addPoint(aux.x,aux.y);
        aux = Angular.generaPunto(new Point(x,y), 25.0*escala, 1.25+ang);
        p2.addPoint(aux.x,aux.y);
    }
    
    public void crecer(){
        escala = escala + 0.1;
    }
    
    public void girar(){
        ang+=0.1;
        this.generaForma();
    }
    /**
     * metodo que hace que el movil pase por la ruta
     */
    public void mover(){
        if(posicion+20<r.maxPuntos()){
            Point donde = r.getPunto(posicion);
            Point otro = r.getPunto(posicion+20);
            Point otro2 = r.getPunto(posicion+5);
            x=donde.x;y=donde.y;
            ang = Angular.anguloPI2(donde, otro);
            generaForma();
            boolean colision = false;
            for (int i = 0; i < l.list.size(); i++) {
                Tile aux = l.list.get(i);
               if(aux.getPared()!=null){
                   if(otro2.x>=aux.getPared().x && otro2.x<=(aux.getPared().x+aux.getPared().largo) && otro2.y>=aux.getPared().y&&otro2.y<=(aux.getPared().y+aux.getPared().ancho)){
                   colision = true;
                   }
               }
               
            }
            if(!colision)posicion+=2;
            else if (colision){
                rebotes++;
                posicion = 0;
                r.cambiarRuta(x,y,ang);
                colision = false;
            }
            
        }
    }
}
