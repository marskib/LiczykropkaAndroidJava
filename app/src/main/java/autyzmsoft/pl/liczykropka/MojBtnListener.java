package autyzmsoft.pl.liczykropka;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/***
 * Klasa do zbudowania listenera podpinanego na klawisze z tButtons
 * Autor: Ja, 2020-07-20
 */
public class MojBtnListener implements OnClickListener {

    public static final int CHWILA = 2000;

    private MojTextView tvCyfra;
    private MojButton[] tButtons;

    /***
     * Konstruktor - "wstrzykniecie' zaleznosci z MainActivity.
     * @param tvCyfra - pole na main_activity.xml
     * @param tButtons- tablica z buttonami
     */
    public MojBtnListener(MojTextView tvCyfra, MojButton[] tButtons) {
        this.tvCyfra  = tvCyfra;
        this.tButtons = tButtons;
    }


    /**
     * Wypisanie cyfry bądź kolek w polu tvCyfra na gorze ekranu
     * Unieczynnienie innych klawiszy - dydaktyka.
     */
    @Override
    public void onClick(View view) {
        //Na gorze ekranu pokazuje cyfre/kolka:
        tvCyfra.pokaz((MojButton)view);
        //
        eksponujKlikniety((MojButton) view);
        //Wyszarzam pozostale:
        MakeGrayExcept(view);
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
    private void MakeGrayExcept(final View btExcept) {
        for (Button mb : tButtons) {
            if (mb != btExcept)
                mb.setEnabled(false);
        }
    }



};



