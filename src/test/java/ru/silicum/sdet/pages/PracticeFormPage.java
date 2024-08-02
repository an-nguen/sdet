package ru.silicum.sdet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.silicum.sdet.enums.Genders;

import java.time.LocalDate;
import java.util.*;

public class PracticeFormPage {

    private final WebDriver driver;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userNumber")
    private WebElement phoneNumberInput;

    @FindBy(css = "div#dateOfBirth")
    private WebElement dateOfBirthContainer;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;

    @FindAll(value = @FindBy(css = "#hobbiesWrapper label[for*='hobbies-checkbox']"))
    private List<WebElement> hobbyRadioInputs;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;

    @FindBy(css = "div#state input")
    private WebElement stateInput;

    @FindBy(css = "div#city input")
    private WebElement cityInput;

    @FindBy(css = "button#submit")
    private WebElement submissionButton;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void selectGender(Genders gender) {
        var genderQuery = "//div[@id='genterWrapper']//label[contains(@for, 'gender-radio')][text()='%s']";
        var query = String.format(
                genderQuery,
                gender.name()
        );
        driver.findElement(By.xpath(query)).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void enterDateBirth(LocalDate date) {
        dateOfBirthContainer.click();
        // Open a datepicker by clicking at input
        dateOfBirthContainer.findElement(By.className("form-control"))
                .click();
        // Find opened datepicker
        var datePicker = dateOfBirthContainer.findElement(By.className("react-datepicker"));
        // Select month
        var monthCssSelector = String.format(
                "select.react-datepicker__month-select option[value='%d']",
                date.getMonthValue() - 1
        );
        datePicker.findElement(By.cssSelector(monthCssSelector))
                .click();
        // Select year
        var yearCssSelector = String.format(
                "select.react-datepicker__year-select option[value='%d']",
                date.getYear()
        );
        datePicker.findElement(By.cssSelector(yearCssSelector))
                .click();
        // Select day
        var dayXpathSelector = String.format(
                "//div[contains(@class, 'react-datepicker__day')][text()='%d']",
                date.getDayOfMonth()
        );
        datePicker.findElement(By.xpath(dayXpathSelector)).click();
    }

    public void enterSubjects(String[] subjects) {
        for (var subject: subjects) {
            subjectsInput.sendKeys(subject);
            subjectsInput.sendKeys(Keys.RETURN);
        }
    }

    public void enterCurrentAddress(String currentAddress) {
        currentAddressInput.sendKeys(currentAddress);
    }

    public void enterState(String stateName) {
        stateInput.sendKeys(stateName);
        stateInput.sendKeys(Keys.TAB);
    }

    public void enterCity(String cityName) {
        cityInput.sendKeys(cityName);
        cityInput.sendKeys(Keys.TAB);
    }

    public Map<String, String> submitForm() {
        submissionButton.click();
        var tableRows = driver.findElements(By.cssSelector("div[role='document'] table > tbody > tr"));
        var actualResults = new HashMap<String, String>();
        tableRows.forEach(row -> {
            var cells = row.findElements(By.cssSelector("td"));
            var labelValue = cells.get(0).getText();
            var actualValue = cells.get(1).getText();
            actualResults.put(labelValue, actualValue);
        });
        return actualResults;
    }

    public String getSubmissionEndDialogTitle() {
        return driver.findElement(By.cssSelector(".modal-content > .modal-header > .modal-title")).getText();
    }

    public void close() {
        driver.quit();
    }

}
