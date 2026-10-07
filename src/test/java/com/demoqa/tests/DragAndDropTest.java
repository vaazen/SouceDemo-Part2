package com.demoqa.tests;

import com.demoqa.pages.DragAndDropPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Interactions")
@Feature("Droppable")
public class DragAndDropTest extends BaseTest {

    @Test(description = "Перетаскивание элемента в зону сброса меняет текст и подсветку зоны")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Kosty")
    public void shouldHighlightDropZoneAfterDrop() {
        var page = new DragAndDropPage(driver, wait);
        page.open();
        page.dragIntoDropZone();

        Assert.assertEquals(page.getDropZoneText(), "Dropped!",
                "Ожидали текст 'Dropped!' в зоне сброса после перетаскивания.");
        Assert.assertTrue(page.getDropZoneClass().contains("ui-state-highlight"),
                "Ожидали класс подсветки 'ui-state-highlight' на зоне сброса. Фактический class: '"
                        + page.getDropZoneClass() + "'");
    }
}
