package autyzmsoft.pl.liczykropka;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import org.junit.*;
import org.junit.runner.*;

;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MojInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule(MainActivity.class);


    MainActivity SUT;


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("autyzmsoft.pl.liczykropka", appContext.getPackageName());
    }

//    @Test
//    public void user_can_enter_first_name() {
//        onView(withId(R.id.firstName)).perform(typeText("Daniel"));
//    }
//
//    @Test
//    public void user_can_enter_last_name() {
//        onView(withId(R.id.lastName)).perform(typeText("Malone"));
//    }

//    @Test
//    public void when_user_enters_first_and_last_names() {
//        onView(withId(R.id.firstName)).perform(typeText("Jacke"));
//        onView(withId(R.id.lastName)).perform(typeText("Smith"));
//        onView(withId(R.id.button)).perform(click()); //czy click() zlozy ww. w tekst jak nizej?
//        onView(withId(R.id.komunikat)).check(matches(withText("Jacke Smith")));
//    }

    @Test
    public void tvCyfra_sie_pojawia() throws Exception {
        onView(withId(R.id.tvCyfra)).check(matches(isDisplayed()));
    }

    @Test
    public void bPrzelacz_sie_pojawia() throws Exception {
        onView(withId(R.id.bPrzelacz)).check(matches(isDisplayed()));
    }

    @Test
    public void tvProbny_sie_pojawia() throws Exception {
        onView(withId(R.id.tvProbny)).check(matches(isDisplayed()));
    }

//    @Test
//    public void moje_buttony_sie_pojawiaja() throws Exception {
//        for (final MojButton tButton : MainActivity.tButtons) {
//            onView(withId(R.id.tButton)).check(matches(isDisplayed()));
//
//        }
//    }

 /*   @Test
    public void proba() {
//SUT = new MainActivity(); - nie trzyma na sleep().....
        SUT = rule.getActivity();
        for (final MojButton tButton : SUT.tButtons) {
            onView(SUT.buttons_area.findViewsWithText("1")).check(matches(isDisplayed()));
        }
    }
*/

    @Test
    public void czy_istnieja_mbuttony() {
        SUT = rule.getActivity();
        for (final MojButton mb : SUT.tButtons) {
            assertTrue(
                    (mb.getText().equals("0")) ||
                            (mb.getText().equals("1")) ||
                            (mb.getText().equals("2")) ||
                            (mb.getText().equals("3")) ||
                            (mb.getText().equals("4")) ||
                            (mb.getText().equals("5")) ||
                            (mb.getText().equals("6"))
            );
        }
    }


}