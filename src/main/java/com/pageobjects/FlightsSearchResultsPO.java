package com.pageobjects;

import com.utilities.Format;
import com.utilities.JavaScriptUtility;
import com.utilities.Synchronization;
import com.webpages.SearchResults;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import java.util.List;

public class FlightsSearchResultsPO extends SearchResults {


    @FindBy(css = "[data-testid='FlightSearchResults__OriginCityLabel']")
    @CacheLookup
    protected WebElement originPlace;

    @FindBy(css = "[data-testid='FlightSearchResults__OriginAirportLabel']")
    @CacheLookup
    protected WebElement originAirport;

    @FindBy(css = "[data-testid='FlightSearchResults__DestinationCityLabel']")
    @CacheLookup
    protected WebElement destPlace;

    @FindBy(css = "[data-testid='FlightSearchResults__DestinationAirportLabel']")
    @CacheLookup
    protected WebElement desAirport;

    @FindBy(css = "[data-testid='FlightSearchResults__DepartureDateLabel']")
    @CacheLookup
    protected WebElement depDate;

    @FindBy(css = "[data-testid='FlightSearchResults__ArrivalDateLabel']")
    @CacheLookup
    protected WebElement arrDate;

    @FindBy(css = "[data-testid='FlightSearchResults__PassengersCountLabel']")
    @CacheLookup
    protected WebElement pass;

    @FindBy(css = "[data-testid='FlightSearchResults__CabinTypeLabel']")
    @CacheLookup
    protected WebElement classFlight;

    @FindBy(css = "button[class*='btn-glow--active']")
    protected WebElement filterApplied;

    @FindBy(css = "button[data-testid='Cheapest__SortBy']")
    @CacheLookup
    protected WebElement cheapestButton;

    @FindBy(css = "div[data-testid*='__PriceLabel']")
    @CacheLookup
    protected WebElement priceLabel;

    @FindBy(css = "div[data-testid='Collapsed_PriceFilter']")
    @CacheLookup
    protected WebElement priceFilterExpand;

    @FindBy(css = "span[data-testid='FlightSearchResult__PriceFilter__Min']")
    protected WebElement minPriceFlight;

    public FlightsSearchResultsPO(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void assertSearch(String fromDate, String toDate, String dateFrom, String dateTo, String classFlight, int pass) throws Exception {
        Assert.assertEquals(originAirport.getText(),fromDate);
        Assert.assertEquals(desAirport.getText(),toDate);
        Assert.assertTrue(depDate.getText().contains(Format.formatDate(dateFrom)));
        if(dateTo != null){
            Assert.assertTrue(arrDate.getText().contains(Format.formatDate(dateTo)));
        }
        Assert.assertEquals(Integer.parseInt(this.pass.getText()),pass);
        Assert.assertEquals(this.classFlight.getText(),classFlight);
        assertSearchURLContains(dateFrom);
    }


    @Override
    public void assertSearchURLContains(String value) throws Exception {
        int count = 0;
        int maxTries = 6;
        while (count < maxTries) {
            try {
                Assert.assertTrue(webDriver.getCurrentUrl().contains(value));
                count = maxTries;
            } catch (AssertionError ex) {
                if(++count == maxTries) throw ex;
            }
        }
    }

    @Override
    public void sortByLeastStops() {
        List<WebElement> stopOptions = webDriver.findElements(By.cssSelector("input[name*='stop-']"));
        List<WebElement> elementClickable = webDriver.findElements(By.cssSelector("label[for*='stop-']"));
        for (int i=0;i<stopOptions.size();i++){
            if(!stopOptions.get(i).getAttribute("data-testid").contains("disabled") && !stopOptions.get(i).getAttribute("data-testid")
            .contains("any_number")){
                elementClickable.get(i).click();
                break;
            }
        }
    }

    @Override
    public void sortByCheapest(){
        cheapestButton.click();
    }

    @Override
    public void assertSortByCheapest(){
        JavaScriptUtility.isPageReady(webDriver);
        Assert.assertTrue(cheapestButton.getAttribute("data-testid").contains("selected"));
        assertPriceIsCheapest();
    }

    // Method to assert the top flight is the cheapest using the price filter
    private void assertPriceIsCheapest(){
        String priceOfFirstFlightSorted = priceLabel.getText();
        priceFilterExpand.click();
        String lowestPriceFilter = minPriceFlight.getText();
        Assert.assertTrue(lowestPriceFilter.contains(priceOfFirstFlightSorted));
    }

}
