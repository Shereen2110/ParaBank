package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import Utitites.BasePage;
import Utitites.LogUtilis;

public class LoginPage extends BasePage {
    private final By userNameField = By.xpath("//*[@id='loginPanel']/form/div[1]/input");
    private final By passWordField = By.xpath("//*[@id='loginPanel']/form/div[2]/input");
    private final By loginButtonField = By.xpath("//*[@id='loginPanel']/form/div[3]/input");
    private final By errorMessageField = By.xpath("//*[@id='rightPanel']/p");

    public LoginPage(WebDriver driver) {

        super(driver);
        LogUtilis.info("LoginPage initialized");
    }

    public void Login(String userName, String password) {
        LogUtilis.info("Entering username: " + userName);
        driver.findElement(userNameField).sendKeys(userName);
        LogUtilis.info("Entering password");
        driver.findElement(passWordField).sendKeys(password);
        LogUtilis.info("Clicking login button");
        driver.findElement(loginButtonField).click();
        LogUtilis.info("Login action performed");
    }

    public void waitForPageToLoad()
    {
        LogUtilis.info("Waiting for LoginPage to load");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageField)).getText();
        LogUtilis.info("Error message displayed: " + msg);
        return msg;
    }

    public String ErrorMessage(String uername, String password) {
        String msg;
        if (uername.isEmpty() || password.isEmpty()) {
            msg = "Please enter a username and password.";

        }
        else
        {
            msg = "The username and password could not be verified.";

        }

        LogUtilis.info("Determined error message: " + msg);
        return msg;
    }
    public boolean isOnLoginPage()
    {
        return driver.getCurrentUrl().contains("login");
    }
}
