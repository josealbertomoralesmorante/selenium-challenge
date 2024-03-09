package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.PriceInterface;

public class CartPage implements PriceInterface{
	
	private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @Override
    public String getPrice() {
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[itemprop='price']")));
	    return priceElement.getText();
    }
    
    
}
