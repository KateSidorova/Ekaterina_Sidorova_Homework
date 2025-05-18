package Lesson_13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MtsByOnlineTopUpTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\wwwya\\Downloads\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockTitle() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));
        String blockText = block.getText();
        assertTrue("Название блока не содержит ожидаемый текст",
            blockText.contains("Онлайн пополнение без комиссии"));
    }

    @Test
    public void testPaymentLogosPresence() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));
        List<WebElement> logos = block.findElements(By.tagName("img"));
        assertFalse("Логотипы платёжных систем не найдены", logos.isEmpty());
        for (WebElement logo : logos) {
            assertTrue("Логотип не отображается", logo.isDisplayed());
        }
    }

    @Test
    public void testMoreAboutServiceLink() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));
        WebElement link = block.findElement(By.linkText("Подробнее о сервисе"));
        String href = link.getAttribute("href");
        assertNotNull("Ссылка 'Подробнее о сервисе' не найдена", href);
        link.click();
        String originalWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        assertTrue("Ссылка не открылась корректно", currentUrl.contains("mts.by"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        assertTrue("Страница содержит ожидаемый текст", driver.getPageSource().contains("МТС"));
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void testFillFieldsAndContinue() {
        WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
        ));
        try {
            WebElement serviceOption = block.findElement(By.xpath(".//label[contains(., 'Услуги связи')]//input[@type='radio' or @type='checkbox']"));
            serviceOption.click();
        } catch (Exception e) {
            fail("Не удалось найти опцию 'Услуги связи'");
        }
        WebElement phoneInput = block.findElement(By.xpath(".//input[contains(@placeholder, 'номер')]"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");
        WebElement continueButton = block.findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        assertTrue("Кнопка 'Продолжить' неактивна", continueButton.isEnabled());
        continueButton.click();
    }
}