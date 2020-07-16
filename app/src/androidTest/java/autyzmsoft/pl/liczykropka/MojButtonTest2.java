package autyzmsoft.pl.liczykropka;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.*;

public class MojButtonTest2 {

    MojButton SUT;

    int wart = 18;  //testowana wartosc w MojButton


    @Before
    public void setUp() throws Exception {
        Context ctx = InstrumentationRegistry.getInstrumentation().getContext();
        SUT = new MojButton(ctx, wart,true, 40,40);
    }


    @Test
    public void dajWartoscJakoCyfre() {
        SUT.setCzyJakLiczba(true);
        String wynik = SUT.dajWartoscJakoCyfre();
        assertEquals(Integer.toString(wart),wynik);
        assertThat(Integer.toString(wart),is(wynik)); //nadmiarowe; przyklad na assertThat()
    }

    @Test
    public void dajWartoscJakoKolka() {
    }

    @Test
    public void dajWartoscStringowo() {
    }
}