package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="emailtype")
	private WebElement emaild;
	
	@FindBy(id="passwordtype")
	private WebElement password;
	
	@FindBy(xpath="//button[@*='button']")
	private WebElement loginbtn;
	
	
	public WebElement emailfield() {
		return emaild;
	}
	public WebElement passwordfield() {
		return password;
	}
	public WebElement loginbtn1() {
		return loginbtn;
	}
	

}
