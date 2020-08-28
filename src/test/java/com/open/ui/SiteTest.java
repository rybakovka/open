package com.open.ui;

import com.codeborne.selenide.Condition;
import com.helpers.Money;
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
    public void testCheckSearchResult() {
        googlePage = open("https://www.google.com/", GooglePage.class);
        int linkCount = googlePage.search("открытие")
                .filteredResults("www.open.ru")
                .filter(Condition.visible)
                .size();
        Assert.assertTrue(linkCount > 0, "Результаты поиска содержат Банк Открытие");
        googlePage.clickResult("www.open.ru");
    }

    @Test(description = "Проверка соответствия Льготного курса EUR правилу.",
            dependsOnMethods = "testCheckSearchResult")
    public void testCheckPreferentialEurRate() {
        bankPage = page(BankPage.class);
        List<Money> moneyEur = bankPage
                .selectInternetBank()
                .switchToPreferentialRate()
                .getEurRow();
        Assert.assertEquals( moneyEur.get(1).compareTo(moneyEur.get(0)) > 0,true,
                "Курс продажи Льготного курса больше для EUR");

    }

    @Test(description = "Проверка соответствия Льготного курса USD правилу.",
            dependsOnMethods = "testCheckPreferentialEurRate")
    public void testCheckPreferentialUsdRate() {
        List<Money> moneyUsd = bankPage.getUsdRow();
        Assert.assertEquals(moneyUsd.get(1).compareTo(moneyUsd.get(0)) > 0, true,
                "Курс продажи Льготного курса больше для USD");
    }


    @Test(description = "Проверка соответствия Стандартного курса EUR правилу.",
            dependsOnMethods = "testCheckSearchResult")
    public void testCheckDefaultEurRate() {
        bankPage = page(BankPage.class);
        List<Money> moneyEur = bankPage
                .switchToDefaultRate()
                .getEurRow();
        Assert.assertEquals(moneyEur.get(1).compareTo(moneyEur.get(0)) > 0, true,
                "Курс продажи Стандартного курса больше для EUR");

    }

    @Test(description = "Проверка соответствия Стандартного курса USD правилу.",
            dependsOnMethods = "testCheckDefaultEurRate")
    public void testCheckDefaultUsdRate() {
        List<Money> moneyUsd = bankPage.getUsdRow();
        Assert.assertEquals(moneyUsd.get(1).compareTo(moneyUsd.get(0)) > 0, true,
                "Курс продажи Стандартного курса больше для USD");
    }
}
