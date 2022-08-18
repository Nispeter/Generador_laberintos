package sistema;
import sistema.Laberinto;
import sistema.Ruta;
import sistema.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseListener;

/**
 * Ambiente es donde se crean las clases principaes de la aplicacion y se administran las relaciones
 * @author Nicolas Esteban Parra Garcia
 */
public class Ambiente extends JPanel implements ActionListener,MouseListener{
    
    private Timer t;
    private Movil m;
    private Laberinto l;
    private int scale;
    private JLabel label1;
    public Modificador mod;

    /**
     *El Cosntructor de ambiente coloca el color del backround, y crea nuevas instancias de  modificador y movil .
     *
     */
    public Ambiente(){
         scale = 1;
         this.setBackground(new Color(39,117,173));
         l = Laberinto.getInstancia();
         int labX = 100, labY = 100;
         l.setAll(labX,labY,10,10);
         l.crearLaberinto();
         l.setStandard();
         m = new Movil(labX+525,labY+525,l,scale);
         mod=new Modificador(l,m);
         label1 = new JLabel("Rebotes: "+mod.contarRebotes());
         this.add(label1);
         t = new Timer(1,null);
         t.addActionListener(this);
         this.addMouseListener(this);
         t.start();
     }
    
     public void paint(Graphics g){
         super.paint(g);
         if(l!=null)l.paint(g);
         if(m!=null)m.paint(g);
         
     }

    public void actionPerformed(ActionEvent e) {
        mod.setMovil(m);
        label1.setText("Rebotes: "+mod.contarRebotes());
        if(m!=null)m.mover();
        this.repaint();
    }

    /**
     * setModActual cambia el modificador actual del ambiente
     * @param modActual mod actual es el modificador seleccionado desde el button group de panelBotonesSur que dicta que tipo de modificacion se le hace
     * al laberinto con los clicks
     */
    public void setModActual(int modActual){
        this.mod.modActual=modActual;
    }

    /**
     *  Devuelve el modificador para que se pueda usar desde panelBotonesSur
     * @return mod
     */
    public Modificador getMod(){
        return mod;
    }
 
    /**
     * Funcion que se activa cuando el Mouse Listener encuentra un click en el panel central.
     * Si el boton clickeado es el izquierdo del mouse y si la posicion del mouse esta sobre una tile
     * Esta cambia segun la opcion seleccionada en el modificador.
     * 
    */
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
            if(mod.modActual ==1 || mod.modActual == 0){
                for (int i = 0; i < l.list.size(); i++) {
                Tile aux = l.list.get(i);
                if(e.getX()>=aux.x && e.getX()<=(aux.x)+50*scale && e.getY()>=aux.y&&e.getY()<=(aux.y)+50*scale&& aux.getVal()!=-1){
                    mod.cambiar(i);
                    
                    }
                }
            }
            if (mod.modActual == 2) {
                m = new Movil(e.getX(),e.getY(),l,scale);
            }
            this.repaint();
        }
        
    }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    
    
}





