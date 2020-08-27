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

    /**
     * В разделе курс обмена выбрать интернет-банк.
     * @return BankPage
     */
    public BankPage selectInternetBank() {
        $("div.main-page-exchange__dropdown-button").click();
        $$(By.xpath("//div[@class='main-page-exchange__dropdown-overlay']/ul[@role='menu']/li"))
                .filterBy(Condition.exactText("интернет-банке"))
                .first()
                .click();
        return this;
    }

    /**
     * В разделе курс обмена переключиться на Льготный курс
     * @return BankPage
     */
    public BankPage switchToPreferentialRate() {
        $("div#tab-preferentialRate")
                .waitUntil(Condition.visible, 10000)
                .scrollTo()
                .click();
        return this;
    }

    /**
     * В разделе курс обмена переключиться на Стандартный курс
     * @return BankPage
     */
    public BankPage switchToDefaultRate() {
        $("div#tab-defaultRate")
                .waitUntil(Condition.visible, 10000)
                .scrollTo()
                .click();
        return this;
    }

    /**
     * Получить курс покупки и курс продажи для USD
     * @return [Курс покупки, Курс продажи]
     */
    public List<Money> getUsdRow() {
        ElementsCollection eurCells = $(By.xpath("//div[@aria-hidden='false']//tbody"))
                .find(byText("USD"))
                .closest("tr")
                .findAll("td");
        List<Money> monies = new ArrayList<>();
        monies.add(new Money(eurCells.get(1).waitUntil(Condition.visible, 5000).getText()));
        monies.add(new Money(eurCells.get(3).waitUntil(Condition.visible, 5000).getText()));
        return monies;
    }

    /**
     * Получить курс покупки и курс продажи для EUR
     * @return [Курс покупки, Курс продажи]
     */
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
