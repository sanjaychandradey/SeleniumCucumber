package StepDefinitions;
import PageObjects.CoronaPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import Utilities.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Corona {
    TestContext testContext;
    CoronaPage coronaPage;

    public Corona(TestContext context) {
        testContext = context;
        coronaPage = testContext.getPageObjectManager().getCoronaPage();
    }
    @Given("Home page of corona site")
    public void home_page_of_corona_site() {
     coronaPage.launchWebsite();
    }
    @When("I am landing page check all the Country")
    public void i_am_landing_page_check_all_the_country() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("display the count based on {string}")
    public void display_the_count_based_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
