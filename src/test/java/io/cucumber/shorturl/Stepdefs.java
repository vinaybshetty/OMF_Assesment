package io.cucumber.shorturl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class Stepdefs {
	
	public static WebDriver driver;
	public static WebElement Login;
	public static WebElement Username;
	public static WebElement Password;
	public String shorturl;
	String baseURL = "https://bitly.com/";
	@Given("open bit.ly url")
	public void open_bit_ly_url() {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver", "C:\\cucumber-java-skeleton-master\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseURL);
	}

	@Then("Display the main page")
	public void display_the_main_page() {
	    // Write code here that turns the phrase above into concrete actions
		 Login = driver.findElement(By.xpath("//*[@id='sidemenu']//span[@class='menu-image-title' and contains(text(),'Log in')]"));
		
		
		Login.isDisplayed();
	}

	@Given("User logs in as user {string} and password {string}")
	public void user_logs_in_as_user_and_password(String username, String password) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	   Login.click();
	   
	  Username = driver.findElement(By.xpath("//input[@type='text' and @placeholder='Email address or username'] "));
	  
	  Username.sendKeys(username);
		
		Password = driver.findElement(By.xpath("//input[@type='password' and @placeholder='Password' and @tabindex=4]"));
		
		Password.sendKeys(password);
		
		driver.findElement(By.xpath("//input[@type='submit' and @class='button button-primary sign-up-in' and @tabindex=5]")).click();
		
		Thread.sleep(5000);
	}

	@Then("User Home Page is displayed")
	public void user_Home_Page_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//span[@class='bitly-icon']"))	.isDisplayed();
	}

	@Given("User click on Create button")
	public void user_click_on_Create_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		 driver.findElement(By.xpath("//span[@class='dropdown--text' and contains(text(),'CREATE')]")).click();
		 
		 Thread.sleep(5000);
	}

	@Given("creates short url for the link {string}")
	public void creates_short_url_for_the_link(String URL) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//div[@class='action-sheet--wrapper-MAIN']//div[@class='text-field--wrapper']//*[@type='text']")).sendKeys(URL);
		   
		   driver.findElement(By.xpath("//button[@class='button--SOLID-SECONDARY' and contains(text(),'CREATE')]")).click();
		   
		   Thread.sleep(5000);
	}

	@Then("short url should be created")
	public void short_url_should_be_created() {
	    // Write code here that turns the phrase above into concrete actions
		boolean created =  driver.findElement(By.xpath("//time[@class='action-sheet--created-date']")).isDisplayed();
		 
		
		 
		 if(created)
		 {
			System.out.println("Link created");
		 }
		 else
		 {
			 System.out.println("Link creation failed");
		 Assert.fail();
		 }
		 
		String code =  driver.findElement(By.xpath("//div[@class='action-sheet--wrapper-MAIN']//div[@class='input-field--CUSTOM   ']//*[@type='text']")).getAttribute("value");
		
		shorturl = "http://bit.ly/"+code;
		 
		 System.out.println("The New Short URL is bit.ly/"+shorturl);
		 
		 driver.quit();
	}
	
	@Given("open shorturl")
	public void open_shorturl() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver", "C:\\cucumber-java-skeleton-master\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	   driver.get(shorturl);
	   
	   Thread.sleep(10000);
	}

	@Then("It should redirect to the long URL {string}")
	public void it_should_redirect_to_the_long_URL(String LongURL) {
	    // Write code here that turns the phrase above into concrete actions
	   String CurrentURL= driver.getCurrentUrl();
	   
	   if(CurrentURL.equals(LongURL))
	   {
		   System.out.println("Passed");
		   
	   }
	   
	   else 
	   {
		   System.out.println("Failed");
		   Assert.fail();
	   }
	   
	   driver.quit();
	}

	@Then("Error message should be displayed")
	public void error_message_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		   driver.findElement(By.xpath("//button[@class='button--SOLID-SECONDARY' and contains(text(),'CREATE')]")).isDisplayed();
		   
		   driver.quit();
	}
	
	@Then("Invalid Login message is displayed")
	public void invalid_login_message_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		   driver.findElement(By.xpath("//*[@class='error-message' and contains(text(),'Nope. Try again.')]")).isDisplayed();
		   
		   driver.quit();
	}
}
