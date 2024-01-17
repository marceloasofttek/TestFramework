package extentReport;

import DriverTest.WebDriverTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import context.Context;
import context.ScenarioContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;


public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extent_test = new ThreadLocal<>();
    static ExtentReports extent;
    static {
        try{
            extent = ExtentManager.getInstance();
        } catch (Exception e){
        }
    }

    public static synchronized ExtentTest getTest(){
        return extent_test.get();
    }

    public static synchronized void endTest() { extent.flush();}

    public static synchronized ThreadLocal<ExtentTest> starTest (String testName, String featureName){
        ExtentTest test = extent.createTest(testName);
        test.assignCategory(featureName);
        extent_test.set(test);
        return extent_test;
    }

    public static synchronized void logExtentReportStatusPass(String message){
        ExtentTestManager.getTest().log(Status.PASS,message);
    }

    public static synchronized String screenshotTest(){
        WebDriver driver = WebDriverTest.getDriver();
        String nameTest = ScenarioContext.getContext(Context.PMICE).toString();
        String nameSuite = ScenarioContext.getContext(Context.suiteName).toString();
        String nameScreenshot = nameTest + "_" + System.nanoTime() + ".png";
        String reportPath = ExtentManager.REPORT_PATH + ExtentManager.FILE_SEPARATOR + "screenshots";

        try{
            File file = new File(reportPath + ExtentManager.FILE_SEPARATOR + nameSuite);
            if(!file.exists()){
                file.mkdirs();
            }

            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String location =  reportPath + ExtentManager.FILE_SEPARATOR + nameSuite + ExtentManager.FILE_SEPARATOR + nameScreenshot;
            File targetFile = new File(location);
            FileHandler.copy(screenshotFile,targetFile);
        }catch (Exception e){
        }

        return nameSuite + ExtentManager.FILE_SEPARATOR + nameScreenshot;
    }

    public static void failReport() throws IOException {
        final String screenshotPath = screenshotTest();
        ExtentTestManager.getTest().log(Status.FAIL,"Error",MediaEntityBuilder.createScreenCaptureFromPath(".\\screenshots" + ExtentManager.FILE_SEPARATOR + screenshotPath).build());
    }
}
