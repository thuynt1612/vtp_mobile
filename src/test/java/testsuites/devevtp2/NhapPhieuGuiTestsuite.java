package testsuites.devevtp2;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/TinhCuoc/Nhapphieugui.feature",
//        glue="steps.devevtp2",
        glue={"steps.devevtp2","steps.TinhCuoc"},
        tags = "@feature=endtoend"
)
public class NhapPhieuGuiTestsuite {
}
