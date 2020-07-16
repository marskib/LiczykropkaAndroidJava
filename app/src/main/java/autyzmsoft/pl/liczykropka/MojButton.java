package autyzmsoft.pl.liczykropka;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;


/**
 * Created by developer on 2020-07-10.
 *
 */

public class MojButton extends androidx.appcompat.widget.AppCompatButton {

    private int     wartosc;       //liczba 0..6 przypisane do klawisza
    private boolean czyJakLiczba;  //jak ma byc obrazowana 'wartosc' - jak liczbe, czy kolka
    private float textRozmiar;
    private int btnWys = 20;

    private Character kolko = 9679;
    private String circles  = "";      //do zobrazowania wartosc'i w postaci kolek

    public MojButton(Context context,  int wartosc, boolean czyJakLiczba, float textRozmiar, int btnWys) {
        super(context);
        this.wartosc = wartosc;
        this.czyJakLiczba = czyJakLiczba;
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textRozmiar);
        this.setHeight(btnWys);
        this.setTypeface(null, Typeface.BOLD);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setLetterSpacing(0.4F);
        }

        this.setText(dajWartoscStringowo());  //dajWartoscStringowo();
    } //koniec Konstruktora


    public void setCzyJakLiczba(final boolean czyJakLiczba) {
        this.czyJakLiczba = czyJakLiczba;
    }

    public String dajWartoscJakoCyfre() {
        return String.valueOf(wartosc);
    }

    public String dajWartoscJakoKolka() {
        circles = "";
        for (int i=0; i<wartosc; i++)  circles=circles+kolko;
        return circles;
    }

    public String dajWartoscStringowo() {
        /* Wypisuje/zwraca 'wartosc' buttona liczbowo lub graficznie */
        if (czyJakLiczba)
            return dajWartoscJakoCyfre();
        else {
            return dajWartoscJakoKolka();
        }
    }

}
