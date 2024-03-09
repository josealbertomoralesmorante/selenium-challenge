package tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.MicrosoftPage;
import pages.VisualStudioPage;
import pages.WindowsPage;

public class MicrosoftTest {
    private WebDriver driver;
    private MicrosoftPage microsoftPage;

    @BeforeMethod
    public void setUp() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/drivers/chromedriver.exe");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.microsoft.com/en-us/");
        
        /* 1: Go to https://www.microsoft.com/en-us/ */
        microsoftPage = new MicrosoftPage(driver);
    }

    @Test(priority = 1)
    public void testMenuItemsPresence() {
        /* 2: Validate all menu items are present (Microsoft 365 – Teams – Copilot -
			  Windows - Surface - Xbox - Deals – Small Business- Support) */
        Assert.assertTrue(microsoftPage.areMenuItemsPresent(), "Not all menu items are present");
    }
    

    @Test(priority = 2)
    public void testWindowsSubMenu() {
    	
    	/* 3: Go to Windows */
    	microsoftPage.clickMenuItem();
    	
    	/* 4: Once in Windows page, click on About Windows Menu */
        WindowsPage windowsPage = new WindowsPage(driver);
        windowsPage.clickMenuItem();
        List<String> linkTexts = windowsPage.getTextOfLinksInUl();
        
    	/* 5: Print all Elements in the dropdown */
	    for (String linkText : linkTexts) {
	    	System.out.println(linkText);
	    }
        windowsPage.clickMenuItem();

    	/* 6: Go to Search next to the All Microsoft */
        windowsPage.clickIcon();
        
    	/* 7: Search for Visual Studio */
        windowsPage.TypeText("Visual Studio");

    	/* 8: Click on Visual Studio Professional 2022 */
        windowsPage.clickSearchLink();

    	/* 9: If modals appear hit in another part of the page */
        VisualStudioPage visualStudioPage = new VisualStudioPage(driver);
        visualStudioPage.closeModalIfVisible();
        
    	/* 10: Store the price */
        String visualprice = visualStudioPage.getPrice();
  
    	/* 11: Click in Add to Cart */
    	visualStudioPage.clickBuyButton();
    	
    	/* 12: Click into Cart button nex */
    	/* This is done automatically. */
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.urlContains("https://www.microsoft.com/en-us/store/cart"));
    	
    	/* 13: Verify both price amounts are the same */
        CartPage cartPage = new CartPage(driver);
        String totalPrice = cartPage.getPrice();
        Assert.assertEquals(visualprice, totalPrice, "Las cadenas no son iguales");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}