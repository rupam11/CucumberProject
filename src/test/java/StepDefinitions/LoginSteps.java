package StepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginSteps {

    private WebDriver driver;

    @Given("user launches the application")
    public void user_launches_the_application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login"); // TODO: Replace with real URL
    }

    @When("user enters username {string}")
    public void user_enters_username(String username) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    @Then("user should see the successful login message")
    public void user_should_see_the_successMessage() {
        boolean isDisplayed = driver.findElement(By.xpath("//*[contains(text(),\"You logged into a secure area!\")]")).isDisplayed();
        if (!isDisplayed) {
            throw new AssertionError("Homepage not displayed!");
        }
        driver.quit();
    }

    @Then("message should be displayed")
    public void message_should_be_displayed() {
        WebElement successMsg = driver.findElement(
                By.xpath("//*[contains(text(),'You logged into a secure area!')]"));
        Assert.assertTrue(successMsg.isDisplayed());
    }
    @Then("user should see the failure login message")
    public void user_should_see_the_failureMessage() {
        boolean isDisplayed = driver.findElement(By.xpath("//*[contains(text(),\"Your password is invalid\")]")).isDisplayed();
        if (!isDisplayed) {
            throw new AssertionError("Homepage not displayed!");
        }
        driver.quit();
    }
    
    @Then("login {string} message should be displayed")
    public void login_message_should_be_displayed(String result) {

        if (result.equalsIgnoreCase("success")) {
            // success message
            WebElement success = driver.findElement(
                By.xpath("//*[contains(text(),'You logged into a secure area!')]")
            );
            if (!success.isDisplayed()) {
                throw new AssertionError("Success message not displayed!");
            }
        } else if (result.equalsIgnoreCase("failure")) {
            // error message for invalid login
            WebElement error = driver.findElement(
                By.xpath("//*[contains(text(),'Your username is invalid') or contains(text(),'Your password is invalid')]")
            );
            if (!error.isDisplayed()) {
                throw new AssertionError("Error message not displayed for invalid login!");
            }
        }

        driver.quit();
    }
}
