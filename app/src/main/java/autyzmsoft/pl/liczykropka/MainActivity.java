package autyzmsoft.pl.liczykropka;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    int MAX_BTNS = 6;
    MojButton[] tButtons = new MojButton[MAX_BTNS];   //tablica buttonów z wyrazami

    LinearLayout   buttons_area;
    RelativeLayout digit_area;

    MojTextView tvCyfra;

    private float txSize = 0.0f;
    private int height   = 0;
    private int btH      = 0;
    private int width    = 0;

    int lBtns = 6;   //aktualna liczba buttonow (z Ustawien)

    MojBtnListener coNaKlikNaBtn;                  //listener do podpiecia na klawisze




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calyEkran();
        //Set content view AFTER ABOVE sequence (calyEkran()) to avoid crash:
        setContentView(R.layout.activity_main);
        buttons_area = findViewById(R.id.buttons_area);

        tvCyfra = findViewById(R.id.tvCyfra);

        digit_area = findViewById(R.id.digit_area);
        digit_area.setOnLongClickListener(mojLongKlikListener());

        coNaKlikNaBtn = new MojBtnListener(tvCyfra,tButtons); //listener do podpiecia na klawisze, potem "podwieszenie" pod kazdy klawisz

        wygenerujButtony();
    }


    OnLongClickListener mojLongKlikListener() {
        return new OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Toast.makeText(MainActivity.this, "Dluuugi klik", Toast.LENGTH_SHORT).show();
                Intent intUstawienia = new Intent("pl.autyzmsoft.liczykropka.UstawieniaActivity");
                startActivity(intUstawienia);
                return true;
            }
        };
    }



    /***
     * Zmina sposobu wyswietlania na buttonach; czyszczenie obszaru z cyfrą - tymczasowe
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
    public void wygenerujButtony() {

        MojButton mb; //robocza, dla wiekszej czytelnosci
        //
        oszacujWysokoscButtonow_i_Tekstu();
        //
        MojGenerator mGen = new MojGenerator(0, lBtns);

        for (int i=0; i< lBtns; i++) {

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


    private void ustawMarginesy(final MojButton tButton) {
        //Ustawienie marginesow miedzy buttonami (musi byc poza konstruktorem - klawisz musi fizyczne lezec na layoucie, inaczej nie dziala):
        int dx = 10; //margin w pionie pomiedzy klawiszami (defaultowo)
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tButton.getLayoutParams();// as LinearLayout.LayoutParams;
        if (lBtns < 4) dx = 20;
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

            if (lBtns <= 4) {
                int lBtsRob = 2;
                btH = height / (lBtsRob + 3); //bylo 2; button height; doswiadczalnie
                btH = btH - 0;
            }
            if (lBtns == 5) {   // bo dobrze wyglada przy 5-ciu klawiszach:
                int lBtsRob = 4;
                btH = height / (lBtsRob + 2); //button height; doswiadczalnie
            }
            if (lBtns == 6) {
                btH = height / (lBtns + 1);  //button height; doswiadczalnie
            }
            txSize = (float) (btH / 3.5);

            //podrasowanie  - 2020.07.16:
            switch(lBtns) {
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