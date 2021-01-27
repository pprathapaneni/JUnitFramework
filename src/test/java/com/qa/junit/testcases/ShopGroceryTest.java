package com.qa.junit.testcases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.qa.junit.base.TestBase;
import com.qa.junit.pages.HomePage;
import com.qa.junit.pages.ShopGrocery;

@ExtendWith(TestResultLogger.class)
public class ShopGroceryTest extends TestBase{
	
	HomePage homepage;
	ShopGrocery shopgrocery;
	
	public ShopGroceryTest() {
		super();
	}
	
	@BeforeEach
	public void SetUp() {
		initialization();
		homepage = new HomePage();
		shopgrocery = homepage.validateShopGrocery();
	}
	
	@Test
	@Order(1)
	public void validateShopGroceryTitleTest() {
		String title = driver.getTitle();
		Assertions.assertEquals(title, "Walmart Grocery Delivery &amp; Pickup: Order Groceries online | Walmart Canada");
	}
	
	@Test
	@Order(2)
	public void validateGroceryOptionsTest() {
		shopgrocery.validateGroceryOptions();
	}
	@AfterEach
	public void tearDown() {
		driver.quit();
	}


}
