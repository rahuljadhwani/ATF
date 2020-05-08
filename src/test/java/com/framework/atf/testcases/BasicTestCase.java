package com.framework.atf.testcases;

import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.Locator;
import com.framework.atf.utils.listeners.DriverListener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

import java.util.List;
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

    BiFunction<Locator, String, List<WebElement>> webElementBiFunctions = (locatorType, locatorArg) -> {
        driver = Profile.getInstance().getDriver();
        List<WebElement> elements;
        switch (locatorType.name()) {
            case "XPATH":
                elements = driver.findElements(By.xpath(locatorArg));
                break;
            case "ID":
                elements = driver.findElements(By.id(locatorArg));
                break;
            case "NAME":
                elements = driver.findElements(By.name(locatorArg));
                break;
            case "TAGNAME":
                elements = driver.findElements(By.tagName(locatorArg));
                break;
            case "CSSSELECTOR":
                elements = driver.findElements(By.cssSelector(locatorArg));
                break;
            case "LINKTEXT":
                elements = driver.findElements(By.linkText(locatorArg));
                break;
            case "PARTIALLINKTEXT":
                elements = driver.findElements(By.partialLinkText(locatorArg));
                break;
            case "CLASSNAME":
                elements = driver.findElements(By.className(locatorArg));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locatorType.name());
        }
        return elements;
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
     * @param elementDescriptor
     * @param textToEnter
     */
    public void enter(Locator locator, String elementDescriptor, String textToEnter) {
        webElementBiFunction.apply(locator, elementDescriptor).sendKeys(textToEnter);
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
     * @param elementDescriptor
     */
    public void clickOnElement(Locator locator, String elementDescriptor) {
        webElementBiFunction.apply(locator, elementDescriptor).click();
    }

    /**
     * Select value from dropdown through visible text
     *
     * @param locator
     * @param elementDescriptor
     * @param value
     */
    public void selectValueFromDropdown(Locator locator, String elementDescriptor, String value) {
        Select select = new Select(webElementBiFunction.apply(locator, elementDescriptor));
        select.selectByVisibleText(value);
    }

    /**
     * upload file
     *
     * @param locator
     * @param elementDescriptor
     * @param pathOfFile
     */
    public void uploadFile(Locator locator, String elementDescriptor, String pathOfFile) {
        webElementBiFunction.apply(locator, elementDescriptor).sendKeys(pathOfFile);
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
    public void switchToFrameUsingName(String name) {
        driver.switchTo().frame(name);
    }

    /**
     * set frame using web element for current driver instance
     * @param locator
     * @param elementDescriptor
     */
    public void switchToFrameUsingWebElement(Locator locator, String elementDescriptor) {
        driver.switchTo().frame(webElementBiFunction.apply(locator, elementDescriptor));
    }

    /**
     * set frame using id for current driver instance
     * @param id
     */
    public void switchToFrameUsingId(String id) {
        driver.switchTo().frame(id);
    }

    /**
     * Move the mouse cursor to specific web element
     * @param locator
     * @param elementDescriptor
     */
    public void moveMouse(Locator locator, String elementDescriptor) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElementBiFunction.apply(locator, elementDescriptor)).build().perform();
    }

    /**
     * Drag from source to target
     * @param sourcelocator
     * @param elementDescriptorSource
     * @param targetLocator
     * @param elementDescriptorTarget
     */
    public void drapAndDrop(Locator sourcelocator, String elementDescriptorSource, Locator targetLocator, String elementDescriptorTarget) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(webElementBiFunction.apply(sourcelocator, elementDescriptorSource)).moveToElement(webElementBiFunction.apply(targetLocator, elementDescriptorTarget))
                .release()
                .build()
                .perform();
    }

    /**
     * returns list of web elements
     * @param locator
     * @param elementDescriptor
     * @return
     */
    public List<WebElement> findElements(Locator locator, String elementDescriptor) {
        return webElementBiFunctions.apply(locator,elementDescriptor);
    }

    /**
     * get to page
     * @param url
     */
    public void get(String url) {
        driver.get(url);
    }

    /**
     * navigate forward
     */
    public void navigateForward() {
        driver.navigate().forward();
    }

    /**
     * navigate back
     */
    public void navigateBack() {
        driver.navigate().back();
    }

    /**
     * navigate to page
     * @param url
     */
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }
}
