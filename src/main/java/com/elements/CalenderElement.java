package com.elements;

import com.driver.CreateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CalenderElement {

    public static void clickCalender(WebElement element){
        element.click();
    }

    public static void searchWithinCalender(String date){
        WebDriver webDriver = CreateDriver.getWebDriver();
        List<WebElement> daysAvailable = webDriver.findElements(By.cssSelector("[class='DayPicker-Day']"));
        for (int i=0; i<daysAvailable.size();i++){
            if(daysAvailable.get(i).findElement(By.cssSelector("span[data-testid*='FlightSearchCalendar']"))
                    .getAttribute("data-testid").equals("FlightSearchCalendar__"+date)
                    && daysAvailable.get(i).getAttribute("aria-disabled").equals("false")){
                daysAvailable.get(i).click();
                break;
            }
        }
    }

    public static void selectFromDropByIndex(WebElement drop, int value){
        Select select = new Select(drop);
        select.selectByIndex(value);
    }

    public static void selectFromDropByText(WebElement drop, String value){
        Select select = new Select(drop);
        select.selectByVisibleText(value);
    }
}
