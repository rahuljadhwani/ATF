package com.framework.atf.testcases;

import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.Locator;
import com.framework.atf.utils.listeners.DriverListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

import java.util.function.BiFunction;

@Listeners(DriverListener.class)
public class BasicTestCase {
    WebDriver driver = Profile.getInstance().getDriver();

    BiFunction<Locator, String, WebElement> webElementBiFunction = (locatorType, locatorArg) -> {
        driver = Profile.getInstance().getDriver();
        WebElement element;
        switch (locatorType.name()) {
            case "XPATH":
                element = driver.findElement(By.xpath(locatorArg));
                break;
            case "ID":
                element = driver.findElement(By.id(locatorArg));
                break;
            case "NAME":
                element = driver.findElement(By.name(locatorArg));
                break;
            case "TAGNAME":
                element = driver.findElement(By.tagName(locatorArg));
                break;
            case "CSSSELECTOR":
                element = driver.findElement(By.cssSelector(locatorArg));
                break;
            case "LINKTEXT":
                element = driver.findElement(By.linkText(locatorArg));
                break;
            case "PARTIALLINKTEXT":
                element = driver.findElement(By.partialLinkText(locatorArg));
                break;
            case "CLASSNAME":
                element = driver.findElement(By.className(locatorArg));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locatorType.name());
        }
        return element;
    };

    /**
     * This method is used to enter some text to the given input element using Selenium By class
     *
     * @param locator
     * @param description
     */
    public void enter(By locator, String description) {
        driver.findElement(locator).sendKeys(description);
    }

    /**
     * This method is used to enter some text to the given input element
     *
     * @param locator
     * @param elementDiscriptor
     * @param textToEnter
     */
    public void enter(Locator locator, String elementDiscriptor, String textToEnter) {
        webElementBiFunction.apply(locator, elementDiscriptor).sendKeys(textToEnter);
    }

    /**
     * This method is used to click on an element using Selenium By class
     *
     * @param locator
     */
    public void clickOnElement(By locator) {
        driver.findElement(locator).click();
    }

    /**
     * This method is used to click on an element
     *
     * @param locator
     * @param elementDiscriptor
     */
    public void clickOnElement(Locator locator, String elementDiscriptor) {
        webElementBiFunction.apply(locator, elementDiscriptor).click();
    }

    /**
     * Select value from dropdown through visible text
     *
     * @param locator
     * @param elementDiscriptor
     * @param value
     */
    public void selectValueFromDropdown(Locator locator, String elementDiscriptor, String value) {
        Select select = new Select(webElementBiFunction.apply(locator, elementDiscriptor));
        select.selectByVisibleText(value);
    }

    /**
     * upload file
     *
     * @param locator
     * @param elementDiscriptor
     * @param pathOfFile
     */
    public void uploadFile(Locator locator, String elementDiscriptor, String pathOfFile) {
        webElementBiFunction.apply(locator, elementDiscriptor).sendKeys(pathOfFile);
    }

    public void acceptAlert() {
        getAlert().accept();
    }

    public void dismissAlert() {
        getAlert().dismiss();
    }

    public String getAlertText() {
        return getAlert().getText();
    }

    public void sendTextToAlert(Locator locator, String input) {
        getAlert().sendKeys(input);
    }

    private Alert getAlert() {
        return driver.switchTo().alert();
    }

    /**
     * set frame using name for current driver instance
     * @param name
     */
    public void setFrameUsingName(String name) {
        driver.switchTo().frame(name);
    }

    /**
     * set frame using web element for current driver instance
     * @param element
     */
    public void setFrameUsingWebElement(WebElement element) {
        driver.switchTo().frame(element);
    }

    /**
     * set frame using id for current driver instance
     * @param id
     */
    public void setFrameUsingId(String id) {
        driver.switchTo().frame(id);
    }
}
