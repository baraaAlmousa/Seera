package com.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Traveller {

    protected WebDriver webDriver;

    public Traveller(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    /**
     * Selects the flight at the top of the search results
     * @throws Exception
     */
    public abstract void selectTopFlight() throws Exception;

    /**
     * Fill traveler details in contact details
     * @param firstName: Travellers first name
     * @param lastName: Travellers last name
     * @param email: Email address of the traveller
     * @param mobileNumber: Travellers mobile number
     * @throws Exception
     */
    public abstract void fillTravellerDetails(String firstName, String lastName, String email, String mobileNumber) throws Exception;

    /**
     * Click to proceed to payment page
     */
    public abstract void proceedToPayment();

    /**
     * Assert user is blocked if mandatory field is left empty
     */
    public abstract void assertUserBlocked();
}
