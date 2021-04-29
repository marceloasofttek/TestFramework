package DriverTest;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Properties;

public class Capability {

    //private ChromeOption chromeOpt;

    public DesiredCapabilities capabilityIE(Properties mavenProps)
    {
        //chromeOpt = new ChromeOption();
        //InternetExplorerOptions options = new InternetExplorerOptions();
        //options.introduceFlakinessByIgnoringSecurityDomains()
        //options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,"true");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        //capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);
        //cap.setCapability("acceptInsecureCerts", true);
        //cap.setCapability(ChromeOptions.CAPABILITY, chromeOpt.chromeOptions(mavenProps));
        //cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+protertyM.getDriverPath());
        //capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //capabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        //capabilities.setCapability(CapabilityType., true);

        return capabilities;
    }
}
