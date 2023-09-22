package tasks.devevtp2;

import helpers.PropertiesManager;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Wait;
import questions.CommonQuestion;

import java.time.Duration;

import static com.google.common.base.Predicates.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class KhaithadiPageTask implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        //chon lable KHAITHACDI
        actor.attemptsTo(
                Click.on(KhaithacdiPageUi.KHAITHACDI_LABLE),
                Click.on(KhaithacdiPageUi.NHAPPHIEUGUI_LABLE),
                Enter.theValue("82").into(KhaithacdiPageUi.MAKHACHHANG_TEXTBOX),
                Hit.the(Keys.ENTER).into(KhaithacdiPageUi.MAKHACHHANG_TEXTBOX)
        );


        //xoa buu cuc mac dinh
        String buucuc = PropertiesManager.getEnvironmentSpecFromProperty("auth.postoffice");

        actor.attemptsTo(
                WaitUntil.the(KhaithacdiPageUi.BUUCUC_TEXTBOX, WebElementStateMatchers.isVisible()),

//                Hit.the(Keys.BACK_SPACE).into(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON),
//                Hit.the(Keys.BACK_SPACE).into(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON),
//                Hit.the(Keys.BACK_SPACE).into(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON),
//                Hit.the(Keys.BACK_SPACE).into(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON),
                Enter.theValue(buucuc).into(KhaithacdiPageUi.BUUCUC_TEXTBOX.waitingForNoMoreThan(Duration.ofSeconds(50))),
                Click.on(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON.waitingForNoMoreThan(Duration.ofSeconds(50)))
        );

        actor.attemptsTo(
                Clear.field(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON.waitingForNoMoreThan(Duration.ofSeconds(10))),
                Enter.theValue(buucuc).into(KhaithacdiPageUi.BUUCUC_TEXTBOX.waitingForNoMoreThan(Duration.ofSeconds(10)))
        );

//        actor.should(
//                seeThat(CommonQuestion.valueGetText(KhaithacdiPageUi.BUUCUC_TEXTBOX), equalTo(buucuc))
//        );

        actor.attemptsTo(
//                Enter.theValue(buucuc).into(KhaithacdiPageUi.BUUCUC_TEXTBOX),
//                Click.on(KhaithacdiPageUi.SEARCH_BUUCUC_BUTTON),
                WaitUntil.the(KhaithacdiPageUi.BUUCUCTN2, isVisible()),
                Click.on(KhaithacdiPageUi.BUUCUCTN2)
        );



//
////                Hit.the(Keys.CLEAR).into(KhaithacdiPageUi.BUUCUC_TEXTBOX),
//
    }

    public static KhaithadiPageTask inputPhieu() {
        return instrumented(KhaithadiPageTask.class);
    }
}
