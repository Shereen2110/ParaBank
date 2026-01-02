package TransferFunds;

import OpenNewAccount.OpenNewAccountPage;
import Utitites.BasePage;
import Utitites.LogUtilis;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class TransferFundsPage extends BasePage {
    OpenNewAccountPage openNewAccountPage;
    public TransferFundsPage(WebDriver driver)
    {
        super(driver);
        openNewAccountPage = new OpenNewAccountPage(driver);
    }
    By transferFundsTextLink = By.xpath("//*[@id='leftPanel']/ul/li[3]/a");
    By fromAccountField= By.xpath("//*[@id='fromAccountId']");
    By toAccountField= By.xpath("//*[@id='toAccountId']");
    By transferButton = By.xpath("//*[@id='transferForm']/div[2]/input");
    By amountField = By.xpath("//*[@id='amount']");
    By validateMessageField = By.xpath("//div[@id='transferApp']/div[2]/p[1]");
    By confirmMessageField = By.xpath("//*[@id='showResult']/h1");

    String toAccount;
    public void waitForPageToLoad()
    {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        LogUtilis.info("Waiting for Transfer Funds page to load");
    }
    public  void transferFundsTextLink()
    {
        LogUtilis.info("Clicking on 'Transfer Funds' link");
        driver.findElement(transferFundsTextLink).click();
    }
    public String getFromAccountNumber()
    {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fromAccountField));
        Select select = new Select(driver.findElement(fromAccountField));
        List<WebElement> options = select.getOptions();

        if (options.isEmpty())
        {
            LogUtilis.error("No accounts found in the 'From Account' dropdown!");
            throw new RuntimeException("No accounts found in the 'From Account' dropdown!");
        }

        Random rand = new Random();
        int randomIndex = rand.nextInt(options.size());
        select.selectByIndex(randomIndex);

        String fromAccount = options.get(randomIndex).getAttribute("value");
        LogUtilis.info("Selected 'From Account': " + fromAccount);
        return fromAccount;
    }

    public  String getToAccountNumber(String fromAccount)
    {
        Select toAccountSelect = new Select(driver.findElement(this.toAccountField));
        List<WebElement> options = toAccountSelect.getOptions();

        boolean foundDifferentAccount = false;

        for (WebElement option : options)
        {
            String toAccount = option.getText();
            if (!toAccount.equals(fromAccount))
            {
                toAccountSelect.selectByVisibleText(toAccount);
              this.toAccount=toAccount;
                foundDifferentAccount = true;
                LogUtilis.info("Selected 'To Account': " + toAccount);
                break;
            }
        }
        if (!foundDifferentAccount)
        {
            LogUtilis.warn("No different 'To Account' found, using default selection");
        }
        return toAccount;

    }
    public  void transferFunds(String fromAccount,String toAccount,String amount)
    {
        LogUtilis.info("Starting funds transfer: $" + amount + " from account " + fromAccount + " to account " + toAccount);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));
        driver.findElement(amountField).sendKeys(amount);
        LogUtilis.info("Entered transfer amount: " + amount);
        Select fromAccountSelect = new Select(driver.findElement(fromAccountField));
        fromAccountSelect.selectByValue(fromAccount);
        getToAccountNumber(fromAccount);
        driver.findElement(transferButton).click();
        LogUtilis.info("Clicked Transfer button");
    }

    public String getActualResultMessage()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(validateMessageField));
        String actualMessage = message.getText().trim();
        LogUtilis.info("Actual transfer message displayed: " + actualMessage);
        return actualMessage;
    }
    public String getExpectedResultMessage(String fromAccount,String amount)
    {

        String expectedMessage="$"+amount+".00 has been transferred from account #"+fromAccount+" to account #"+toAccount+".";
        LogUtilis.info("Expected transfer message: " + expectedMessage);
        return expectedMessage;
    }
    public double getCurrentBalanceSenderAccount(double fromAccountBalance,int amount)
    {
        double newBalance = fromAccountBalance - amount;
        LogUtilis.info("Sender account balance after transfer: " + newBalance);
        return newBalance;

    }
    public double getCurrentBalanceTransmiterAccount(double ToAccountBalance,int amount)
    {
        double newBalance = ToAccountBalance + amount;
        LogUtilis.info("Receiver account balance after transfer: " + newBalance);
        return newBalance;
    }
    public boolean isDisplayedActualResultMessage()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMessageField));
        return message.isDisplayed();
    }




}
