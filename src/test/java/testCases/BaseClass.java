package testCases;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilities.ReadConfig;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getAppURL();
    public String username = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public static WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser){
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
            driver = new ChromeDriver();
        }else if (browser.equals("firefox")){
            System.setProperty("webdriver.chrome.driver",readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    public static String captureScreenshot() {
        try {
            TakesScreenshot screenshot = ((TakesScreenshot) driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Leer el contenido de la imagen en bytes
            byte[] fileContent = FileUtils.readFileToByteArray(srcFile);

            // Convertir los bytes en una cadena base64
            String base64Image = Base64.getEncoder().encodeToString(fileContent);

            return base64Image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String generateScreenshotFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return "Screenshot_" + timeStamp + ".png";
    }

    public String randomString(){
        String generatedString = RandomStringUtils.randomAlphabetic(8);
        return generatedString;
    }

    public String randomNumber(){
        String generatedNumber = RandomStringUtils.randomNumeric(4);
        return generatedNumber;
    }

}
