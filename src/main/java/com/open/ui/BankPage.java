package com.open.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import helpers.Money;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Страница "Банк Открытие".
 */
public class BankPage {

    @FindBy(how = How.CSS, using = "div#tab-defaultRate")
    private SelenideElement defaultRateButton;

    @FindBy(how = How.CSS, using = "div#tab-preferentialRate")
    private SelenideElement preferentialRateButton;

    public BankPage selectInternetBank() {
        $("div.main-page-exchange__dropdown-button").click();
        $$(By.xpath("//div[@class='main-page-exchange__dropdown-overlay']/ul[@role='menu']/li"))
                .filterBy(Condition.exactText("интернет-банке"))
                .first()
                .click();
        return this;
    }

    public BankPage switchToPreferentialRate() {
        preferentialRateButton.waitUntil(Condition.visible, 10000).scrollTo();
        preferentialRateButton.click();
        return this;
    }

    public BankPage switchToDefaultRate() {
        defaultRateButton.waitUntil(Condition.visible, 10000).scrollTo();
        defaultRateButton.click();
        return this;
    }

    public List<Money> getUsdRow() {
        ElementsCollection eurCells = $(By.xpath("//div[@aria-hidden='false']//tbody"))
                .find(byText("USD"))
                .closest("tr")
                .findAll("td");
        List<Money> monies = new ArrayList<>();
        monies.add(new Money(eurCells.get(1).getText()));
        monies.add(new Money(eurCells.get(3).getText()));
        return monies;
    }

    public List<Money> getEurRow() {
        ElementsCollection eurCells = $(By.xpath("//div[@aria-hidden='false']//tbody"))
                .find(byText("EUR"))
                .closest("tr")
                .findAll("td");
        List<Money> monies = new ArrayList<>();
        monies.add(new Money(eurCells.get(1).waitUntil(Condition.visible, 5000).getText()));
        monies.add(new Money(eurCells.get(3).waitUntil(Condition.visible, 5000).getText()));
        return monies;
    }
}
