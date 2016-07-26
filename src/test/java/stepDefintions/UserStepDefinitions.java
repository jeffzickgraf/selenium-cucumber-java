package stepDefintions;
import cucumber.api.java.en.Given;
import env.BaseTest;

public class UserStepDefinitions implements BaseTest{

	@Given("^I Wait for (\\d+) sec$")
	public void i_Wait_for_sec(int time) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(time*1000);
	}
}
