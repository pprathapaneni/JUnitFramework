package com.qa.junit.testcases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.qa.junit.base.TestBase;
import com.qa.junit.pages.HomePage;
import com.qa.junit.pages.ShopGrocery;

public class HomePageTest extends TestBase {
	
	HomePage homepage;
	ShopGrocery shopgrocery;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeEach
	public void setUp() {
		initialization();
		homepage = new HomePage();
	}
	
	@Test
	@Order(1)
	public void validateHomePageTitleTest() {
		String title = homepage.validateHomePageTitle();
		Assertions.assertEquals(title, "Online Shopping Canada: Everyday Low Prices at Walmart.ca!");
	}
	
	@Test
	@Order(2)
	public void validateWalmartImageTest() {
		boolean flag = homepage.validateWalmartImage();
		Assertions.assertTrue(flag);
	}
	
	@Test
	@Order(3)
	public void validateShopGroceryTest() {
		shopgrocery = homepage.validateShopGrocery();
	}
	
	@AfterEach
	public void tearDown() {
		driver.quit();
	}


}
