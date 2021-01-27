package com.qa.junit.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.junit.base.TestBase;

public class ShopGrocery extends TestBase{
	
	@FindBy(xpath="//div[contains(@class,'tileGenV2_wrapper  tenCol ')]//div/descendant::span[contains(@class,'chevron_white')]")
	List <WebElement> list;
	
	public ShopGrocery() {
		PageFactory.initElements(driver, this);
	}
	
	public void validateGroceryOptions() {
		System.out.println("Number of suggestions are " + list.size());
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getText());
		}
	}

}
