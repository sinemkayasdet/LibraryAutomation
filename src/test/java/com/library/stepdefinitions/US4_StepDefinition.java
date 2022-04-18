package com.library.stepdefinitions;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utulity.BrowserUtils;
import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.*;

public class US4_StepDefinition {


    DashBoardPage dashBoardPage = new DashBoardPage();
    LoginPage loginPage = new LoginPage();

    String expectedPeopleReadsMost;
    String actualPeopleReadsMost;

    @When("I execute a query to find the most popular user")
    public void iExecuteAQueryToFindTheMostPopularUser() {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlLogin"));
        loginPage.login();
        DB_Util.runQuery("select full_name, count(*)from users u\n" +
                "    inner join book_borrow bb on u.id= bb.user_id\n" +
                "group by full_name order BY 2 desc");
         expectedPeopleReadsMost = DB_Util.getFirstRowFirstColumn();
    }


    @Then("verify {string} is the user who reads the most")
    public void verifyIsTheUserWhoReadsTheMost(String arg0) {
        dashBoardPage.clickBookModule();
        BrowserUtils.waitFor(3);
        List<String> list = new ArrayList<>();
        for (WebElement each : dashBoardPage.borrowedBooksListByUser) {
            list.add(each.getText());
        }
        System.out.println("list = " + list);


        Map<String, Integer> mapList = new LinkedHashMap<>();
        for (String each : list) {
            mapList.put(each, Collections.frequency(list, each));
        }
        System.out.println(mapList);


        int highest= Integer.MIN_VALUE;
       actualPeopleReadsMost= "";
        for (Map.Entry<String, Integer> each : mapList.entrySet()) {
            if(each.getValue() >highest){
                highest= each.getValue();
                actualPeopleReadsMost= each.getKey();

            }
        }
        Assert.assertEquals(expectedPeopleReadsMost,actualPeopleReadsMost);
        System.out.println("expectedPeopleReadsMost = " + expectedPeopleReadsMost);
        System.out.println("actualPeopleReadsMost = " + actualPeopleReadsMost);
    }
}