package Lesson_13;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class Lesson_13 {
    public static class MtsByOnlineTopUpTest {
        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void setUp() {
            WebDriverManager.chromedriver().setup();
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
            List<WebElement> logos = block.findElements(By.xpath(".//div[contains(@class, 'payment-icons')]//img"));
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

            driver.switchTo().window(originalWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }

        @Test
        public void testFillFieldsAndContinueForServices() {
            WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]")
            ));
            String[] optionsLabels = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
            for (String label : optionsLabels) {
                WebElement optionLabel = block.findElement(By.xpath(".//label[contains(., '" + label + "')]"));
                WebElement radioOrCheckbox = optionLabel.findElement(By.xpath(".//input[@type='radio' or @type='checkbox']"));
                String caption = optionLabel.getText();
                assertTrue("Надпись не содержит: " + label, caption.contains(label));
            }
            WebElement servicesOptionLabel = block.findElement(By.xpath(".//label[contains(., 'Услуги связи')]"));
            WebElement servicesRadio = servicesOptionLabel.findElement(By.xpath(".//input[@type='radio']"));
            servicesRadio.click();

            WebElement phoneInput = block.findElement(By.xpath(".//input[contains(@placeholder, 'номер')]"));
            phoneInput.clear();
            String testPhone = "297777777";
            phoneInput.sendKeys(testPhone);

            WebElement continueButton = block.findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            assertTrue("Кнопка 'Продолжить' неактивна", continueButton.isEnabled());
            continueButton.click();

            WebElement summaryWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'payment-summary') or contains(., 'Подтверждение')]")
            ));

            WebElement amountButton = summaryWindow.findElement(By.xpath(".//button[contains(@class, 'amount')]"));
            String amountText = amountButton.getText();
            assertTrue("Сумма не отображается корректно", amountText.matches("\\d+\\s?руб."));

            WebElement totalSum = summaryWindow.findElement(By.xpath(".//div[contains(., 'Итоговая сумма')]"));
            String totalSumText = totalSum.getText();
            assertTrue("Общая сумма не отображается", totalSumText.matches("Итоговая сумма: \\d+\\s?руб."));

            WebElement displayedPhone = summaryWindow.findElement(By.xpath(".//div[contains(., 'Телефон')]"));
            String phoneText = displayedPhone.getText();
            assertTrue("Телефон не отображается или некорректен", phoneText.contains(testPhone));

            List<WebElement> cardFields = summaryWindow.findElements(By.xpath(".//input[contains(@placeholder, 'номер карты')]"));
            assertFalse("Поля для реквизитов карты не найдены", cardFields.isEmpty());

            List<WebElement> paymentIcons = summaryWindow.findElements(By.xpath(".//div[contains(@class, 'payment-icons')]//img"));
            assertFalse("Иконки платёжных систем не найдены", paymentIcons.isEmpty());
            for (WebElement icon : paymentIcons) {
                assertTrue("Иконка платёжной системы не отображается", icon.isDisplayed());
            }
        }
    }
}