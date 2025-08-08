package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

import java.time.Duration;

public class CartTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @Test(priority = 2)
    public void testAddToCartAndVerify() throws InterruptedException {
        HomePage home = new HomePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav navbar-nav']//li//a[@href='/products']"))).click();


        home.searchForProduct("Men Tshirt");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");

        Thread.sleep(2000);


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='View Product']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[normalize-space()='Add to cart']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//u[normalize-space()='View Cart']"))).click();
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.getProductName().contains("Men Tshirt"));

        CartPage cart1 = new CartPage(driver);
        Assert.assertTrue(cart1.getProductPrice().contains("Rs. 400"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}