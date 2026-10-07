package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * /windows — открытие новой вкладки и возврат в исходный контекст.
 * Важно: после работы с новой вкладкой ОБЯЗАТЕЛЬНО возвращаемся в исходную
 * (как советует методичка про "возврат в исходный контекст"), иначе
 * следующие шаги/тесты будут выполняться в неожиданной вкладке.
 */
public class BrowserWindowsPage extends BasePage {

    private final By tabButton = By.id("tabButton");
    private final By sampleHeading = By.id("sampleHeading");

    private String originalWindowHandle;

    public BrowserWindowsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Browser Windows")
    public void open() {
        driver.get("https://demoqa.com/windows");
    }

    @Step("Открыть новую вкладку по кнопке")
    public void openNewTab() {
        originalWindowHandle = driver.getWindowHandle();
        click(tabButton);

        wait.until(d -> d.getWindowHandles().size() > 1);
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(originalWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(sampleHeading));
    }

    public String getNewTabHeadingText() {
        return el(sampleHeading).getText();
    }

    @Step("Закрыть новую вкладку и вернуться в исходную")
    public void closeNewTabAndReturn() {
        driver.close();
        driver.switchTo().window(originalWindowHandle);
    }

    public int getOpenWindowsCount() {
        return driver.getWindowHandles().size();
    }
}
