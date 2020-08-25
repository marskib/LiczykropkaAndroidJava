package autyzmsoft.pl.liczykropka;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;


/**
 * Created by developer on 2020-07-10.
 *
 */

public class MojButton extends androidx.appcompat.widget.AppCompatButton {

    private int wartosc;           //liczba 0..6 przypisane do klawisza

    private boolean czyJakLiczba;  //jak ma byc obrazowana 'wartosc' - jak liczbe, czy kolka

    private float initTextRozmiar;    //na przechowywania inicjalnego rozmiaru tekstu
    private int btnWys = 20;

    private Character kolko = 9679;
    private String circles  = "";      //do zobrazowania wartosci w postaci kolek


    public MojButton(Context context,  int wartosc, boolean czyJakLiczba, float textRozmiar, int btnWys) {
        super(context);
        this.wartosc = wartosc;
        this.initTextRozmiar = textRozmiar;
        this.czyJakLiczba = czyJakLiczba;
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, textRozmiar);
        this.setHeight(btnWys);
        this.setBackgroundColor(Color.GRAY);
        this.setTypeface(null, Typeface.BOLD);

        setVisibility(View.INVISIBLE);

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

    /***
     * Uzywana przy drukowaniu kolek na klawiszu.
     * Troche zmniejsza, jesli jest ich duzo...
     */
    private float modyfSize(float size) {
        return 5*(size/6);
    }

    public void powiekszTekst(int unit, float newSize) {
        if (!czyJakLiczba) { //jesli maja byc kolka, to trzeba troche zmniejszyc przy 6-ciu, bo wychodzą...
            newSize = modyfSize(newSize);
        }
        this.setTextSize(unit,newSize);
    }


    public int getValue() {
        return wartosc;
    }

    public void restoreInitialTextSize() {
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, initTextRozmiar);
    }

    public boolean isCzyJakLiczba() {
        return czyJakLiczba;
    }

}
