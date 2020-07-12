package autyzmsoft.pl.liczykropka;

import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.*;
import org.junit.runner.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    MojButton SUT;

    @Before
    public void mojTest(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SUT = new MojButton(appContext, 4,true,40,20);
    }

    //Ponizej 'fabryczna' metoda - stad bralem wzor - nie wyrzucac:
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("autyzmsoft.pl.liczykropka", appContext.getPackageName());
    }

    @Test
    public void czy10() {
        String result = SUT.dajWartoscJakoCyfre();
        assertEquals("4", result);
    }

    @Test
    public void czy2kolka() {
        Character kolko = 9679;
        String kolka4szt = "";
        kolka4szt = kolka4szt +kolko+kolko+kolko+kolko;
        SUT.setCzyJakLiczba(false);
        String result = SUT.dajWartoscJakoKolka();
        assertEquals(kolka4szt, result);
    }
}