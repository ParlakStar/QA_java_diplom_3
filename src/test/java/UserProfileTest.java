import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.*;
import user.User;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserProfileTest extends BrowserTest {
    private User validUserData;

    @Before
    @DisplayName("Создайте пользователя и выполните вход и авторизация")
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

        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
    }

    @Test
    @DisplayName("Проверка перехода на вкладку Конструктора из Профиля пользователя")
    public void transitionFromProfileToConstructor() {
        page(TransitionsHeader.class)
                .clickConstructorHeaderButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .MainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
    }

    @Test
    @DisplayName("Проверка перехода на Главную страницу нажатием на логотип из Профиля пользователя")
    public void transitionFromProfileToMainPageLogo() {
        page(TransitionsHeader.class)
                .clickLogoHeaderButton();
        boolean isMainPageLoaded = page(MainPage.class)
                .MainPageLoaded()
                .isMainPageLoaded();
        String currentURL = webdriver().driver().url();
        assertEquals("Не верная ссылка", MainPage.URL, currentURL);
        assertTrue("Главная страница не загружена", isMainPageLoaded);
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
