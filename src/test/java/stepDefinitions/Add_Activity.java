package stepDefinitions; 
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Sleeper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import object_repos.Activity_objectrepo;
import utils.Webinterface;

public class Add_Activity extends Webinterface
{

	Activity_objectrepo element = new Activity_objectrepo();
	Logger logger= Logger.getLogger(Add_Activity.class.getName()); 


	@Given("^Navigate to TODO system$")
	public void navigate_to_TODO_system()
	{
		initiate_browser();

	}

	@Then("^Validate TODO search box and item list is empty$")
	public void validate_TODO_search_box_and_item_list_is_empty() throws Throwable {

		Assert.assertEquals(isElementPresent(element.todo_filters),false);
	}

	@When("^Add new activity \"(.*?)\"$")
	public void add_new_activity(String input) throws Throwable {
		enter(element.todo_add_input, input+Keys.ENTER);

	}

	@Then("^Validate new activity is added to the list \"(.*?)\"$")
	public void validate_new_activity_is_added_to_the_list(String item_name) throws Throwable 
	{
		Assert.assertTrue(isElementPresent(String.format(element.todo_added_item,item_name)));
		Assert.assertEquals(get_value(element.todo_items_left), "1");
	}


	@When("^Mark Item completed$")
	public void mark_Item_completed() throws Throwable {
		click(element.todo_item_checkbox);
	}

	@Then("^Verify Item is strike out from the list$")
	public void verify_Item_is_strike_out_from_the_list() 
	{
		Assert.assertTrue(isElementPresent(element.todo_item_completed));
	}

	@Then("^Verify the items left in footer$")
	public void verify_the_items_left_in_footer()
	{
		Assert.assertEquals(get_value(element.todo_items_left), "");
	}

	@When("^Delete an item from the todo list \"(.*?)\"$")
	public void Delete_an_item_from_the_todo_list(String item_name) throws Throwable 
	{
		hover_and_click_element(String.format(element.todo_added_item,item_name),element.todo_cross_button);
	}

	@Then("^Verify Item is removed from the list \"(.*?)\"$")
	public void verify_Item_is_removed_from_the_list(String item_name) throws Throwable 
	{
		Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),false);
	}

	

	@Then("^Verify Active Tab \"(.*?)\"$")
	public void verify_Active_Tab(String item_name) throws Throwable 
	{
	    click(element.todo_active_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),true);
	    click(element.todo_completed_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),false);
	    click(element.todo_all_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),true);
	    
	
	}
	
	@Then("^Verify Completed Tab \"(.*?)\"$")
	public void verify_Completed_Tab(String item_name) throws Throwable 
	{
		click(element.todo_completed_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),true);
	    click(element.todo_active_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),false);
	    click(element.todo_all_items_tab);
	    Assert.assertEquals(isElementPresent(String.format(element.todo_added_item,item_name)),true);
	}
	
	@Then("^Close TODO System$")
	public void close_todo_system() throws Throwable 
	{
		closeBrowser();
		
	}


	
	
	//Unit Testing
	public static void main(String args[]) throws Throwable
	{
		Add_Activity bk = new Add_Activity();
		bk.navigate_to_TODO_system();;
		bk.validate_TODO_search_box_and_item_list_is_empty();
		bk.add_new_activity("Activity 1");
		bk.validate_new_activity_is_added_to_the_list("Activity 1");
		bk.Delete_an_item_from_the_todo_list("Activity 1");
		

	}

	//	@Given("^Navigate to Budget Com$")
	//	public void navigate_to_Budget_Com() throws InterruptedException 
	//	{
	//		initiate_browser();
	//
	//		click(element.reservation_menu);
	//		click(element.make_reservation_menu);
	//	}
	//
	//
	//	@When("^Choose Pickup location as \"([^\"]*)\"$")
	//	public void choose_Pickup_location_as(String pickuplocation) throws InterruptedException 
	//	{
	//		TimeUnit.SECONDS.sleep(3);
	//		enter(element.pickup_location_element,pickuplocation);
	//		TimeUnit.SECONDS.sleep(5);
	//
	//		click_pickup_location(String.format(element.pickup_location_dropdown,pickuplocation.split(",")[0]));
	//
	//	}
	//
	//	@When("^Filter with vehicle type \"([^\"]*)\"$")
	//	public void filter_with_vehicle_type(String vehicle_type) throws InterruptedException
	//	{
	//		click(element.vehicle_type_element);
	//		click(String.format(element.select_vehicle_type,vehicle_type));		
	//	}
	//
	//	@When("^Identify Low Price vehicles and click on the Pay Now \"([^\"]*)\",\"([^\"]*)\"$")
	//	public void identify_Low_Price_vehicles_and_click_on_the_Pay_Now(String doors, String seats) throws Throwable {
	//		
	//		List<String> vehicle_price_list = get_webelements(element.vehicle_list_preferred,doors,  seats);
	//
	//		List<Integer> pricelist = new ArrayList<Integer>();
	//
	//		for(String vehicle_price:vehicle_price_list)
	//		{
	//			pricelist.add(Integer.parseInt(vehicle_price));
	//		}
	//		logger.info("Added all the filtered price to the list");
	//		Collections.sort(pricelist);
	//		low_price = NumberFormat.getIntegerInstance().format(pricelist.get(0));
	//		page_refresh();
	//		click(String.format(element.vehicle_low_price+element.pay_now,low_price));
	//
	//		if(isElementPresent(element.upgrade_popup))
	//		{
	//			click(element.upgrade_popup);
	//		}
	//
	//	}
	//
	//	@When("^Capture and validate pickup and return locations from the previous page \"([^\"]*)\"$")
	//	public void capture_and_validate_pickup_and_return_locations_from_the_previous_page(String location) throws InterruptedException
	//	{
	//		TimeUnit.SECONDS.sleep(5);
	//		Assert.assertTrue(location.toLowerCase().contains(get_value(element.pickup_location_preview).toLowerCase()));
	//		Assert.assertTrue(location.toLowerCase().contains(get_value(element.return_location_preview).toLowerCase()));
	//	}
	//
	//	@Then("^Validate \"([^\"]*)\" from chosen vehicle List$")
	//	public void validate_from_chosen_vehicle_List(String vehicle_type) throws InterruptedException 
	//	{
	//		TimeUnit.SECONDS.sleep(5);
	//		Assert.assertTrue(get_value(element.vehicle_type_preview).contains(vehicle_type));
	//	}
	//
	//	@Then("^Validate estimated total and amount Prepaid$")
	//	public void validate_estimated_total_and_amount_Prepaid() throws Throwable {
	//
	//		double baserate = Double.parseDouble(get_value(element.baserate_preview));
	//		double taxesrate = Double.parseDouble(get_value(element.taxes_preview));
	//		double estimatetotal = Double.parseDouble(get_value(element.estimated_total_preview));
	//		double actual_total = baserate + taxesrate;
	//		if(Math.round(estimatetotal) == Math.round(actual_total))
	//		{
	//			Assert.assertTrue(true);
	//		}
	//		else
	//		{
	//			Assert.assertFalse(true);
	//		}
	//		closeBrowser();
	//	}
	//
	//	@When("^Choose Pickup Date as a Week ahead of Current Date$")
	//	public void choose_Pickup_Date_as_a_Week_ahead_of_Current_Date()
	//	{
	//		enter(element.pickup_date_element,get_pickup_date());
	//	}
	//
	//	@When("^Choose Return Date as a Week of pickup Date$")
	//	public void choose_Return_Date_as_a_Week_of_pickup_Date() throws Throwable {
	//		// Write code here that turns the phrase above into concrete actions
	//		enter(element.return_date_element,get_return_date());
	//	}
	//
	//	@When("^Click on Select My Car$")
	//	public void click_on_Select_My_Car() throws InterruptedException 
	//	{
	//		click(element.select_my_car_element);
	//	}
	//
	//	@Then("^Capture and validate pickup and return locations from inputs \"([^\"]*)\"$")
	//	public void capture_and_validate_pickup_and_return_locations_from_inputs(String location) throws Throwable {
	//		TimeUnit.SECONDS.sleep(10);
	//		Assert.assertTrue(location.toLowerCase().contains(get_value(element.pickup_location_label).toLowerCase()));
	//		Assert.assertTrue(location.toLowerCase().contains(get_value(element.return_location_label).toLowerCase()));
	//	}
	//
	//	@Then("^Capture BaseRate and Fees Taxes and validate base rate from the previous page$")
	//	public void capture_BaseRate_and_Fees_Taxes_and_validate_base_rate_from_the_previous_page() throws Throwable {
	//
	//		Assert.assertTrue(get_value(element.baserate_preview).contains(low_price));
	//	}




}
