package work.part07.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
      SelenideElement
            username = $("#username"),
            password = $("#password"),
            loginButton = $("#loginButton"),
            errorMessage = $("#message"),
            greeting = $("#greeting");

    public LoginPage() {

    }
    @Step("Вход в систему")
    public void login(String username, String password) {
        // $("#username").setValue("standard_user");
        this.username.setValue(username);
        this.password.setValue(password);
        this.loginButton.click();
    }

    @Step("Неуспешный логин")
    public void isLoginUnsuccessful() {
        this.errorMessage.shouldBe(visible);
        this.errorMessage.shouldHave(Condition.cssClass("error"));
        this.errorMessage.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Step("Успешный логин")
    public void isLoginSuccessful(String fio) {
        this.greeting.shouldHave(text("Добро пожаловать, " + fio + "!"));
    }

    @Step("Заблокированный пользователь")
    public void isLoginBlock() {
        this.errorMessage.shouldHave(text("Пользователь заблокирован."));
    }
    @Step("Вход без ввода данных")
    public void setLoginButton() {
        this.loginButton.shouldBe(visible);;
        this.loginButton.click();
        this.errorMessage.shouldBe(visible);
        this.errorMessage.shouldHave(text("Username and Password are required."));
    }
}