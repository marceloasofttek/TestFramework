package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.nio.file.FileSystems;

public class ExtentManager {
    private static ExtentReports extent;
    public static String reportFileName = "Regression-Automation.html";
    public static String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();
    public static String REPORT_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "TestReport";

    public static synchronized ExtentReports getInstance(){
        if (extent == null)
            createInstance();
        return extent;
    }

    public static synchronized ExtentReports createInstance(){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getReportPath());
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportFileName);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportFileName);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setCSS("css-string");
        htmlReporter.config().setJS("js-string");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    private static synchronized String getReportPath(){
        String reportFileLocation = REPORT_PATH + FILE_SEPARATOR + reportFileName;
        File testDirectory = new File(REPORT_PATH);
        if(!testDirectory.exists()){
            if(testDirectory.mkdirs()){
                return reportFileLocation;
            }else {
                return System.getProperty("user.dir");
            }
        }
        return reportFileLocation;
    }
}
