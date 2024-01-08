package StepDefinition;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

/**
 * 
 * @author ARSHAD Child class of base class
 */
public class StepDef extends BaseClass {

	@Before
	public void setup() { // we can also define condition which setup before method will execute for
							// sanity and which one for regression setup("@Sanity)
		readConfig = new ReadConfig();

		// initialise logger
		log = LogManager.getLogger("StepDef");

		System.out.println("Set up method executed...");

		String browser = readConfig.getBrowser();

		// launch browser
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		// implicit wait of 10 secs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		log.fatal("Setup executed...");

	}

//	@Before(order=0) //we can have multiple before method and also define order in which they execute order start from zero and 1, 2, 3, so on..
//	public void setup2() {
//		System.out.println("Set up method executed...");
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		loginPage = new LoginPage(driver);
		addNewCustPg = new AddNewCustomerPage(driver);
		SearchCustPg = new SearchCustomerPage(driver);
		log.info("Chrome brower launched");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		driver.get(url);
		log.info("url oppend");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String emailAdd, String password) {
		loginPage.enterEmail(emailAdd);
		loginPage.enterPassword(password);
		log.info("username and password entered");
	}

	@When("Click on Login")
	public void click_on_login() {
		loginPage.clickOnLoginButton();
		log.info("Clicked on login button");
	}

	////////// Login feature///////////////////////////
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expTitle) {
		String actualTitle = driver.getTitle();

		if (actualTitle.equals(expTitle)) {
			log.warn("Test Passed : login feature page title mached");
			Assert.assertTrue(true);// true
		} else {
			log.warn("Test Failed : login feature page title not mached");
			Assert.assertTrue(false);// false
		}

	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() {
		loginPage.clickOnLogOutButton();
		log.info("clicked on logout buttonm link");

	}

	/*@Then("close browser")
	public void close_browser() {
		driver.close();
		log.info("Browser closed");
//		driver.quit();

	}*/

	////////////////// Add New Customer ////////////

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Dashboard / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {
			log.info("user can view dashboard test passed.");
			Assert.assertTrue(true);

		} else {
			Assert.assertTrue(false);
			log.warn("user can view dashboard test failed.");

		}
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() {
		addNewCustPg.clickOnCustomersMenu();
		log.info("customer menu clicked");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() {
		addNewCustPg.clickOnCustomersMenuItem();
		log.info("customer menu item clicked");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("click on Add new button")
	public void click_on_add_new_button() {
		addNewCustPg.clickOnAddnew();
		log.info("clicked on add new button.");

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String actualTitle = addNewCustPg.getPageTitle();
		String expectedTitle = "Add a new customer / nopCommerce administration";

		if (actualTitle.equals(expectedTitle)) {
			log.info("User can view Add new customer page- passed");

			Assert.assertTrue(true);// pass
		} else {
			log.info("User can view Add new customer page- failed");

			Assert.assertTrue(false);// fail
		}
	}

	@When("User enter customer info")
	public void user_enter_customer_info() {
		// addNewCustPg.enterEmail("cs129@gmail.com");
		addNewCustPg.enterEmail(generateEmailId() + "@gmail.com");
		addNewCustPg.enterPassword("test1");
		addNewCustPg.enterFirstName("Arshad");
		addNewCustPg.enterLastName("ALi");
		addNewCustPg.enterGender("Male");
		addNewCustPg.enterDob("6/13/1988");
		addNewCustPg.enterCompanyName("CodeStudio");
		addNewCustPg.enterAdminContent("Admin content");
		addNewCustPg.enterManagerOfVendor("Vendor 1");

		log.info("customer information entered");

	}

	@When("click on Save button")
	public void click_on_save_button() {
		addNewCustPg.clickOnSave();
		log.info("clicked on save button");

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {

		String bodyTagText = driver.findElement(By.tagName("Body")).getText();
		if (bodyTagText.contains(exptectedConfirmationMsg)) {
			Assert.assertTrue(true);// pass
			log.info("User can view confirmation message - passed");

		} else {
			log.warn("User can view confirmation message - failed");

			Assert.assertTrue(false);// fail

		}

	}

	//////////// Search Customer//////////////////////////
	@When("Enter customer EMail")
	public void enter_customer_e_mail() {
		SearchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
		log.info("Email address entered");

	}

	@When("Click on search button")
	public void click_on_search_button() {
		SearchCustPg.clickOnSearchButton();
		log.info("Clicked on search button.");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
		String expectedEmail = "victoria_victoria@nopCommerce.com";

//   Assert.assertTrue(SearchCustPg.searchCustomerByEmail(expectedEmail)); alternate

		if (SearchCustPg.searchCustomerByEmail(expectedEmail) == true) {
			Assert.assertTrue(true);
			log.info("User should found Email in the Search table - passed");

		} else {
			log.info("User should found Email in the Search table - passed");
			Assert.assertTrue(false);

		}

	}

	//////////////// Search Customer By Name ///////////////////

	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		SearchCustPg.enterFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		SearchCustPg.enterLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		String expectedName = "Victoria Terces";

		if (SearchCustPg.searchCustomerByName(expectedName) == true) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	//@After
	public void teardown(Scenario sc) {
		System.out.println("Tear down method executed...");
		if (sc.isFailed() == true) {
			// Convert web driver object to TakeScreenshot

			String fileWithPath = "E:\\cucumberProject\\CucumberFramework\\Screenshot\\failedScreenshot.png";
			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
			File DestFile = new File(fileWithPath);

			// Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}
	
	
	@AfterStep
	public void addScreenshot(Scenario Scenario) { //generating screenshort using extent report
		
		if(Scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			//attche image file report(data, media type, name of the attchement)
			Scenario.attach(screenshot, "image/png", Scenario.getName());
		}		
	
	}

//	@After(order=in reverse order 5, 4 , 3, 2)
//	public void teardown1() { //we can have multiple After method and also define order in which they execute order start from 5, 4, 3, 2 reverse order so on..
//		System.out.println("Tear down method executed...");
//		driver.quit();
//	}

	/*
	 * @BeforeStep public void beforeStepMethodDemo() {
	 * System.out.println("This is before step...."); }
	 * 
	 * 
	 * @AfterStep public void afterStepMethodDemo() {
	 * System.out.println("This is after step...."); }
	 */

}
