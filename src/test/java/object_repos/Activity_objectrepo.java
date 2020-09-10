package object_repos;

public class Activity_objectrepo 
{
	
	public String todo_add_input = ".//*[@ng-model='newTodo']";
	public String todo_filters = "//*[@class='filters']";
	public String todo_added_item = ".//label[text()='%s']";
	public String todo_items_left=".//*[name()='strong' and @class='ng-binding']";
	public String todo_item_checkbox = ".//*[@class='todo-list']//input[@type='checkbox']";
	public String todo_item_completed = ".//ul[@class='todo-list']//*[contains(@class,'completed')]";
	public String todo_cross_button = ".//button[@class='destroy']";
	public String todo_all_items_tab = ".//*[@class='filters']//a[text()='All']";
	public String todo_active_items_tab = ".//*[@class='filters']//a[text()='Active']";
	public String todo_completed_items_tab = ".//*[@class='filters']//a[text()='Completed']";
	
		
}