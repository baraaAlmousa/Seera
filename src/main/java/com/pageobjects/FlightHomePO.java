package com.pageobjects;

import com.elements.CalenderElement;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import com.utilities.JavaScriptUtility;
import com.utilities.Synchronization;
import com.webpages.Homepage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightHomePO extends Homepage {

    @FindBy(css = "select[data-testid='FlightSearchCalendar__MonthDropdown']")
    protected WebElement dropMonth;

    @FindBy(css = "button[data-testid='FlightSearchBox__SearchButton']")
    protected WebElement searchFlight;

    @FindBy(css = "select[data-testid='FlightSearchCalendar__YearDropdown']")
    protected WebElement dropYear;

    @FindBy(css = "input[data-testid='AutoCompleteInput']")
    protected WebElement searchHotel;

    @FindBy(css = "input[data-testid='FlightSearchBox__FromAirportInput']")
    protected WebElement flightFrom;

    @FindBy(css = "input[data-testid='FlightSearchBox__ToAirportInput']")
    protected WebElement flightTo;

    @FindBy(css="[data-testid*='SearchBox__FromDateButton']")
    @CacheLookup
    protected WebElement fromDate;

    @FindBy(css = "ul[data-testid='AutoCompleteResultsList']")
    protected WebElement autoList;

    @FindBy(css = "[data-testid='FlightSearchBox__OneWayButton']")
    protected WebElement oneWay;

    @FindBy(css = "[data-testid='FlightSearchBox__CabinTypeDropdown']")
    @CacheLookup
    protected WebElement flightDropList;

    @FindBy(css = "[data-testid='FlightSearchBox__PaxDropdown']")
    @CacheLookup
    protected WebElement passDetails;

    @FindBy(css = "[data-testid='FlightSearchPAXSelection__AdultsPlusButton']")
    protected WebElement increasePass;

    public FlightHomePO(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void navigateHomePage(String site) {
        if(site.equals("mosafer"))
            webDriver.get("https://www.almosafer.com/en/");
        else if(site.equals("tajwal"))
            webDriver.get("https://www.tajawal.com/en");
    }

    @Override
    public void searchFlight(String fromPlace, String toPlace, String dateFrom, String dateTo, String flightClass, int numOfPass) throws Exception {
        flightsTab.click();
        fillDestination(fromPlace,toPlace);
        handleDate(dateFrom,dateTo,fromDate);
        flightClass(flightClass);
        increaseNumOfPass(numOfPass);
        performSearch();
        waitPageLoad();
    }

    public void performSearch() throws Exception {
        searchFlight.click();
        Synchronization.waitForBarLoading(50);
    }

    // Method to determine type of trip (round or one way) and select date
    private void handleDate(String dateFrom, String dateTo, WebElement calender) throws Exception {
        if (dateTo == null) {
            oneWay.click();
            fillDateFrom(calender, dateFrom);
        }else {
            fillDateFrom(calender, dateFrom);
            fillDateTo(dateTo);
        }
    }

    private void fillDateFrom(WebElement calender,String date) throws Exception{
        String []formatDate = date.split("-");
        CalenderElement.clickCalender(calender);
        CalenderElement.selectFromDropByText(dropYear,formatDate[0]);
        CalenderElement.selectFromDropByIndex(dropMonth,Integer.parseInt(formatDate[1])-1);
        CalenderElement.searchWithinCalender(date);
    }

    private void fillDateTo(String date){
        CalenderElement.searchWithinCalender(date);
    }

    private void fillDestination(String from, String to) throws Exception {
        flightFrom.sendKeys(from);
        getValueOfList(from);
        Thread.sleep(2000);
        flightTo.sendKeys(to);
        getValueOfList(to);
    }

    // Private method to handle the dynamic auto-complete list
    private void getValueOfList(String value) throws Exception {
        List<WebElement> options = Synchronization.waitFor(autoList,5).findElements(By.xpath("//li[contains(@class,'AutoComplete__ListItem')]/div/span"));
        for (WebElement option: options){
            String textValue;
            try {
                 textValue = option.getText();
            }catch (Exception ex){
                continue;
            }
            if(textValue != null && textValue.equals(value)){
                Synchronization.waitFor(option,5).click();
                break;
            }
        }
    }

    // Select the desired flight class from the list of its available options
    private void flightClass(String fClass){
        flightDropList.click();
        List<WebElement> listValues = flightDropList.findElements(By.cssSelector("[data-testid*='FlightSearchCabinSelection__']"));
        for (WebElement value : listValues){
            if(value.getText().equalsIgnoreCase(fClass)){
                value.click();
                break;
            }
        }
    }

    // Increases the number of adults for a flight, by default one is set
    private void increaseNumOfPass(int number){
        if(number > 1){
            passDetails.click();
            for(int i = 1; i < number; i++){
                increasePass.click();
            }
        }
    }

    // Method is used to wait until page loads using JavaScript command
    private void waitPageLoad () throws Exception {
            JavaScriptUtility.isPageReady(webDriver);
    }


}
