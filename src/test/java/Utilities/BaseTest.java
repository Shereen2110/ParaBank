package Utilities;

import driver_factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest{
    public WebDriver driver;
    @BeforeClass(alwaysRun = true)
    public void setupBaseTest()
    {
        driver= DriverFactory.getWebDriver("chrome");
        driver.get("https://parabank.parasoft.com");
    }
//    @AfterClass(alwaysRun = true)
//    public void tearDown()
//    {
//        driver.quit();
//}

}
