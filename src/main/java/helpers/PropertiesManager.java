package helpers;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;

public class PropertiesManager {
    private PropertiesManager() {
        throw new IllegalStateException("Utitlity class");
    }

    public static String getEnvironmentSpecFromProperty(String propertyName) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(variables).getProperty(propertyName);
    }

    public static String getValueOf(String key) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        return variables.getProperty(key);
    }

    public static boolean getValueOfAsBoolean(String key) {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        return variables.getPropertyAsBoolean(key, true);
    }

    public static String getCurrentBaseUrl() {
        return getEnvironmentSpecFromProperty("base.url");
    }

    public static String getAuthenUrl() {
        return getEnvironmentSpecFromProperty("token.url");
    }

    public static String getAuthenUsername() {
        return getEnvironmentSpecFromProperty("auth.username");
    }

    public static String getAuthenPassword() {
        return getEnvironmentSpecFromProperty("auth.password");
    }

    public static String getAuthenPostOffice() {
        return getEnvironmentSpecFromProperty("auth.postoffice");
    }
}
