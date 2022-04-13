package tests.homework1;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import helpers.Attach;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.components.CalendarComponents;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm {

    String firstName = "Ivanushka";
    String lastName = "Durachok";
    String studentEmail = "VanyaDuren@tridevyatoe.ru";
    String studentNumber = "0102030405";
    String currentAddress = "Tridevyatoe Tsartsvo, Dvorets, Palata #1";
    String gender = "Male";
    String hobbies1 = "Sports";
    String hobbies2 = "Music";
    String subject = "Maths";
    String pictureName = "Photo.jpg";
    String state = "NCR";
    String city = "Noida";
    String day = "21";
    String month = "March";
    String year = "2012";

    @BeforeAll
    static void startPage() {
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("startMaximised", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = System.getProperty("browser_address");
    }

    @AfterEach
    void addAttachment() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    //components
    private CalendarComponents calendarComponents = new CalendarComponents();

    //locators
    private SelenideElement
            headerTitle = $(".main-header"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            resultsTable = $(".table-responsive"),
            studentEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            currentAddressLocator = $("#currentAddress"),
            genderLocator = $("#genterWrapper"),
            hobbies = $("#hobbiesWrapper"),
            subjectLocator = $("#subjectsInput"),
            upload = $("#uploadPicture"),
            stateLocator = $("#state"),
            stateCity = $("#stateCity-wrapper"),
            cityLocator = $("#city");

    //actions
    @Step("Открываем страницу с формой и проверяем, что попали в нужную форму")
    public RegistrationForm openPage() {
        open("/automation-practice-form");
        headerTitle.shouldHave(text("Practice Form"));
        return this;
    }
    @Step("Вводим имя в поле Name")
    public RegistrationForm setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }
    @Step("Вводим фамилия в поле Lastname")
    public RegistrationForm setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }
    @Step("Указываем свой email")
    public RegistrationForm setUserEmail(String studentEmail) {
        studentEmailInput.setValue(studentEmail);
        return this;
    }
    @Step("Указываем номер телефона")
    public RegistrationForm setUserNumber(String number) {
        userNumberInput.setValue(number);
        return this;
    }
    @Step("Указываем адрес проживания")
    public RegistrationForm setCurrentAddress(String address) {
        currentAddressLocator.setValue(address);
        return this;
    }
    @Step("Указываем дату рождения из интерактивного календаря")
    public void setBirthDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendarComponents.setDate(day, month, year);

    }
    @Step("Выбираем пол")
    public RegistrationForm setGender(String wrapperValue) {
        genderLocator.$(byText(wrapperValue)).click();
        return this;
    }
    @Step("Выбираем хобби, одно или несколько")
    public RegistrationForm setHobby(String wrapperValue) {
        hobbies.$(byText(wrapperValue)).click();
        return this;
    }
    @Step("Указываем интересующие предметы обучения")
    public void enterSubject(String subjectName) {
        subjectLocator.setValue(subjectName).pressEnter();
    }
    @Step("Загружаем фото")
    public void uploadPicture(String pictureName) {
        upload.uploadFromClasspath(pictureName);
    }
    @Step("Закрываем баннер")
    public void closeFixedBanner() {
        $("#close-fixedban").click();}
    @Step("Указываем регион")
    public void setState(String stateName) {
        stateLocator.scrollTo().click();
        stateCity.$(byText(stateName)).click();
    }
    @Step("Указываем город")
    public void setCity(String cityName) {
        cityLocator.click();
        stateCity.$(byText(cityName)).click();
    }
    @Step("Сабмитим форму")
    public void submitForm() {
        $("#submit").click();
    }
    @Step("Проверяем, что сабмит прошел успешно")
    public void checkSubmitOk() {
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
    @Step("Проверяем соответствие заполнения полей указанным данным")
    public RegistrationForm checkForm(String fieldName, String value) {
        resultsTable.$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }
}
