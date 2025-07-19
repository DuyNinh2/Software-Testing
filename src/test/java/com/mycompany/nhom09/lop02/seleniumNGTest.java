/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.mycompany.nhom09.lop02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author duy
 */
public class seleniumNGTest {

    WebDriver driver;
    private static final String CSV_FILE_PATH = "C:\\Users\\hieub\\Downloads\\Nhom09-Lop02\\SeleniumTestResult.csv";

    public static void logTestResultToCSV(String testCaseID, Boolean testResult) {
        String resultMessage = testResult ? "PASS" : "FAILED";
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            writer.append(testCaseID)
                    .append(',')
                    .append(resultMessage)
                    .append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public seleniumNGTest() {
    }

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            writer.append("Test case ID,Result\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void checkUrl() throws InterruptedException {
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(500);
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void seleniumUI01() {
        try {
            WebElement logo = driver.findElement(By.id("gh-logo"));
            assertTrue(logo.isDisplayed());
            logTestResultToCSV("seleniumUI01", true);
        } catch (Exception e) {
            logTestResultToCSV("seleniumUI01", false);
            fail("UI test for logo failed: " + e.getMessage());
        }
    }

    @Test
    public void seleniumUI02() {
        WebElement btnSearch = driver.findElement(By.xpath("//*[@id=\"gh-btn\"]"));
        String actualValue = btnSearch.getCssValue("background-color");
        String expectedValue = "rgba(54, 101, 243, 1)";
        logTestResultToCSV("seleniumUI02", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumUI03() {
        WebElement categories = driver.findElement(By.xpath("//*[@id=\"vl-flyout-nav\"]/ul"));
        String actualValue = categories.getCssValue("display");
        String expectedValue = "flex";
        logTestResultToCSV("seleniumUI03", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumUI04() throws InterruptedException {
        WebElement elementToHover = driver.findElement(By.xpath("//*[@id=\"gh-eb-My\"]/div/a[1]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elementToHover).perform();
        Thread.sleep(3000);
        WebElement myEbay = driver.findElement(By.xpath("//*[@id=\"gh-eb-My-o\"]"));
        String actualValue = myEbay.getCssValue("display");
        String expectedValue = "block";
        logTestResultToCSV("seleniumUI04", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumUI05() {
        WebElement footer = driver.findElement(By.xpath("//*[@id=\"glbfooter\"]"));
        String actualValue = footer.getCssValue("background-color");
        String expectedValue = "rgba(54, 101, 243, 1)";
        logTestResultToCSV("seleniumUI05", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumUI06() {
        WebElement search = driver.findElement(By.xpath("//*[@id=\"gh-ac\"]"));
        String actualValue = search.getAttribute("placeholder");
        String expectedValue = "Search for anything";
        logTestResultToCSV("seleniumUI06", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumUI07() {
        String pageTitle = driver.getTitle();
        boolean result = pageTitle.equals("Electronics, Cars, Fashion, Collectibles & More | eBay");
        logTestResultToCSV("seleniumUI07", result);
        assertTrue(result, "Tiêu đề của trang đã được hiện thị sai.");
    }

    @Test
    public void seleniumUI08() {
        try {
            WebElement defaultSite = driver.findElement(By.id("gf-fbtn"));
            String actualDefaultSiteCountry = defaultSite.getText();
            boolean result = actualDefaultSiteCountry.equals("United States");
            logTestResultToCSV("seleniumUI08", result);
            assertTrue(result, "Quốc gia mặc định sai. Quốc gia mặc định thực tế: " + actualDefaultSiteCountry);
        } catch (Exception e) {
            logTestResultToCSV("seleniumUI08", false);
            fail("Exception occurred in seleniumUI02: " + e.getMessage());
        }
    }

    @Test
    public void seleniumUI09() {
        try {
            WebElement shopByCategoryButton = driver.findElement(By.id("gh-shop-a"));
            shopByCategoryButton.click();

            Thread.sleep(2000);

            WebElement categoryDropdown = driver.findElement(By.id("gh-sbc-o"));

            boolean result = categoryDropdown.isDisplayed();
            logTestResultToCSV("seleniumUI09", result);
            assertTrue(result, "Dropdown \"Shop by category\" không hiển thị sau khi nhấp chuột.");
        } catch (Exception e) {
            logTestResultToCSV("seleniumUI09", false);
            fail("Exception occurred in seleniumUI04: " + e.getMessage());
        }
    }

    @Test
    public void seleniumUI10() {
        try {
            WebElement bannerTitle = driver.findElement(By.xpath("//div[@aria-label='Discover a kaleidoscope of cards']//span[text()='Discover a kaleidoscope of cards']"));
            String actualTitle = bannerTitle.getText();
            String expectedTitle = "cards";

            boolean result = actualTitle.equals(expectedTitle);

            logTestResultToCSV("seleniumUI10", result);

            assertTrue(result, "Tiêu đề banner không khớp với tiêu đề mong đợi. Tiêu đề thực tế: " + actualTitle);
        } catch (Exception e) {
            logTestResultToCSV("seleniumUI10", false);
            fail("Exception occurred in testBannerTitle: " + e.getMessage());
        }
    }

    @Test
    public void seleniumUI11() {
        try {
            WebElement bannerTitle = driver.findElement(By.xpath("//div[@aria-label='Discover a kaleidoscope of cards']//span[text()='Discover a kaleidoscope of cards']"));
            String actualFontSize = bannerTitle.getCssValue("font-size");
            String expectedFontSize = "32px"; // Cỡ chữ mong muốn
            boolean fontSizeResult = actualFontSize.equals(expectedFontSize);
            logTestResultToCSV("seleniumUI11", fontSizeResult);
            assertTrue(fontSizeResult, "Kích thước phông chữ của tiêu đề banner không khớp với kích thước phông chữ mong đợi. Kích thước thực tế: " + actualFontSize);
        } catch (Exception e) {
            logTestResultToCSV("seleniumUI11", false);
            fail("Exception occurred in testBannerFontSize: " + e.getMessage());
        }
    }

    @Test
    public void seleniumUI12() {
        WebElement carouselList = driver.findElement(By.xpath("//*[@id=\"s0-1-0-53-1-2-5-14-0[0]-2-@match-media-0-@ebay-carousel-list\"]"));
        int actResult = carouselList.findElements(By.xpath(".//li[@class='carousel__snap-point vl-carousel__item']")).size();
        int expResult = 4;
        boolean testResult = actResult == expResult;
        logTestResultToCSV("seleniumUI12", testResult);
        assertTrue(testResult, "Carousel phải có chính xác 4 mục với class 'carousel__snap-point vl-carousel__item'.");
    }

    @Test
    public void seleniumUI13() {
        WebElement carouselList = driver.findElement(By.id("s0-1-0-53-1-2-5-14-0[2]-10-@match-media-0-@ebay-carousel-list"));
        List<WebElement> popularCategories = carouselList.findElements(By.cssSelector("li.vl-popular-destinations-evo__element"));
        List<String> actualCategoryNames = new ArrayList<>();
        for (WebElement category : popularCategories) {
            WebElement nameElement = category.findElement(By.cssSelector("h3.vl-popular-destinations-evo__name"));
            actualCategoryNames.add(nameElement.getText().trim());
        }
        List<String> expectedCategoryNames = Arrays.asList("Collectible cards up to 15% off!", "Sneakers", "P&A", "Refurbished", "Trading cards", "Pre-loved Luxury", "Toys");
        boolean namesMatch = actualCategoryNames.equals(expectedCategoryNames);
        logTestResultToCSV("seleniumUI13", namesMatch);
        assertTrue(namesMatch, "Tên các danh mục phổ biến không khớp với các giá trị mong đợi.");
    }

    @Test
    public void seleniumUI14() {
        WebElement divElement = driver.findElement(By.xpath("//*[@id='gf-BIG']"));
        WebElement tableElement = divElement.findElement(By.xpath("//*[@id=\"gf-BIG\"]/table"));
        assertNotNull(tableElement, "Table should be present within the div with id 'gf-BIG'");
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        int rowCount = rows.size();
        int columnCount = rows.get(0).findElements(By.tagName("td")).size();
        int expectedRowCount = 1;
        int expectedColumnCount = 5;
        boolean rowsMatch = rowCount == expectedRowCount;
        boolean columnsMatch = columnCount == expectedColumnCount;
        if (rowsMatch && columnsMatch) {
            logTestResultToCSV("seleniumUI14", rowsMatch);
        }
        assertTrue(rowsMatch, "Bảng phải có chính xác " + expectedRowCount + " hàng.");
        assertTrue(columnsMatch, "Bảng phải có chính xác " + expectedColumnCount + " cột trong mỗi hàng.");
    }

    @Test
    public void seleniumUI15() {
        WebElement footerElement = driver.findElement(By.id("vlGlobalFooter"));
        String heightValue = footerElement.getCssValue("height");
        int actualHeight = Integer.parseInt(heightValue.replace("px", ""));
        int expectedHeight = 450;
        boolean testResult = actualHeight == expectedHeight;
        logTestResultToCSV("seleniumUI15", testResult);
        assertTrue(testResult, "Chiều cao của footer phải chính xác là " + expectedHeight + "px.");
    }

    @Test
    public void seleniumUI16() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"s0-1-0-53-1-2-5-14-0[2]-10-@match-media-0-@ebay-carousel-list\"]/li[2]/a"));
        link.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("bn_7000259435"));
        String currentUrl = driver.getCurrentUrl();
        boolean testResult = currentUrl.contains("bn_7000259435");
        assertTrue(testResult, "URL phải chứa 'bn_7000259435' sau khi nhấp vào liên kết.");
        logTestResultToCSV("seleniumUI16", testResult);
    }

    @Test
    public void seleniumFC01() {
        driver.findElement(By.xpath("//*[@id=\"gh-p-2\"]/a")).click();
        String actualValue = driver.getCurrentUrl();
        String expectedValue = "https://www.ebay.com/sl/sell";
        logTestResultToCSV("seleniumFC01", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumFC02() {
        driver.findElement(By.xpath("//*[@id=\"gh-ac\"]")).sendKeys("card" + Keys.ENTER);
        WebElement result = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[1]/div[1]/div[1]/h1/span[2]"));
        String actualValue = result.getText();
        String expectedValue = "card";
        logTestResultToCSV("seleniumFC02", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumFC03() throws InterruptedException {
        WebElement nextButton = driver.findElement(By.xpath("//*[@id=\"s0-1-0-53-1-2-5-14-0[0]-2-@match-media-0-@ebay-carousel-next\"]"));
        WebElement result = driver.findElement(By.xpath("//*[@id=\"s0-1-0-53-1-2-5-14-0[0]-2-@match-media-0-@ebay-carousel-list\"]"));
        String initialState = result.getAttribute("innerHTML");
        nextButton.click();
        String newState = result.getAttribute("innerHTML");
        assertNotEquals(initialState, newState);
        logTestResultToCSV("seleniumFC03", !initialState.equals(newState));
    }

    @Test
    public void seleniumFC04() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"gh-ug\"]/a")).click();
        Thread.sleep(15000); //Thời gian verify người dùng
        driver.findElement(By.xpath("//*[@id=\"signin-continue-btn\"]")).click();
        WebElement result = driver.findElement(By.xpath("//*[@id=\"signin-error-msg\"]"));
        String actualValue = result.getText();
        String expectedValue = "Oops, that's not a match.";
        logTestResultToCSV("seleniumFC04", actualValue.equals(expectedValue));
        assertEquals(actualValue, expectedValue);
    }

    @Test
    public void seleniumFC05() {
        try {
            WebElement usaButton = driver.findElement(By.id("gf-fbtn"));
            usaButton.click();
            Thread.sleep(2000);  // Đợi menu hiển thị
            WebElement australiaLink = driver.findElement(By.linkText("Australia"));
            australiaLink.click();
            Thread.sleep(2000);  // Đợi trang tải lại
            String currentUrl = driver.getCurrentUrl();
            boolean result = currentUrl.equals("https://www.ebay.com.au/");
            logTestResultToCSV("seleniumFC05", result);
            assertTrue(result, "Điều hướng đến eBay Australia không thành công.");
        } catch (Exception e) {
            logTestResultToCSV("seleniumFC05", false);
            fail("Exception occurred in seleniumFC01: " + e.getMessage());
        }
    }

    @Test
    public void seleniumFC06() {
        try {
            WebElement shopByCategoryButton = driver.findElement(By.id("gh-shop-a"));
            shopByCategoryButton.click();
            Thread.sleep(2000);
            WebElement artCategory = driver.findElement(By.linkText("Art"));
            artCategory.click();
            Thread.sleep(2000);
            WebElement mainContent = driver.findElement(By.xpath("/html/body/div[2]/div[2]/section[1]/h1"));
            String mainContentText = mainContent.getText();
            boolean isTitleCorrect = mainContentText.equals("Art");
            boolean result = isTitleCorrect;
            logTestResultToCSV("seleniumFC06", result);
            assertTrue(result, "Tiêu đề của content không chính xác. Tiêu đề thực tế: " + mainContentText);
        } catch (Exception e) {
            logTestResultToCSV("seleniumFC06", false);
            fail("Exception occurred in seleniumFC02: " + e.getMessage());
        }
    }

    @Test
    public void seleniumFC07() {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);
            WebElement scrollToTopButton = driver.findElement(By.xpath("//*[@id=\"gh-bt\"]"));
            scrollToTopButton.click();
            Thread.sleep(2000);
            Long scrollPosition = (Long) ((JavascriptExecutor) driver).executeScript("return window.scrollY;");
            boolean functionResult = (scrollPosition == 0);
            logTestResultToCSV("seleniumFC07", functionResult);
            assertTrue(functionResult, "Nút \"Scroll to Top\" không hoạt động đúng với mong đợi.");
        } catch (Exception e) {
            logTestResultToCSV("seleniumFC07", false);
            fail("Exception occurred in testScrollToTopButtonFunction: " + e.getMessage());
        }
    }


    @Test
    public void seleniumFC08() {
        try {
            WebElement advancedSearchLink = driver.findElement(By.id("gh-as-a"));
            advancedSearchLink.click();
            Thread.sleep(2000);
            WebElement findStoresLink = driver.findElement(By.cssSelector(".fake-menu__item[href='/sch/ebayadvsearch?_sofindtype=7']"));
            findStoresLink.click();
            Thread.sleep(2000);
            WebElement storeSearchBox = driver.findElement(By.xpath("//*[@id=\"s0-1-17-10[0]-store_search\"]"));
            storeSearchBox.clear();
            storeSearchBox.sendKeys("El");
            Thread.sleep(2000);
            WebElement searchButton = driver.findElement(By.cssSelector(".adv-form__actions .btn.btn--primary"));
            searchButton.click();
            Thread.sleep(2000);
            List<WebElement> items = driver.findElements(By.cssSelector(".sns-item__title a"));

            boolean functionResult = true;
            for (int i = 0; i < Math.min(5, items.size()); i++) {
                String title = items.get(i).getText();
                if (!title.toLowerCase().contains("el".toLowerCase())) { // Kiểm tra không phân biệt chữ hoa/thường
                    functionResult = false; // Đánh dấu thất bại nếu có mục nào không chứa "El"
                }
            }
            logTestResultToCSV("seleniumFC08", functionResult);
            assertTrue(functionResult, "Không phải tất cả 5 mục hàng đầu đều chứa 'El' trong tiêu đề của chúng.");

        } catch (Exception e) {
            logTestResultToCSV("seleniumFC08", false);
            fail("Exception occurred in seleniumFC04: " + e.getMessage());
        }
    }

    @Test
    public void seleniumFC09() {
        WebElement searchInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gh-ac\"]")));
        searchInput.sendKeys("Pokemon Card");
        searchInput.submit();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='srp-river-results']/ul")));
        WebElement resultsContainer = driver.findElement(By.xpath("//*[@id='srp-river-results']/ul"));
        int itemCount = resultsContainer.findElements(By.tagName("li")).size();
        boolean testResult = itemCount > 0;
        assertTrue(testResult, "There should be at least one result for 'Pokemon Card'.");
        logTestResultToCSV("seleniumFC09", testResult);
    }

    @Test
    public void seleniumFC10() {
        WebElement searchInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='gh-ac']")));
        searchInput.sendKeys("115scđ");
        searchInput.submit();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='srp-river-results']/ul")));
        WebElement resultMessage = driver.findElement(By.xpath("//*[@id='srp-river-results']/ul/li[2]/div/div[1]/h3"));
        String actResultText = resultMessage.getText();
        String expResultText = "No exact matches found";
        boolean testResult = expResultText.equals(actResultText);
        logTestResultToCSV("seleniumFC10", testResult);
        assertEquals(expResultText, actResultText);
    }

    @Test
    public void seleniumFC11() throws InterruptedException {
        WebElement advSearch = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gh-as-a\"]")));
        advSearch.click();
        WebElement inputKey = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"_nkw\"]")));
        inputKey.sendKeys("Van Gogh Museum AiGrade");
        WebElement soldItemCheckBox = driver.findElement(By.xpath("//*[@id=\"s0-1-17-5[1]-[2]-LH_Sold\"]"));
        if (!soldItemCheckBox.isSelected()) {
            soldItemCheckBox.click();  // Click vào checkbox để chọn
        }
        WebElement submitBtn = driver.findElement(By.xpath("/html/body/div[2]/div/main/form/div[2]/button"));
        submitBtn.click();
        Thread.sleep(5000);
        WebElement resultMessage = driver.findElement(By.xpath("//*[@id='srp-river-results']/ul/li[2]/div/div[1]/h3"));
        String actResultText = resultMessage.getText();
        String expResultText = "No exact matches found";
        boolean testResult = expResultText.equals(actResultText);
        logTestResultToCSV("seleniumFC11", testResult);
        assertEquals(expResultText, actResultText);
    }
}
