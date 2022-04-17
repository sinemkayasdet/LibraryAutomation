package com.library.stepdefinitions;

import com.library.pages.HomePage;
import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class US1_StepDefinitions {
    HomePage homePage = new HomePage();

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));

    }

    int expectedSize;

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() throws SQLException {
        homePage.printID();

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() throws SQLException {
       homePage.verifyUniquId();

    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() throws SQLException {
        homePage.printCloumnName();

    }

    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expected) {
        DB_Util.runQuery("SELECT * FROM users");
       List<String> actual =  DB_Util.getAllColumnNamesAsList();
     Assert.assertEquals(expected,actual);


    }


}
