package testsuites.LuongKCQ;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/LuongKCQ/TinhLuongKCQ_dev.feature",
        glue="steps.LuongKCQ",
        tags ="@feature=new"
)
public class TinhLuongKCQ_devTestsuite {
}
