package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;

	// Objects
	@FindBy(linkText = "Edit your account information")
	private WebElement editAccountInformationOption;

	
	//Actions
	public AccountPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

		public boolean getDisplayStatusOfEditAccountInformationOption() {
		
		boolean dispalyStatus = editAccountInformationOption.isDisplayed();
		return dispalyStatus;
	}

}
