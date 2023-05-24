package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;

	@FindBy(linkText = "HP LP3065")
	private WebElement validHPPropduct;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noPropductMessageText;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public boolean displayStatusOfHPValidProduct() {
		boolean displayStatus = validHPPropduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductMessageText() {
		String noProductMessageText = noPropductMessageText.getText();
	return noProductMessageText;
	}
}
