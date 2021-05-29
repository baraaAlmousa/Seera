package com.pageobjects;

import com.utilities.JavaScriptUtility;
import com.utilities.Synchronization;
import com.webpages.Traveller;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class FlightTravellerPO extends Traveller {

    @FindBy(css = "button[data-testid*='SelectFlightButton']")
    @CacheLookup
    protected WebElement selectFlight;

    @FindBy(css = "input[id='contact.firstName']")
    @CacheLookup
    protected WebElement firstNameContact;

    @FindBy(css = "input[name='contact.lastName']")
    @CacheLookup
    protected WebElement lastNameContact;

    @FindBy(css = "input[name='contact.email']")
    @CacheLookup
    protected WebElement emailContact;

    @FindBy(css = "input[name='contact.phone']")
    @CacheLookup
    protected WebElement mobileNumberContact;

    @FindBy(css = "button[data-testid*='ContinueToPaymentButton']")
    protected WebElement paymentButton;

    @FindBy(css = "label[type='invalid']")
    protected WebElement labelInvalid;

    public FlightTravellerPO(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public void selectTopFlight() throws Exception {
        Synchronization.waitFor(selectFlight,5).click();
    }

    @Override
    public void fillTravellerDetails(String firstName, String lastName, String email, String mobileNumber) throws Exception {
        JavaScriptUtility.scrollDown();
        Synchronization.waitFor(firstNameContact,10).sendKeys(firstName);
        lastNameContact.sendKeys(lastName);
        emailContact.sendKeys(email);
        mobileNumberContact.sendKeys(mobileNumber);
    }

    @Override
    public void proceedToPayment() {
        JavaScriptUtility.click(paymentButton);
    }

    @Override
    public void assertUserBlocked(){
        Assert.assertTrue(labelInvalid.isDisplayed());
    }
}
