package pageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class PasswordRecoveryPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement logInLink;

    @Step("Клик по кнопке 'Войти' на станице сброса пароля")
    public LoginPage clickLogInLink() {
        logInLink.shouldBe(Condition.visible).click();
        return page(LoginPage.class);
    }

    @Step("Ожидание загрузки страницы Сброса пароля")
    public PasswordRecoveryPage PasswordRecoveryPageLoaded() {
        logInLink.shouldBe(Condition.visible);
        return this;
    }

    @Step("Ожидание закрытия страницы Сброса пароля")
    public PasswordRecoveryPage PasswordRecoveryPageDisappear() {
        logInLink.shouldBe(Condition.disappear);
        return this;
    }
}
