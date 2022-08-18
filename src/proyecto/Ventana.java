package proyecto;
import sistema.Ambiente;
import java.awt.BorderLayout;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Nisp
 */
public class Ventana extends JFrame {
    private Ambiente pc;
    private PanelBotonesSur pbs;

    /**
     *Metodo constructor de ventana
     */
    public Ventana(){
        this.setLayout(new BorderLayout());
        
        pc = new Ambiente();//se debe cambiar nombre de acuerdo al tema del proyecto
        this.add(pc, BorderLayout.CENTER);
        pbs = new PanelBotonesSur(pc.getMod());
        this.add(pbs, BorderLayout.SOUTH);
       
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1400,1000); 
        this.setVisible(true);  
    }
}
