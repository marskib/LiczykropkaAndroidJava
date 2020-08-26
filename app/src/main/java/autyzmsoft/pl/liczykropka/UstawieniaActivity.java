package autyzmsoft.pl.liczykropka;


import static autyzmsoft.pl.liczykropka.ZmienneGlobalne.MAX_BTNS;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UstawieniaActivity extends AppCompatActivity {

    ZmienneGlobalne mGlob;

    TextView    tv_lkl;
    RadioButton rb_cyfry;
    RadioButton rb_kolka;
    RadioButton rb_trening;
    RadioButton rb_nauka;


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //Uwaga - wywoluje sie rowniez po wejsciu z MainActivity przez LongClick na obrazku(!)
        super.onCreate(savedInstanceState);

        //Na caly ekran:
        //1.Remove title bar:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //2.Remove notification bar:
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        //3.Set content view AFTER ABOVE sequence (to avoid crash):

        setContentView(R.layout.activity_ustawienia);

        //pobranie zmiennych globalnych (ustawien):
        mGlob = (ZmienneGlobalne) getApplication();

        //uchwyty do kontrolek:
        tv_lkl     = (TextView)    findViewById(R.id.tv_lkl);
        rb_cyfry   = (RadioButton) findViewById(R.id.rb_cyfry);
        rb_kolka   = (RadioButton) findViewById(R.id.rb_kolka);
        rb_trening = (RadioButton) findViewById(R.id.rb_trening);
        rb_nauka   = (RadioButton) findViewById(R.id.rb_nauka);

    }


    @Override
    protected void onResume() {
        /* ******************************************************************************************/
        /* Na ekranie (splashScreenie) pokazywane sa aktualne ustawienia.                           */
        /* Wywolywana (nie bezposrednio, ale jako skutek) na long touch na obrazku - wtedy          */
        /* przywolywana jest UstawieniaActivity z pokazanymi ustawieniami -> MainActivity.onLOngClick */
        /* Wywolywana rowniez przy starcie aplikacji(!)                                             */
        /* **************************************************************************************** */
        super.onResume();
        ustawKontrolki();
    }

    public void bOkKlik(View view) {
        finish();
    }


    private void ustawKontrolki() {
        /*******************************************************************************************/
        //Ustawienie kontrolek na layoucie splash.xml na wartosci inicjacyjne ze <-- ZmiennychGlobalnych
        /*******************************************************************************************/
        //
        tv_lkl.setText(Integer.toString(mGlob.LBTNS));

        rb_cyfry.setChecked(mGlob.czyJakLiczba);
        rb_kolka.setChecked(!mGlob.czyJakLiczba);

        rb_trening.setChecked(mGlob.czyTrening);
        rb_nauka.setChecked(!mGlob.czyTrening);
    }


    public void bMinusKlik(View view) {
        int level = Integer.parseInt(tv_lkl.getText().toString());
        level--;
        if (level==0) { //zapewniam zakres od 1..6
            level=1;
        }
        tv_lkl.setText(Integer.toString(level));
    }

    public void bPlusKlik(View view) {
        int level = Integer.parseInt(tv_lkl.getText().toString());
        level++;
        if (level== MAX_BTNS+1) { //zapewniam zakres od 1..6
            level = MAX_BTNS;
        }
        tv_lkl.setText(Integer.toString(level));
    }

    @Override
    protected void onPause() {
        //*******************************************//
        //Przekazanie ustawien na --> ZmienneGlobalne//
        //*******************************************//
        super.onPause();

        int rob = Integer.valueOf(tv_lkl.getText().toString());
        mGlob.LBTNS = rob;

        mGlob.czyJakLiczba = rb_cyfry.isChecked();
        mGlob.czyTrening = rb_trening.isChecked();

        Toast.makeText(this, "onPause w UstawieniaActivity", Toast.LENGTH_SHORT).show();
    }



    }
