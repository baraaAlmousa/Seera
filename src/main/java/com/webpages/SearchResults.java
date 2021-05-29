package com.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class SearchResults {

    protected WebDriver webDriver;

    public SearchResults(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Assert search criteria matches the results
     * @param fromDate: From place
     * @param toDate: To place
     * @param dateFrom: Flight date
     * @param dateTo: Return flight date for round trips
     * @param classFlight: Cabin class
     * @param pass: Number of adults taking the flight
     * @throws Exception
     */
    public abstract void assertSearch(String fromDate, String toDate, String dateFrom, String dateTo, String classFlight, int pass) throws Exception;

    /**
     * Sort the flights by the cheapest
     */
    public abstract void sortByLeastStops();

    /**
     * Assert URL contains value
     * @param value: Part of URL
     * @throws Exception
     */
    public abstract void assertSearchURLContains(String value) throws Exception;

    /**
     * Sort the flights by the cheapest
     */
    public abstract void sortByCheapest();

    /**
     * Assert the flights returned are sorted by the cheapest
     */
    public abstract void assertSortByCheapest();

}
