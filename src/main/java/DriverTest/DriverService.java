package DriverTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverService {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void dismissDriver(){
        try{
            if (driver.get() == null)
                return;
            driver.get().quit();
            driver.set(null);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static WebDriver getDriverInstance() {
        if(driver.get() == null) {
            try{
                driver.set(setDriver());
            }catch (Exception e) {
                dismissDriver();
                driver.set(setDriver());
            }
        }
        return driver.get();
    }

    public static WebDriver setDriver(){
        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        seleniumCapabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        WebDriverManager webDriverManager = WebDriverManager.getInstance(ChromeDriver.class);
        webDriverManager.capabilities(seleniumCapabilities);
        webDriverManager.setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }
}
