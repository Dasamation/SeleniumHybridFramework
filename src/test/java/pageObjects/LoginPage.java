package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.logs.Logger;

public class LoginPage {

    WebDriver localDriver;

    //constructor
    public LoginPage(WebDriver remoteDriver){
        localDriver = remoteDriver;
        PageFactory.initElements(remoteDriver,this);
    }

    @FindBy(name = "uid")
    WebElement txtUserName;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(name = "btnLogin")
    WebElement btnLogin;

    @FindBy(xpath = "//a[normalize-space()='Log out']")
    @CacheLookup
    WebElement lnkLogout;

    //create actions methods

    public void setUserName(String userName){
        txtUserName.sendKeys(userName);
        Logger.info("name set");
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
        Logger.info("Entered password");
    }

    public void clickSubmit() throws InterruptedException {
        Thread.sleep(3000);
        btnLogin.click();
        Logger.info("Login button clicked");
    }

    public void clickLogout(){
        lnkLogout.click();
    }


}
