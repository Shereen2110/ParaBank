package ValidScenarios;

import AccountsOverView.AccountsOverviewPage;
import Login.LoginDataProvider;
import Login.LoginPage;
import OpenNewAccount.OpenNewAccountDataProvider;
import OpenNewAccount.OpenNewAccountPage;
import Utilities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


@Test(groups = "validScenario")
public class Scenario2_OpenNewAccountTest extends BaseTest {
    LoginPage loginPage;
    OpenNewAccountPage openNewAccountPage;
    String NewAccountNumber;
    AccountsOverviewPage accountsOverviewPage;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void beforeClass()
    {
        openNewAccountPage = new OpenNewAccountPage(driver);
        loginPage = new LoginPage(driver);
        accountsOverviewPage=new AccountsOverviewPage(driver);
    }

    @Test(priority = 1, dataProvider = "validLogin", dataProviderClass = LoginDataProvider.class)
    public void validLogin(String userName, String password) {

        loginPage.Login(userName, password);
        Assert.assertEquals(driver.getCurrentUrl(), accountsOverviewPage.getAccountsOverviewUrl(), "Failed in Login CheckOut");
    }

    @Test(priority = 2,dataProvider = "Checking", dataProviderClass = OpenNewAccountDataProvider.class)
    public void OpenNewAccount(String accountTypeValue)
    {
        openNewAccountPage.OpenNewAccount(accountTypeValue);
        Assert.assertEquals(openNewAccountPage.validateNewAccountMessage(), openNewAccountPage.messageOfNewAccount());
        NewAccountNumber = openNewAccountPage.getYourNewAccountNumber();
        accountsOverviewPage.goToAccountsOverview();
        boolean accountFound =accountsOverviewPage.isAccountPresent(NewAccountNumber);
        Assert.assertTrue(accountFound,"New account is NOT displayed in Accounts Overview");
        accountsOverviewPage.goToAccountOverviewPage(NewAccountNumber);
        Assert.assertEquals(accountsOverviewPage.getAccountType(),accountTypeValue);


    }
}