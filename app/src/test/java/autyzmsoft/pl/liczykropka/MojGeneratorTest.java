package autyzmsoft.pl.liczykropka;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.*;

public class MojGeneratorTest {

    MojGenerator SUT;

    int min,max;


    public void setUp() throws Exception {
        SUT = new MojGenerator(min,max);
    }

    @Test
    public void dajWartDowolna_ShoulBeInMinMax() throws Exception {
        min=-1;
        max=-1;
        setUp();
        int gen = SUT.dajWartDowolna();
        boolean wPrzedziale = (gen>=min&&gen<=max);
        assertTrue(wPrzedziale);
    }


    @Test(expected=IllegalArgumentException.class)
    public  void dajWartDowolna_ShouldThrowException() throws Exception {
        min=5;
        max=0;
        setUp();
        int gen = SUT.dajWartDowolna();
    }

    @Test
    public void dajWartUnikalna_zwraca_Tablice_Unikalnych() throws Exception {
        min= -10;
        max=100;
        setUp();
        int[] tab = new int[max-min+1];
        for (int i=0; i<tab.length; i++) {
            tab[i] = SUT.dajWartUnikalna();
        }
        Arrays.sort(tab);
        boolean wszystkieRozne = true;
        int pop = Integer.MIN_VALUE;
        for (final int elem : tab) {
            if (elem<pop){
                wszystkieRozne = false;
                break;
            }
            pop = elem;
        }
        assertTrue(wszystkieRozne);
    }


}