package Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Utitites.BasePage;
import Utitites.LogUtilis;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage extends BasePage
{
    By registerButton = By.linkText("Register");
    By firstNameField = By.id("customer.firstName");
    By lastNameField = By.id("customer.lastName");
    By addressField = By.id("customer.address.street");
    By cityField = By.id("customer.address.city");
    By stateField = By.id("customer.address.state");
    By zipCodeField = By.id("customer.address.zipCode");
    By phoneField = By.id("customer.phoneNumber");
    By ssnField = By.id("customer.ssn");
    By usernameField = By.id("customer.username");
    By passwordField = By.id("customer.password");
    By confirmPasswordField = By.id("repeatedPassword");
    By submitButton = By.cssSelector("input[value='Register']");
    By confirmMessageField = By.xpath("//*[@id='rightPanel']/p");

    public RegisterPage(WebDriver driver)
    {
        super(driver);
    }
    public void openRegisterPage()
    {
        LogUtilis.info("Clicking on Register link to open registration page");
        driver.findElement(registerButton).click();

    }
    public void tryRegister(String firstName,
                            String lastName,
                            String address,
                            String city,
                            String state,
                            String zipCode,
                            String phone,
                            String ssn,
                            String username,
                            String password
                               )
    {
        LogUtilis.info("Filling registration form for user: " + username);
        driver.findElement(firstNameField).sendKeys(firstName);
        LogUtilis.info("Entered First Name: " + firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        LogUtilis.info("Entered Last Name: " + lastName);
        driver.findElement(addressField).sendKeys(address);
        LogUtilis.info("Entered Address: " + address);
        driver.findElement(cityField).sendKeys(city);
        LogUtilis.info("Entered City: " + city);
        driver.findElement(stateField).sendKeys(state);
        LogUtilis.info("Entered State: " + state);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        LogUtilis.info("Entered Zip Code: " + zipCode);
        driver.findElement(phoneField).sendKeys(phone);
        LogUtilis.info("Entered Phone Number: " + phone);
        driver.findElement(ssnField).sendKeys(ssn);
        LogUtilis.info("Entered SSN: " + ssn);
        driver.findElement(usernameField).sendKeys(username);
        LogUtilis.info("Entered Username: " + username);
        driver.findElement(passwordField).sendKeys(password);
        LogUtilis.info("Entered Password: " + password);
        driver.findElement(confirmPasswordField).sendKeys(password);
        LogUtilis.info("Entered Confirm Password: " + password);
        driver.findElement(submitButton).click();
        LogUtilis.info("Clicked Submit button to complete registration");

    }
    public boolean registerUrl()
    {
        return driver.getCurrentUrl().contains("register");
    }
    public boolean getConfirmMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMessageField));
        int attempts = 0;
        while (attempts < 3) {
            try {
                return driver.findElement(confirmMessageField).getText().contains("created successfully");
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Confirmation message not found or stale after retries");
    }

}

