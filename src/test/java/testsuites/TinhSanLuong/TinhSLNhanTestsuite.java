package testsuites.TinhSanLuong;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/TinhSanLuong/TinhSanLuongNhanSuccess.feature",
        glue="steps.TinhSanLuong"
//        tags = "@feature=G01"
)
public class TinhSLNhanTestsuite {
}
