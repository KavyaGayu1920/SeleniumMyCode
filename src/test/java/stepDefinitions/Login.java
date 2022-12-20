package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.MerchantPage;
import resources.Base;

public class Login extends Base{
	
	WebDriver driver;
	LoginPage loginpage;
	WebDriverWait wait;
	MerchantPage merchantPage;
	
	@Given("^Valid merchant credential like \"([^\"]*)\" and \"([^\"]*)\" to login portal$")
    public void valid_merchant_credential_like_something_and_something_to_login_portal(String mailid, String password) throws IOException {
		driver = initalizeDriver();
		driver.get(prop.getProperty("URL"));
		loginpage = new LoginPage(driver);
		loginpage.emailfield().sendKeys(prop.getProperty("email"));
		loginpage.passwordfield().sendKeys(prop.getProperty("password"));
        
    }

    @When("^I Click on login button$")
    public void i_click_on_login_button(){
    	loginpage.loginbtn1().click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			WebElement DialogBox = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[2]"))));
			if (DialogBox.isDisplayed()) {
				DialogBox.click();

			}
		} catch (Exception e) {
			
		}

       
    }
    @Then("^Merchant account page will be displayed$")
    public void merchant_account_page_will_be_displayed(){
    	WebElement portal_body = wait.until(ExpectedConditions.presenceOfElementLocated(
				(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-regular app-toolbar MuiToolbar-gutters']"))));
		if (portal_body.isDisplayed()) {
			driver.navigate().refresh();

		}
      
    }
    @And("^I Clicked on VT module$")
    public void i_clicked_on_vt_module(){
    	merchantPage = new MerchantPage(driver);
    	merchantPage.VTmdl().click();
       
    }

	@And("^I Entered all valid transaction details like$")
	public void i_entered_all_valid_transaction_details_like(DataTable data) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Map<String, String> dataMap = data.asMap(String.class,String.class);
		merchantPage.amount().sendKeys(dataMap.get("amount"));
		merchantPage.mobileno().sendKeys(dataMap.get("mobilenum"));
		merchantPage.mailId().sendKeys(dataMap.get("mail"));
		merchantPage.cardnumber().sendKeys(dataMap.get("PAN"));
		merchantPage.expiry().sendKeys(dataMap.get("expiry"));
	}

	@When("^I clicked on process button$")
	public void i_clicked_on_process_button() {
		merchantPage.process().click();
	}


	@When("^I clicked on process button in confirmation screen$")
	public void i_clicked_on_process_button_in_confirmation_screen() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement confProcess = wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("(//span[contains(text(),'Process')])[2]"))));
		boolean confScreen = confProcess.isDisplayed();
		Assert.assertTrue(confScreen);
		confProcess.click();

	}

	@Then("^Transaction should be approved or declined \"([^\"]*)\"$")
	public void transaction_should_be_approved_or_declined_expectedResult(String expectedResult) {
		WebElement approval_msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'APPROVED')]")));
		Assert.assertEquals(approval_msg.getText(), "APPROVED");
		if (approval_msg.getText().contains("APPROVED")) {
			merchantPage.clsbtn().click();
			merchantPage.txnmdl().click();
		}
}
	@After
	public void closure() {
		driver.quit();
	}
	
}
