package com.library.pages;

import com.library.utulity.BrowserUtils;
import com.library.utulity.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class DashBoardPage {

    public DashBoardPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//h2[.='24']")
    public WebElement borrowedBook;

    ////a[@class='nav-link']/i[@class='fa fa-book']
    @FindBy(xpath = "//a[@class='nav-link']/i[@class='fa fa-book']")
    public WebElement booksModule;

    @FindBy(xpath = "//select[@name='tbl_books_length']")
    public WebElement showCheckBoxButton;


    @FindBy(xpath = "//th[@data-name='U.full_name']")
    public WebElement borrowedBy;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchBox;

    @FindBy(xpath = "//table[@id='tbl_books']/tbody/tr/td[7]/text()/../../td[7]")
    public List<WebElement> borrowedBooksListByUser;


    public void clickBookModule() {
        Actions actions = new Actions(Driver.getDriver());
        BrowserUtils.highlight(booksModule);
        actions.click(booksModule).perform();
        Select select = new Select(showCheckBoxButton);
        select.selectByVisibleText("500");

        borrowedBy.click();
        borrowedBy.click();


    }
}
