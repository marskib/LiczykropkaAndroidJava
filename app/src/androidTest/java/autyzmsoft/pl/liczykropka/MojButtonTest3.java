package autyzmsoft.pl.liczykropka;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.*;

public class MojButtonTest3 {

    MojButton SUT;

    int wart = 19;      //testowana wartosc w MojButton
    String wartString;  //to samo przerobiona na stringa

    @Before
    public void setUp() throws Exception {
        wartString = Integer.toString(wart);
        Context ctx = InstrumentationRegistry.getInstrumentation().getContext();
        SUT = new MojButton(ctx, wart,true, 40,40);
    }


    @Test
    public void dajWartoscJakoCyfre() {
        SUT.setCzyJakLiczba(true);
        String wynik = SUT.dajWartoscJakoCyfre();
        assertEquals(wartString,wynik);
        assertThat(wartString,is(wynik)); //nadmiarowe; przyklad na assertThat()
    }

    @Test
    public void dajWartoscJakoKolka() {
    }

    @Test
    public void dajWartoscStringowo() {
    }
}