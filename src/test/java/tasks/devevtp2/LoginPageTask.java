package tasks.devevtp2;

import helpers.PropertiesManager;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginPageTask implements Task {
    private String username;
    private String password;
    public LoginPageTask(String username,String password) {
        this.username = username;
        this.password = password;
    };
    @Override
    public <T extends Actor> void performAs(T actor) {
        String baseUrl = helpers.PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.url");
        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
        System.out.println("Thực hiện login với thông tin username: " + username);

        actor.attemptsTo(
                Open.url(baseUrl),
                Enter.theValue(username).into(LoginPageUi.USERNAME_FIELD),
                Click.on(LoginPageUi.OTP_BUTTON),
                Enter.theValue(password).into(LoginPageUi.PASSWORD_FIELD),
                Click.on(LoginPageUi.LOGIN_BUTTON)
        );
    }

    public static LoginBuilder withUsername(String username) {
        return new LoginBuilder(username);
    }
    public static class LoginBuilder {
        private String username;
        public LoginBuilder(String username) {
            this.username = username;
        }
        public LoginPageTask andPassword(String password) {
            return instrumented(LoginPageTask.class,this.username,password);
        }
    }

}
