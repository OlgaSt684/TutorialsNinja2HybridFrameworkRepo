package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsNinja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;

public class RegisterTest extends Base {
	AccountSuccessPage accountSuccessPage;
		RegisterPage registerPage;
	public WebDriver driver;
	public RegisterTest() {
		super();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolice();
		accountSuccessPage =  registerPage.clickOnContinueButton();

		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeadText();

		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account success page not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolice();
		registerPage.selectYesNewsLetterOption();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeadText();

		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"Account success page not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolice();
		registerPage.clickOnContinueButton();

		String actualWarning = registerPage.retrieveDublicateEmailAdressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {
		registerPage.clickOnContinueButton();

		String actualPravicyPolicyWarning = registerPage.retrievePravicyPoliceWarning();
		Assert.assertEquals(actualPravicyPolicyWarning, dataProp.getProperty("privacyPolicyWarning"),
				"Pravicy policy warning message is not displayed");

		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),
				"First name warning message is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"),
				"Last name warning message is not displayed");

		String actualEmailWarning = registerPage.retrieveEmailWarning();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),
				"Email warning message is not displayed");

		String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),
				"Telephone warning message is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),
				"Password warning message is not displayed");

	}
}
