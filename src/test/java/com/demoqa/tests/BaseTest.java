package com.demoqa.tests;

import com.demoqa.utils.AllureAttachments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

/**
 * Единая точка инициализации/утилизации WebDriver для всех тестов.
 *
 * Браузер можно задать двумя способами (оба поддержаны):
 * 1) через testng.xml - <parameter name="browser" value="firefox"/>;
 * 2) через системное свойство Maven без testng.xml:
 *    mvn -Dbrowser=firefox -Dheadless=false clean test
 * Системное свойство, если оно задано, имеет приоритет над параметром из XML.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("false") String headless) {
        String resolvedBrowser = System.getProperty("browser", browser);
        boolean resolvedHeadless = Boolean.parseBoolean(System.getProperty("headless", headless));

        driver = createDriver(resolvedBrowser, resolvedHeadless);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "firefox" -> {
                FirefoxOptions fo = new FirefoxOptions();
                if (headless) fo.addArguments("-headless");
                return new FirefoxDriver(fo);
            }
            case "edge" -> {
                EdgeOptions eo = new EdgeOptions();
                if (headless) eo.addArguments("--headless=new");
                return new EdgeDriver(eo);
            }
            default -> {
                ChromeOptions co = new ChromeOptions();
                if (headless) co.addArguments("--headless=new");
                return new ChromeDriver(co);
            }
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null && !result.isSuccess()) {
            AllureAttachments.screenshot(driver, "Failure screenshot");
            AllureAttachments.pageSource(driver, "Page source on failure");
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
