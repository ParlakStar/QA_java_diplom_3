import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageObject.MainPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class SwitchConstructorTest extends BrowserTest {
    @Test
    @DisplayName("Проверка переключения на вкладку 'Булки'")
    public void checkClickBuns() {
        String expectedTab = "Булки";
        MainPage mainPage = open(MainPage.URL, MainPage.class); // Открываем страницу и создаем объект MainPage
        mainPage.clickTabFillings().clickTabBun(); // Кликаем на вкладку "Начинки", затем на вкладку "Булки"

        String currentTab = mainPage.getCurrentTab(); // Получаем текущую активную вкладку
        assertEquals("Вкладка не переключилась", expectedTab, currentTab); // Проверяем, что вкладка переключилась на "Булки"
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    public void checkClickSouce() {
        String expectedTab = "Соусы";
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickTabSouce(); // Кликаем на вкладку "Соусы"

        String currentTab = mainPage.getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    public void checkClickFilling() {
        String expectedTab = "Начинки";
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickTabFillings(); // Кликаем на вкладку "Начинки"

        String currentTab = mainPage.getCurrentTab();
        assertEquals("Вкладка не поменялась", expectedTab, currentTab);
    }
}
