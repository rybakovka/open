package com.open.ui;

import com.codeborne.selenide.Condition;
import helpers.Money;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Тесты UI
 */
public class SiteTest {

    GooglePage googlePage;
    BankPage bankPage;

    @Test(description = "Проверка выдачи поиска Google")
    public void checkSearchResult() {
        googlePage = open("https://www.google.com/", GooglePage.class);
        int linkCount = googlePage.search("открытие")
                .filteredResults("www.open.ru")
                .filter(Condition.visible)
                .size();
        Assert.assertTrue(linkCount > 0, "Результаты поиска содержат Банк Открытие");
        googlePage.clickResult("www.open.ru");
    }

    @Test(description = "Проверка соответствия Льготного курса правилу.",
            dependsOnMethods = "checkSearchResult")
    public void checkPreferentialRate() {
        bankPage = page(BankPage.class);
        List<Money> moniesEur = bankPage
                .selectInternetBank()
                .switchToPreferentialRate()
                .getEurRow();
        List<Money> moniesUsd = bankPage
                .getUsdRow();
        System.out.println(moniesEur);
        System.out.println(moniesUsd);
        Assert.assertEquals(1, moniesEur.get(1).compareTo(moniesEur.get(0)),
                "Курс продажи больше курса покупки для EUR");
        Assert.assertEquals(1, moniesUsd.get(1).compareTo(moniesUsd.get(0)),
                "Курс продажи больше курса покупки для USD");
    }

    @Test(description = "Проверка соответствия Стандартного курса правилу.",
            dependsOnMethods = "checkSearchResult")
    public void checkDefaultRate() {
        bankPage = page(BankPage.class);
        List<Money> moniesEur = bankPage
                .switchToDefaultRate()
                .getEurRow();
        List<Money> moniesUsd = bankPage
                .getUsdRow();
        System.out.println(moniesEur);
        System.out.println(moniesUsd);
        Assert.assertEquals(1, moniesEur.get(1).compareTo(moniesEur.get(0)),
                "Курс продажи больше курса покупки для EUR");
        Assert.assertEquals(1, moniesUsd.get(1).compareTo(moniesUsd.get(0)),
                "Курс продажи больше курса покупки для USD");
    }
}
