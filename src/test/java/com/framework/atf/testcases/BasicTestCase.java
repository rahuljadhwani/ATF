package com.framework.atf.testcases;

import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.Locator;
import com.framework.atf.utils.listeners.DriverListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import java.util.function.BiFunction;

@Listeners(DriverListener.class)
public class BasicTestCase {
    WebDriver driver = Profile.getInstance().getDriver();

    BiFunction<Locator, String, WebElement> webElementBiFunction = (locator, path) -> {
        driver = Profile.getInstance().getDriver();
        WebElement element;
        switch (locator.name()) {
            case "XPATH":
                element = driver.findElement(By.xpath(path));
                break;
            case "ID":
                element = driver.findElement(By.id(path));
                break;
            case "NAME":
                element = driver.findElement(By.name(path));
                break;
            case "TAGNAME":
                element = driver.findElement(By.tagName(path));
                break;
            case "CSSSELECTOR":
                element = driver.findElement(By.cssSelector(path));
                break;
            case "LINKTEXT":
                element = driver.findElement(By.linkText(path));
                break;
            case "PARTIALLINKTEXT":
                element = driver.findElement(By.partialLinkText(path));
                break;
            case "CLASSNAME":
                element = driver.findElement(By.className(path));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locator.name());
        }
        return element;
    };

    public void enter(By locator, String description) {
        driver.findElement(locator).sendKeys(description);
    }

    public void enter(Locator locator, String elementDiscriptor, String textToEnter) {
        webElementBiFunction.apply(locator, elementDiscriptor).sendKeys(textToEnter);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void click(Locator locator, String elementDiscriptor) {
        webElementBiFunction.apply(locator, elementDiscriptor).click();
    }

}
