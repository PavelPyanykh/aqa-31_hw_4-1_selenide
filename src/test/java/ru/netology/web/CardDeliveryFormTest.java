package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {

    @BeforeEach
    public void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitForm() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldNotSubmitEmptyCity() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).should(visible);
    }

    @Test
    public void shouldNotSubmitLatinCity() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Kostroma");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Доставка в выбранный город недоступна")).should(visible);
    }

    @Test
    public void shouldNotSubmitEmptyDate() {
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Неверно введена дата")).should(visible);
    }

    @Test
    public void shouldNotSubmitEmptyName() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).should(visible);
    }

    @Test
    public void shouldNotSubmitLatinName() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Ivanov Aleksandr");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).should(visible);
    }

    @Test
    public void shouldNotSubmitEmptyPhone() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Поле обязательно для заполнения")).should(visible);
    }

    @Test
    public void shouldNotSubmitInvalidPhone() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+7999777");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).should(visible);
    }

    @Test
    public void shouldNotSubmitUnchecked() {
        String formDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='city'] input").setValue("Кострома");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79997772233");
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).should(visible);
    }

}
