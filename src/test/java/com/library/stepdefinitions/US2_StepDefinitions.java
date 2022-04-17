package com.library.stepdefinitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US2_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    @Given("I am in the homepage of the library app")
    public void iAmInTheHomepageOfTheLibraryApp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlLogin"));
        loginPage.login();

    }
    int UIborrowedBookCount;
    @When("I take borrowed books number")
    public void iTakeBorrowedBooksNumber() {
      UIborrowedBookCount = Integer.parseInt(dashBoardPage.borrowedBook.getText());

    }


    @Then("borrowed books number information must match with DB")
    public void borrowedBooksNumberInformationMustMatchWithDB() {
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        DB_Util.runQuery("SELECT * FROM book_borrow where  is_returned <> 1 ");
        int databaseBorrowedBook =   DB_Util.getRowCount();

        Assert.assertEquals(UIborrowedBookCount,databaseBorrowedBook);


    }


}
