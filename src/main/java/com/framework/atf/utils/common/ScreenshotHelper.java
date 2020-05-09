/*
 * Copyright (c) 2020. automation testing framework team.
 */

package com.framework.atf.utils.common;

import com.framework.atf.utils.Profile;
import com.framework.atf.utils.enums.ScreenshotType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiConsumer;

public class ScreenshotHelper {

    WebDriver driver = Profile.getInstance().getDriver();

    BiConsumer<ScreenshotType, String> screenshotSupplier = (screenshotType, testcaseName) -> {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        switch (screenshotType.name()) {
            case "VIEWABLEAREA":
                File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    FileUtils.copyFile(scr, new File("src/test/resources/screenshots/" + testcaseName + "_" + timestamp + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "FULLWEBPAGE":
                Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
                try {

                    ImageIO.write(screenshot.getImage(), "PNG", new File("src/test/resources/screenshots/" + testcaseName + "_" + timestamp + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "PERTICULARWEBELEMENT":
                //TODO: https://www.javacodegeeks.com/2019/07/using-selenium-webdriver-full-page-screenshots.html
                break;
        }

    };

    private void saveScreenshot(ScreenshotType screenshotType, String testcaseName) {
        screenshotSupplier.accept(screenshotType, testcaseName);
    }

    public static void takeScreenshot(ScreenshotType screenshotType, String testcaseName) {
        new ScreenshotHelper().saveScreenshot(screenshotType,testcaseName);
    }
}
