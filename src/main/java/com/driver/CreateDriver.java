package com.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CreateDriver {

    private static WebDriver webDriver;

    public CreateDriver() {

    }

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public final void setWebDriver(){
        System.setProperty("webdriver.chrome.driver","src/main/java/com/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }
}
