package com.library.stepdefinitions;

import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import com.library.utulity.Driver;
import io.cucumber.java.Scenario;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {
    @BeforeClass
    public void setup(){
        // create connection ONLY ONCE! for all tests in this class
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    @AfterClass

        public void tearDown(Scenario scenario) {

            if (scenario.isFailed()) {

                byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }


            DB_Util.destroy();
            Driver.closeDriver();

        }
}
