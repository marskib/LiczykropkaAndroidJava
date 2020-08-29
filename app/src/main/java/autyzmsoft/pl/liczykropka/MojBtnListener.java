package autyzmsoft.pl.liczykropka;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

/***
 * Klasa do zbudowania listenera podpinanego na klawisze z tButtons
 * Zadania listenera:
 * 1.Eksponowac klikniety klawisz
 * 2.Wyszarzyc pozostale
 * 3.Pokazac cyfre/kolka na gorze ekranu
 * 4.Pokazac klawisze nawigacyjne na dole
 * Autor: M.S., 2020-07-20
 */
public class MojBtnListener implements OnClickListener {

    private MojTextView  tvCyfra;     //zeby moc ją wyswietlic po kliknieciu buttona
    private MojButton[]  tButtons;    //zeby po klikniecu buttona moc zablokowac wszystkie inne z tej tablicy
    private LinearLayout nawig_area; //obszar z klawiszami nawigacyjnymi, ktory nalezy pokazac po kliknieciu klawisza

    /***
     * Konstruktor - "wstrzykniecie' zaleznosci z MainActivity.
     * @param tvCyfra - pole na main_activity.xml
     * @param tButtons- tablica z buttonami
     * @param nawig_area - obszar do pokazywania
     */
    public MojBtnListener(MojTextView tvCyfra, MojButton[] tButtons, LinearLayout nawig_area) {
        this.tvCyfra  = tvCyfra;
        this.tButtons = tButtons;
        this.nawig_area = nawig_area;
    }


    /**
     * Wypisanie cyfry bądź kolek w polu tvCyfra na gorze ekranu
     * Eksponowanie kliknietego klawisza.
     * Unieczynnienie innych klawiszy - dydaktyka.
     * Pokazanie klawiszy nawigacyjnych.
     */
    @Override
    public void onClick(View view) {
        //Na gorze ekranu pokazuje cyfre/kolka:
        tvCyfra.pokaz((MojButton)view);
        //
        eksponujKlikniety((MojButton) view);
        //Wyszarzam pozostale:
        makeGrayExcept(view);
        //Pokazanie obszaru klawiszy nawigacyjnych pod klawiszami cwiczeniowymi:
        nawig_area.setVisibility(View.VISIBLE);
    }

    private void eksponujKlikniety(final MojButton bt) {
        //Na buttonie bt powiekszam zastany text/kolka:
        bt.restoreInitialTextSize();
        float ts = bt.getTextSize();
        bt.powiekszTekst(COMPLEX_UNIT_PX,ts*1.6F);
    }


    /***
     * Unieczynnia (=wyszarza) wszystke klawisze oprocz kliknietego (except)
     * @param btExcept - klikniety klawisz
     */
    private void makeGrayExcept(final View btExcept) {
        for (Button mb : tButtons) {
            if (mb != btExcept)
                mb.setEnabled(false);
        }
    }

};



