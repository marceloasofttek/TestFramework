package DriverTest;

import Datos.DataExcel;
import Util.MetodosFeature;
import Util.MetodosVarios;
import cucumber.api.Scenario;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.runners.model.TestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Properties;

public class WebDriverTest extends DataExcel{

    static WebDriver driverWeb;
    static ChromeOption chromeOption;
    static Capability capability;
    static Properties mavenProps;
    public static void startMavenProps(){

        try {
            mavenProps = new Properties();
            InputStream in = TestClass.class.getResourceAsStream("/maven.properties");
            mavenProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setDriverChrome(){


            startMavenProps();
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
            chromeOption = new ChromeOption();
            driverWeb = new ChromeDriver(chromeOption.chromeOptions(mavenProps));
            driverWeb.manage().window().maximize();
            driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driverWeb.get(retornarValor("URL", MetodosFeature.getScenario()));

    }


    public static void setDriverIE(){


        startMavenProps();
        System.setProperty("webdriver.ie.driver", "D:\\auto\\GWClaim\\src\\test\\resources\\iedriver\\IEDriverServer.exe");
        capability = new Capability();
        driverWeb = new InternetExplorerDriver(capability.capabilityIE(mavenProps));
        driverWeb.manage().window().maximize();
        driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driverWeb.get(retornarValor("URL", MetodosFeature.getScenario()));
        //driverWeb.get("javascript:document.getElementById('overridelink').click();");

    }

    public static void setDriverFirefox(){



    }


    public static void  driverRemote(){

        try {
            //capability = new Capability();
            startMavenProps();
            chromeOption = new ChromeOption();
            driverWeb = new RemoteWebDriver(new URL(mavenProps.getProperty("remoteDriver")), chromeOption.chromeOptions(mavenProps));
            driverWeb.manage().window().maximize();
            driverWeb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driverWeb.get(retornarValor("URL", MetodosFeature.getScenario()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    public static WebDriver getDriver()
    {
        return driverWeb;
    }

    public static void setDriverClose(){
        //driverWeb.close();
        driverWeb.quit();
    }
}
