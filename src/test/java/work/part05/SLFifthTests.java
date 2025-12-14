package work.part05;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
public class SLFifthTests {
    @ParameterizedTest
    @CsvFileSource(resources = "sl1_data.csv", numLinesToSkip = 2)
    void test01(String login, String pass) {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        $("#username").sendKeys(login);
        $("#password").sendKeys(pass);
        $("#loginButton").click();
        $("#loginButton").shouldBe(visible);

    }

}
