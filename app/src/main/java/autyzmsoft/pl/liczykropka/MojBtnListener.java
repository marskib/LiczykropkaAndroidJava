package autyzmsoft.pl.liczykropka;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/***
 * Klasa do zbudowania listenera podpinanego na klawisze z tButtons
 * Autor: Ja, 2020-07-20
 */
public class MojBtnListener implements OnClickListener {

    TextView tvCyfra;
    float tsCyfra;
    MojButton[] tButtons;

    /***
     * Konstruktor.
     * "Wstrzykniecie' zaleznosci z MainActivity.
     * @param tvCyfra - pole na main_activity.xml
     * @param tsCyfra - text size ww. pola
     * @param tButtons- tablica z buttonami
     */
    public MojBtnListener(TextView tvCyfra,float tsCyfra, MojButton[] tButtons) {
        this.tvCyfra = tvCyfra;
        this.tsCyfra = tsCyfra;
        this.tButtons = tButtons;
    }


    /**
     * Wypisanie cyfry bądź kolek w polu tvCyfra
     * Unieczynnienie innych klawiszy na chwile - dydaktyka.
     */
    @Override
    public void onClick(View view) {
        //jak na klawiszu jest cyfra, to wyswietlamy kolka:
        if (((MojButton) view).isCzyJakLiczba()) {
            ustawTextSize((Button) view, tvCyfra);
            ustawLetterSpacing((MojButton) view, tvCyfra);
            tvCyfra.setText(((MojButton) view).dajWartoscJakoKolka());
        } else { //jak sa kolka, to wyswietlamy cyfre
            tvCyfra.setTextSize(tsCyfra);
            tvCyfra.setText(((MojButton) view).dajWartoscJakoCyfre());
        }
        UnieczynnijNaChwile(2000, (Button)view);
    }

    /***
     * Na 'chwile' unieczynnia wszystke klawisze oprocz kliknietego (except)
     * Po 'chwili' czysci
     * @param chwila   - miliseconds
     * @param except   - klikniety klawisz
     */
    private void UnieczynnijNaChwile(int chwila, Button except) {
        Handler mHandler = new Handler();
        for (final MojButton bt : tButtons) {
            if (bt==except) continue;
            bt.setEnabled(false);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bt.setEnabled(true);
                    tvCyfra.setText("");
                }
            },chwila);
        }
    }

    /***
     * Na tvCyfra ustawia takie letter spacing jak na buttonach (gdy rysujemy kolka)
     */
    private void ustawLetterSpacing(final Button source, TextView dest) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            float ls = source.getLetterSpacing();
            ls = (float) (0.8*ls);
            if (((MojButton)source).getValue()==6) ls=(float) (0.7*ls); //bo troche za szeroko...
            dest.setLetterSpacing(ls);
        }
    }

    /***
     * Na tvCyfra ustawia taki text size jak na buttonach (gdy rysujemy kolka)
     */
    private void ustawTextSize(final Button source, TextView dest) {
        float ts = source.getTextSize();
        ts = (float) (0.7*ts);
        dest.setTextSize(ts);
    }
};



