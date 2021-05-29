package com.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class Homepage {


    protected WebDriver webDriver;

    @FindBy(id = "uncontrolled-tab-example-tab-flights")
    @CacheLookup
    protected WebElement flightsTab;

    @FindBy(id = "uncontrolled-tab-example-tab-hotels")
    @CacheLookup
    protected WebElement hotelsTab;


    public Homepage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Navigate to site's homepage based on the name of the site passed
     * @param site: Site name
     */
    public abstract void navigateHomePage(String site);


    /**
     * Search for a flight through the defined parameters
     * @param fromPlace: From place
     * @param toPlace: To place
     * @param dateFrom: Date of flight
     * @param dateTo: Return Date for round trips, when passed flight is considered as round trip
     * @param flightClass: Cabin Class
     * @param numOfPass: Number of adults for the booking
     * @throws Exception
     */
    public abstract void searchFlight(String fromPlace, String toPlace, String dateFrom, String dateTo, String flightClass, int numOfPass) throws Exception;


}
