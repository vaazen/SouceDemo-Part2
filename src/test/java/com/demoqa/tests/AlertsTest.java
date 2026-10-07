package com.demoqa.tests;

import com.demoqa.pages.AlertsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Alerts, Frame & Windows")
@Feature("Alerts")
public class AlertsTest extends BaseTest {

    @Test(description = "Confirm: dismiss выводит корректный результат")
    @Severity(SeverityLevel.MINOR)
    @Owner("Kosty")
    public void shouldDismissConfirm() {
        var page = new AlertsPage(driver, wait);
        page.open();
        page.openConfirmAndDismiss();

        String text = page.confirmText().toLowerCase();
        Assert.assertTrue(text.contains("cancelled") || text.contains("cancel"),
                "Ожидался текст о выборе Cancel. Фактический текст: '" + page.confirmText() + "'");
    }

    @Test(description = "Prompt: введённый текст отражается в результате")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kosty")
    public void shouldAcceptPromptWithText() {
        var page = new AlertsPage(driver, wait);
        page.open();
        page.openPromptAndSend("demo");

        Assert.assertTrue(page.promptText().toLowerCase().contains("demo"),
                "Ожидалось отображение введённого текста в результате. Фактический текст: '" + page.promptText() + "'");
    }
}
