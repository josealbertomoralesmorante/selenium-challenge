package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.MenuInterface;

public class WindowsPage implements MenuInterface {
	
	private WebDriver driver;

    @FindBy(id = "c-shellmenu_57")
    WebElement about;
    
    @FindBy(id = "search")
    WebElement search;
    
    @FindBy(id = "cli_shellHeaderSearchInput")
    private WebElement searchInput;

    @FindBy(css = "a[href*='visual-studio-professional-2022']")
    private WebElement visualStudioLink;
    
    public WindowsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @Override
    public void clickMenuItem() {
        about.click();
    }
    
    public List<String> getTextOfLinksInUl() {
        WebElement ulElement = driver.findElement(By.cssSelector("ul[data-class-idn][aria-hidden='false']"));
        List<WebElement> linkElements = ulElement.findElements(By.tagName("a"));
        return linkElements.stream().map(WebElement::getText).toList();
    }
    
    public void clickIcon() {
    	search.click();
    }
    
    public void TypeText(String searchText) {
        searchInput.sendKeys(searchText);
    }
    
    public void clickSearchLink() {
    	WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(visualStudioLink));
        visualStudioLink.click();
    }
}
