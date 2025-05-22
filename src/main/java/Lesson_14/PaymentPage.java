package Lesson_14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class PaymentPage {
    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSummaryWindow() {
        return driver.findElement(By.xpath("//div[contains(@class, 'payment-summary') or contains(., 'Подтверждение')]"));
    }

    public WebElement getAmountButton() {
        return getSummaryWindow().findElement(By.xpath(".//button[contains(@class, 'amount')]"));
    }

    public String getAmountText() {
        return getAmountButton().getText();
    }

    public WebElement getTotalSumText() {
        return getSummaryWindow().findElement(By.xpath(".//div[contains(., 'Итоговая сумма')]"));
    }

    public String getTotalSum() {
        return getTotalSumText().getText();
    }

    public WebElement getPhoneDisplay() {
        return getSummaryWindow().findElement(By.xpath(".//div[contains(., 'Телефон')]"));
    }

    public String getPhone() {
        return getPhoneDisplay().getText();
    }

    public List<WebElement> getUnfilledCardFields() {
        return getSummaryWindow().findElements(By.xpath(".//input[contains(@placeholder, 'номер карты')]"));
    }

    public List<WebElement> getPaymentIcons() {
        return getSummaryWindow().findElements(By.xpath(".//div[contains(@class, 'payment-icons')]//img"));
    }
}