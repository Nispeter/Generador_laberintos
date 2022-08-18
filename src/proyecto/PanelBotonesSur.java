package proyecto;
import sistema.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sistema.Laberinto;

/**
 *  Clase que contiene los botones para modificar al proyecto caracteristicas del proyecto
 *
 * @author Nisp
 */
public class PanelBotonesSur extends JPanel {
    /**
     *
     * @param pc referencia del proxy modificador que se le pasas a panelBotonesSur.
     */
    public PanelBotonesSur(Modificador pc){
        //usamos layout por defecto: flowlayout: no se hace nada
        this.setBackground(new Color(255,183,77));  
        ButtonGroup bg = new ButtonGroup();
        SeleccionModificar aux = new SeleccionModificar("Pared",pc);
        this.add(aux);bg.add(aux);
        aux = new SeleccionModificar("Camino",pc);
        this.add(aux);bg.add(aux);
        aux = new SeleccionModificar("Automata",pc);
        this.add(aux);bg.add(aux);
        BotonRandomLab br = new BotonRandomLab("Randomizar",pc);
        this.add(br);
        
    }
}

/**
 * 
 * Clase que contiene 3 botonces y un puntero al proxy modificador
 */
class SeleccionModificar extends JRadioButton  implements ActionListener{
    private Modificador a;
    private String cual;
    /**
     * 
     * @param s String correspondiente al boton.
     * @param a Modificador que conecta el panelBotonesSur y el ambiente para modificar los tiles del laberinto
     */
    public SeleccionModificar (String s,Modificador a){
        super(s);//super siempre va primero.
        this.a=a;
        cual = s;
        this.addActionListener(this);
    }
    /**
     * 
     * Metodo que cambia el valor de modificacion del proxy modificador.
     * 0 para cambiar a camino.
     * 1 para cambiar a Pared.
     * 2 para posicionar el automata.
     */
    public void actionPerformed(ActionEvent e) {  
        switch(cual){
            case "Pared":
                a.modificarTiles(1);
                break;
            case "Camino":
                a.modificarTiles(0);
                break;
            case "Automata":
                a.modificarTiles(2);
                break;

        }
        
    }
}

class BotonRandomLab extends JButton implements ActionListener{
    private Modificador m;
    public BotonRandomLab (String s,Modificador m){
        super(s);
        this.m=m;
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        m.randomizar();
    }

}


