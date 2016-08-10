

package bomberman;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Mysz implements MouseListener{
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
             public Rectangle przyciskPlay=new Rectangle(Plansza.WIDTH/2+150,150,400,90);
             public Rectangle przyciskSurv=new Rectangle(Plansza.WIDTH/2+150,250,400,90);
             public Rectangle przyciskEdyt=new Rectangle(Plansza.WIDTH/2+150,350,400,90);
             public Rectangle przyciskExit=new Rectangle(Plansza.WIDTH/2+150,450,400,90);
             public Rectangle przyciskPlay=new Rectangle(500,550,150,50);
        */
        int mx=e.getX();
        int my=e.getY();
        if(Plansza.stan==Plansza.Stan.HIGHSCORE){
            if(my>=550&&my<=600)
                if(mx>=500&&mx<=650)
                    Plansza.stan=Plansza.Stan.MENU;
        }else
        if(Plansza.stan==Plansza.Stan.MENU)
        if(mx>=Plansza.WIDTH/2+150&&mx<=Plansza.WIDTH/2+550)
        {
            if(my>=150&&my<=240)
            {
                Plansza.hardcore=false;
                zerowanie.zerowanie();
                
        }else
            if(my>=250&&my<=340)
            {
                Plansza.hardcore=true;
                zerowanie.zerowanie();
                Plansza.zasiegBomb=3;
                Plansza.iloscBomb=4;
                
                
        }else
            if(my>=350&&my<=440)
            {
                Plansza.stan=Plansza.Stan.HIGHSCORE;
        }else
            if(my>=450&&my<=540)
            {
                System.exit(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
       
    
}
