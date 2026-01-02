package ValidScenarios;

import AccountsOverView.AccountsOverviewPage;
import Login.LoginDataProvider;
import Login.LoginPage;
import OpenNewAccount.OpenNewAccountDataProvider;
import OpenNewAccount.OpenNewAccountPage;
import TransferFunds.TransferFundsPage;
import Utitites.ConfigLoaderPropertyFile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.BaseTest;
@Test(groups = "validScenario")
public class Scenario3_TransferFundsBetweenAccountsTest extends BaseTest
{
    LoginPage loginPage;
    OpenNewAccountPage openNewAccountPage;
    TransferFundsPage transferFundsPage;
    AccountsOverviewPage accountsOverviewPage;
    ConfigLoaderPropertyFile config;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    void  beforeClass()
    {   loginPage = new LoginPage(driver);
        openNewAccountPage=new OpenNewAccountPage(driver);
        transferFundsPage=new TransferFundsPage(driver);
        accountsOverviewPage=new AccountsOverviewPage(driver);
        config = new ConfigLoaderPropertyFile("src/test/resources/ValidTransferFundsData.properties");


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
    public void TransferFundsBetweenAccountsTest()
    {
        transferFundsPage.transferFundsTextLink();
        String fromAccount = transferFundsPage.getFromAccountNumber();
        String toAccount=transferFundsPage.getToAccountNumber(fromAccount);
        double toAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(toAccount);
        double fromAccountBalanceBefore=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        transferFundsPage.transferFundsTextLink();
        String amountStr = config.getContent("transfer.amount.medium");
        int amount = Integer.parseInt(amountStr);
        transferFundsPage.transferFunds(fromAccount,toAccount,amountStr);
        Assert.assertEquals(transferFundsPage.getActualResultMessage(),transferFundsPage.getExpectedResultMessage(fromAccount,amountStr));
        double fromAccountBalanceAfter=accountsOverviewPage.balanceChangeTransfer(fromAccount);
        double toAccountBalanceAfter=accountsOverviewPage.balanceChangeTransfer(toAccount);
        Assert.assertNotEquals(fromAccountBalanceAfter,fromAccountBalanceBefore);
        Assert.assertNotEquals(toAccountBalanceAfter,toAccountBalanceBefore);
    }

}
