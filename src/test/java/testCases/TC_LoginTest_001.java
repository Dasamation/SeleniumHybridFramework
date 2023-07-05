package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import utils.logs.Logger;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginTest() {
        try{
            Logger.info("URL is opened");

            LoginPage loginPage= new LoginPage(driver);
            loginPage.setUserName(username);
            loginPage.setPassword(password);
            loginPage.clickSubmit();

            String title = driver.getTitle();
            if (title.equals("Guru99 Bank Manager HomePage")){
                Assert.assertTrue(true);
                Logger.info("Login test passed");
            }else {
                captureScreenshot();
                Assert.assertTrue(false);
                Logger.info("Login test failed");
            }
        } catch (Exception e) {
            Logger.error("An error occurred during login test: " + e.getMessage(), e);
            e.printStackTrace(); // Imprimir la traza completa de la excepción
            Assert.fail("An error occurred during login test. Check log for details.");
        }

        }
}

