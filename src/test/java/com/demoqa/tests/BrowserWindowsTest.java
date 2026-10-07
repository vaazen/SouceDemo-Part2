package com.demoqa.tests;

import com.demoqa.pages.BrowserWindowsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Alerts, Frame & Windows")
@Feature("Browser Windows")
public class BrowserWindowsTest extends BaseTest {

    @Test(description = "Открытие новой вкладки показывает ожидаемый заголовок, после закрытия возвращаемся в исходную")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kosty")
    public void shouldOpenNewTabAndReturnToOriginal() {
        var page = new BrowserWindowsPage(driver, wait);
        page.open();
        page.openNewTab();

        Assert.assertEquals(page.getNewTabHeadingText(), "This is a sample page",
                "Ожидали конкретный заголовок на новой вкладке.");
        Assert.assertEquals(page.getOpenWindowsCount(), 2, "Ожидали ровно 2 открытых окна/вкладки.");

        page.closeNewTabAndReturn();

        Assert.assertEquals(page.getOpenWindowsCount(), 1,
                "После закрытия новой вкладки должна остаться только исходная.");
    }
}
