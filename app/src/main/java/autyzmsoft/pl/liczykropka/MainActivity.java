package autyzmsoft.pl.liczykropka;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    public final static int MAX_BTS = 6;
    MojButton[] tButtons = new MojButton[MAX_BTS];   //tablica buttonów z wyrazami
    LinearLayout buttons_area;
    private float txSize = 0.0f;

    private int height   = 0;
    private int btH      = 0;
    private int width    = 0;

    int  lBts = 4;                             //liczba buttonow (z Ustawien)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calyEkran();
        //Set content view AFTER ABOVE sequence (calyEkran()) to avoid crash:
        setContentView(R.layout.activity_main);
        buttons_area = findViewById(R.id.buttons_area);
        wygenerujButtony();

    }

    private void wygenerujButtony() {
        /* Generujemy lBts buttonow; zapamietujemy w tablicy tButtons[]; pokazujemy na ekranie */
        MojButton mb = null; //robocza, dla wiekszej czytelnosci

        int dx = 0; //margin w pionie pomiedzy klawiszami
        //
        oszacujWysokoscButtonow_i_Tekstu();
        //

        MojGenerator mGen = null;
        mGen = new MojGenerator(0, 6);

        for (int i=0; i<lBts; i++) {

            try {
                mb = new MojButton(this, mGen.dajWartUnikalna(), false, txSize, btH);
//                mb.setOnClickListener(coNaKlikNaBtn)
                tButtons[i] = mb;
                buttons_area.addView(tButtons[i]);

                //Ustawienie marginesow miedzy buttonami (musi byc poza konstruktorem - klawisz musi fizyczne lezec na layoucie, inaczej nie dziala):
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tButtons[i].getLayoutParams();// as LinearLayout.LayoutParams;
                dx = 10;
                if (lBts < 4) dx = 20;
                params.setMargins(0, dx, 0, 0);
                tButtons[i].setLayoutParams(params);
                tButtons[i].setVisibility(View.VISIBLE); //za chwile pokaze z opoznieniem - efekciarstwo ;)

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } //koniec Funkcji

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