package com.test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.driver.CreateDriver;
import com.pageobjects.FlightHomePO;
import com.pageobjects.FlightTravellerPO;
import com.pageobjects.FlightsSearchResultsPO;
import com.testUtilities.TestData;
import com.webpages.Homepage;
import com.webpages.SearchResults;
import com.webpages.Traveller;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {

    WebDriver webDriver;

    ExtentReports extentReports;

    @BeforeTest
    public void beforeTest(){
        CreateDriver createDriver = new CreateDriver();
        createDriver.setWebDriver();
        webDriver = CreateDriver.getWebDriver();
        String path = System.getProperty("user.dir") + "\\report\\index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("ExtendReportTest");
        extentSparkReporter.config().setDocumentTitle("Run Results");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester","Baraa");
    }

    @Test
    public void firstScenario() throws Exception {
        //AUH
        extentReports.createTest("first scenario");
        Homepage homepage = new FlightHomePO(webDriver);
        homepage.navigateHomePage("mosafer");
        String destination = TestData.generateOrigin();
        String origin = TestData.generateDestination();
        homepage.searchFlight(origin,destination,"2022-01-01","2022-01-22","Economy",2);
        SearchResults searchResults = new FlightsSearchResultsPO(webDriver);
        searchResults.assertSearch(origin,destination,"2022-01-01","2022-01-22","Economy",2);
        searchResults.sortByLeastStops();
        searchResults.assertSearchURLContains("?s=");
        Traveller traveller = new FlightTravellerPO(webDriver);
        traveller.selectTopFlight();
        traveller.fillTravellerDetails("baraa","khalid","khalidbaraa@gmail.com","529999999");
        traveller.proceedToPayment();
        traveller.assertUserBlocked();
    }

    @Test
    public void secondScenario() throws Exception {
        extentReports.createTest("second scenario");
        Homepage homepage = new FlightHomePO(webDriver);
        homepage.navigateHomePage("mosafer");
        homepage.searchFlight("RUH","JED","2021-09-01",null,"Economy",2);
        SearchResults searchResults = new FlightsSearchResultsPO(webDriver);
        searchResults.assertSearch("RUH","JED","2021-09-01",null,"Economy",2);
        searchResults.sortByCheapest();
        searchResults.assertSortByCheapest();
    }

    @AfterTest
    public void afterTest(){
     //   webDriver.quit();
        extentReports.flush();
    }
}
