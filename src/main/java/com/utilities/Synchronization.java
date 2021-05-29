package com.utilities;

import com.driver.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronization {

    /**
     * Wait for element to appear in page
     * Uses refreshed method to avoid StaleElementReferenceException
     * @param webElement
     * @param timer
     * @throws Exception
     */
    public static WebElement waitFor (WebElement webElement, int timer) throws Exception{

        WebDriver webDriver = CreateDriver.getWebDriver();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,timer);

        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(webElement)));

        return webElement;
    }

    public static void waitForGone (WebElement webElement, int timer) throws Exception{

        WebDriver webDriver = CreateDriver.getWebDriver();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,timer);

        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.invisibilityOf(webElement)));
    }

    public static void waitForBarLoading(int timer) throws Exception{

        WebDriver webDriver = CreateDriver.getWebDriver();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver,timer);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='FlightSearchResults__ProgressBar__finished']")));


    }
}
