package bomberman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Plansza extends JPanel implements ActionListener {
    
    public static final int B_WIDTH = 670;
    public static final int B_HEIGHT = 630;
    private final int DOT_SIZE = 40;
    private final int DELAY = 100;
    private static int kontroler;
    
    private static int x;
    private static int y;
    public static int[] licznik;
    public static boolean[] aktBomb;
    public static int[] nrBomb;
    private int fazaWybuchu=0;

    private int typ=0;
    private int stand=0;
    

    public static String imieGracza=new String();
    
    String sekundyStr;
    String minutyStr;
    String msg;
    private Timer timer;
    private Timer zegar;
    private static int szybkosc;
    
    private static boolean leftDirection = false;
    private static boolean rightDirection = false;
    private static boolean upDirection = false;
    private static boolean downDirection = false;
    
    
    private static int[] xbot;
    private static int[] ybot;
    private final int[] typBot=new int[4];
    private static int szybkoscBot=80;//szybkość bota
    private static int licznikAktBot,licznikAktSzybkosci;
    public static int iloscBomb;
    private static int[] licznikOdradzania;
    private static int zabiciaBot;
    public static int iloscCzasu;

    public static boolean[] aktBot;
    public static int zasiegBomb;

    private static final boolean[] leftBot = {false,false,false,false,true};
    private final boolean[] rightBot = new boolean[5];
    private final boolean[] upBot ={true,true,true,true,false};
    private final boolean[] downBot = new boolean[5];
    
    public static int nrPoziomu=1;
    static int[][] rozmiar;//=!hardcore ? tablicaTxt.tablicaTxt(nrPoziomu):tablicaTxt.tablicaTxt(5);
    WczytObrazow wczytObraz=new WczytObrazow();
    Sterowanie sterowanie=new Sterowanie();
    public static boolean inGame = true;
    public static boolean aktNrPoziomu=true;
    
    static int minuty;
    static double sekundy;
    
    public static boolean hardcore;
    
    public static enum Stan{
      MENU,
      GAME,
      HIGHSCORE
    }
    public static Stan stan=Stan.MENU;
    private final Menu menu=new Menu();
    private final Highscore highscore=new Highscore();

    public Plansza(){
        addKeyListener(new Sterowanie());
        this.addMouseListener((MouseListener) new Mysz());
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        przypisanieZmiennych();
        wczytObraz.WczytObrazow();
        zacznijGre();
        
    }

    public static void przypisanieZmiennych(){
        if(hardcore){
        xbot=new int[]{600,600,40,280,600};
        ybot=new int[]{560,80,560,320,400};}
        else{
        xbot=new int[]{600,600,40,280};
        ybot=new int[]{560,80,560,320};
        }
        x=40;
        y=80;
        aktNrPoziomu=true;
        minuty=0;
        sekundy=0;
        zasiegBomb=1;
        szybkosc=4;//4 to normalnie, 2 to dwa razy szybciej
        szybkoscBot=10;//10 to normalnie, 80 to w ogóle
        licznikAktBot=0;
        iloscBomb=1;
        licznikOdradzania=new int[]{0,0,0,0};
        zabiciaBot=0;
        Plansza.aktBot=new boolean[]{true,true,true,true,true};
        leftDirection = false;
        rightDirection = false;
        upDirection = false;
        downDirection = false;
        kontroler=0;
    }
    
    private void zacznijGre(){
        
        timer = new Timer(DELAY, this);
        zegar = new Timer(1000,updateClock);

        timer.start();
    }

//trigger dla wysw poziomu i rysowania    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(stan == Stan.GAME){
        if(aktNrPoziomu&&licznik[4]<20&&nrPoziomu<5)
            wyswPoziomu(g);
        else{  
        Rysowanie(g);}}
        else if(stan==Stan.MENU){
            menu.renderowanie(g);} 
        else if(stan==Stan.HIGHSCORE)
            highscore.renderowanie(g);
    }
    
    
private void Kwadraty(Graphics g){
    g.setColor(Color.red);
    g.drawRect(600, 560, 39, 39);
    g.drawRect(600, 80, 39, 39);
    g.drawRect(40, 560, 39, 39);
    g.drawRect(120, 80, 39, 39);


}    
//trigger dla ruchu bot i bohatera i odświeżanie 
@Override
public void actionPerformed(ActionEvent e) {
        if(stan==Stan.GAME)
        if (inGame) {
            ruch();
            for(int i=0;i<xbot.length;i++){
                if(aktBot[i])
                    ruchBot(i);}
        }
            
        repaint();
        
 }
//zakaz nachodzenia na sciany i bomby
private void ruch(){
        if(y%80==0)
        if (leftDirection&&x!=40) {
            if(x%40==0)
                if(rozmiar[y/40-1][(x-40)/40]==0
                        ||rozmiar[y/40-1][(x-40)/40]>6)
                x -= DOT_SIZE/szybkosc;
                else;
            else x-= DOT_SIZE/szybkosc;
        }
        else
        if (rightDirection&&x!=600) {
            if(x%40==0)
                if(rozmiar[y/40-1][(x+40)/40]==0
                        ||rozmiar[y/40-1][(x+40)/40]>6)
                    x += DOT_SIZE/szybkosc;
                else;
            else
            x += DOT_SIZE/szybkosc;
        }
        if(x%80==40)
        if (upDirection&&y!=80) {
            if(y%40==0)
               if(rozmiar[y/40-2][x/40]==0
                        ||rozmiar[y/40-2][x/40]>6)
                   y -= DOT_SIZE/szybkosc;
               else;
            else
            y -= DOT_SIZE/szybkosc;
        }
        else
        if (downDirection&&y!=560) {
            if(y%40==0)
               if(rozmiar[y/40][x/40]==0
                        ||rozmiar[y/40][x/40]>6)
                   y += DOT_SIZE/szybkosc;
               else;
            else
            y += DOT_SIZE/szybkosc;
        }

}
//trigger dla wyświetlania poziomu i dla game over i win
private void Rysowanie(Graphics g){ //trigger dla wyświetlania poziomu i dla game over i win
    if(inGame){
        
        if(!aktBot[0]&&!aktBot[1]&&!aktBot[2]&&!aktBot[3]){
                poziomTrigger();
        }else{
            if(!zegar.isRunning())
                zegar.start();
            if(!inGame)return;
        pasek(g); 
        Kwadraty(g);
        for(int i=0;i<rozmiar[0].length;i++){
            for(int n=0;n<rozmiar.length;n++){
                if(rozmiar[n][i]==1)
                    g.drawImage(wczytObraz.mur, i*40, n*40+40,this);
                else 
                     if(rozmiar[n][i]==2)
                    g.drawImage(wczytObraz.sciana, i*40, n*40+40,this);
                     else
                if(rozmiar[n][i]>10){
                    wyswBonus(g,n,i);
                    zebranieBonus(n,i);
                }else
                wyswBomb(g,n,i);
                sprWybuch(n,i);
                if(fazaWybuchu>0||hardcore)
                    wybuchBomb(g,n,i);
                if(!inGame)return;
                }
        }if(hardcore)
        wyswSmoka(g);
        wyswBohatera(g);
        for(int i=0;i<4;i++)
            if(aktBot[i])
                wyswBot(g, i);}
    }else if(nrPoziomu>4){
        while(0==kontroler){
        kontroler=1;
        podanieUzytkownika.podanieUzytkownika();
        }
        zegar.stop();
        winLose.win(g);
        licznik[5]++;
        if(licznik[5]>20)
        stan=Stan.MENU;
    }
    else{
        zegar.stop();
        winLose.gameOver(g);
        licznik[5]++;
        if(licznik[5]>20)
        stan=Stan.MENU;
    }
}

private void wyswBonus(Graphics g,int n,int i){
    if(rozmiar[n][i]==11)
        g.drawImage(wczytObraz.bombka, i*40,n*40+40, this);
    else
    if(rozmiar[n][i]==12)
        g.drawImage(wczytObraz.szybkosc, i*40,n*40+40, this);
    else
    if(rozmiar[n][i]==13)
        g.drawImage(wczytObraz.przerwa, i*40,n*40+40, this);
    else
    if(rozmiar[n][i]==14)
        g.drawImage(wczytObraz.zasieg, i*40,n*40+40, this);

}
//zerowanie licznika aktywnosci bota 
ActionListener updateClock =new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
                iloscCzasu++;
                sekundy++;
                if(sekundy==60){
                    sekundy=0;
                    minuty++;
                    }
                if(szybkoscBot==80)
        if(licznikAktBot<5&&licznikAktBot!=0)
        licznikAktBot++;
        else {
            szybkoscBot=10;
            licznikAktBot=0;
        }
        if(licznikAktSzybkosci<10&&licznikAktSzybkosci!=0)
        licznikAktSzybkosci++;
        else {
            szybkosc=4;
            licznikAktSzybkosci=0;
        }       
            }
            };

private void losowBonus(int n,int i){

    if(Math.random()*10<5){
        rozmiar[n][i]=(int) Math.ceil(Math.random()*4)+10;
    }else {rozmiar[n][i]=7;}
} 

    @SuppressWarnings("empty-statement")
private void zebranieBonus(int n,int i){
    if(rozmiar[n][i]==11)
    if(Math.abs(n*40+40-y)<39&&Math.abs(i*40-x)<39){
        if(iloscBomb<4)
        iloscBomb++;
        rozmiar[n][i]=0;
    }else;else
    if(rozmiar[n][i]==12)
    if(Math.abs(n*40+40-y)<=20&&Math.abs(i*40-x)<=20){
        szybkosc=2;
        licznikAktSzybkosci++;
        rozmiar[n][i]=0;
    }else;else
    if(rozmiar[n][i]==13)
    if(Math.abs(n*40+40-y)<39&&Math.abs(i*40-x)<39){
        szybkoscBot=80;
        licznikAktBot=1;
        rozmiar[n][i]=0;
    }else;else
    if(rozmiar[n][i]==14)
    if(Math.abs(n*40+40-y)<39&&Math.abs(i*40-x)<39){
        zasiegBomb++;
        rozmiar[n][i]=0;
    }

}


private void pasek(Graphics g){
    Font fnt0=new Font("Monospaced",Font.BOLD,30);
    g.setFont(fnt0);
    g.setColor(Color.white);
    DecimalFormat myFormatter = new DecimalFormat("00");
    sekundyStr = myFormatter.format(sekundy);
    minutyStr = myFormatter.format(minuty);
    msg=minutyStr+":"+sekundyStr;
    g.drawString(msg, 0, 40);
    msg="TIME:";
    g.drawString(msg, 0, 20);
    if(hardcore){
        msg="Survival";
        g.drawString(msg, 530, 30);
    }else{    
    msg=zabiciaBot+"/"+licznikOdradzania.length*nrPoziomu;
    g.drawString(msg, 580, 40);
    msg="To kill:";
    g.drawString(msg,530,20);}
    if(hardcore)
    g.drawImage(wczytObraz.smokd[typ], 160, 0, this);
    else g.drawImage(wczytObraz.smokd[0], 160, 0, this);
    g.drawImage(wczytObraz.down[typ], 85, 0, this);
    g.drawImage(wczytObraz.mur, 120, 0, this);
    for(int i =0;i<wczytObraz.downb.length;i++){
        g.drawImage(wczytObraz.downb[typBot[i]], 240+i*80, 0, this);
        g.drawImage(wczytObraz.mur,200+i*80,0,this);}
}

private void wyswBomb(Graphics g,int n, int i){
    
    if(rozmiar[n][i]==3&&aktBomb[0]){
                    g.drawImage(wczytObraz.bomba[nrBomb[0]], i*40,n*40+40, this);
                    if(6==licznik[0]){
                    licznik[0]=0;
                    nrBomb[0]++;
                    }else licznik[0]++;
                     }
                else
                     if(rozmiar[n][i]==4&&aktBomb[1]){
                    g.drawImage(wczytObraz.bomba[nrBomb[1]], i*40,n*40+40, this);
                    if(6==licznik[1]){
                    licznik[1]=0;
                    nrBomb[1]++;
                    }else licznik[1]++;
                     }
                else
                     if(rozmiar[n][i]==5&&aktBomb[2]){
                    g.drawImage(wczytObraz.bomba[nrBomb[2]], i*40,n*40+40, this);
                    if(6==licznik[2]){
                    licznik[2]=0;
                    nrBomb[2]++;
                    }else licznik[2]++;
                     }
                else
                     if(rozmiar[n][i]==6&&aktBomb[3]){
                    g.drawImage(wczytObraz.bomba[nrBomb[3]], i*40,n*40+40, this);
                    if(6==licznik[3]){
                    licznik[3]=0;
                    nrBomb[3]++;
                    }else licznik[3]++;
                     }

}

private void losowanieOdradzania(int k){

    int i=(int)(Math.random()*4);
    if(i==1){
        xbot[k]=600;
        ybot[k]=560;
    }else 
    if(i==2){
        xbot[k]=600;
        ybot[k]=80;
    }else
    if(i==3){
        xbot[k]=40;
        ybot[k]=560; 
    }else
    if(i==4){
        xbot[k]=120;
        ybot[k]=80;}
        
}


//odradzanie się botów
//także sprawdzanie kolizji płomienia z botem i bohaterem
private void wybuchBomb(Graphics g,int n, int i){ //także sprawdzanie kolizji płomienia z botem i bohaterem
    
    if(rozmiar[n][i]==7){
        g.drawImage(wczytObraz.plomien[0],i*40,n*40+40,this);
        rozmiar[n][i]++;
        if(Math.abs(n*40+40-y)<39&&Math.abs(i*40-x)<39){
            if(hardcore){
            podanieUzytkownika.podanieUzytkownika();
            return;
            }else inGame=false; }    
        for(int k=0;k<licznikOdradzania.length;k++){
            if(Math.abs(n*40+40-ybot[k])<39&&Math.abs(i*40-xbot[k])<39&&aktBot[k]){
                if(k==0&&(licznikOdradzania[k]<nrPoziomu-1||hardcore)){
                    if(hardcore){
                xbot[k]=600;
                ybot[k]=560;}else losowanieOdradzania(k);
                licznikOdradzania[k]++;
                }else if(k==1&&(licznikOdradzania[k]<nrPoziomu-1||hardcore)){
                    if(hardcore){
                xbot[k]=600;
                ybot[k]=80;}else losowanieOdradzania(k);
                licznikOdradzania[k]++;
                }else if(k==2&&(licznikOdradzania[k]<nrPoziomu-1||hardcore)){
                    if(hardcore){
                xbot[k]=40;
                ybot[k]=560;}else losowanieOdradzania(k);
                licznikOdradzania[k]++;
                }else if(k==3&&(licznikOdradzania[k]<nrPoziomu-1||hardcore)){
                    if(hardcore){
                xbot[k]=120;
                ybot[k]=80;}else losowanieOdradzania(k);
                licznikOdradzania[k]++;}
                else
                aktBot[k]=false;
                zabiciaBot++;
                }
            }
    }else
    if(rozmiar[n][i]==8){
        g.drawImage(wczytObraz.plomien[1],i*40,n*40+40,this);
        rozmiar[n][i]++;
    }else
    if(rozmiar[n][i]==9){
        g.drawImage(wczytObraz.plomien[2],i*40,n*40+40,this);
        rozmiar[n][i]++;
    }else
    if(rozmiar[n][i]==10){
        g.drawImage(wczytObraz.plomien[3],i*40,n*40+40,this);
        rozmiar[n][i]=0;
    }
}    
//wywolywanie detonacji
private void sprWybuch(int n, int i){
    for(int k=0;k<nrBomb.length;k++)  
            if(nrBomb[k]==4){
                    nrBomb[k]=0;
                    rozmiar[n][i]=0;
                    aktBomb[k]=false;
                    detonacja(n,i);
                    fazaWybuchu=1;
                    }
}
//razem z wybuchaniem okolicznych bomb 
//dodatkowo zasięg zmieniający się i wywołanie losowania bonusu
private void detonacja(int n,int i){
    int k=1;
    rozmiar[n][i]=7;
    do{
    if(rozmiar[n+k][i]==1)
        break;
    if(rozmiar[n+k][i]!=1)
        if(rozmiar[n+k][i]>=3&&rozmiar[n+k][i]<=6){
            nrBomb[rozmiar[n+k][i]-3]=4;
            sprWybuch(n+k,i);}else
        if(rozmiar[n+k][i]==2){
        losowBonus(n+k,i);
        break;}else
        rozmiar[n+k][i]=7;
    k++;}while(k<zasiegBomb+1);
    k=1;
    do{
    if(rozmiar[n-k][i]==1)
            break;
    if(rozmiar[n-k][i]!=1)
        if(rozmiar[n-k][i]>=3&&rozmiar[n-k][i]<=6){
            nrBomb[rozmiar[n-k][i]-3]=4;
            sprWybuch(n-k,i);}else
        if(rozmiar[n-k][i]==2){
        losowBonus(n-k,i);   
        break;}else
        rozmiar[n-k][i]=7;
    k++;}while(k<zasiegBomb+1);
    k=1;
    do{
    if(rozmiar[n][i+k]==1)
        break;
    if(rozmiar[n][i+k]!=1)
        if(rozmiar[n][i+k]>=3&&rozmiar[n][i+k]<=6){
            nrBomb[rozmiar[n][i+k]-3]=4;
            sprWybuch(n,i+k);}else
        if(rozmiar[n][i+k]==2){
        losowBonus(n,i+k);
        break;}else
        rozmiar[n][i+k]=7;
    k++;}while(k<zasiegBomb+1);
    k=1;
    do{
    if(rozmiar[n][i-k]==1)
        break;
    if(rozmiar[n][i-k]!=1)
        if(rozmiar[n][i-k]>=3&&rozmiar[n][i-k]<=6){
            nrBomb[rozmiar[n][i-k]-3]=4;
            sprWybuch(n,i-k);}else
        if(rozmiar[n][i-k]==2){
        losowBonus(n,i-k);
        break;}else
        rozmiar[n][i-k]=7;
    k++;
    }while(k<zasiegBomb+1);
}

private void poziomTrigger(){
                nrPoziomu++;
                zegar.stop();
                if(nrPoziomu==5){
                    inGame=false;
                    aktNrPoziomu=false;}
                else{
                aktBot=new boolean[]{true,true,true,true};
                rozmiar=tablicaTxt.tablicaTxt(nrPoziomu);
                aktNrPoziomu=true;}
                licznik[4]=0;
                przypisanieZmiennych();
}

private void wyswPoziomu(Graphics g) {
        licznik[5]=0;
        String msg1;
        if(!hardcore)
        msg1 = "Poziom "+nrPoziomu;
        else msg1 = "Tryb Survival";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        licznik[4]++;
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg1, (B_WIDTH - metr.stringWidth(msg1)) / 2, B_HEIGHT / 2);
    }

private void wyswSmoka(Graphics g){

        if(downBot[4]){
        g.drawImage(wczytObraz.smokd[typ], xbot[4], ybot[4], this);}
        else
        if(upBot[4]){
        g.drawImage(wczytObraz.smoku[typ], xbot[4], ybot[4], this);}
        else
        if(rightBot[4]){
        g.drawImage(wczytObraz.smokr[typ], xbot[4], ybot[4], this);}
        else    
        if(leftBot[4]){
        g.drawImage(wczytObraz.smokl[typ], xbot[4], ybot[4], this);}
    
}

private void wyswBohatera(Graphics g){

        if(typ<3)
            typ++;
        else typ=0;
        if(downDirection){
        g.drawImage(wczytObraz.down[typ], x, y, this);
        stand=0;}
        else
        if(upDirection){
        g.drawImage(wczytObraz.up[typ], x, y, this);
        stand=1;}
        else
        if(rightDirection){
        g.drawImage(wczytObraz.right[typ], x, y, this);
        stand=2;}
        else    
        if(leftDirection){
        g.drawImage(wczytObraz.left[typ], x, y, this);
        stand=3;}
        else
        switch(stand){
            case 0:
                g.drawImage(wczytObraz.down[0],x,y,this);
                break;
            case 1:
                g.drawImage(wczytObraz.up[0],x,y,this);
                break;
            case 2:
                g.drawImage(wczytObraz.right[0],x,y,this);
                break;
            case 3:
                g.drawImage(wczytObraz.left[0],x,y,this);
                break;
        }
    
}
//wywołanie zawracania gdy jest na bombie
private void wyswBot(Graphics g, int i) {  //wywołanie zawracania gdy jest na bombie
    if(downBot[i]){
        g.drawImage(wczytObraz.downb[typBot[i]], xbot[i], ybot[i], this);}
        else
        if(upBot[i]){
        g.drawImage(wczytObraz.upb[typBot[i]], xbot[i], ybot[i], this);}
        else
        if(rightBot[i]){
        g.drawImage(wczytObraz.rightb[typBot[i]], xbot[i], ybot[i], this);}
        else    
        if(leftBot[i]){
        g.drawImage(wczytObraz.leftb[typBot[i]], xbot[i], ybot[i], this);}
    if(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)]==3)
        zawracanieBot(i);
    if(typBot[i]<3)
            typBot[i]++;
        else typBot[i]=0;
    if(Math.abs(xbot[i]-x)<39&&Math.abs(ybot[i]-y)<39){
        if(hardcore){
       podanieUzytkownika.podanieUzytkownika();
        return;}
        else inGame=false;
       }
}

private void ruchBot(int i){
    if(xbot[i]%80==40&&ybot[i]%80==0){
        if(i==4)
            zioniecie();
    sprPrzeszkod(i);}
    else 
    if(xbot[i]%80==0||ybot[i]%80==40)
        zawracanieBot(i);
    if(xbot[i]%80==40)
    if(upBot[i])
        ybot[i]-=DOT_SIZE/szybkoscBot;
    else
    if(downBot[i])
        ybot[i]+=DOT_SIZE/szybkoscBot;
    if(ybot[i]%80==0)
    if(rightBot[i])
        xbot[i]+=DOT_SIZE/szybkoscBot;
    else
    if(leftBot[i])
        xbot[i]-=DOT_SIZE/szybkoscBot;
}

private void zioniecie(){
    int i=1;
    rozmiar[(ybot[4]+20)/40-1][(xbot[4]+20)/40]=7;
    while(rozmiar[((ybot[4]+20)/40-1)-i][((xbot[4]+20)/40)]!=1){
        rozmiar[((ybot[4]+20)/40-1)-i][((xbot[4]+20)/40)]=7;
        i++;}i=1;
    while(rozmiar[((ybot[4]+20)/40-1)+i][((xbot[4]+20)/40)]!=1){
        rozmiar[((ybot[4]+20)/40-1)+i][((xbot[4]+20)/40)]=7;
        i++;}i=1;
    while(rozmiar[((ybot[4]+20)/40-1)][((xbot[4]+20)/40)-i]!=1){
        rozmiar[((ybot[4]+20)/40-1)][((xbot[4]+20)/40)-i]=7;
        i++;}i=1;
    while(rozmiar[((ybot[4]+20)/40-1)][((xbot[4]+20)/40)+i]!=1){
        rozmiar[((ybot[4]+20)/40-1)][((xbot[4]+20)/40)+i]=7;
        i++;}
}

private void sprPrzeszkod(int i){
    int nrPrzejscia=(int) (Math.random()*3);
    if(upBot[i])
        if(nrPrzejscia==0&&rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]==0){
            leftBot[i]=true;
            upBot[i]=false;
        }
        else
        if(nrPrzejscia==1&&(rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]>6)){
            upBot[i]=true;
        }
        else
        if(nrPrzejscia==2&&rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]==0){
            rightBot[i]=true;
            upBot[i]=false;
        }
        else
        if(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]!=0&&
                rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]!=0&&
                rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]!=0&&
                rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]<=6)
            zawracanieBot(i);
        else
            sprPrzeszkod(i);
    else 
    if(rightBot[i])
        if(nrPrzejscia==0&&(rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]>6)){
            rightBot[i]=false;
            upBot[i]=true;
        }
        else
        if(nrPrzejscia==1&&(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]==0||rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]>6)){
            return;
        }
        else
        if(nrPrzejscia==2&&(rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]>6)){
            rightBot[i]=false;
            downBot[i]=true;
        }
        else 
        if(rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]!=0&&
                rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]!=0
                &&rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]!=0&&
                rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]<=6)
            zawracanieBot(i);
        else
            sprPrzeszkod(i);
    else 
    if(downBot[i])
        if(nrPrzejscia==0&&(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]==0||rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]>6)){
            leftBot[i]=true;
            downBot[i]=false;
        }
        else
        if(nrPrzejscia==1&&(rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]>6)){
            return;
        }
        else
        if(nrPrzejscia==2&&(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]==0||rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]>6)){
            rightBot[i]=true;
            downBot[i]=false;
        }
        else 
        if(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]!=0
                &&rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]!=0
                &&rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]!=0&&
                rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]<=6)
            zawracanieBot(i);
        else
            sprPrzeszkod(i);
    else 
    if(leftBot[i])
        if(nrPrzejscia==0&&(rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]>6)){
            leftBot[i]=false;
            upBot[i]=true;
        }
        else
        if(nrPrzejscia==1&&(rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]==0||rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]>6)){
            return;
        }
        else
        if(nrPrzejscia==2&&(rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]==0||rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]>6)){
            leftBot[i]=false;
            downBot[i]=true;
        }
        else 
        if(rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]!=0
                &&rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]!=0
                &&rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]!=0&&
                rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]<=6)
            zawracanieBot(i);
        else
            sprPrzeszkod(i);

}



private void zawracanieBot(int i){
    if((rozmiar[((ybot[i]+20)/40-1)-1][((xbot[i]+20)/40)]!=0&&
            rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)]<=10)&&upBot[i]){
        upBot[i]=false;
        downBot[i]=true;
    }else
    if((rozmiar[((ybot[i]+20)/40-1)+1][((xbot[i]+20)/40)]!=0&&
            rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)]<=10)&&downBot[i]){
        upBot[i]=true;
        downBot[i]=false;
    }else
    if((rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)+1]!=0&&
            rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)]<=10)&&rightBot[i]){
        leftBot[i]=true;
        rightBot[i]=false;
    }else
    if((rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)-1]!=0&&
            rozmiar[((ybot[i]+20)/40-1)][((xbot[i]+20)/40)]<=10)&&leftBot[i]){
        leftBot[i]=false;
        rightBot[i]=true;
    }
    

}


    
private class Sterowanie extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if(stan==Stan.GAME)
            if (key == KeyEvent.VK_SPACE)
                if(licznik[4]>=20)
                posadzenieBomby();
                else;
            else
            if (key == KeyEvent.VK_LEFT) {
                leftDirection = true;
            }else
            if (key == KeyEvent.VK_RIGHT) {
                rightDirection = true;
            }else
            if (key == KeyEvent.VK_UP) {
                upDirection = true;
            }else
            if (key == KeyEvent.VK_DOWN) {
                downDirection = true;
            }
            
            }

            @Override
        public void keyReleased(KeyEvent e) {
            
            int key = e.getKeyCode();
            if(stan==Stan.GAME)
            if (key == KeyEvent.VK_UP) {
                upDirection = false;
                }else
            if (key == KeyEvent.VK_DOWN) {
                downDirection = false;
                }else
            if (key == KeyEvent.VK_LEFT) {
                leftDirection = false;
                }else
            if (key == KeyEvent.VK_RIGHT) {
                rightDirection = false;            
                }
        }
        
        private void posadzenieBomby(){
            if(rozmiar[(y+20)/40-1][(x+20)/40]==0){
                if(!aktBomb[0]||!aktBomb[1]||!aktBomb[2]||!aktBomb[3])
            if(!aktBomb[0]&&iloscBomb>=1){
                aktBomb[0]=true;
                rozmiar[(y+20)/40-1][(x+20)/40]=3;
            }
            else
            if(!aktBomb[1]&&iloscBomb>=2){
                aktBomb[1]=true;
                rozmiar[(y+20)/40-1][(x+20)/40]=4;
            }
            else
            if(!aktBomb[2]&&iloscBomb>=3){
                aktBomb[2]=true;
                rozmiar[(y+20)/40-1][(x+20)/40]=5;
            }
            else
            if(!aktBomb[3]&&iloscBomb>=4){
                aktBomb[3]=true;
                rozmiar[(y+20)/40-1][(x+20)/40]=6;
            }
            }
        }
    }
}



            
        
