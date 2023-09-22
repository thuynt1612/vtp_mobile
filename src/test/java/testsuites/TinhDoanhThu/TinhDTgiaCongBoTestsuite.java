package testsuites.TinhDoanhThu;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/TinhDoanhThu/TinhDTgiaCongBo.feature",
        glue="steps.TinhCuoc",
        tags = "@feature=doanhthu"
)
public class TinhDTgiaCongBoTestsuite {
}
