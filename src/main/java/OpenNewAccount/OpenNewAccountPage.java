package OpenNewAccount;

import Utitites.LogUtilis;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import Utitites.BasePage;

public class OpenNewAccountPage extends BasePage {

    By radioButton = By.id("type");
    By openNewAccountTextLink =By.xpath("//*[@id='leftPanel']/ul/li[1]/a");
    By yourAccountNumber =By.xpath("//*[@id='fromAccountId']");
    By openNewAccountTextButton = By.xpath("//*[@id='openAccountForm']/form/div/input");
    By messageOfNewAccount=By.xpath("//*[@id='openAccountResult']/p[2]/b");
    By yourNewAccountNumber=By.xpath(" //*[@id='newAccountId']");

    public OpenNewAccountPage(WebDriver driver)
    {
        super(driver);
    }

    public void waitForPageToLoad()
    {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LogUtilis.debug("Waiting for Open New Account page to load");
    }
    public void OpenNewAccount(String accountTypeValue)
    {
        LogUtilis.info("Opening Open New Account page");
        driver.findElement(openNewAccountTextLink).click();
        waitForPageToLoad();
        LogUtilis.info("Selecting account type: " + accountTypeValue);
        Select accountTypeDropdownSelect = new Select(driver.findElement(radioButton));
        accountTypeDropdownSelect.selectByVisibleText(accountTypeValue);
        Select accountNumberSelect = new Select(driver.findElement(yourAccountNumber));
        String fromAccount = accountNumberSelect.getFirstSelectedOption().getText();
        LogUtilis.info("Using existing account number: " + fromAccount);
        driver.findElement(openNewAccountTextButton).click();
        LogUtilis.info("Clicked Open New Account button");
        waitForPageToLoad();
    }
    public String validateNewAccountMessage()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageOfNewAccount)).getText();
        LogUtilis.info("New account confirmation message displayed: " + message);
        return message;
    }
    public  String messageOfNewAccount()
    {
        return "Your new account number:";
    }

    public String getYourNewAccountNumber()
    {
        String newAccountNumber = driver.findElement(yourNewAccountNumber).getText();
        LogUtilis.info("New account number generated: " + newAccountNumber);
        return newAccountNumber;
    }

    public  boolean isDisplayMessageOfNewAccount()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(messageOfNewAccount)).getText();
        LogUtilis.info("New account confirmation message displayed: " + message);
        return driver.findElement(messageOfNewAccount).isDisplayed();
    }
}
