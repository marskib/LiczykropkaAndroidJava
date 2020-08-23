package autyzmsoft.pl.liczykropka;

import static autyzmsoft.pl.liczykropka.ZmienneGlobalne.MAX_BTNS;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UstawieniaActivity extends AppCompatActivity {


    ZmienneGlobalne mGlob;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //Uwaga - wywoluje sie rowniez po wejsciu z MainActivity przez LongClick na obrazku(!)
        super.onCreate(savedInstanceState);
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

        ((TextView) findViewById(R.id.tv_lkl)).setText(Integer.toString(MAX_BTNS));
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

}
