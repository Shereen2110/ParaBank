package ValidScenarios;
import AccountsOverView.AccountsOverviewPage;
import Login.LoginDataProvider;
import Login.LoginPage;
import OpenNewAccount.OpenNewAccountPage;
import Utilities.BaseTest;
import Utitites.LogUtilis;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
@Test(groups = "validScenario")
public class Scenario1_LoginVerificationTest extends BaseTest {
    LoginPage loginPage;
    AccountsOverviewPage accountsOverviewPage;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupLogin() {
        loginPage = new LoginPage(driver);
        accountsOverviewPage = new AccountsOverviewPage(driver);
    }

    @Test(priority = 1, dataProvider = "validLogin", dataProviderClass = LoginDataProvider.class)
    public void validLogin(String userName, String password) {
        loginPage.Login(userName, password);
        loginPage.waitForPageToLoad();
       Assert.assertEquals(driver.getCurrentUrl(), accountsOverviewPage.getAccountsOverviewUrl(), "Failed in Login CheckOut");
        LogUtilis.info("Login successful, user is on Accounts Overview page");
        accountsOverviewPage.setLogOutButton();
        LogUtilis.info("User logged out successfully");

    }
    @Test(priority = 2,dataProvider ="inValidLogin",dataProviderClass = LoginDataProvider.class)
    public void inValidLogin(String userName,String password)
    {
        loginPage.Login(userName,password);
        Assert.assertEquals(loginPage.getErrorMessage(),loginPage.ErrorMessage(userName,password),"No error message appeared ");
        LogUtilis.info("Invalid login error message verified successfully");
    }



}
