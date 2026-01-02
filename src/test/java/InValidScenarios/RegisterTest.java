package InValidScenarios;

import Register.RegisterData;
import Register.RegisterDataProvider;
import Register.RegisterPage;
import Utilities.BaseTest;
import Utitites.JsonReader;
import Utitites.LogUtilis;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(groups = "invalidScenario")
public class RegisterTest extends BaseTest
{


RegisterPage registerPage;
RegisterData data;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUpRegister()
    {
        LogUtilis.info("Initializing RegisterPage and loading test data from dataProvider");
        registerPage = new RegisterPage(driver);
    }

    @Test(dataProvider = "invalidRegisterData", dataProviderClass = RegisterDataProvider.class)
    public void testInvalidRegister(String firstName, String lastName, String address, String city, String state,
                                    String zipCode, String phone, String ssn, String username, String password)
    {
        registerPage.openRegisterPage();
        registerPage.tryRegister(firstName, lastName, address, city, state, zipCode, phone, ssn, username, password);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(registerPage.getConfirmMessage());
        LogUtilis.info(" register is invalid");
    }


}
