package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MerchantPage;
import resources.Base;

public class VTLogin extends Base {

	public WebDriver driver;

	@Test
	public WebDriver login() throws IOException{
		
		driver = initalizeDriver();
		driver.get(prop.getProperty("URL"));

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailfield().sendKeys(prop.getProperty("email"));
		loginpage.passwordfield().sendKeys(prop.getProperty("password"));
		loginpage.loginbtn1().click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement DialogBox = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[2]"))));
			if (DialogBox.isDisplayed()) {
				DialogBox.click();

			}
		} catch (Exception e) {

		}

		WebElement portal_body = wait.until(ExpectedConditions.presenceOfElementLocated(
				(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-regular app-toolbar MuiToolbar-gutters']"))));
		if (portal_body.isDisplayed()) {
			driver.navigate().refresh();

		}

		MerchantPage merchantPage = new MerchantPage(driver);
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		merchantPage.VTmdl().click();
		merchantPage.amount().sendKeys("10000");
		merchantPage.mobileno().sendKeys("9941453510");
		merchantPage.mailId().sendKeys("gayathrikavy1920@gmail.com");
		merchantPage.cardnumber().sendKeys("4111111111111111");
		merchantPage.expiry().sendKeys("0225");
		merchantPage.process().click();
		merchantPage.confProcess().click();
		WebElement approval_msg = wait1
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'APPROVED')]")));
		Assert.assertEquals(approval_msg.getText(), "APPROVED");
		if (approval_msg.getText().contains("APPROVED")) {
			merchantPage.clsbtn().click();
			merchantPage.txnmdl().click();

		}
		return driver;

	}

	@DataProvider
	public Object[][] getData() {
		Object[][] loginData = {{"gayathrikavya1920+merchant@gmail.com","1234Qwerty","Successful"}};
		return loginData;
	}

}
