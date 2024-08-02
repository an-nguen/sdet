package ru.silicum.sdet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.silicum.sdet.pages.PracticeFormPage;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationFormV2Test {

    private static PracticeFormPage practiceFormPage;

    public Map<String, String> inputExpectedResultMap = Map.ofEntries(
            Map.entry("Student Name", String.format("%s %s", Constants.FIRST_NAME, Constants.LAST_NAME)),
            Map.entry("Student Email", Constants.USER_EMAIL),
            Map.entry("Gender", Constants.GENDER.name()),
            Map.entry("Mobile", Constants.USER_NUMBER),
            Map.entry("Date of Birth", Constants.DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy"))),
            Map.entry("Subjects", String.join(", ", Constants.SUBJECTS)),
            Map.entry("Address", Constants.CURRENT_ADDRESS),
            Map.entry("State and City", String.format("%s %s", Constants.STATE, Constants.CITY)),
            Map.entry("Picture", Constants.PICTURE_FILE.getName())
    );

    @BeforeAll
    public static void initPage() {
        var driver = new ChromeDriver();
        driver.get(Constants.TESTABLE_PAGE_URL);
        practiceFormPage = new PracticeFormPage(driver);
    }

    @Test
    public void testRegistrationForm() {
        practiceFormPage.enterFirstName(Constants.FIRST_NAME);
        practiceFormPage.enterLastName(Constants.LAST_NAME);
        practiceFormPage.enterEmail(Constants.USER_EMAIL);
        practiceFormPage.selectGender(Constants.GENDER);
        practiceFormPage.enterPhoneNumber(Constants.USER_NUMBER);
        practiceFormPage.enterDateBirth(Constants.DATE_OF_BIRTH);
        practiceFormPage.enterSubjects(Constants.SUBJECTS);
        practiceFormPage.uploadPicture(Constants.PICTURE_FILE);
        practiceFormPage.enterCurrentAddress(Constants.CURRENT_ADDRESS);
        practiceFormPage.enterState(Constants.STATE);
        practiceFormPage.enterCity(Constants.CITY);
        var actualResultMap = practiceFormPage.submitForm();
        var actualDialogTitle = practiceFormPage.getSubmissionEndDialogTitle();

        assertEquals(Constants.SUCCESSFUL_DIALOG_TITLE, actualDialogTitle);
        actualResultMap.forEach((label, value) -> {
            if (inputExpectedResultMap.containsKey(label)) {
                var expectedValue = inputExpectedResultMap.get(label);
                assertEquals(expectedValue, value);
            }
        });
    }

    @AfterAll
    public static void quitWebDriver() {
        practiceFormPage.close();
    }

}
