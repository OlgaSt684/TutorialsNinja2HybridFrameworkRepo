package com.tutorialsNinja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsNinja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

public class SearchTest extends Base {
	SearchPage searchPage;
	public WebDriver driver;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchBuitton();

		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),
				"Valid product HP is not displayed in search results ");
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		HomePage homePage = new HomePage(driver);
		homePage.enterProductNameIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchBuitton();

		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		/*Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSerachResult"),
				"No product message in search results is displayed");*/
		Assert.assertEquals(actualSearchMessage, "abcd",
				"No product message in search results is displayed");
	}

	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"} )
	public void verifySearchWithoutAnyProduct() {
		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchBuitton();

		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSerachResult"),
				"No product message in search results is displayed");

	}

}
