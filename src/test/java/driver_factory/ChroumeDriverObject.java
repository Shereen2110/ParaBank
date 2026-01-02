package driver_factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChroumeDriverObject {
    public static WebDriver getChroumDriver()
    {
        ChromeOptions options = new ChromeOptions();
        Map<String,Object> prefs = new HashMap<>();//to diable notifications from setting
        prefs.put("profile.password_manager_leak_detection",false);
        options.setExperimentalOption("prefs",prefs);
        WebDriver driver =new ChromeDriver(options);
       // WebDriver driver =new org.openqa.selenium.chrome.ChromeDriver(options);//take this path as you call this same to name of class
        return driver;
    }
}
