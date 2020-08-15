package autyzmsoft.pl.liczykropka;

import android.content.Context;
import android.util.AttributeSet;

public class MojTextView extends androidx.appcompat.widget.AppCompatTextView /*androidx.appcompat.widget.AppCompatTextView*/ {

    private int wartosc;
    private boolean czyJakLiczba;   //jak ma byc obrazowana 'wartosc' - jak liczbe, czy kolka
    private float initTextRozmiar;  //na przechowywania inicjalnego rozmiaru tekstu
    private Character kolko = 9679;
    private String circles  = "";      //do zobrazowania wartosci w postaci kolek


    public MojTextView(final Context context, final AttributeSet attrs) {
      super(context, attrs);
      ustawZmienne();
    }

    public void ustawZmienne() {
    }



 /*
    public MojTextView(final Context context, final AttributeSet attrs, final int wartosc, final boolean czyJakLiczba, final float initTextRozmiar, final Character kolko,
            final String circles) {
        super(context, attrs);
        this.wartosc = wartosc;
        this.czyJakLiczba = czyJakLiczba;
        this.initTextRozmiar = initTextRozmiar;
        this.kolko = kolko;
        this.circles = circles;
    }

*/



    /*
    public MojTextView(final Context context, int wartosc, boolean czyJakLiczba, float textRozmiar) {
        super(context);

        this.wartosc = wartosc;
        this.initTextRozmiar = textRozmiar;
        this.czyJakLiczba = czyJakLiczba;
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textRozmiar);
        this.setBackgroundColor(Color.GRAY);
        this.setTypeface(null, Typeface.BOLD);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setLetterSpacing(0.4F);
        }
    }
 */




}
