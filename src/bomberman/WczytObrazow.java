
package bomberman;

import java.awt.Image;
import javax.swing.ImageIcon;


public class WczytObrazow {
    
    public final Image[] down= new Image[4];
    public final Image[] up= new Image[4];
    public final Image[] right= new Image[4];
    public final Image[] left= new Image[4];
    public final Image[] downb= new Image[4];
    public final Image[] upb= new Image[4];
    public final Image[] rightb= new Image[4];
    public final Image[] leftb= new Image[4];
    public Image mur;
    public Image sciana;
    public final Image[] bomba= new Image[4];
    public final Image[] plomien=new Image[4];
    public final Image[] bot1=new Image[2];
    public final Image[] smokd= new Image[4];
    public final Image[] smoku= new Image[4];
    public final Image[] smokr= new Image[4];
    public final Image[] smokl= new Image[4];
    public Image zasieg;
    public Image szybkosc;
    public Image przerwa;
    public Image bombka;
    
        public void WczytObrazow(){
        ImageIcon iid;
        for(int i=0;i<down.length;i++){
            iid=new ImageIcon("Grafiki/Postac/dol"+(i+1)+".png");
            down[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Postac/gora"+(i+1)+".png");
            up[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Postac/prawo"+(i+1)+".png");
            right[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Postac/lewo"+(i+1)+".png");
            left[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Dragon/dol"+(i+1)+".png");
            smokd[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Dragon/gora"+(i+1)+".png");
            smoku[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Dragon/prawo"+(i+1)+".png");
            smokr[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Dragon/lewo"+(i+1)+".png");
            smokl[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Bot1/dol"+(i+1)+".png");
            downb[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Bot1/gora"+(i+1)+".png");
            upb[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Bot1/prawo"+(i+1)+".png");
            rightb[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Bot1/lewo"+(i+1)+".png");
            leftb[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Plomien/fire"+(i+1)+".png");
            plomien[i]=iid.getImage();
            iid=new ImageIcon("Grafiki/Bomba/bomb"+(i+1)+".png");
            bomba[i]=iid.getImage();
        }
        iid=new ImageIcon("Grafiki/Plansza/mur.png");
        mur=iid.getImage();
        iid=new ImageIcon("Grafiki/Plansza/sciana.png");
        sciana=iid.getImage();
        iid=new ImageIcon("Grafiki/Bonus/bombka.png");
        bombka=iid.getImage();
        iid=new ImageIcon("Grafiki/Bonus/szybkosc.png");
        szybkosc=iid.getImage();
        iid=new ImageIcon("Grafiki/Bonus/przerwa.png");
        przerwa=iid.getImage();
        iid=new ImageIcon("Grafiki/Bonus/zasieg.png");
        zasieg=iid.getImage();
    }  
}
