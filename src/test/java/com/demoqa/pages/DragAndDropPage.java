package com.demoqa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * /droppable — Interactions: перетаскивание элемента в зону сброса (jQuery UI).
 */
public class DragAndDropPage extends BasePage {

    private final By draggable = By.id("draggable");
    private final By droppable = By.id("droppable");

    public DragAndDropPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Step("Открыть страницу Droppable")
    public void open() {
        driver.get("https://demoqa.com/droppable");
    }

    @Step("Перетащить элемент в зону сброса")
    public void dragIntoDropZone() {
        new Actions(driver)
                .dragAndDrop(el(draggable), el(droppable))
                .perform();
    }

    public String getDropZoneText() {
        return el(droppable).getText();
    }

    public String getDropZoneClass() {
        return driver.findElement(droppable).getAttribute("class");
    }
}
