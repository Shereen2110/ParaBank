package ValidScenarios;

import Register.RegisterData;
import Register.RegisterPage;
import Utilities.BaseTest;
import Utitites.JsonReader;
import Utitites.LogUtilis;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test(groups = "validScenario")
public class RegisterTest extends BaseTest
{


RegisterPage registerPage;
RegisterData data;
    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUpRegister()
    {
        LogUtilis.info("Initializing RegisterPage and loading test data from JSON");
        registerPage = new RegisterPage(driver);
        data = JsonReader.readJson("src/test/resources/ValidRegisterData.json", RegisterData.class);
    }

    @Test
    public void validRegisterTest()
    {
        LogUtilis.info("Starting valid registration test");
        registerPage.openRegisterPage();
        LogUtilis.info("Opened Register Page");
        registerPage.tryRegister(
                data.firstName,
                data.lastName,
                data.address,
                data.city,
                data.state,
                data.zipCode,
                data.phone,
                data.ssn,
                data.username,
                data.password
        );
  Assert.assertTrue(registerPage.getConfirmMessage());
    }

}
