package Lesson_14;

import Lesson_14.pages.MainPage;
import Lesson_14.pages.PaymentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByOnlineTopUpTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBlockTitleAndLogos() {
        WebElement section = wait.until(ExpectedConditions.visibilityOf(mainPage.getSection()));
        String sectionText = section.getText();
        Assert.assertTrue(sectionText.contains("Онлайн пополнение без комиссии"));
        List<WebElement> logos = mainPage.getPaymentIcons();
        Assert.assertFalse(logos.isEmpty());
        for (WebElement logo : logos) {
            Assert.assertTrue(logo.isDisplayed());
        }
    }

    @Test
    public void testMoreAboutServiceLink() {
        WebElement link = mainPage.getMoreAboutServiceLink();
        String href = link.getAttribute("href");
        Assert.assertNotNull(href);
        link.click();
        String originalWindow = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("mts.by"));
        Assert.assertTrue(driver.getPageSource().contains("МТС"));
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void testUnfilledFieldsInOptions() {
        String[] labels = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String label : labels) {
            WebElement optionLabel = mainPage.getServiceOptionByLabel(label);
            List<WebElement> inputs = optionLabel.findElements(By.xpath(".//input[@type='radio' or @type='checkbox']"));
            Assert.assertFalse(inputs.isEmpty());
            String caption = optionLabel.getText();
            Assert.assertTrue(caption.contains(label));
        }
    }

    @Test
    public void testFillAndProceedForUslugiSvyazi() {
        WebElement servicesOptionLabel = mainPage.getServiceOptionByLabel("Услуги связи");
        WebElement radio = servicesOptionLabel.findElement(By.xpath(".//input[@type='radio']"));
        if (!radio.isSelected()) {
            radio.click();
        }

        List<WebElement> unfilledFields = driver.findElements(By.xpath("//div[contains(@class,'payment-summary')]//input[@value='']"));
        Assert.assertFalse(unfilledFields.isEmpty());

        WebElement cardNumberField = driver.findElement(By.xpath("//input[contains(@placeholder,'номер карты')]"));
        WebElement cardExpiryField = driver.findElement(By.xpath("//input[contains(@placeholder,'срок действия')]"));
        WebElement cardCVCField = driver.findElement(By.xpath("//input[contains(@placeholder,'CVC')]"));
        cardNumberField.sendKeys("4111 1111 1111 1111");
        cardExpiryField.sendKeys("12/25");
        cardCVCField.sendKeys("123");

        WebElement phoneInput = mainPage.getPhoneInput();
        String testPhone = "297777777";
        phoneInput.clear();
        phoneInput.sendKeys(testPhone);

        WebElement continueBtn = mainPage.getContinueButton();
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
        continueBtn.click();

        PaymentPage paymentPage = new PaymentPage(driver);
        WebElement summary = wait.until(ExpectedConditions.visibilityOf(paymentPage.getSummaryWindow()));

        String amountText = paymentPage.getAmountText();
        Assert.assertTrue(amountText.matches("\\d+\\s?руб."));
        Assert.assertTrue(paymentPage.getTotalSum().contains("Итоговая сумма"));
        Assert.assertTrue(paymentPage.getPhone().contains(testPhone));

        List<WebElement> cardFields = paymentPage.getUnfilledCardFields();
        Assert.assertFalse(cardFields.isEmpty());
        for (WebElement field : cardFields) {
            Assert.assertEquals("", field.getAttribute("value"));
        }

        List<WebElement> icons = paymentPage.getPaymentIcons();
        Assert.assertFalse(icons.isEmpty());
        for (WebElement icon : icons) {
            Assert.assertTrue(icon.isDisplayed());
        }
    }
}