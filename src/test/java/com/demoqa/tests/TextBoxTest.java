package com.demoqa.tests;

import com.demoqa.pages.TextBoxPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Elements")
@Feature("Text Box")
public class TextBoxTest extends BaseTest {

    @Test(description = "Позитив: валидные данные приводят к отображению блока результата")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Kosty")
    public void shouldSubmitValidData() {
        var page = new TextBoxPage(driver, wait);
        page.open();
        page.fill("Ivan Petrov", "ivan.petrov@example.com", "Current", "Permanent");

        Assert.assertTrue(page.isOutputVisible(), "Ожидалось появление блока результатов.");
    }

    @Test(description = "Негатив: невалидный email подсвечивается как ошибка")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kosty")
    public void shouldHighlightInvalidEmail() {
        var page = new TextBoxPage(driver, wait);
        page.open();
        page.fill("Ivan Petrov", "not-an-email", "Current", "Permanent");

        String emailClass = page.emailFieldClass();
        Assert.assertTrue(emailClass.contains("error") || emailClass.contains("invalid"),
                "Ожидалась индикация ошибки для поля email. Фактический class: '" + emailClass + "'");
    }
}
