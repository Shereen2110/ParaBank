package driver_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverObject {
    public static WebDriver getFireFoxDriver()
    {
        WebDriver driver =new FirefoxDriver();
        // WebDriver driver =new org.openqa.selenium.chrome.ChromeDriver(options);//take this path as you call this same to name of class
        return driver;
    }
}
