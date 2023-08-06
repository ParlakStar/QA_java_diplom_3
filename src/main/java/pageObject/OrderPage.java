package pageObject;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/feed";
    @FindBy(how = How.XPATH, using = "//h1[text()='Лента заказов']")
    private SelenideElement feedOrdersHeader;

    @Step("Ожидание загрузки страницы Лента заказов")
    public OrderPage OrderPageLoaded() {
        feedOrdersHeader.shouldBe(Condition.visible);
        return this;
    }

    @Step("Ожидание закрытия страницы Лента заказов")
    public OrderPage OrderPageDisappear() {
        feedOrdersHeader.shouldBe(Condition.disappear);
        return this;
    }
}
