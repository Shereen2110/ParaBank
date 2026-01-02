package InValidScenarios;
import AccountsOverView.AccountsOverviewPage;
import Login.LoginDataProvider;
import Login.LoginPage;
import Utilities.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups = "invalidScenario")
public class LoginTest extends BaseTest {
    LoginPage loginPage;
    AccountsOverviewPage accountsOverviewPage;
    SoftAssert  softAssert ;

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setupLogin() {
        loginPage = new LoginPage(driver);
        accountsOverviewPage = new AccountsOverviewPage(driver);
        softAssert = new SoftAssert();
    }

    @Test(priority = 1,dataProvider ="inValidLogin",dataProviderClass = LoginDataProvider.class)
    public void inValidLogin(String userName,String password)
    {
        loginPage.Login(userName,password);
        softAssert.assertEquals(loginPage.getErrorMessage(), loginPage.ErrorMessage(userName, password), "No error message appeared");
        softAssert.assertTrue(loginPage.isOnLoginPage(), "User is NOT on Login Page after invalid login");
        softAssert.assertAll();
    }

}
