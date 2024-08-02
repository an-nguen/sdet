package ru.silicum.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;

public class TestableInput {

    private final By locator;

    private final Consumer<WebElement> webElementConsumer;

    private TestableInput(By locator, Consumer<WebElement> elementAction) {
        this.locator = locator;
        this.webElementConsumer = elementAction;
    }

    public static TestableInput create(By locator, Consumer<WebElement> elementAction) {
        return new TestableInput(locator, elementAction);
    }

    public void test(WebDriver driver) {
        var webElement = driver.findElement(locator);
        webElementConsumer.accept(webElement);
    }

}
