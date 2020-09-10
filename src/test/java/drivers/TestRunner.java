package drivers;

import java.io.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = {".\\src\\test\\resources\\features"},
	glue = {"stepDefinitions"},
	plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
	monochrome = true
	)
  

public class TestRunner {
	
	@BeforeClass
	public static void beforeclass_func()
	{
		System.out.println("Before class is executed");
	}
	
	
	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(".\\extent-config.xml"));
	}
}