package testsuites.TinhSanLuong;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/TinhSanLuong/TinhSanLuongGiaoSuccess.feature",
        glue="steps.TinhSanLuong",
        tags = "@feature=G02"
)
public class TinhSLGiaoTestsuite {

}
