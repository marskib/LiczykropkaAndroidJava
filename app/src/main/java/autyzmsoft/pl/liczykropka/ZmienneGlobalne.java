package autyzmsoft.pl.liczykropka;

import android.app.Application;

/**
 * autor: Developer 2020-08-23

 * singleton na przechowywanie zmiennych globalnych.
 *
 * Szczegoly: patrz film z Educativo odc. 4-3 Application-Glowny-obiekt-aplikacji... Obiekt klasy dzieciczacej po klasie Application pozostaje zywy podczas calej sesji z apką. Obiekt ten tworzony jest
 * przez system, jest tylko JEDEN i nadaje sie do przechowywania zmiennych wspoldzielonych. Mozna nadpisywać jego onCreate() ! Mozna tam nawet powolywac nowe obiekty z klas wewnetrzych(!) W
 * manifest.xml TRZEBA go zadeklarowac w tagu 'applicatin', w atrybucie name jak w przykladzie: <application android:name=".ZmienneGlobalne"
 *
 * Odwolanie we wszystkich klasach apki w np. onCreate() poprzez (przyklad z mojego MainActivity) : ZmienneGlobalne mGlob;   //'m-member' na zmienne globalne - obiekt singleton klasy ZmienneGlobalne
 * mGlob = (ZmienneGlobalne) getApplication(); (zwroc uwage na rzutowanie!!!)
 *
 * W onCreate() tego obiektu najlepiej odwolywac sie do SharedPreferences... :)
 *
 * Obiekt ten ( getApplication() ) ma wszystkie zalety Singletona, ale jest NIEZNISZCZALNY!
 */

public class ZmienneGlobalne extends Application {
    public static final boolean PELNA_WERSJA = false;       //czy Pelna czy Darmowa wersja aplikacji
    public int MAX_BTNS = 6;                                //maksymalna dopuszczlna liczba klawiszy
    public boolean czyJakLiczba = false;                     //sposob zobrazowania na klawiszach - liczby/kolka

    @Override
    public void onCreate() {
        super.onCreate();
    }
}