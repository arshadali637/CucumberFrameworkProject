package StepDefinition;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import PageObject.AddNewCustomerPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utilities.ReadConfig;

/**
 * 
 * @author ARSHAD Base class - parent class
 */
public class BaseClass {

	public static WebDriver driver;
	public LoginPage loginPage;
	public SearchCustomerPage SearchCustPg;
	public AddNewCustomerPage addNewCustPg;
	public static Logger log;
	public ReadConfig readConfig;

	// generate unique email id
	public String generateEmailId() {
		return (RandomStringUtils.randomAlphabetic(5));
	}

}
