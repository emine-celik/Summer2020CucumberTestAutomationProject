package com.vytrack.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {
    private static WebDriverWait wait=new WebDriverWait(Driver.getDriver(),20);
    public static void wait(int second){
        try{ Thread.sleep(second*1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public static void clickWithJs(WebElement element){
        ((JavascriptExecutor)(Driver.getDriver())).executeScript("argument[0].click",element);
    }
    public static void enterText(WebElement elemt,String text){
        wait.until(ExpectedConditions.visibilityOf(elemt));
        elemt.clear();
        elemt.sendKeys(text);
        //this is for entering the text fully
        wait.until(ExpectedConditions.attributeToBe(elemt,"value",text));
        System.out.println("Enter text: "+text);
        /**
         * For instance, selenium webdriver is trying to enter the text:
         * Expected text input  actual text input
         *
         * [java is great] --> [java is g]
         *
         * for some reason, selenium jumps to the next step without finishing text input here
         *
         * to prevent this issue, we need to make it wait until text is completely entered.
         *
         * How to do that?
         *
         * As we know, text is stored in the value attribute. So we need to wait until value attribute
         * of the element will obtain expected text.
         */
    }
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }
}
