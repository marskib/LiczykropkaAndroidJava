package autyzmsoft.pl.liczykropka;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public final static int MAX_BTS = 6;
    int  lBts = 6;                             //liczba buttonow (z Ustawien)
    MojButton[] tButtons = new MojButton[MAX_BTS];   //tablica buttonów z wyrazami
    LinearLayout buttons_area;

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
        //oszacujWysokoscButtonow_i_Tekstu()
        //

        MojGenerator mGen = null;
        mGen = new MojGenerator(0, 6);

        for (int i=0; i<lBts; i++) {

            try {
                  mb = new MojButton(this, mGen.dajWartUnikalna(), false, 20, 40);
//                mb.setOnClickListener(coNaKlikNaBtn)
                tButtons[i] = mb;
                buttons_area.addView(tButtons[i]);
/*
                //Ustawienie marginesow miedzy buttonami (musi byc poza konstruktorem - klawisz musi fizyczne lezec na layoucie, inaczej nie dziala):
                val params: LinearLayout.LayoutParams
                        params = tButtons[i]?.getLayoutParams() as LinearLayout.LayoutParams
                        dx = 10;
                if (lBts < 4) dx = 20;
                params.setMargins(0, dx, 0, 0)
                tButtons[i]?.setLayoutParams(params)
                tButtons[i]?.setVisibility(View.VISIBLE) //za chwile pokaze z opoznieniem - efekciarstwo ;)
 */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } //koniec Funkcji

    private void calyEkran() {
        //* Aplikacja rozdmuchana na caly ekran */
        //1.Remove title bar:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //2.Remove notification bar:
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        //3. Usuwa godzine, date i inne pierdoly:
        //supportActionBar.hide();
    }


}