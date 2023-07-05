package testCases;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utilities.TxtUtils;
import utilities.XLUtils;
import utils.logs.Logger;

import java.io.IOException;
import java.io.InputStream;

public class TC_LoginDDT_002 extends BaseClass {

    @Test(dataProvider = "LoginData")
    public void loginDDT(String user, String pwd) throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(user);
        Logger.info("User name provided");
        loginPage.setPassword(pwd);
        Logger.info("password provided");
        loginPage.clickSubmit();
        Thread.sleep(3000);

        if (isAlertPresent() == true){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            Logger.warn("Login failed");
        }else {
            Assert.assertTrue(true);
            Logger.info("Login passed");
            loginPage.clickLogout();
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();

        }

    }
    public boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e){
            return  false;
        }
    }

    @DataProvider(name = "LoginData")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/testData/LoginData.txt"; // Reemplaza con la ruta correcta de tu archivo

        return TxtUtils.readTxtData(path);
    }

}
