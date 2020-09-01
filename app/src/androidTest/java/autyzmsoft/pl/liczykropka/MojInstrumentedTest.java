package autyzmsoft.pl.liczykropka;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.espresso.ViewInteraction;
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


    //MainActivity SUT; - nie zalecane


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
/*

    @Test
    public void bPrzelacz_sie_pojawia() throws Exception {
        onView(withId(R.id.bPrzelacz)).check(matches(isDisplayed()));
    }

    @Test
    public void tvProbny_sie_pojawia() throws Exception {
        onView(withId(R.id.tvProbny)).check(matches(isDisplayed()));
    }
*/

    @Test
    public void moje_buttony_sie_pojawiaja() throws Exception {
        onView(withId(R.id.mb1)).check(matches(isDisplayed()));
        onView(withId(R.id.mb2)).check(matches(isDisplayed()));
        onView(withId(R.id.mb3)).check(matches(isDisplayed()));
        onView(withId(R.id.mb4)).check(matches(isDisplayed()));
        onView(withId(R.id.mb5)).check(matches(isDisplayed()));
        onView(withId(R.id.mb6)).check(matches(isDisplayed()));
    }

    @Test
    public void naciskam_mb1() throws Exception {
        onView(withId(R.id.mb1)).perform(click());
        onView(withId(R.id.tvCyfra)).check(matches(isDisplayed()));
       /*
        assertThat(
        onView(withId(R.id.tvCyfra)).check(matches(withText("0")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("1")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("2")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("3")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("4")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("5")))||
        onView(withId(R.id.tvCyfra)).check(matches(withText("6")))
        );
*/
    }


 /*   @Test
    public void proba() {
//SUT = new MainActivity(); - nie trzyma na sleep().....
        SUT = rule.getActivity();
        for (final MojButton tButton : SUT.tButtons) {
            onView(SUT.buttons_area.findViewsWithText("1")).check(matches(isDisplayed()));
        }
    }
*/

 /* Tak nie nalezy robic
    @Test
    public void czy_istnieja_mbuttony() {
        SUT = rule.getActivity();  //NEVER!!!
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
*/



 @Test
 public void sa_buttony_cwiczebne() {
    //to jest (prawie) OK onView(withClassName(containsString("MojButton"))).check(matches(isDisplayed()));

     //onView(withClassName(containsString("MojButton"))).check(matches(isDisplayed()));

//    onView(anyOf(withClassName(containsString("MojButton")))).check(matches(isDisplayed()));
   // anyOf(withClassName(containsString("MojButton")))).isD

     ViewInteraction bArea = onView(withId(R.id.buttons_area));

     //AssertThat(bArea.check(hasDescendant(withClassName(containsString("MojButton")))),iss);

     //onView(hasDescendant(withClassName(containsString("MojButton")))).isDi


 }




} //koniec klasy