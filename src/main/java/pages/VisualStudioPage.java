package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.PriceInterface;

public class VisualStudioPage implements PriceInterface {
	
	private WebDriver driver;

    public VisualStudioPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public void closeModalIfVisible() {
    	WebElement modal = driver.findElement(By.id("geo-selector-modal"));
        
        if (modal.isDisplayed()) {
        	WebElement closeButton = driver.findElement(By.cssSelector("button.close"));
            closeButton.click();
        }
    }

    @Override
    public String getPrice() {
    	WebElement pricingParagraph = driver.findElement(By.cssSelector("p[data-automation-test-id='buy-box-pricing']"));
        WebElement priceSpan = pricingParagraph.findElement(By.tagName("span"));

        return priceSpan.getText().trim();
    }
    
    public void clickBuyButton() {
    	WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement buyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buybox-cta-proper")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buyButton);        
        buyButton.click();
    };
}
