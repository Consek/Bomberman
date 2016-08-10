package bomberman;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class tablicaTxt {
    
    private static final int[][] rozmiar=new int[15][17];
    public static final int[] czasPrzejscia=new int[10];
    public static String[] imieGracza=new String[10];
    
    public static int[][] tablicaTxt(int k){
        int n=0;
        FileReader fr=null;
        String linia;
        String b[];


    // OTWIERANIE PLIKU:
    try {
        if(k<5)
     fr = new FileReader("plansza"+k+".txt");
        else
            fr = new FileReader("survival.txt");
    } catch (FileNotFoundException e) {
       System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
       System.exit(1);
    }

    BufferedReader bfr = new BufferedReader(fr);
    // ODCZYT KOLEJNYCH LINII Z PLIKU:
    try {
     while((linia = bfr.readLine()) != null){
        b=linia.split(" ");
        for(int i=0;i<rozmiar[0].length;i++)
            rozmiar[n][i]=Integer.parseInt(b[i]);
        n++;
    }
    } catch (IOException e) {
        System.out.println("BŁĄD ODCZYTU Z PLIKU!");
        System.exit(2);
    }

        return rozmiar;
    }
    
    public static void zapisWynikow(String imieGraczaPod,int iloscCzasu){
        int n=0;
        FileReader fr = null;
        String linia;
    // OTWIERANIE PLIKU:
    try {
        if(Plansza.hardcore)
     fr = new FileReader("highscorehardcore.txt");
        else
            fr = new FileReader("highscore.txt");
    } catch (FileNotFoundException e) {
       System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
       System.exit(1);
    }

    BufferedReader bfr = new BufferedReader(fr);
    // ODCZYT KOLEJNYCH LINII Z PLIKU:
    
    try {
     while((linia = bfr.readLine()) != null){
        if(n%2==0)
         czasPrzejscia[n/2]=Integer.parseInt(linia);
        else
            imieGracza[n/2]=linia;
        n++;
    }
    } catch (IOException e) {
        System.out.println("BŁĄD ODCZYTU Z PLIKU!");
        System.exit(2);
    }
    int buf=0;
    String bufor="";
    for(int i=0;i<imieGracza.length;i++){
        
        for(int k=0;k<imieGracza.length-1;k++){
        if(czasPrzejscia[k]<czasPrzejscia[k+1]){
            buf=czasPrzejscia[k+1];
            czasPrzejscia[k+1]=czasPrzejscia[k];
            czasPrzejscia[k]=buf;
            bufor=imieGracza[k+1];
            imieGracza[k+1]=imieGracza[k];
            imieGracza[k]=bufor;
            
        }
    }        
        
        if(czasPrzejscia[i]<iloscCzasu){
        czasPrzejscia[i]=iloscCzasu;
        imieGracza[i]=imieGraczaPod;
        iloscCzasu=0;
        }
    }
    String sciezka=Plansza.hardcore?"highscorehardcore.txt":"highscore.txt";
    PrintWriter zapis = null;
        try {
            zapis = new PrintWriter(new FileWriter(sciezka));
        } catch (FileNotFoundException ex) {
            System.exit(3);
        } catch (IOException ex) {
            System.exit(3);
        }
    for(int i=0;i<imieGracza.length*2;i++){
        if(i%2==0)
        zapis.println(czasPrzejscia[i/2]);
        else
            zapis.println(imieGracza[i/2]);
    }
    zapis.close();
    }
    
    public static int[] odczytWynikow(String typ){
    
        int n=0;
        FileReader fr = null;
        String linia;
    // OTWIERANIE PLIKU:
    try {
        if(typ=="HARDCORE")
     fr = new FileReader("highscorehardcore.txt");
        else
            fr = new FileReader("highscore.txt");
    } catch (FileNotFoundException e) {
       System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
       System.exit(1);
    }

    BufferedReader bfr = new BufferedReader(fr);
    // ODCZYT KOLEJNYCH LINII Z PLIKU:
    
    try {
     while((linia = bfr.readLine()) != null){
        if(n%2==0)
         czasPrzejscia[n/2]=Integer.parseInt(linia);
        n++;
    }
    } catch (IOException e) {
        System.out.println("BŁĄD ODCZYTU Z PLIKU!");
        System.exit(2);
    }
        return czasPrzejscia;
    
    }
    
    public static String[] odczytWynikowStr(String typ){
    
        int n=0;
        FileReader fr = null;
        String linia;
    // OTWIERANIE PLIKU:
    try {
        if(typ=="HARDCORE")
     fr = new FileReader("highscorehardcore.txt");
        else
            fr = new FileReader("highscore.txt");
    } catch (FileNotFoundException e) {
       System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
       System.exit(1);
    }

    BufferedReader bfr = new BufferedReader(fr);
    // ODCZYT KOLEJNYCH LINII Z PLIKU:
    
    try {
     while((linia = bfr.readLine()) != null){
        if(!(n%2==0))
            imieGracza[n/2]=linia;
        n++;
    }
    } catch (IOException e) {
        System.out.println("BŁĄD ODCZYTU Z PLIKU!");
        System.exit(2);
    }
        return imieGracza;
    
    }
}
