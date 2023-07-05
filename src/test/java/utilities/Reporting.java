package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static testCases.BaseClass.captureScreenshot;
import static testCases.BaseClass.generateScreenshotFileName;

public class Reporting extends TestListenerAdapter {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReport;
    private ExtentTest logger;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
        String reportName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);//specify location
        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);

        extentReport.setSystemInfo("Host name", "localhost");
        extentReport.setSystemInfo("Environment", "QA");
        extentReport.setSystemInfo("User", "Dasaev");

        sparkReporter.config().setDocumentTitle("InetBanking Test Project");
        sparkReporter.config().setReportName("Functional Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    public void onTestSuccess(ITestResult tr) {
        logger = extentReport.createTest(tr.getName());
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult tr) {
        logger = extentReport.createTest(tr.getName());
        logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

        String screenshotBase64 = captureScreenshot();
        if (screenshotBase64 != null) {
            try {
                logger.fail("Screenshot is below:", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotBase64).build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onTestSkipped(ITestResult tr) {
        logger = extentReport.createTest(tr.getName());
        logger.log(Status.SKIP, tr.getName() + " Test Case Skipped");
    }

    public void onFinish(ITestContext testContext) {
        extentReport.flush();
    }
}
