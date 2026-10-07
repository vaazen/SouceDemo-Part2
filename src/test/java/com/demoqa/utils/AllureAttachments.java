package com.demoqa.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Вложения для Allure-отчёта: скриншот и HTML страницы в момент падения.
 * Вызываются дозированно — только при падении теста (см. BaseTest.tearDown),
 * чтобы не перегружать отчёт лишними вложениями на зелёных прогонах.
 */
public final class AllureAttachments {

    private AllureAttachments() {
    }

    public static void screenshot(WebDriver driver, String name) {
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(name, new ByteArrayInputStream(bytes));
    }

    public static void pageSource(WebDriver driver, String name) {
        Allure.addAttachment(name, "text/html", driver.getPageSource(), ".html");
    }
}
