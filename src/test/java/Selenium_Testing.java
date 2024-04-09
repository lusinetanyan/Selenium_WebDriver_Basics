import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

class Selenium_Testing {
    public static WebDriver driver;
    public static String baseUrl="https://www.goodreads.com/";

    @BeforeAll
    public static void initWebDriver(){
        System.setProperty("chromedriver.chrome.driver", "chromedriver-mac-arm64");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void WebDriverTest() {
        driver.get(baseUrl);
        // 1) input field
        WebElement searchField = driver.findElement(By.id("sitesearch_field"));
        searchField.sendKeys("Harry Potter");
        searchField.sendKeys(Keys.ENTER);

        // 2) button
        WebElement getCopyButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div[3]/div/div/button"));
        getCopyButton.click();

        // 3) link
        WebElement harryPotterBookLink = driver.findElement(By.linkText("Harry Potter and the Sorcerer’s Stone (Harry Potter, #1)"));
        Assertions.assertTrue(harryPotterBookLink.getText().contains("Harry Potter"));
        harryPotterBookLink.click();

        // 4) text
        WebElement rating = driver.findElement(By.className("RatingStatistics__rating"));
        double rate = Double.parseDouble(rating.getText());
        System.out.println("Rating: " + rate);
        Assertions.assertEquals(4.47, rate);

        // 5) image
        WebElement authorAvatar = driver.findElement(By.className("Avatar__image"));
        System.out.println(authorAvatar.getAttribute("alt"));

        // showing all genres
        WebElement moreGenresButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div[2]/div[2]/div[2]/div[5]/ul/div/button/span"));
        moreGenresButton.click();
        WebElement showAllGenresButton = driver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div[1]/div[2]/div[2]/div[2]/div[5]/ul/a/span"));
        showAllGenresButton.click();

        List<WebElement> genres = driver.findElements(By.className("actionLinkLite"));
        System.out.println("Genres");
        System.out.println("----------------------");
        for(WebElement genre: genres) {
            System.out.println(genre.getText());
        }
    }

}
