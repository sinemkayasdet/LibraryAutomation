package com.library.pages;

import com.library.utulity.ConfigurationReader;
import com.library.utulity.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@id='inputEmail']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='inputPassword']")
    public WebElement password;

    @FindBy(xpath = "//button[.='Sign in']")
    public WebElement loginButton;


    public void login(){
        userName.sendKeys(ConfigurationReader.getProperty("libraryEmail"));
        password.sendKeys(ConfigurationReader.getProperty("libraryPassword"));
        loginButton.click();
    }
}
