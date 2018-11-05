package com.corevalue.test;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;

@Slf4j
public abstract class AbstractAfterMethod {
    private final VoidConsumer DEFAULT_EXIT_LOGIC = () -> {};

    @Getter @Setter
    private VoidConsumer exitLogic = DEFAULT_EXIT_LOGIC;

    protected abstract WebDriver driver();

    @AfterMethod(alwaysRun = true)
    public void trigger(ITestResult result) {
        if(ITestResult.FAILURE == result.getStatus()) {
            screenShot(driver(), result.getName());
        }

        if (getExitLogic() != DEFAULT_EXIT_LOGIC) {
            getExitLogic().run();
            setExitLogic(DEFAULT_EXIT_LOGIC);
        }
    }

    private void screenShot(WebDriver driver, String name) {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File src = ss.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(src, new File(String.format("./screenshots/%s.png", name)));
        } catch (IOException e) {
            log.error("Screenshot failed: " + e.getMessage());
        }
    }

    @FunctionalInterface
    public interface VoidConsumer {
        void run();
    }
}
