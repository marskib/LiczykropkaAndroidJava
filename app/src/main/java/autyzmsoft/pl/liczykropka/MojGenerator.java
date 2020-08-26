package autyzmsoft.pl.liczykropka;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/***
 * Klasa do generowania liczb losowych z przedzialu min..max]
 * M.Skibinski, 2020.07.09
 * 2 tryby:
 * a) liczby losowe z przedzialu min..max, NIEPOWTARZALNE
 * b) jw., ale powtarzalne
 * W trybie pierwszym rzuca wyjatek, jesli nie da sie uzyskac liczby, ktora wczesniej nie wystapila
 */
public class MojGenerator {

    private Set<Integer> zbLiczb;  //obiekt klasy pamieta liczby, ktore juz kiedys wygenerowal
    private int min;
    private int max;

    class CustomExceptionSkib extends Exception {

        /**
         * Klasa na razie nie uzywana...
         */
        String msg;
        CustomExceptionSkib(String komunikat) {
            this.msg = komunikat;
        }
    }

    public MojGenerator(final int min, final int max) {
//        this.zbLiczb.clear();

        this.zbLiczb = new HashSet<Integer>();

        if (min > max) {
            throw new IllegalArgumentException("max musi być większe niż min");
        }

//        if (min > max) {
//            throw new CustomExceptionSkib("max musi być większe niż min");
//        }

        this.min = min;
        this.max = max;
    }

    public int dajWartUnikalna() throws CustomExceptionSkib {

        //Najpierw sprawdzam, czy da sie jeszcze cos niepowtarzalnego wygenerowac:
        boolean jestCosWolnego = false;
        for (int liczba=min; liczba<=max; liczba++) {
            if (!zbLiczb.contains(liczba)) {
                jestCosWolnego = true;
                break;
            }
        }
        if (!jestCosWolnego) {
            throw new CustomExceptionSkib("brak wolnych liczb");
        }

        int gen = getRandomNumberInRange(min,max);

        //generuje tak dlugo, az sie wstrzeli (na pewno jest wolne miejsce - patrz wyzej):
        while (zbLiczb.contains(gen)) {
            gen = getRandomNumberInRange(min,max);
        }
        zbLiczb.add(gen); //zeby pamietac, ze juz kiedys wygenerowano te liczbe
        return gen;
    }

    public int dajWartDowolna() {
        return getRandomNumberInRange(min,max);
    }

    public static int getRandomNumberInRange(int min, int max) {
//        if (min > max) {
//            throw new IllegalArgumentException("max must be greater than min");
//        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


}

//-----------------------------


/*
package autyzmsoft.pl.liczykropka


class MojGenerator(val min:Int, val max:Int) {

private val zbLiczb = mutableSetOf<Int>() //obiekt klasy pamieta liczby, ktore juz kiedys wygenerowal

class CustomExceptionSkib(message: String) : Exception(message)

        fun dajWartUnikalna():Int {

        //Najpierw sprawdzam, czy da sie cos jeszcze niepowtarzalnego wygenerowac
        var jestCosWolnego:Boolean = false;
        for (i in min..max) {
        if (!zbLiczb.contains(i)) {
        jestCosWolnego = true
        }
        }
        if (!jestCosWolnego) {
        throw (CustomExceptionSkib("brak wolnych liczb"))
        }

        var gen = (min..max).random()
        while (zbLiczb.contains(gen)) {
        gen = (min..max).random()
        }
        zbLiczb.add(gen) //zeby pamietac, ze juz kiedys wygenerowano te liczbe
        return gen;
        }

        fun dajWartDowolna():Int {
        return (min..max).random()
        }

        }
*/