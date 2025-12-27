package work.part07;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demo.part07.pages.FlightsListPage;
import demo.part07.pages.LoginPage;
import demo.part07.pages.RegistrationPage;
import demo.part07.pages.SearchPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMHome4 {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = "chrome";
    }

    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        getWebDriver().manage().window().maximize();
        sleep(5_000);
    }

    // Старая дата
    @Test
    void testOldDate() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("01.12.2025");
        searchPage.isDateInOld();
    }

    // Проверить вход заблокированного пользователя (locked_out_user)
    @Test
    void testBlockLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("locked_out_user", "lock_pass2");
        loginPage.isLoginBlock();
    }
    // Проверить нажатие кнопки Войти на пустой форме авторизации
    @Test
    void testButton() {
        LoginPage loginPage = new LoginPage();
        loginPage.setLoginButton();
        sleep(2_000);
    }

    // Регистрация на рейс Санкт-Петербург - Нью-Йорк и возврат на страницу с найденными рейсами
    @Test
    void testFlightSpb() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("29.01.2026","Санкт-Петербург", "Нью-Йорк");
        sleep(2_000);

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.FindFlight();
        sleep(2_000);
        flightsList.registerToFirstFlight();

        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Санкт-Петербург", "Нью-Йорк");
        registrationPage.registration("Иванов Иван Иванович", "1234 567890", "ivanov@example.com", "+7 (123) 456-7890");
        registrationPage.successRegistration();
        sleep(2_000);
        registrationPage.setButtonReturnFlightListPage();
        sleep(2_000);
    }
}
