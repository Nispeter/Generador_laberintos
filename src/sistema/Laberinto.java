package sistema;
import auxiliar.Generadores;
import java.awt.*;
import java.util.ArrayList;

/**
 *  Clase que crea un sistema para que el movil interactue, una zona de inicio y una meta.
 *  Contiene tiles y paredes
 * @author Nisp
 */
public class Laberinto {
    
    private static Laberinto ins = null;//Singleto de laberinto
    public int x,y;
    private int dimX,dimY,scale;
    private Tile meta;
    public static  ArrayList<Tile> list;

    /**
     * constructor privado de Laberinto el cual junto con getInstancia, combierte a esta clase en un singleton.
     */
    private Laberinto(){}
    
    /**
     * metodo que devuelve la instancia del Laberinto
     * @return ins
     */
    public static Laberinto getInstancia(){
        if(ins==null)ins=new Laberinto();
        return ins;
    }
    
    /**
     *  Metodo que configura gran parte del laberinto fuerda del constructor
     *  para que pueda ser llamada en cualquier instancia.
     * @param x posicion relativa del Laberinto respecto del ambiente
     * @param y posicion relativa del Laberinto respecto del ambiente
     * @param dX dimenciones del laberinto 
     * @param dY 
     * @param scale escala del laberinto
     */
    public  void setAll(int x, int y,int dX,int dY){
        this.x=x;this.y=y;this.scale=1;
        dimX=dX+2;dimY=dY+2;
        list = new ArrayList<Tile>();
    }
    
    /**
     * Establece la base del laberinto como una matriz de 2x2 y asi generar las paredes en todos los bordes y tiles entremedio.
     *Crear laberinto Crea tiles con valores de Modificacion aleatoreos para que sean paredes o caminos
     */
    public void crearLaberinto(){
        int bordes = (2*(dimX-2+dimY));
        int salida = 2;
        int contadorBordes=0;
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                Tile aux;
                int valAux;
                if(i == 0 || i == dimX-1 ||j == 0 ||j == dimY-1 ){  
                   valAux = -1;
                   ++contadorBordes;
                   if(contadorBordes == salida){
                       if(i!=j&&((i != 11 || j != 0)&&(i!= 0 || j != 11)))valAux = 9;
                   }
                }
                else {
                    valAux = auxiliar.Generadores.generarRandom(1);
                    int[] safeX = {1,1,2,2,dimX-2,dimX-2,dimX-3,dimX-3};
                    int[] safeY = {1,2,1,2,dimY-2,dimY-3,dimY-2,dimY-3};
                    for (int k = 0; k < 8; k++) {
                        if(i == safeX[k] && j == safeY[k] ){
                           valAux = 0; 
                        }
                    }
                    if(i == dimX-2 && j == dimY-2) valAux = 0;
                    if(i == 1 && j == 1) valAux = 0;
                }
                aux = new Tile(x+50*i, y+50*j,valAux,scale);
                if(valAux == 9){
                    aux.setVal(0);
                    meta = aux;
                }
                if(aux!=null)list.add(aux);
            }
        }
    }
    
    /**
     * metodo que establece la forma basica e inicial del laberinto para evitar problemas de generacion
     */
    public void setStandard(){
        int[] standard = {
            -1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0,-1,
            -1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0,-1,
            -1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1,-1,
            -1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0,-1,
            -1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0,-1,
            -1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,-1,
            -1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1,-1,
            -1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1,-1,
            -1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0,-1,
            -1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1
        };
        for (int i = 0; i < list.size(); i++) {
            Tile aux = list.get(i);
            aux.setVal(standard[i]);
        }
    }
    
    public void paint(Graphics g){
        Polygon pol1=new Polygon();
        Polygon pol2=new Polygon();
        g.setColor(new Color(62,9,71));
        pol1.addPoint(x,y+600);pol1.addPoint(x+600,y+600);
        pol1.addPoint(x+620,y+620);pol1.addPoint(x+20,y+620);
        g.fillPolygon(pol1);
        
        g.setColor(new Color(131,3,153));
        pol2.addPoint(x+600,y);pol2.addPoint(x+620,y+20);
        pol2.addPoint(x+620,y+620);pol2.addPoint(x+600,y+600);
        g.fillPolygon(pol2);
        for (int i = 0; i < list.size(); i++) {
           Tile aux = list.get(i);
           if(aux!=null){
               aux.paint(g);
           }
        }
    }

    /**
     * retorna la meta del laberinto, es decir, hacia donde se dirige el movil
     * @return meta
     */
    public Tile getMeta(){
        return meta;
    }
}

/**
 * Clase que es parte del laberinto y es el espacio el cual podra tener una pared o vacio.
 * Tiene la facultad de devolver su pared si es que la tiene
 */
class Tile{
    
    public int x,y;
    private int val,scale,n;
    private Pared p;
    public boolean meta;
    
    /**
     * Constructor de tile que crea una pared y define si el tile es camino 0 pared (solo por razones de construccion).
     * @param x posicion relativa respecto de la posicion entregada por el laberinto
     * @param y
     * @param val valor del tile (1 para que contenga una pared parcial,-1 para que tenga una pared completa en los bordes)
     * @param scale escala entera relativa respecto del laberinto
     */
    public Tile(int x, int y, int val,int scale){
        this.scale = scale;n = Generadores.generarRandom(4)+1;
        this.x=(x)*scale;this.y=(y)*scale;this.val = val;
        if(val == 1){
            p = new Pared(this.x,this.y,scale);
            p.setPared(n);
        }
        if(val == -1){
            p = new Pared(this.x,this.y,scale);
            p.setPared(5);
        }
    }
    
    public void paint(Graphics g){
        if(val==0){
            g.setColor(new Color(171,109,17));
            g.fillRect(x, y, 50*scale, 50*scale);
            g.setColor(new Color(171,100,17));
            g.fillRect(x+5*scale, y+5*scale, 40*scale, 40*scale);
        }
        else if(val==1){
            //Polygon p3 = new Polygon();
            //Point aux = new Point ()
            g.setColor(new Color(171,109,17));
            g.fillRect(x, y, 50*scale, 50*scale);
            g.setColor(new Color(171,100,17));
            g.fillRect(x+5*scale, y+5*scale, 40*scale, 40*scale);
            g.setColor(new Color(247,167,49));
            g.fillRect(p.x, p.y, p.largo, p.ancho);
        }
        else if(val==-1){
            g.setColor(new Color(49,165,247));
            g.fillRect(x, y, 50*scale, 50*scale);
            g.setColor(new Color(49,188,247));
            g.fillRect(x+5*scale, y+5*scale, 40*scale, 40*scale);
        }
    }
    
    /**
     * metodo para modificar el valor de un tile y pasar de pared a camino y  viceversa
     * @param val define como se modificara el tile 
     */
    public void setVal(int val){
        this.val = val;
        if(val == 0)p = null;
        else if(val == 1 || val == 2){
            p = new Pared(x,y,scale);
            if(n<=4)n+=1;
            else n = 1;
            p.setPared(n);
        }
    }
    
    /**
     * Metodo para obtener la pared de un tile
     * @return p
     */
    public Pared getPared(){
        return p;
    }
    
    /**
     * devuelve el valor del tile
     * @return val
     */
    public int getVal(){
        return val;
    }

}

/**
 * clase que se encuentra en tiles con la facultad de poseer una pared y contiene un poligono que es con el cual
 * el movil choca para atravezar el laberinto.
 */
class Pared{
    
    public int x,y;
    public int ancho,largo;
    private int scale;
    
    /**
     * Constructor de la pared de un tile del laberinto
     * @param x posicion en y relativa al tile
     * @param y posicion en x relativa al tile
     * @param scale escala de los tiles
     */
    public Pared(int x, int y, int scale){
        this.x=x;this.y=y;this.scale=scale;
    }
    
    /**
     * define que tipo de pared tendra el tile, tiene 5 variantes
     * @param n es el tipo de pared asociada (varian el forma)
     */
    public void setPared(int n){
        switch (n){
            case 1:
                ancho = 10*scale;largo = 50*scale;
                break;
            case 3:
                y+=40*scale;
                ancho = 10*scale;largo = 50*scale;
                break;
            case 4:
                ancho = 50*scale;largo = 10*scale;
                break;
            case 2:
                x+=40*scale;
                ancho = 50*scale;largo = 10*scale;
                break;
            case 5:
                ancho = 50*scale;largo = 50*scale;
                break;
        }
        
    }

}
