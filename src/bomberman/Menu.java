

package bomberman;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
    
    public Rectangle przyciskPlay=new Rectangle(Plansza.WIDTH/2+150,150,400,90);
    public Rectangle przyciskSurv=new Rectangle(Plansza.WIDTH/2+150,250,400,90);
    public Rectangle przyciskEdyt=new Rectangle(Plansza.WIDTH/2+150,350,400,90);
    public Rectangle przyciskExit=new Rectangle(Plansza.WIDTH/2+150,450,400,90);
    
    public void renderowanie(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        
        Font fnt0=new Font("Monospaced",Font.BOLD,50);
        Font fnt1=new Font("Monospaced",Font.BOLD,30);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Bomberman 2D",170, 100);
        g.setFont(fnt1);
        g.drawString("Rozpocznij Grę", przyciskPlay.x+70, przyciskPlay.y+55);
        g2d.draw(przyciskPlay);
        g.drawString("Tryb Survival", przyciskSurv.x+87, przyciskSurv.y+55);
        g2d.draw(przyciskSurv);
        g.drawString("Highscore", przyciskEdyt.x+123, przyciskEdyt.y+55);
        g2d.draw(przyciskEdyt);
        g.drawString("Wyjście", przyciskExit.x+140, przyciskExit.y+55);
        g2d.draw(przyciskExit);
        
    }
    
}
