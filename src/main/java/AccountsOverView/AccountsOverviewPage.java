package AccountsOverView;

import TransferFunds.TransferFundsPage;
import Utitites.LogUtilis;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import Utitites.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountsOverviewPage extends BasePage
{
    private final String accountsOverviewLogin="https://parabank.parasoft.com/parabank/overview.htm";
    private  By logOutButton=By.xpath("//*[@id='leftPanel']/ul/li[8]/a");
    private  By accountsOverviewTextLink = By.xpath("//*[@id='leftPanel']/ul/li[2]/a");
    private  By allAccountsInTable=By.xpath("//table[@id='accountTable']//a");
    private By accountType = By.xpath("//*[@id='accountType']");
    private By balance = By.xpath("//*[@id='balance']");
    private   By lastTransactionDescription = By.xpath("//table[@id='transactionTable']/tbody/tr[1]/td[2]/a");
    private  By lastTransactionDebit = By.xpath("//table[@id='transactionTable']/tbody/tr[1]/td[3]");
    private By lastTransactionCredit = By.xpath("//table[@id='transactionTable']/tbody/tr[1]/td[4]");

    TransferFundsPage transferFundsPage;
    public AccountsOverviewPage(WebDriver driver)
    {
        super(driver);
        transferFundsPage=new TransferFundsPage(driver);
    }
    public String getAccountsOverviewUrl()
    {

        return accountsOverviewLogin;
    }
    public void setLogOutButton()
    {
        LogUtilis.info("Logging out from application");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until( ExpectedConditions.visibilityOfElementLocated(logOutButton));
        driver.findElement(logOutButton).click();

    }
    public void goToAccountsOverview()
    {
        LogUtilis.info("Navigating to Accounts Overview page");
        driver.findElement(accountsOverviewTextLink).click();
    }

    public boolean isAccountPresent(String accountNumber)
    {
        LogUtilis.debug("Checking if account exists: " + accountNumber);
        List<WebElement> accounts =driver.findElements(allAccountsInTable);
        for (WebElement acc : accounts)
        {
            if (acc.getText().equals(accountNumber))
            {
                LogUtilis.info("Account found: " + accountNumber);
                return true;
            }
        }
        LogUtilis.warn("Account NOT found: " + accountNumber);
        return false;
    }
    public String getAccountType() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(accountType));

        wait.until(driver -> !el.getText().trim().isEmpty());

        String type = el.getText().trim();
        LogUtilis.info("Account type is: " + type);
        return type;
    }



    public double getBalance() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement el = wait.until(
                ExpectedConditions.visibilityOfElementLocated(balance)
        );

        wait.until(driver -> !el.getText().trim().isEmpty());

        String text = el.getText().trim();
        text = text.replace("$", "").replace(",", "");
        double balanceValue = Double.parseDouble(text);
        LogUtilis.info("Current account balance: " + balanceValue);
        return balanceValue;
    }


public void goToAccountOverviewPage(String accountNumber)
{
    LogUtilis.info("Opening account details page for account: " + accountNumber);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    boolean clicked = false;
    int attempts = 0;

    while (!clicked && attempts < 3) {
        try {
            List<WebElement> accounts = driver.findElements(allAccountsInTable);

            for (WebElement acc : accounts) {
                if (acc.getText().trim().equals(accountNumber)) {
                    wait.until(ExpectedConditions.elementToBeClickable(acc)).click();
                    clicked = true;
                    LogUtilis.info("Clicked account: " + accountNumber);
                    break;
                }
            }
        } catch (StaleElementReferenceException e) {
            attempts++;
            LogUtilis.warn("Stale element detected, retrying... Attempt: " + attempts);
        }
    }

    if (!clicked) {
        LogUtilis.error("Failed to open account: " + accountNumber);
        throw new RuntimeException("Account " + accountNumber + " not found or clickable!");
    }
}
    public double balanceChangeTransfer(String AccountNumber)
    {
        LogUtilis.info("Checking balance after transfer for account: " + AccountNumber);
        goToAccountsOverview();
        goToAccountOverviewPage(AccountNumber);
        return getBalance();
    }
    public String getLastTransactionDescription()
    {
        String desc = driver.findElement(lastTransactionDescription).getText().trim();
        LogUtilis.info("Last transaction description: " + desc);
        return desc;
    }
    public double getLastTransactionAmount() {
        String amountText = driver.findElement(lastTransactionDebit).getText().replace("$", "").replace(",", "").trim();
        double amount = Double.parseDouble(amountText);
        LogUtilis.info("Last transaction amount: " + amount);
        return amount;
    }
    public String getLastTransactionType() {

        String debit = driver.findElement(lastTransactionDebit).getText().trim();
        if (!debit.isEmpty())
        {
            LogUtilis.info("Last transaction type: Debit");
            return "Debit";
        }

        String credit = driver.findElement(lastTransactionCredit).getText().trim();
        if (!credit.isEmpty())
        {
            LogUtilis.info("Last transaction type: Credit");
            return "Credit";
        }
        LogUtilis.warn("Last transaction type: Unknown");
        return "Unknown";
    }





}

