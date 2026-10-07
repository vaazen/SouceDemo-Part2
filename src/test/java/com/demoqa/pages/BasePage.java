package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Общие обёртки над явными ожиданиями/действиями, как того требует архитектура
 * из задания: BasePage - единые ожидания, Page Objects - только локаторы и
 * методы высокого уровня, Tests - только сценарии (без логики внутри @Test).
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected WebElement el(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Обычный клик по условию "кликабелен". На demoqa.com часто всплывает
     * рекламный баннер, перекрывающий элементы (ElementClickInterceptedException) —
     * в этом случае откатываемся на JS-клик, как и советует методичка
     * ("утилиты: прокрутка, JS-клик при необходимости").
     */
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void type(By locator, String text) {
        el(locator).clear();
        el(locator).sendKeys(text);
    }
}
