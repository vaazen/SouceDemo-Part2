package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {

    private final By alertBtn = By.id("alertButton");
    private final By confirmBtn = By.id("confirmButton");
    private final By promptBtn = By.id("promtButton"); // опечатка в самом id на сайте demoqa — это не моя ошибка
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Alerts")
    public void open() {
        driver.get("https://demoqa.com/alerts");
    }

    @Step("Открыть и принять простой alert")
    public void openAlertAndAccept() {
        click(alertBtn);
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    @Step("Открыть confirm и выполнить dismiss")
    public void openConfirmAndDismiss() {
        click(confirmBtn);
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    @Step("Открыть prompt, ввести текст и принять")
    public void openPromptAndSend(String text) {
        click(promptBtn);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    public String confirmText() {
        return el(confirmResult).getText();
    }

    public String promptText() {
        return el(promptResult).getText();
    }
}
