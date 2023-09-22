package testsuites.devevtp2;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/KhaiThacDi/Bangke.feature",
//        glue="steps.devevtp2",
        glue={"steps.devevtp2"},
        tags = "@feature=inputphieudi"
)
public class BangKeTestsuite {
}
