package autyzmsoft.pl.liczykropka;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

/***
 * Klasa do opisania i wyswietlania cyfry tvCyfra wyswietlanej na gorze ekranu, po kliknieciu buttona.
 * Autor - Skibinski, 2020.08.20
 */

public class MojTextView extends androidx.appcompat.widget.AppCompatTextView  {

    private float tsOrigin; //pierwotny (z xml) TextSize; bedzie potrzebny do zmiany rozmiaru miedzy cyfra/kółka

    /**
     * Taki konstruktor jest uzywany przez framework w czasie umieszcznia obiektu na layoucie -> attrs to to, co opisane w xml'u
     * @param context
     * @param attrs
     */
    public MojTextView(final Context context, final AttributeSet attrs) {
      super(context, attrs);
      tsOrigin = this.getTextSize();
      //Jesli mialby pokazywac kolka, to niech bedzie nieco szerzej:
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          this.setLetterSpacing(0.20F);
      }
    }

    /***
     * Pokazuje tvCyfra, znajdująca sie na gorze ekranu, w odpowiedni sposob.
     * @param naPdst : na podstawie ktorego (=kliknietego) buttona ma pokazywac tvCyfrę
     */
    public void pokaz(final MojButton naPdst) {
        if (naPdst.isCzyJakLiczba()) {
            this.setText(naPdst.dajWartoscJakoKolka());
            this.setTextSize(COMPLEX_UNIT_PX,tsOrigin);
        } else {
            this.setText(naPdst.dajWartoscJakoCyfre());
            this.setTextSize(COMPLEX_UNIT_PX,2.8f*tsOrigin);
        }
    }

    /***
     * zawartosc tvCyfra znika.
     */
    public void wymaz() {
        this.setText(" ");  //lepiej zostawic spacje niz pusty znak, bo appium/espresso nie zobaczy....
    }




}
