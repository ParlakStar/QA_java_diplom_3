import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.OrderPage;
import pageObject.TransitionsHeader;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderUnauthorizedTest extends BrowserTest {
    @Test
    @DisplayName("Проверка перехода в Личный кабинет не авторизованного пользователя")
    public void headerAccountButtonClickWithOutLogIn() {
        open(MainPage.URL, TransitionsHeader.class).clickAccountLinkHeaderButton();
        page(LoginPage.class).LoginPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", LoginPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка перехода в Ленту заказов не авторизованного пользователя")
    public void headerFeedButtonClickWithOutLogIn() {
        open(MainPage.URL, TransitionsHeader.class).clickFeedHeaderButton();
        page(OrderPage.class).OrderPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", OrderPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка перехода в Конструктор не авторизованного пользователя")
    public void headerConstructorButtonClickWithOutLogIn() {
        open(MainPage.URL, TransitionsHeader.class).clickConstructorHeaderButton();
        page(MainPage.class).MainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверканажатия на Логотип не авторизованного пользователя")
    public void headerLogoClickWithOutLogIn() {
        open(MainPage.URL, TransitionsHeader.class).clickLogoHeaderButton();
        page(MainPage.class).MainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }
}
