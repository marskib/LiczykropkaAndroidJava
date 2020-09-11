package autyzmsoft.pl.liczykropka;

import static autyzmsoft.pl.liczykropka.ZmienneGlobalne.MAX_BTNS;
import static autyzmsoft.pl.liczykropka.ZmienneGlobalne.MAX_LICZBA;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    ZmienneGlobalne mGlob;

    MojButton[] tButtons;            //tablica buttonów z cyframi/symbolami (kolkami)

    LinearLayout buttons_area;
    LinearLayout digit_area;
    LinearLayout nawigacja_area;

    MojTextView tvCyfra;

    private float txSize = 0.0f;
    private int height   = 0;
    private int btH      = 0;
    private int width    = 0;

    MojBtnListener coNaKlikNaBtn;                  //listener do podpiecia na klawisze


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calyEkran();
        //Set content view AFTER ABOVE sequence (calyEkran()) to avoid crash:
        setContentView(R.layout.activity_main);

        mGlob = (ZmienneGlobalne) getApplication();

        buttons_area = findViewById(R.id.buttons_area);
        nawigacja_area = findViewById(R.id.nawigacja_area);

        tvCyfra = findViewById(R.id.tvCyfra);

        digit_area = findViewById(R.id.digit_area);
        digit_area.setOnLongClickListener(mojLongKlikListener());

    }



    public void bAgainOnKlik(View view) {powtorzUklad();}


    /***
     * Powtorka ćwiczenia/sytuacji treningowej.
     * Nie zmienia sie uklad na klawiszach.
     * Cyfra/kolka na gorze - wymazana jesli trening, zostaje jesli cwiczenie
     */
    private void powtorzUklad() {

        if (mGlob.czyTrening) tvCyfra.wymaz();
        for (final MojButton mb : tButtons) {
            mb.setEnabled(true);
            mb.restoreInitialTextSize();
        }
    }

    public void bDalejOnKlik(View view) {
        wyczyscPrzedpole();

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                wygenerujUklad();
                if (mGlob.czyTrening) return;
                dajCwiczenie();
            }
        },400);
    }

    /***
     * Generacja (NOWEGO) losowego ukladu klawiszy.
     * Uwaga - nie okreslam jeszcze "cwiczenia", czyli zwycieskiego klawisz/cyfry na gorze itp.
     */
    private void wygenerujUklad() {
        //Tworzenie tablicy, zeby miec na czym dzialac:
        tButtons = new MojButton[mGlob.LBTNS];
        //Listener do podpiecia na klawisze, potem "podwieszenie" pod kazdy klawisz:
        coNaKlikNaBtn = new MojBtnListener(tvCyfra,tButtons, nawigacja_area);
        //
        wygenerujButtony();
    }

    /***
     * Okreslenie 'parametrow' cwiczenia - czyli 'pytania' i związanego z nim zwycieskiego klawisza; generowanie cyfry/kolek na gorze ekranu.
     * Uwaga - nie ma tutaj tworzenia klawiszy
     */
    private void dajCwiczenie() {
       int wylosBtn = MojGenerator.getRandomNumberInRange(0,mGlob.LBTNS-1);; //  <-- wylosuj button sposrod uwidocznionych na ekranie
       MojButton naPdst = tButtons[wylosBtn]; //dla lepszej czytelnosci
       tvCyfra.pokaz(naPdst);
       blokujListeneryOprocz(naPdst);

    }

    private void blokujListeneryOprocz(final MojButton bExcept) {
        for (final MojButton mb : tButtons) {
            if (mb != bExcept) {
                mb.setClickable(false);
            }
        }
    }


    OnLongClickListener mojLongKlikListener() {
        return new OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                Intent intUstawienia = new Intent("pl.autyzmsoft.liczykropka.UstawieniaActivity");
                startActivity(intUstawienia);
                return true;
            }
        };
    }



      /**
     * Generuje mGlob.LBTNS buttonow; zapamietuje w tablicy tButtons[]; pokazuje na ekranie
     */
    public void wygenerujButtony() {

        MojButton mb; //robocza, dla wiekszej czytelnosci
        //
        oszacujWysokoscButtonow_i_Tekstu();
        //
        int min = (mGlob.czyZero) ? 0 : 1;
        MojGenerator mGen = new MojGenerator(min, MAX_LICZBA);

        for (int i=0; i< mGlob.LBTNS; i++) {

            try {
                mb = new MojButton(this, mGen.dajWartUnikalna(), mGlob.czyJakLiczba, txSize, btH);
                mb.setOnClickListener(coNaKlikNaBtn);
                tButtons[i] = mb;
                buttons_area.addView(tButtons[i]);
                ustawMarginesy(tButtons[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        nadajIdButtonom();
    } //koniec Funkcji

    /***
     * Nadaje programowom utworzonym buttonom nazwy
     * Z punktu widzenia aplikacji nie jest to potrzebne(!),
     * ale przydatne w testowania Espresso - mamy uchwyty do buttonow
     * Korzysta z utworzonego recznie ids.xml w res.
     */
    private void nadajIdButtonom() {
        for (int i = 0; i < tButtons.length; i++) {
            switch (i) {
                case 0: tButtons[i].setId(R.id.mb1);break;
                case 1: tButtons[i].setId(R.id.mb2);break;
                case 2: tButtons[i].setId(R.id.mb3);break;
                case 3: tButtons[i].setId(R.id.mb4);break;
                case 4: tButtons[i].setId(R.id.mb5);break;
                case 5: tButtons[i].setId(R.id.mb6);break;
            }
        }
    }

    /**
     *  Ustawienie marginesow miedzy buttonami (musi byc poza konstruktorem - klawisz musi fizyczne lezec na layoucie, inaczej nie dziala):
     * @param tButton
     */
    private void ustawMarginesy(final MojButton tButton) {

        int dx = 10; //margin w pionie pomiedzy klawiszami (defaultowo)
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tButton.getLayoutParams();// as LinearLayout.LayoutParams;
        if (mGlob.LBTNS < 4) dx = 20;
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


            //sledzenie:
            DajGestosc();

            int h = DajWysokoscButtonsArea();

            btH = (int) (h/(mGlob.LBTNS + modyfikator(mGlob.LBTNS)));
        txSize = (float) (btH / 3.5);


/*
            if (mGlob.LBTNS <= 4) {
                int lBtsRob = 2;
                btH = height / (lBtsRob + 3); //button height; doswiadczalnie
            }
            if (mGlob.LBTNS == 5) {   // bo dobrze wyglada przy 5-ciu klawiszach:
                int lBtsRob = 4;
                btH = height / (lBtsRob + 2); //button height; doswiadczalnie
            }
            if (mGlob.LBTNS == 6) {
                btH = height / (mGlob.LBTNS + 1);  //button height; doswiadczalnie
            }
            txSize = (float) (btH / 3.5);

            //podrasowanie  - 2020.07.16:
            switch(mGlob.LBTNS) {
                case 6: btH = (int) (btH / 1.30); break;
                case 5: btH = (int) (btH / 1.25); break;
                default: btH = (int) (btH / 1.20); break;
            }

 */

        } //koniec Metody()

    /***
     * Do zwiekszenia dzielnika przy obliczaniu wysokosci buttona,
     * w zaleznosci od wybranej w ustawieniach liczby buttonow.
     * Chodzi o to, zeby "jakos" wygladalo ;) - im mniej klawiszy, tym szersze
     */
    private float modyfikator(final int ileBtns) {
        switch (ileBtns) {
            case MAX_BTNS : case MAX_BTNS-1 : return 0.8f; //6,5 klawiszy
            case MAX_BTNS-2 : return 1.8f;                 //4   klawisze
            case MAX_BTNS-3 : return 2.1f;                 //3   klawisze
            default: return 3;                             //2,1 klawisze
        }
    }


    /***
     * Znajduje wysokosc obszaru z klawiszami w px
     * Zakladam, ze istnieja 3 linear layouty z nadanymi wagami (!)
     */
    private int DajWysokoscButtonsArea() {

        final float waga1 = ((LinearLayout.LayoutParams) digit_area.getLayoutParams()).weight;
        final float waga2 = ((LinearLayout.LayoutParams) buttons_area.getLayoutParams()).weight;
        final float waga3 = ((LinearLayout.LayoutParams) nawigacja_area.getLayoutParams()).weight;

        float czescSzukanaProcent =  (waga2 / (waga1+waga2+waga3));

        int wysokoscPx = (int) (czescSzukanaProcent * height);

        return wysokoscPx;

    }


    public void DajGestosc() {

        String sufiks;

        int screenSize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        int density = getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                Toast.makeText(this, "LDPI", Toast.LENGTH_SHORT).show();
                sufiks = "LDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                Toast.makeText(this, "MDPI", Toast.LENGTH_SHORT).show();
                sufiks = "MDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                Toast.makeText(this, "HDPI", Toast.LENGTH_SHORT).show();
                sufiks = "HDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                Toast.makeText(this, "XHDPI", Toast.LENGTH_SHORT).show();
                sufiks = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                Toast.makeText(this, "XXHDPI", Toast.LENGTH_SHORT).show();
                sufiks = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                Toast.makeText(this, "XXXHDPI", Toast.LENGTH_SHORT).show();
                sufiks = "XXXDPI";
                break;
            case DisplayMetrics.DENSITY_560:
                Toast.makeText(this, "560 ski ski", Toast.LENGTH_SHORT).show();
                sufiks = "560 ski ski";
                break;
            default:
                Toast.makeText(this, "nie znalazłem...", Toast.LENGTH_SHORT).show();
                sufiks = "nie znalazlem gestosci...";
        }

        TextView tv_rozdz = (TextView) findViewById(R.id.tv_rozdzielczosc);
        tv_rozdz.setText("  "+width+" x "+height+"   ... "+sufiks);

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


    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(mGlob, "onResume w MainActivity", Toast.LENGTH_SHORT).show();
        bDalejOnKlik(null);
    }

    private void wyczyscPrzedpole() {
        tvCyfra.wymaz();
        buttons_area.removeAllViews();
        nawigacja_area.setVisibility(View.INVISIBLE);
    }
}