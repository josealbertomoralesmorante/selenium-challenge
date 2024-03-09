package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import interfaces.MenuInterface;

public class MicrosoftPage implements MenuInterface {

	private WebDriver driver;

    @FindBy(linkText = "Microsoft 365")
    WebElement microsoft365;

    @FindBy(linkText = "Teams")
    WebElement teams;

    @FindBy(linkText = "Copilot")
    WebElement copilot;

    @FindBy(linkText = "Windows")
    WebElement windows;

    @FindBy(linkText = "Surface")
    WebElement surface;

    @FindBy(linkText = "Xbox")
    WebElement xbox;

    @FindBy(linkText = "Deals")
    WebElement deals;

    @FindBy(linkText = "Small Business")
    WebElement smallBusiness;

    @FindBy(linkText = "Support")
    WebElement support;

    public MicrosoftPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @Override
    public void clickMenuItem() {
        windows.click();
    }

    public boolean areMenuItemsPresent() {
        return microsoft365.isDisplayed() && teams.isDisplayed() &&
               copilot.isDisplayed() && windows.isDisplayed() &&
               surface.isDisplayed() && xbox.isDisplayed() &&
               deals.isDisplayed() && smallBusiness.isDisplayed() &&
               support.isDisplayed();
    }
}