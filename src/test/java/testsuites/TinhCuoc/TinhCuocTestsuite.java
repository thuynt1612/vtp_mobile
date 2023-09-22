package testsuites.TinhCuoc;


import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features="src/test/java/features/TinhCuoc/TinhCuoc.feature",
        glue={"steps.TinhCuoc","steps.devevtp2"},
        tags = "@feature=dvchinh"
)
public class TinhCuocTestsuite {
}
