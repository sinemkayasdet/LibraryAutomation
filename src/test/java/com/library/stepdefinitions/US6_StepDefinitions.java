package com.library.stepdefinitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US6_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("I log in as a librarian")
    public void iLogInAsALibrarian() {

        Driver.getDriver().get(ConfigurationReader.getProperty("urlLogin"));
        loginPage.login();
    }

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String string) {
        dashBoardPage.clickBookModule();
    }

    List<String> expected;
    @And("I take all book categories in UI")
    public void iTakeAllBookCategoriesInUI() {
        expected = dashBoardPage.getAllDropDown();



    }
    List<String> actual;

    @And("I execute a query to get book categories")
    public void iExecuteAQueryToGetBookCategories() {
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        DB_Util.runQuery("\n" +
                "select name from book_categories");
       actual = DB_Util.getColumnDataAsList(1);
    }

    @Then("verify book categories must match the book_categories table from DB.")
    public void verifyBookCategoriesMustMatchTheBook_categoriesTableFromDB() {
        Assert.assertEquals(expected,actual);
    }
}
