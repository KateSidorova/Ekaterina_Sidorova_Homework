package Lesson_14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSection() {
        return driver.findElement(By.xpath("//section[contains(., 'Онлайн пополнение без комиссии')]"));
    }

    public WebElement getMoreAboutServiceLink() {
        return getSection().findElement(By.linkText("Подробнее о сервисе"));
    }

    public List<WebElement> getPaymentIcons() {
        WebElement section = getSection();
        return section.findElements(By.xpath(".//div[contains(@class, 'payment-icons')]//img"));
    }

    public List<WebElement> getServiceOptions() {
        WebElement section = getSection();
        return section.findElements(By.xpath(".//label[contains(., 'Услуги связи') or contains(., 'Домашний интернет') or contains(., 'Рассрочка') or contains(., 'Задолженность')]"));
    }

    public WebElement getServiceOptionByLabel(String labelText) {
        return getSection().findElement(By.xpath(".//label[contains(., '" + labelText + "')]"));
    }

    public WebElement getPhoneInput() {
        return getSection().findElement(By.xpath(".//input[contains(@placeholder, 'номер')]"));
    }

    public WebElement getContinueButton() {
        return getSection().findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));
    }
}