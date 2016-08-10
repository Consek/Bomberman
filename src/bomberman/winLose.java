

package bomberman;

import static bomberman.Plansza.B_HEIGHT;
import static bomberman.Plansza.B_WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;


public class winLose {
    
    public static void win(Graphics g){
        String msg = "Gratulacje!\n Wygrałeś!!";
        Font small = new Font("Helvetica", Font.BOLD, 20);
        FontMetrics metr = g.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    
    public static void gameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = g.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
}
