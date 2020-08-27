package com.open.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

/**
 * Страница Google.
 */
public class GooglePage {

    public GooglePage search(String query) {
        $(By.name("q")).setValue(query).pressEnter();
        return this;
    }

    public ElementsCollection filteredResults(String site) {
        return $$(By.xpath("//cite"))
                .filterBy(Condition.exactText(site));
    }

    public void clickResult(String url) {
        $$(By.xpath("//cite"))
                .filterBy(Condition.exactText(url))
                .first()
                .click();
    }
}
