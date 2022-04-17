package com.library.stepdefinitions;

import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US3_StepDefinitions {
    String actualPopularBookGenre;

    @When("I execute a query to find the most popular book genre")
    public void iExecuteAQueryToFindTheMostPopularBookGenre() {
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        DB_Util.runQuery("select bc.name,count(*) from book_borrow bb\n" +
                "    inner  join books b on bb.book_id = b.id\n" +
                "    inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name");
        actualPopularBookGenre = DB_Util.getFirstRowFirstColumn();

    }

    @Then("verify that {string} is the most popular book genre.")
    public void verifyThatIsTheMostPopularBookGenre(String expectedpopularBookGenre) {
        Assert.assertEquals(expectedpopularBookGenre,actualPopularBookGenre);
    }


}
