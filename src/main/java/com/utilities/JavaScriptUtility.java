package com.utilities;

import com.driver.CreateDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility {

    /**
     * Wait for a page to complete
     *
     * @param webDriver
     * @return
     */
    public static Boolean isPageReady(WebDriver webDriver) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return js.executeScript("return document.readyState").equals("complete");
    }

    /**
     * To click web element using JavaScrip command
     *
     * @param webElement
     */
    public static void click(WebElement webElement) {
        WebDriver webDriver = CreateDriver.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();", webElement);
    }

    public static void scrollDown(){
        WebDriver webDriver = CreateDriver.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

}