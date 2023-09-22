package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebElement;

import static org.springframework.util.ClassUtils.isVisible;

public class CommonQuestion {
    private CommonQuestion() {
    }

    public static Question<String> valueString(String value) {
        return actor -> value;
    }

    public static Question<Object> valueInt(int value) {
        return actor -> value;
    }

    public static Question<Object> valueObject(Object obj){return actor -> obj;}
    public static Question<Long> valueLong(long value){return actor -> value;}

    public static Question<String> valueGetText(Target LOCATOR) {
        return actor -> LOCATOR.resolveFor(actor).getValue();
    }
}
