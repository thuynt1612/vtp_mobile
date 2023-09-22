package testsuites.LuongKCQ;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/LuongKCQ/TinhLuongKCQ.feature",
        glue="steps.LuongKCQ",
        tags ="@feature=first"
)
public class TinhLuongKCQTestsuite {
}
