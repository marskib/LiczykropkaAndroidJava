package autyzmsoft.pl.liczykropka;


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

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //Uwaga - wywoluje sie rowniez po wejsciu z MainActivity przez LongClick na obrazku(!)
        super.onCreate(savedInstanceState);


        //Na caly ekran:
        //1.Remove title bar:
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //2.Remove notification bar:
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //3.Set content view AFTER ABOVE sequence (to avoid crash):

        setContentView(R.layout.activity_ustawienia);

        //pobranie zmiennych globalnych (ustawien):
        mGlob = (ZmienneGlobalne) getApplication();


        //na caly ekran:
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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


    private void ustawKontrolki() {
        /*******************************************************************************************/
        //Ustawienie kontrolek na layoucie splash.xml na wartosci inicjacyjne ze ZmiennychGlobalnych
        /*******************************************************************************************/

        ((TextView) findViewById(R.id.tv_lkl)).setText(Integer.toString(mGlob.MAX_BTNS));
        ((RadioButton) findViewById(R.id.rb_cyfry)).setChecked(mGlob.czyJakLiczba);
        ((RadioButton) findViewById(R.id.rb_kolka)).setChecked(!mGlob.czyJakLiczba);
    }

    public void bMinusKlik(View view) {
        TextView tv = (TextView) findViewById(R.id.tv_lkl);
        int level = Integer.parseInt(tv.getText().toString());
        level--;
        if (level==0) { //zapewniam zakres od 1..6
            level=1;
        }
        tv.setText(Integer.toString(level));
    }

    public void bPlusKlik(View view) {
        TextView tv = (TextView) findViewById(R.id.tv_lkl);
        int level = Integer.parseInt(tv.getText().toString());
        level++;
        if (level==7) { //zapewniam zakres od 1..6
            level=6;
        }
        tv.setText(Integer.toString(level));
    }

    @Override
    protected void onPause() {
        //*******************************************//
        //Przekazanie ustawien na --> ZmienneGlobalne//
        //*******************************************//
        super.onPause();

        TextView tv = (TextView) findViewById(R.id.tv_lkl);
        int rob = Integer.valueOf(tv.getText().toString());
        mGlob.MAX_BTNS = rob;

        RadioButton rb = (RadioButton) findViewById(R.id.rb_cyfry);
        mGlob.czyJakLiczba = rb.isChecked();

        Toast.makeText(this, "onPause w UstawieniaActivity", Toast.LENGTH_SHORT).show();
    }



    }
