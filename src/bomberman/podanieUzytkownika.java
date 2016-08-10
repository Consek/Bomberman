
package bomberman;

import javax.swing.JOptionPane;


public class podanieUzytkownika {
    
    public static void podanieUzytkownika(){
        Plansza.inGame=false;
        if(Plansza.hardcore||Plansza.nrPoziomu>=5){
            Plansza.imieGracza=JOptionPane.showInputDialog(null,"Twój czas to:"+Plansza.iloscCzasu/60+" min "+Plansza.iloscCzasu%60+" sek."+" \nPodaj Swoje imię:","Podaj imię",JOptionPane.PLAIN_MESSAGE);
            while(Plansza.imieGracza==null||Plansza.imieGracza.length()>15||Plansza.imieGracza.length()<4)
                Plansza.imieGracza=JOptionPane.showInputDialog(null,"Twoje imie jest za długie bądź za krótkie.\nPodaj Swoje imię:","Podaj imię",JOptionPane.PLAIN_MESSAGE);
            tablicaTxt.zapisWynikow(Plansza.imieGracza,Plansza.iloscCzasu);
            } 

    
    }
    
    
}
