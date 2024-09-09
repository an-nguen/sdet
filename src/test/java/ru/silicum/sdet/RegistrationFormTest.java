package ru.silicum.sdet;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.silicum.sdet.pages.PracticeFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFormTest {

    private static PracticeFormPage formPage;

    @BeforeAll
    public static void initPage() {
        var driver = new ChromeDriver();
        driver.get(Constants.TESTABLE_PAGE_URL);
        formPage = new PracticeFormPage(driver);
    }

    @Test
    @DisplayName("Registration form test")
    @Description("This test just fill all fields of the form and submits it, then assert values from an appeared result dialog")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("An Nguyen")
    public void testRegistrationForm() {
        arrangeStep();
        actStep();
        assertStep();
    }

    @Step("Arrange")
    public void arrangeStep() {
        formPage.enterFirstName(TestData.FIRST_NAME);
        formPage.enterLastName(TestData.LAST_NAME);
        formPage.enterEmail(TestData.USER_EMAIL);
        formPage.selectGender(TestData.GENDER);
        formPage.enterPhoneNumber(TestData.USER_NUMBER);
        formPage.enterDateOfBirth(TestData.DATE_OF_BIRTH);
        formPage.enterSubjects(TestData.SUBJECTS);
        formPage.uploadPicture(TestData.PICTURE_FILE);
        formPage.enterCurrentAddress(TestData.CURRENT_ADDRESS);
        formPage.enterState(TestData.STATE);
        formPage.enterCity(TestData.CITY);
    }

    @Step("Act")
    public void actStep() {
        formPage.submitForm();
    }

    @Step("Assert")
    public void assertStep() {
        assertEquals(Constants.SUCCESSFUL_DIALOG_TITLE, formPage.getSubmissionEndDialogTitle(), "Dialog title mismatch");
        assertEquals(TestData.STUDENT_NAME, formPage.getActualStudentName(), "Student name mismatch");
        assertEquals(TestData.USER_EMAIL, formPage.getActualStudentEmail(), "Student email mismatch");
        assertEquals(TestData.GENDER.name(), formPage.getActualGender(), "Gender mismatch");
        assertEquals(TestData.USER_NUMBER, formPage.getActualMobile(), "Phone number mismatch");
        assertEquals(TestData.DATE_OF_BIRTH_STR, formPage.getActualDateOfBirth(), "Date of birth mismatch");
        assertEquals(TestData.SUBJECTS_STR, formPage.getActualSubjects(), "Subjects mismatch");
        assertEquals(TestData.CURRENT_ADDRESS, formPage.getActualAddress(), "Address mismatch");
        assertEquals(TestData.STATE_AND_CITY, formPage.getActualStateAndCity(), "State and city mismatch");
        assertEquals(TestData.PICTURE_FILE.getName(), formPage.getActualPicture(), "Picture mismatch");
    }

    @AfterAll
    public static void quitWebDriver() {
        formPage.close();
    }

}
