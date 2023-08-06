import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import pageObject.*;
import user.User;

public class SignOutTest extends BrowserTest {
    private User validUserData;

    @Before
    @DisplayName("Создание пользователя, вход и авторизация")
    public void setUp() {
        validUserData = User.getRandomUserValidData();
        registerAndLogInUser(validUserData);
    }

    @Test
    @Description("Проверка выхода из профиля")
    public void logoutFromProfile() {
        logoutUser();
        boolean isLoginPageLoaded = page(LoginPage.class).isLoginPageLoaded();
        String currentUrl = webdriver().driver().url();
        assertEquals("Не верная ссылка", LoginPage.URL, currentUrl);
        assertTrue("Страница авторизации не загружена", isLoginPageLoaded);
    }

    @After
    @DisplayName("Удаление пользователя и очистка cookies")
    public void cleanData() {
        // Удалить пользователя, если он был создан во время теста
        if (validUserData != null) {
            validUserData.cleanupTestData();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    // Этот метод регистрирует нового пользователя, входит в систему и открывает страницу его профиля пользователя.
    private void registerAndLogInUser(User user) {
        RegistrationPage registrationPage = open(RegistrationPage.URL, RegistrationPage.class);
        registrationPage.fillNameInput(user.getName())
                .fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickButtonReg()
                .RegistrationPageDisappear();

        LoginPage loginPage = open(LoginPage.URL, LoginPage.class);
        loginPage.fillEmailInput(user.getEmail())
                .fillPasswordInput(user.getPassword())
                .clickButtonLogIn()
                .LoginPageDisappear();

        // После входа откройте страницу профиля пользователя
        page(TransitionsHeader.class).clickAccountLinkHeaderButton();
    }

    // Этот метод выходит из системы пользователя со страницы его профиля.
    private void logoutUser() {
        UserProfilePage userProfilePage = page(UserProfilePage.class);
        userProfilePage.UserProfilePageLoaded()
                .clickLogOutButton()
                .UserProfilePageDisappear();
    }
}
