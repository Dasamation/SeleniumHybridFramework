package testCases;

import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import utils.logs.Logger;

import java.io.IOException;

public class TC_AddCustomerTest_003 extends BaseClass {
    @Test
    public void addNewCustomer() throws InterruptedException, IOException
    {
        LoginPage loginPage=new LoginPage(driver);
        loginPage.setUserName(username);
        Logger.info("User name is provided");
        loginPage.setPassword(password);
        Logger.info("Passsword is provided");
        loginPage.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addcust=new AddCustomerPage(driver);

        addcust.clickAddNewCustomer();

        Logger.info("providing customer details....");


        addcust.custName("Dasaev");
        addcust.custgender("male");
        addcust.custdob("10","15","1985");
        Thread.sleep(5000);
        addcust.custaddress("Caracas");
        addcust.custcity("CCS");
        addcust.custstate("DC");
        addcust.custpinno("5000074");
        addcust.custtelephoneno("987890091");
        String email= randomString()+"@gmail.com";
        addcust.custemailid(email);
        addcust.custpassword("abcdef");
        addcust.custsubmit();

        Thread.sleep(3000);

        Logger.info("validation started....");

        boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

        if(res==true)
        {
            Assert.assertTrue(true);
            Logger.info("test case passed....");

        }
        else
        {
            Logger.info("test case failed....");
            captureScreenshot();
            Assert.assertTrue(false);
        }

    }

}
