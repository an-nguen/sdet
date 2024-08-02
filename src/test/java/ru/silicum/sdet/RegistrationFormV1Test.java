package ru.silicum.sdet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationFormV1Test {

    // WebDriver class object creates a new session which is for opening and closing a browser.
    public static WebDriver driver;

    // It is needed to avoid repetition of getting of WebElement and do some action on it
    public List<TestableInput> registerInputs = new ArrayList<>();
    public Map<String, String> inputExpectedResultMap = Map.ofEntries(
            Map.entry("Student Name", String.format("%s %s", Constants.FIRST_NAME, Constants.LAST_NAME)),
            Map.entry("Student Email", Constants.USER_EMAIL),
            Map.entry("Gender", Constants.GENDER),
            Map.entry("Mobile", Constants.USER_NUMBER),
            Map.entry("Date of Birth", Constants.DATE_OF_BIRTH.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy"))),
            Map.entry("Subjects", String.join(", ", Constants.SUBJECTS)),
            Map.entry("Hobbies", String.join(", ", Constants.HOBBIES)),
            Map.entry("Address", Constants.CURRENT_ADDRESS),
            Map.entry("State and City", String.format("%s %s", Constants.STATE, Constants.CITY))
    );

    @BeforeAll
    public static void initWebDriver() {
        driver = new ChromeDriver();
        driver.get(Constants.TESTABLE_PAGE_URL);
    }

    @Test
    public void fillRegFormTest() {
        /* Arrange section */
        initInputs();
        /* Act section */
        // Fill a form
        registerInputs.forEach((input) -> input.test(driver));
        // Submit a filled form
        driver.findElement(By.cssSelector("button#submit")).click();
        /* Assertion */
        // There are should be a dialog
        var tableRows = driver.findElements(By.cssSelector("div[role='document'] table > tbody > tr"));
        tableRows.forEach(row -> {
            var cells = row.findElements(By.cssSelector("td"));
            assertEquals(2, cells.size());
            var labelValue = cells.get(0).getText();
            var actualValue = cells.get(1).getText();
            if (inputExpectedResultMap.containsKey(labelValue)) {
                assertEquals(inputExpectedResultMap.get(labelValue), actualValue);
            }
        });
    }

    @AfterAll
    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Initialize testable inputs in registration form
    private void initInputs() {
        registerInputs.add(TestableInput.create(By.id("firstName"), webElement -> webElement.sendKeys(Constants.FIRST_NAME)));
        registerInputs.add(TestableInput.create(By.id("lastName"), webElement -> webElement.sendKeys(Constants.LAST_NAME)));
        registerInputs.add(TestableInput.create(By.id("userEmail"), webElement -> webElement.sendKeys(Constants.USER_EMAIL)));
        registerInputs.add(TestableInput.create(By.xpath("//div[@id='genterWrapper']"), genderWrapperElement ->
                genderWrapperElement.findElement(
                        By.xpath(String.format("//label[contains(@for, 'gender-radio')][contains(text(), '%s')]", Constants.GENDER))
                ).click()));
        registerInputs.add(TestableInput.create(By.id("userNumber"), WebElement::click));
        registerInputs.add(TestableInput.create(By.cssSelector("div#dateOfBirth"), dateOfBirthContainer -> {
            // Open a datepicker by clicking at input
            dateOfBirthContainer.findElement(By.className("form-control"))
                    .click();
            // Find opened datepicker
            var datePicker = dateOfBirthContainer.findElement(By.className("react-datepicker"));
            // Select month
            var monthCssSelector = String.format(
                    "select.react-datepicker__month-select option[value='%d']",
                    Constants.DATE_OF_BIRTH.getMonthValue() - 1
            );
            datePicker.findElement(By.cssSelector(monthCssSelector))
                    .click();
            // Select year
            var yearCssSelector = String.format(
                    "select.react-datepicker__year-select option[value='%d']",
                    Constants.DATE_OF_BIRTH.getYear()
            );
            datePicker.findElement(By.cssSelector(yearCssSelector))
                    .click();
            // Select day
            var dayXpathSelector = String.format(
                    "//div[contains(@class, 'react-datepicker__day')][text()='%d']",
                    Constants.DATE_OF_BIRTH.getDayOfMonth()
            );
            datePicker.findElement(By.xpath(dayXpathSelector)).click();
        }));
        registerInputs.add(TestableInput.create(By.cssSelector("input#subjectsInput"), subjectsInput -> {
            for (var subjectValue : Constants.SUBJECTS) {
                subjectsInput.sendKeys(subjectValue);
                subjectsInput.sendKeys(Keys.RETURN);
            }
        }));
        registerInputs.add(TestableInput.create(By.id("hobbiesWrapper"), hobbiesWrapper -> {
            var hobbyElements = hobbiesWrapper.findElements(By.xpath("//label[contains(@for, 'hobbies-checkbox')]"));
            assertNotNull(Constants.HOBBIES);
            var rng = new Random();
            var hobbyIndex = rng.nextInt(0, hobbyElements.size());
            hobbyElements.get(hobbyIndex).click();
        }));
        registerInputs.add(TestableInput.create(By.id("currentAddress"), textArea -> {
            textArea.sendKeys(Constants.CURRENT_ADDRESS);
        }));
        // if I try to click on div container selenium will throw an error that is the element is not clickable
        // instead we just write a value
        registerInputs.add(TestableInput.create(By.cssSelector("div#state"), container -> {
            var input = container.findElement(By.cssSelector("input"));
            input.sendKeys(Constants.STATE);
            input.sendKeys(Keys.RETURN);
        }));
        registerInputs.add(TestableInput.create(By.cssSelector("div#city"), container -> {
            var input = container.findElement(By.cssSelector("input"));
            input.sendKeys(Constants.CITY);
            input.sendKeys(Keys.RETURN);
        }));
    }

}
