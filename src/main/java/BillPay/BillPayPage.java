package BillPay;

import Utitites.LogUtilis;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utitites.BasePage;

import java.time.Duration;

public class BillPayPage extends BasePage
{
    By billPayTextLink = By.xpath("//*[@id='leftPanel']/ul/li[4]/a");
    By payeeNameField= By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[1]/td[2]/input");
    By address=By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[2]/td[2]/input");
    By city= By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[3]/td[2]/input");
    By state=By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[4]/td[2]/input");
    By zipCode=By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[5]/td[2]/input");
    By phone=By.xpath("//input[@name='payee.phoneNumber']");
    By amountField = By.name("amount");
    By account=By.name("payee.accountNumber");
    By verifyAccount = By.name("verifyAccount");
    By fromAccountField = By.name("fromAccountId");
    By sendPaymentButton = By.xpath("//*[@id='billpayForm']/form/table/tbody/tr[14]/td[2]/input");
    By confirmayionMessage = By.xpath("//div[@id='billpayResult']/p[1]");
    String fromAccount;
    public BillPayPage(WebDriver driver)
    {
        super(driver);
    }
    public void BillPayButton()
    {
        LogUtilis.info("Navigating to Bill Pay page");
        driver.findElement(billPayTextLink).click();
    }

    public  String BillPay(BillPayData data, String amount)
    {
        LogUtilis.info("Starting Bill Payment process");
        driver.findElement(payeeNameField).sendKeys(data.getPayeeName());
        LogUtilis.info("Entered payee name");
        driver.findElement(address).sendKeys(data.getAddress());
        driver.findElement(city).sendKeys(data.getCity());
        driver.findElement(state).sendKeys(data.getState());
        driver.findElement(zipCode).sendKeys(data.getZipCode());
        LogUtilis.info("Entered payee address details");
        driver.findElement(phone).sendKeys(data.getPhone());
        driver.findElement(account).sendKeys(data.getAccount());
        driver.findElement(verifyAccount).sendKeys(data.getVerifyAccount());
        LogUtilis.info("Entered account and verified account");
        driver.findElement(amountField).sendKeys(amount);
        LogUtilis.info("Entered payment amount: " + amount);
        Select fromAccountRadio = new Select(driver.findElement(fromAccountField));
        fromAccount=fromAccountRadio.getFirstSelectedOption().getText();
        LogUtilis.info("Selected from account: " + fromAccount);
        driver.findElement(sendPaymentButton).click();
        LogUtilis.info("Clicked Send Payment button");
        waitConfirmationMessage();
        LogUtilis.info("Bill Payment confirmation message is displayed");
        return fromAccount;

    }
public void waitConfirmationMessage()
{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until( ExpectedConditions.visibilityOfElementLocated(confirmayionMessage)).getText();
    LogUtilis.info("Bill Payment confirmation message is displayed");
}

    public String getActualConfirmationMessage()
    {
        LogUtilis.info("Getting actual confirmation message");
        return  driver.findElement(confirmayionMessage).getText();

    }
    public String getExpectedConfirmationMessage(String amount)
    {
        String expected ="Bill Payment to John Doe in the amount of $"+amount+".00 from account "+ fromAccount +" was successful.";
        LogUtilis.info("Expected confirmation message: " + expected);
        return expected;
    }
}
