package driver_factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static WebDriver driver;
    public static WebDriver getWebDriver(String browserName)
    {
        switch (browserName.toLowerCase())
        {
            case "chrome":
                driver=ChroumeDriverObject.getChroumDriver();
                break;
            case "firefox":
                driver= FireFoxDriverObject.getFireFoxDriver();
                break;
            default:
                driver=EdgeDriverOpject.getEdgeDriver();
                break;
        }
        return driver;
    }
}
