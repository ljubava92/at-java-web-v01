package work.part05;

import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class FormTests {

    @BeforeEach
    void beforeEach() {
       open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
    }
    //Нажать кнопку "Рассчитать" для незаполненной формы
    @Test
    void test01() {
        $("[type='submit']").click();
        sleep(1_000);
        String priceMessage = "Стоимость билета: *надо указать возраст для расчёта стоимости билета* *надо указать фильм для расчёта стоимости билета* *надо указать дату сеанса для расчёта стоимости билета* *надо указать сеанс для расчёта стоимости билета*";
        $("div").shouldHave(text(priceMessage));
        sleep(1_000);
    }
    //Заполнить форму корректными данными (возраст, дата сеанса, начало сеанса, название фильма и нажать кнопку "Рассчитать".
    @Test
    void test02() {
        $("input[name=age]").setValue("19");
        String dateValue = "24-12-2025";
        $("input[name='date']").setValue(dateValue);
        $("input[name=session][value='5']").click();
        $("input[name=film][value=back]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: 400 рублей."));
    }
    //Заполнить форму сегодняшней датой и корректными остальными данными и нажать кнопку "Рассчитать".
    @Test
    void test03() {
        $("input[name=age]").setValue("19");
        String dateValue = "17-12-2025";
        $("input[name='date']").setValue(dateValue);
        $("input[name=session][value='4']").click();
        $("input[name=film][value=tango]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: *нажмите кнопку для расчёта*"));
    }
    //Проверить, что можно изменить время сеанса и фильм после рассчета стоимости.
    @Test
    void test04() {
        $("input[name=age]").setValue("45");
        String dateValue = "20-12-2025";
        $("input[name='date']").setValue(dateValue);
        $("input[name=session][value='10']").click();
        $("input[name=film][value=tango]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: 500 рублей."));
        $("input[name=session][value='5']").click();
        $("input[name=film][value=crime]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: 500 рублей."));
    }
    //Проверить, что при возрасте меньше 18 при выборе фильма +18 появляется сообщение об ограничении просмотра по возрасту, а при выборе фильма для возраста 0+ исчезает это сообщение.
    @Test
    void test05() {
        $("input[name=age]").setValue("12");
        String dateValue = "22-12-2025";
        $("input[name='date']").setValue(dateValue);
        $("input[name=session][value='3']").click();
        $("input[name=film][value=tango]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: *Этот фильм можно смотреть только с 18 лет*"));
        $("input[name=film][value=king]").click();
        $("input[type=submit").click();
        sleep(1_000);
        $("div").shouldHave(text("Стоимость билета: 350 рублей."));
    }
}
