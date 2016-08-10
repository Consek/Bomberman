package bomberman;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Bomberman extends JFrame {

    public Bomberman() {
        add(new Plansza());        
        setTitle("Bomberman 2D");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                JFrame ex = new Bomberman();
                ex.setVisible(true);                
            }
        });
    }
}
