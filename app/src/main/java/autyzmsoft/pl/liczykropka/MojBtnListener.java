package autyzmsoft.pl.liczykropka;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import android.os.Handler;
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
     * Konstruktor. "Wstrzykniecie' zaleznosci z MainActivity.
     * @param tvCyfra - pole na main_activity.xml
     * @param tButtons- tablica z buttonami
     */
    public MojBtnListener(MojTextView tvCyfra, MojButton[] tButtons) {
        this.tvCyfra  = tvCyfra;
        this.tButtons = tButtons;
    }


    /**
     * Wypisanie cyfry bądź kolek w polu tvCyfra na gorze ekranu
     * Unieczynnienie innych klawiszy na chwile - dydaktyka.
     */
    @Override
    public void onClick(View view) {
        float ts = ((Button)view).getTextSize();
        //Na buttonie - tylko powiekszam zastany text/kolka:
        ((MojButton)view).powiekszTekst(COMPLEX_UNIT_PX,ts*1.6F);
        //Na gorze ekranu:
        tvCyfra.pokaz((MojButton)view);
        //
        UnieczynnijPotemPrzywroc(CHWILA, (Button)view);
        tvCyfra.wymaz(CHWILA);
    }



    /***
     * Na 'chwile' unieczynnia wszystke klawisze oprocz kliknietego (except)
     * Po 'chwili' czysci
     * @param chwila   - miliseconds
     * @param btExcept - klikniety klawisz
     */
    private void UnieczynnijPotemPrzywroc(int chwila, final Button btExcept) {
        //Unieczynnienie:
        for (MojButton mb : tButtons) {
            if (mb==btExcept) {
               mb.setClickable(false);
            } else {
                mb.setEnabled(false);
            }
        }
        //Przywracanie:
        Handler h1 = new Handler();
        h1.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (MojButton mb : tButtons) {
                    mb.restoreInitialTextSize();
                    mb.setEnabled(true);
                    mb.setClickable(true);
                }
            }
        },chwila);
    }

};



