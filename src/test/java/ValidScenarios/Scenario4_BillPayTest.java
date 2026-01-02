package ValidScenarios;

import AccountsOverView.AccountsOverviewPage;
import BillPay.BillPayData;
import BillPay.BillPayPage;
import BillPay.BillpayDataProvider;
import Login.LoginDataProvider;
import Login.LoginPage;
import Utitites.JsonReader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Utilities.BaseTest;

@Test(groups = "validScenario")
public class Scenario4_BillPayTest extends BaseTest
{
    LoginPage loginPage;
    BillPayData data;
    BillPayPage billPayPage;
    AccountsOverviewPage accountsOverviewPage;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void initPage()
    {
        loginPage = new LoginPage(driver);
        billPayPage = new BillPayPage(driver);
        accountsOverviewPage=new AccountsOverviewPage(driver);
        data = JsonReader.readJson("src/test/resources/ValidPayInformation.json",BillPayData.class);
    }
    @Test(priority = 1, dataProvider = "validLogin", dataProviderClass = LoginDataProvider.class)
    public void validLogin(String userName, String password)
    {
        loginPage.Login(userName, password);
    }
    @Test(priority=2,dataProvider = "validBillPayAmounts",dataProviderClass = BillpayDataProvider.class)
    public void BillPayTest(String amount)
    {
        double expectedAmount = Double.parseDouble(amount);
        billPayPage.BillPayButton();
        String fromAccount=billPayPage.BillPay(data,amount);
        Assert.assertEquals(billPayPage.getActualConfirmationMessage(),billPayPage.getExpectedConfirmationMessage(amount));
        accountsOverviewPage.goToAccountsOverview();
        accountsOverviewPage.goToAccountOverviewPage(fromAccount);
        System.out.println(fromAccount);


    }


}
