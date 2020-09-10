Feature: TODOs app
	Validation of the TODOS functions.

   @Smoke @Regression
  Scenario Outline: Validate adding an activity in ToDos search.
    Given Navigate to TODO system
    Then Validate TODO search box and item list is empty
    When Add new activity "<Item_name>"
    Then Validate new activity is added to the list "<Item_name>"
    When Mark Item completed
    Then Verify Item is strike out from the list    
    And Verify the items left in footer
    Then Close TODO System
    		
		Examples:
		|Item_name |
		|Activity_1|
		
	@Smoke @Regression
  Scenario Outline: Validate Marking an activity as completed.
    Given Navigate to TODO system
    When Add new activity "<Item_name>"
    Then Validate new activity is added to the list "<Item_name>"
    When Mark Item completed
    Then Verify Item is strike out from the list    
    And Verify the items left in footer
    Then Close TODO System

		Examples:
		|Item_name |
		|Activity_1|
		
	@Smoke @Regression
  Scenario Outline: Validate Deleting an activity in Items list.
    Given Navigate to TODO system
    When Add new activity "<Item_name>"
    Then Validate new activity is added to the list "<Item_name>"
    When Delete an item from the todo list "<Item_name>"
    Then Verify Item is removed from the list "<Item_name>"    
    And Verify the items left in footer
		Then Close TODO System
		
		Examples:
		|Item_name |
		|Activity_1|
		
	@Smoke @Regression
  Scenario Outline: Validate Filtering option in Items list.
    Given Navigate to TODO system
    When Add new activity "<Item_name>"
    Then Validate new activity is added to the list "<Item_name>"
    And Verify Active Tab "<Item_name>"
    When Mark Item completed
    Then Verify Completed Tab "<Item_name>"
    Then Close TODO System
    
    Examples:
		|Item_name |
		|Activity_1|