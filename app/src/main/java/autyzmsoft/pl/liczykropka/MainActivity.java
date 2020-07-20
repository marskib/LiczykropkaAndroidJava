package autyzmsoft.pl.liczykropka;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public final static int MAX_BTS = 6;
    MojButton[] tButtons = new MojButton[MAX_BTS];   //tablica buttonów z wyrazami

    LinearLayout buttons_area;
    TextView tvCyfra;
    float  tsCyfra; //rozmiar tvCyfra z design-time

    private float txSize = 0.0f;
    private int height   = 0;
    private int btH      = 0;
    private int width    = 0;

    int  lBts = 6;   //liczba buttonow (z Ustawien)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calyEkran();
        //Set content view AFTER ABOVE sequence (calyEkran()) to avoid crash:
        setContentView(R.layout.activity_main);
        buttons_area = findViewById(R.id.buttons_area);
        tvCyfra = findViewById(R.id.tvCyfra);
        tsCyfra = tvCyfra.getTextSize();
        wygenerujButtony();
    }

    /***
     * Zmina sposobu wyswietlania na buttonach; czyszczenie obszaru z cyfrą
     */
    public void bPrzelaczKlik(View view) {
        for (final MojButton mb : tButtons) {
            mb.setCzyJakLiczba(!mb.isCzyJakLiczba());
            mb.setText(mb.dajWartoscStringowo());
        }
        tvCyfra.setText("");
    }

    /**
     * Generuje lBts buttonow; zapamietuje w tablicy tButtons[]; pokazuje na ekranie
     */
    private void wygenerujButtony() {

        MojButton mb; //robocza, dla wiekszej czytelnosci
        //
        oszacujWysokoscButtonow_i_Tekstu();
        //
        MojGenerator mGen = new MojGenerator(1, 6);

        for (int i=0; i<lBts; i++) {

            try {
                mb = new MojButton(this, mGen.dajWartUnikalna(), true, txSize, btH);
                mb.setOnClickListener(coNaKlikNaBtn);
                tButtons[i] = mb;
                buttons_area.addView(tButtons[i]);
                ustawMarginesy(tButtons[i]);
                tButtons[i].setVisibility(View.VISIBLE); //za chwile pokaze z opoznieniem - efekciarstwo ;)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } //koniec Funkcji


    /**
     * Wypisanie liczby bądź kolek w polu tv_liczba i odpowiedniego stowarzyszonego stringa w tvCyfra
     * Unieczynnienie innych klawiszy na chwile - dydaktyka.
     */
    private OnClickListener coNaKlikNaBtn = new OnClickListener() {
        @Override
        public void onClick(final View view) {
            //jak na klawiszu jest cyfra, to wyswietlamy kolka:
            if (((MojButton) view).isCzyJakLiczba()) {
                ustawTextSize((Button) view, tvCyfra);
                ustawLetterSpacing((MojButton) view, tvCyfra);
                tvCyfra.setText(((MojButton) view).dajWartoscJakoKolka());
            } else { //jak sa kolka, to wyswietlamy cyfre
                tvCyfra.setTextSize(tsCyfra);
                tvCyfra.setText(((MojButton) view).dajWartoscJakoCyfre());
            }
            UnieczynnijNaChwile(2000,tButtons,(Button)view);
        }

        /***
         * Na 'chwile' unieczynnia wszystke klawisze oprocz kliknietego (except)
         * Po 'chwili' czysci
         * @param chwila   - miliseconds
         * @param tButtons - tablica z buttonami
         * @param except   - klikniety klawisz
         */
        private void UnieczynnijNaChwile(final int chwila, final MojButton[] tButtons, final Button except) {
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

    private void ustawMarginesy(final MojButton tButton) {
        //Ustawienie marginesow miedzy buttonami (musi byc poza konstruktorem - klawisz musi fizyczne lezec na layoucie, inaczej nie dziala):
        int dx = 10; //margin w pionie pomiedzy klawiszami (defaultowo)
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tButton.getLayoutParams();// as LinearLayout.LayoutParams;
        if (lBts < 4) dx = 20;
        params.setMargins(0, dx, 0, 0);
        tButton.setLayoutParams(params);
    }

    private void oszacujWysokoscButtonow_i_Tekstu() {
            /* ******************************************************************************************************************************** */
            /* Na podstawie liczby buttonow (=wybranego poziomu trudnosci) szacuje wysokosc buttonow btH i wielkosc tekstów na buttonach txSize */
            /* Wartosci te beda uzywane przy kreowaniu buttonow (wysokosc but.) + wielkosci textu na tvWyraz                                    */
            /* Algorytm wypracowany doświadczalnie....                                                                                          */
            /* ******************************************************************************************************************************** */

            //Pobieram wymiary ekranu:

            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;

            if (lBts <= 4) {
                int lBtsRob = 2;
                btH = height / (lBtsRob + 3); //bylo 2; button height; doswiadczalnie
                btH = btH - 0;
            }
            if (lBts == 5) {   // bo dobrze wyglada przy 5-ciu klawiszach:
                int lBtsRob = 4;
                btH = height / (lBtsRob + 2); //button height; doswiadczalnie
            }
            if (lBts == 6) {
                btH = height / (lBts + 1);  //button height; doswiadczalnie
            }
            txSize = (float) (btH / 3.5);

            //podrasowanie  - 2020.07.16:
            switch(lBts) {
                case 6: btH = (int) (btH / 1.30); break;
                case 5: btH = (int) (btH / 1.25); break;
                default: btH = (int) (btH / 1.20); break;
            }

        } //koniec Metody()

    private void calyEkran() {
        //* Aplikacja rozdmuchana na caly ekran */
        //1.Remove title bar:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //2.Remove notification bar:
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //3. Usuwa godzine, date i inne pierdoly:
        getSupportActionBar().hide();
    }


}