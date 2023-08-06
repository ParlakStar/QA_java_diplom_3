import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;
import pageObject.TransitionsHeader;
import user.User;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class HeaderAuthorizedTest extends BrowserTest {
    private User validUserData;

    @Before
    @DisplayName("Создайте пользователя и выполните вход")
    public void setUp() {
        validUserData = User.getRandomUserValidData();

        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .RegistrationPageDisappear();

        open(LoginPage.URL, LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .LoginPageDisappear();
        page(MainPage.class).MainPageLoaded();
    }

    @Test
    @DisplayName("Проверка перехода в 'Личный кабинет' авторизованного пользователя")
    public void headerAccountButtonClickWithLogIn() {
        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
        page(UserProfilePage.class).UserProfilePageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", UserProfilePage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка перехода в 'Лента заказов' авторизованного пользователя")
    public void headerFeedButtonClickWithLogIn() {
        page(TransitionsHeader.class).clickFeedHeaderButton();
        page(OrderPage.class).OrderPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", OrderPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка перехода в закладку Конструктор авторизованного пользователя")
    public void headerConstructorButtonClickWithLogIn() {
        page(TransitionsHeader.class).clickConstructorHeaderButton();
        page(MainPage.class).MainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }

    @Test
    @DisplayName("Проверка нажатия на Логотип авторизованного пользователя")
    public void headerLogoClickWithLogIn() {
        page(TransitionsHeader.class).clickLogoHeaderButton();
        page(MainPage.class).MainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentUrl);
    }

    @After
    @DisplayName("Проверка выхода из профиля, удаления пользователя и чистки куки")
    public void cleanupTestData() {
        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
        page(UserProfilePage.class)
                .UserProfilePageLoaded()
                .clickLogOutButton()
                .UserProfilePageDisappear();
        if (validUserData != null) {
            validUserData.cleanupTestData();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
