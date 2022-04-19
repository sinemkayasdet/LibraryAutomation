package com.library.stepdefinitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utulity.BrowserUtils;
import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class US5_StepDefinitions {
    DashBoardPage dashBoardPage = new DashBoardPage();
    LoginPage loginPage = new LoginPage();


    @And("I navigate to {string} page")
    public void iNavigateToPage(String arg0) {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlLogin"));
        loginPage.login();
        dashBoardPage.clickBookModule();
    }

    @When("I open book {string}")
    public void iOpenBook(String input) {
        BrowserUtils.highlight(dashBoardPage.searchBox);
        dashBoardPage.searchBox.sendKeys(input + Keys.ENTER);


    }

    @Then("book information must match the Database")
    public void bookInformationMustMatchTheDatabase(Map<String, String> mapList) {
        DB_Util.runQuery("select b.isbn,b.name,b.author,bc.name,b.year from books b\n" +
                "inner join book_categories bc on b.book_category_id = bc.id\n" +
                "where b.name = 'Chordeiles minor'");
        List<String> actualData = DB_Util.getRowDataAsList(1);


        dashBoardPage.editBook.click();
        BrowserUtils.waitFor(5);
        List<String> expectedData = new ArrayList<>();
        for (WebElement each : dashBoardPage.bookTable) {
            expectedData.add(each.getText());
        }


        System.out.println("actualData = " + actualData);
        System.out.println("expectedData = " + expectedData);


        Iterator<String> it = expectedData.iterator();
        while (it.hasNext()) {
            if (actualData.contains(it.next())) {
                continue;
            }
            it.remove();
        }


        Assert.assertEquals(actualData, expectedData);
    }


}
