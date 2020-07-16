package autyzmsoft.pl.liczykropka;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {

        assertEquals(4, 2 + 2);
    }

    @Test
    public void wypis_z_mockiem() {
        Context ctx = mock(Context.class);
        MojButton mb = new MojButton(ctx, 7, true, 40, 20);

    }
}