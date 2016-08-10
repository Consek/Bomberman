

package bomberman;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Highscore {
    
    public Rectangle przyciskPlay=new Rectangle(500,550,150,50);
    int[] czasGracza;
    String[] imieGracza;
    int pierwszyRzad=100;
    int drugiRzad=430;
    
    public void renderowanie(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        
        Font fnt0=new Font("Monospaced",Font.BOLD,50);
        Font fnt1=new Font("Monospaced",Font.BOLD,30);
        Font fnt2=new Font("Monospaced",Font.BOLD,40);
        Font fnt3=new Font("Monospaced",Font.BOLD,17);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("Tabela wyników",140, 50);
        g.setFont(fnt2);
        g.drawString("Survival",70,90);
        g.drawString("Normal", 430, 90);
        g.setFont(fnt1);
        g.drawString("Powrót", przyciskPlay.x+24, przyciskPlay.y+35);
        g2d.draw(przyciskPlay);
        g.setFont(fnt3);
        czasGracza=tablicaTxt.odczytWynikow("NORMAL");
        imieGracza=tablicaTxt.odczytWynikowStr("NORMAL");
        for(int i=0;i<imieGracza.length;i++){
            g.drawString((i+1)+"."+imieGracza[i], drugiRzad-17, 120+40*i);
            g.drawString(czasGracza[i]/60+" min i "+czasGracza[i]%60+" sek", drugiRzad, 140+40*i);}
        czasGracza=tablicaTxt.odczytWynikow("HARDCORE");
        imieGracza=tablicaTxt.odczytWynikowStr("HARDCORE");
        for(int i=0;i<imieGracza.length;i++){
            g.drawString((i+1)+"."+imieGracza[i], pierwszyRzad-17, 120+40*i);
            g.drawString(czasGracza[i]/60+" min i "+czasGracza[i]%60+" sek", pierwszyRzad, 140+40*i);}
        
    }
    
}
