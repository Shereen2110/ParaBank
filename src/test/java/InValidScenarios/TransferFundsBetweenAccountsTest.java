package InValidScenarios;

import AccountsOverView.AccountsOverviewPage;
import Login.LoginDataProvider;
import Login.LoginPage;
import OpenNewAccount.OpenNewAccountDataProvider;
import OpenNewAccount.OpenNewAccountPage;
import TransferFunds.TransferFundsPage;
import Utilities.BaseTest;
import Utitites.ConfigLoaderPropertyFile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups = "invalidScenario")
public class TransferFundsBetweenAccountsTest extends BaseTest
{
    LoginPage loginPage;
    OpenNewAccountPage openNewAccountPage;
    TransferFundsPage transferFundsPage;
    AccountsOverviewPage accountsOverviewPage;
    ConfigLoaderPropertyFile config;
    @BeforeClass
    void  beforeClass()
    {   loginPage = new LoginPage(driver);
        openNewAccountPage=new OpenNewAccountPage(driver);
        transferFundsPage=new TransferFundsPage(driver);
        accountsOverviewPage=new AccountsOverviewPage(driver);
        config = new ConfigLoaderPropertyFile("src/test/resources/InValidTransferFundsData.properties");


    }
    @Test(priority = 1, dataProvider = "validLogin", dataProviderClass = LoginDataProvider.class)
    public void validLogin(String userName, String password)
    {

        loginPage.Login(userName, password);
    }
    @Test(priority = 2,dataProvider = "Checking", dataProviderClass = OpenNewAccountDataProvider.class)
    public void OpenNewAccount(String accountTypeValue)
    {

        openNewAccountPage.OpenNewAccount(accountTypeValue);
    }
    @Test(priority = 3)
    public void TransferFundsBetweenAccountsZero()
    {
        transferFundsPage.transferFundsTextLink();
        String fromAccount = transferFundsPage.getFromAccountNumber();
        String toAccount=transferFundsPage.getToAccountNumber(fromAccount);
        double toAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(toAccount);
        double fromAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        transferFundsPage.transferFundsTextLink();
        String amountStr = config.getContent("transfer.amount.invalid.zero");
        int amount = Integer.parseInt(amountStr);
        transferFundsPage.transferFunds(fromAccount,toAccount,amountStr);
        double fromAccountBalanceAfter=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        double toAccountBalanceAfter=accountsOverviewPage.balanceChangeTransfer(toAccount);
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(fromAccountBalanceAfter,fromAccountBalanceBefore);
        softAssert.assertEquals(toAccountBalanceAfter,toAccountBalanceBefore);
        softAssert.assertAll();
    }
    @Test(priority = 4)
    public void TransferFundsBetweenAccountsNegative()
    {
        transferFundsPage.transferFundsTextLink();
        String fromAccount = transferFundsPage.getFromAccountNumber();
        String toAccount=transferFundsPage.getToAccountNumber(fromAccount);
        double toAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(toAccount);
        double fromAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        transferFundsPage.transferFundsTextLink();
        String amountStr = config.getContent("transfer.amount.invalid.negative");
        transferFundsPage.transferFunds(fromAccount,toAccount,amountStr);
        Assert.assertFalse(transferFundsPage.isDisplayedActualResultMessage());

    }
    @Test(priority = 5)
    public void TransferFundsBetweenAccountsEmpty()
    {
        transferFundsPage.transferFundsTextLink();
        String fromAccount = transferFundsPage.getFromAccountNumber();
        String toAccount=transferFundsPage.getToAccountNumber(fromAccount);
        double toAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(toAccount);
        double fromAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        transferFundsPage.transferFundsTextLink();
        String amountStr = config.getContent("transfer.amount.invalid.empty");
        transferFundsPage.transferFunds(fromAccount,toAccount,amountStr);
        Assert.assertFalse(transferFundsPage.isDisplayedActualResultMessage());

    }
    @Test(priority = 6)
    public void TransferFundsBetweenAccountsLetters()
    {
        transferFundsPage.transferFundsTextLink();
        String fromAccount = transferFundsPage.getFromAccountNumber();
        String toAccount=transferFundsPage.getToAccountNumber(fromAccount);
        double toAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(toAccount);
        double fromAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        transferFundsPage.transferFundsTextLink();
        String amountStr = config.getContent("transfer.amount.invalid.letters");
        transferFundsPage.transferFunds(fromAccount,toAccount,amountStr);
        Assert.assertFalse(transferFundsPage.isDisplayedActualResultMessage());


    }



}
