package work.part03;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ModalTestshome {
//Домашнее задание AT-1 (обязательное): На сайте https://www.specialist.ru/ в модальном окне "Наш сайт использует файлы cookie" нажать "Согласен". Выбрать пункт меню "Форматы обучения", затем "Свободное обучение", затем "Выбрать курс", в поле "Направление" выбрать "Программирование", нажать кнопку "Применить" и убедиться, что на странице есть элемент содержащий текст "Тестирование ПО"
    @Test
    void test01Modal() {
        Configuration.pageLoadStrategy = "eager";
        open("https://www.specialist.ru/");
        getWebDriver().manage().window().maximize();
        $x("//div[@id='cookieConsent']").shouldBe(exist);
        $x("//*[@id='cookieConsent__ok']").click();
        sleep(1_000);
        $x("//li[@class='header-menu-item']/a[text()='Форматы обучения']").click();
        sleep(1_000);
        $x("//a[contains(text(), 'Свободное обучение')]").click();
        sleep(1_000);
        $x("//a[@class='page-button banner-button' and @href='#selectCourse' and text()='Выбрать курс']").click();
        sleep(1_000);
        $x("//select[@id='Filter_CategoriesDirectionFilter']//option[@value='ПРГ']").click();
        sleep(1_000);
        $x("//button[@id='sendBtn']").click();
        sleep(1_000);
        $x("//a[@class='course-link' and contains(@href, 'course/tpo') and contains(text(), 'Тестирование ПО')]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Тестирование ПО"), Duration.ofSeconds(10));
        sleep(1_000);

    }

}