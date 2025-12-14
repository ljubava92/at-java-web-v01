package work.part02;

import com.codeborne.selenide.WebElementCondition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

    public class AuthorizationTests {
        @Test
        public void test01LoginSuccess() {
            open("https://slqamsk.github.io/cases/slflights/v01/");
            $(By.id("username")).sendKeys("standard_user");
            $(By.id("password")).sendKeys("stand_pass1");
            $(By.id("loginButton")).click();
            $(By.id("message")).shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
            $(By.id("departureDate")).sendKeys("08.12.2025");
        }
        @Test
        public void test02LoginWrongPassword() {
            open("https://slqamsk.github.io/cases/slflights/v01/");
            $(By.id("username")).sendKeys("standard1233_user");
            $(By.id("password")).sendKeys("stan4141414d_pass1");
            $(By.id("loginButton")).click();
            $(By.id("message")).shouldHave(text("Неверное имя пользователя или пароль."));
        }
}
