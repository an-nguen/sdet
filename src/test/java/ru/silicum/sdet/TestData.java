package ru.silicum.sdet;

import ru.silicum.sdet.enums.Genders;
import ru.silicum.sdet.enums.State;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestData {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy");

    public static final String FIRST_NAME = "Anton";
    public static final String LAST_NAME = "Antonovich";
    public static final String STUDENT_NAME = String.format("%s %s", FIRST_NAME, LAST_NAME);
    public static final String USER_EMAIL = "aaa1999@example.com";
    public static final Genders GENDER = Genders.Male;
    public static final String USER_NUMBER = "1234567890";
    public static final LocalDate DATE_OF_BIRTH = LocalDate.of(1999, 12, 10);
    public static final String DATE_OF_BIRTH_STR = DATE_OF_BIRTH.format(formatter);
    public static final String[] SUBJECTS = {"Maths", "Physics", "Computer Science"};
    public static final String SUBJECTS_STR = String.join(", ", SUBJECTS);
    public static final String CURRENT_ADDRESS = "Russia, Ulyanovsk";
    public static final String STATE = State.NCR.stateName;
    public static final String CITY = State.NCR.cities[0].name;
    public static final String STATE_AND_CITY = String.format("%s %s", STATE, CITY);
    public static final String PICTURE_FILE_PATH = "src/test/resources/jackal.jpg";
    public static final File PICTURE_FILE = new File(PICTURE_FILE_PATH);

}
