

package bomberman;


public class zerowanie {


   
    Plansza plansza=new Plansza();
    
    public static void zerowanie(){
        Plansza.inGame=true;
        Plansza.aktNrPoziomu=true;
        Plansza.aktBomb = new boolean[]{false,false,false,false};
        Plansza.licznik = new int[]{0,0,0,0,0,0};
        Plansza.nrBomb=new int[]{0,0,0,0};
        Plansza.nrPoziomu=1;
        Plansza.iloscCzasu=0;
        Plansza.rozmiar=!Plansza.hardcore ? tablicaTxt.tablicaTxt(Plansza.nrPoziomu):tablicaTxt.tablicaTxt(5);
        Plansza.stan=Plansza.stan.GAME;
        Plansza.przypisanieZmiennych();
    
    }
    
}
