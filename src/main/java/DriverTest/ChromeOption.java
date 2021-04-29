package DriverTest;


import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;

public class ChromeOption {

    public ChromeOptions chromeOptions(Properties mavenProps)
    {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(Boolean.parseBoolean(mavenProps.getProperty("acceptInsecureCerts")));
        String[] arguments = mavenProps.getProperty("addArguments").split(";");
        for(String argument:arguments){options.addArguments(argument);}
        return options;
    }
}
