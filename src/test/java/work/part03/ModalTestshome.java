package work.part03;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ModalTestshome {
    @Test
    void test01SimpleModal() {
        Configuration.pageLoadStrategy = "eager";
        open("https://practice-automation.com/modals/");
        $x("//div[@id='popmake-1318']").shouldBe(exist);
        $("#simpleModal").click();
        sleep(2_000);
        $x("//a[text()='Home']").click();
        $x("//div[@id='popmake-1318']").shouldBe(visible);
        $x("//div[@id='popmake-1318']//button").shouldBe(clickable);
        $x("//div[@id='popmake-1318']//button").click();
        sleep(2_000);
    }
    @Test
    void test01Modal() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.specialist.ru/");
        getWebDriver().manage().window().maximize();
        $x("//div[@id='cookieConsent']").shouldBe(exist);
        $("#cookieConsent__ok").click();
        sleep(2_000);
        $x("//li[@class='header-menu-item']/a[text()='Форматы обучения']").click();
        sleep(2_000);
        $x("//div[@class='format-article-content']/a[text()='Свободное обучение']").click();
    }
    @Test
    void test02Modal() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.specialist.ru/");
    $x("//li[@class='header-menu-item']/a[text()='Форматы обучения']").shouldBe(clickable);

    }
}