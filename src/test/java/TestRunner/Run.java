package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(

		features = {".//Features/LoginFeature.feature",".//Features/Customers.feature"}, //execute specific feature file
		//features = ".//Features/", //execute all feature file
		
		glue = "StepDefinition", 
		dryRun = false, //it will check for every feature step there will be step defination written or not
		monochrome = true,
		
		tags = "@Sanity",//scenarios under @sanity tag will be executed only
		
//		tags = "@Sanity or @regreassion",//scenarios under @sanity or @regression tag will be executed both sanity and regression
//		tags = "@Sanity and @regreassion",//scenarios under @sanity or @regression tag will execute only those test which senario have Written @Sanity @regression annotation
//		tags = "@Sanity and not @regreassion",//scenarios under @sanity or @regression tag will be executed sanity not regression
		
		//plugin = { "pretty", "html:target/cucumber-reports/reports_html.html" } // basic cucumber report
		
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} // generate report using extent report

//		plugin = { "pretty", "junit:target/cucumber-reports/report_xml.xml", "html:target/cucumber-reports/reports_html.html", "json:target/cucumber-reports/report_json.json"} in one line generate all three report html, json and xml
// 		plugin = {"pretty","junit:target/cucumber-reports/report_xml.xml"}
//		plugin = {"pretty","json:target/cucumber-reports/report_json.json"}

)

public class Run extends AbstractTestNGCucumberTests{
	// this class will be empty
}
