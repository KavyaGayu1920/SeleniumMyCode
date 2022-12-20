package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MerchantPage {

	WebDriver driver;
	public MerchantPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//span[contains(text(),'Virtual Terminal')]")
	private WebElement VT;
	
	@FindBy(xpath="(//input[@name='transaction'])[2]")
	private WebElement Auth;
	
	@FindBy(xpath="//input[@name='amount']")
	WebElement amount;
	
	@FindBy  (xpath="//input[@name='phoneNo']")
	private WebElement mobileno;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement mailId;
	
	@FindBy(xpath="//input[@placeholder='Card number*']")
	private WebElement cardnumber;
	
	@FindBy(xpath="//input[@placeholder='MM/YY*']")
	private WebElement expiry;
	
	@FindBy(xpath="//div[@class='card-btn']")
	private WebElement process;
	
	@FindBy(xpath="(//span[contains(text(),'Process')])[2]")
	public WebElement confProcess;
	
	@FindBy(xpath="(//span[contains(text(),'APPROVED')]")
	private WebElement approvalScrn;
	
	@FindBy(xpath="//div[@id='removeArea']//button[2]")
	private WebElement clsbtn;
	
	@FindBy(xpath="//span[contains(text(),'Transactions')]")
	private WebElement txn;
	
	public WebElement VTmdl() {
		return VT;
	}
	public WebElement Auth() {
		return Auth;
	}
	public WebElement amount() {
		return amount;
	}
	public WebElement mobileno() {
		return mobileno;
	}
	public WebElement mailId() {
		return mailId;
	}
	public WebElement cardnumber() {
		return cardnumber;
	}
	public WebElement expiry() {
		return expiry;
	}
	public WebElement process() {
		return process;
	}
	public WebElement confProcess() {
		return confProcess;
	}
	public WebElement approvalScreen() {
		return approvalScrn;
	}
	public WebElement clsbtn() {
		return clsbtn;
	}
	public WebElement txnmdl() {
		return txn;
	}
}
