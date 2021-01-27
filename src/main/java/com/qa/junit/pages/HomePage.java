package com.qa.junit.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.junit.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//div[contains(@class,'css-ywfyo0 enytrj80')]")
	WebElement walmartlogo;
	
	@FindBy(xpath="//span[contains(text(),'Shop grocery')]")
	WebElement shopgrocery;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateWalmartImage() {
		return walmartlogo.isDisplayed();
	}
	
	public ShopGrocery validateShopGrocery() {
		shopgrocery.click();
		return new ShopGrocery();
	}


}
