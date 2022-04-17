package com.library.stepdefinitions;

import com.library.utulity.ConfigurationReader;
import com.library.utulity.DB_Util;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Hook {
    @BeforeClass
    public void setup(){
        // create connection ONLY ONCE! for all tests in this class
        DB_Util.createConnection(ConfigurationReader.getProperty("url"),
                ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
    }

    @AfterClass
    public void teardown(){
        // tear down the connection only once after all the tests in this class
        DB_Util.destroy();
    }
}
