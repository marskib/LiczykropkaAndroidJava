package autyzmsoft.pl.liczykropka;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void dajWartUnikalna() {
    }
}