package com.open.ui;

import com.codeborne.selenide.Condition;
import helpers.Money;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

/**
 * Тесты UI
 * @author Константин Рыбаков
 */
public class SiteTest {

    private GooglePage googlePage;
    private BankPage bankPage;

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

    @Test(description = "Проверка соответствия Льготного курса EUR правилу.",
            dependsOnMethods = "checkSearchResult")
    public void checkPreferentialEurRate() {
        bankPage = page(BankPage.class);
        List<Money> moniesEur = bankPage
                .selectInternetBank()
                .switchToPreferentialRate()
                .getEurRow();
        Assert.assertEquals(1, moniesEur.get(1).compareTo(moniesEur.get(0)),
                "Курс продажи Льготного курса больше для EUR");

    }

    @Test(description = "Проверка соответствия Льготного курса USD правилу.",
            dependsOnMethods = "checkPreferentialEurRate")
    public void checkPreferentialUsdRate() {
        List<Money> moniesUsd = bankPage.getUsdRow();
        Assert.assertEquals(1, moniesUsd.get(1).compareTo(moniesUsd.get(0)),
                "Курс продажи Льготного курса больше для USD");
    }


    @Test(description = "Проверка соответствия Стандартного курса EUR правилу.",
            dependsOnMethods = "checkSearchResult")
    public void checkDefaultEurRate() {
        bankPage = page(BankPage.class);
        List<Money> moniesEur = bankPage
                .switchToDefaultRate()
                .getEurRow();

        Assert.assertEquals(1, moniesEur.get(1).compareTo(moniesEur.get(0)),
                "Курс продажи Стандартного курса больше для EUR");

    }

    @Test(description = "Проверка соответствия Стандартного курса USD правилу.",
            dependsOnMethods = "checkDefaultEurRate")
    public void checkDefaultUsdRate() {
        List<Money> moniesUsd = bankPage.getUsdRow();
        Assert.assertEquals(1, moniesUsd.get(1).compareTo(moniesUsd.get(0)),
                "Курс продажи Стандартного курса больше для USD");
    }
}
