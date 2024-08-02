package ru.silicum.sdet;

import ru.silicum.sdet.enums.Genders;

import java.io.File;
import java.time.LocalDate;

public class Constants {

    // Constant fields
    public static final String TESTABLE_PAGE_URL = "https://demoqa.com/automation-practice-form";
    public static final String FIRST_NAME = "Anton";
    public static final String LAST_NAME = "Antonovich";
    public static final String USER_EMAIL = "aaa1999@example.com";
    public static final Genders GENDER = Genders.Male;
    public static final String USER_NUMBER = "1234567890";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(1999, 12, 10);
    public static final String[] SUBJECTS = {"Maths", "Physics", "Computer Science"};
    public static final String CURRENT_ADDRESS = "Russia, Ulyanovsk";
    public static final String STATE = "NCR";
    public static final String CITY = "Delhi";
    public static final String SUCCESSFUL_DIALOG_TITLE = "Thanks for submitting the form";
    public static final String PICTURE_FILE_PATH = "src/test/resources/jackal.jpg";
    public static final File PICTURE_FILE = new File(PICTURE_FILE_PATH);
}
