import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;
import user.User;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class AuthorizedTest extends BrowserTest {
    private User validUserData;

    @Before
    @DisplayName("Создание пользователя и вход")
    public void setUp() {
        validUserData = User.getRandomUserValidData();
        createUserAndLogIn();
    }

    @Test
    @DisplayName("Вход через Личный кабинет с верными данными")
    public void loginThroughAccountWithValidData() {
        open(MainPage.URL, TransitionsHeader.class).clickAccountLinkHeaderButton();
        checkLogInWithValidData();
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' с верными данными")
    public void loginThroughAccountButtonWithValidData() {
        open(MainPage.URL, MainPage.class).clickLogInButton();
        checkLogInWithValidData();
    }

    @Test
    @DisplayName("Вход со страницы Регистрации с верными данными")
    public void loginThroughRegistrationLinkWithValidData() {
        open(RegistrationPage.URL, RegistrationPage.class).clickLinkLogIn();
        checkLogInWithValidData();
    }

    @Test
    @DisplayName("Вход со страницы Забыли пароль с верными данными")
    public void loginThroughPasswordRecoveryLinkWithValidData() {
        open(PasswordRecoveryPage.URL, PasswordRecoveryPage.class).clickLogInLink();
        checkLogInWithValidData();
    }

    @After
    @DisplayName("Выход из профиля, удаление пользователя и очистка куки")
    public void cleanData() {
        logoutAndcleanupTestData();
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @Step("Создание пользователя и вход")
    private void createUserAndLogIn() {
        open(RegistrationPage.URL, RegistrationPage.class)
                .fillNameInput(validUserData.getName())
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonReg()
                .RegistrationPageDisappear();
    }

    @Step("Выход из профиля, удаление пользователя и очистка куки")
    private void logoutAndcleanupTestData() {
        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
        page(UserProfilePage.class)
                .UserProfilePageLoaded()
                .clickLogOutButton()
                .UserProfilePageDisappear();
        if (validUserData != null) {
            validUserData.cleanupTestData();
        }
    }

    @Step("Проверка успешного входа с верными данными")
    private void checkLogInWithValidData() {
        page(LoginPage.class)
                .fillEmailInput(validUserData.getEmail())
                .fillPasswordInput(validUserData.getPassword())
                .clickButtonLogIn()
                .LoginPageDisappear()
                .MainPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Залогиниться не удалось", MainPage.URL, currentUrl);

        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
        String actualLogin = page(UserProfilePage.class).getLogInInput();
        assertEquals("Логин не совпадает", validUserData.getEmail(), actualLogin);
    }
}