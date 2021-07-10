package carfax.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UsedCarSearch {

    public static void main(String[] args) throws InterruptedException {

        // Navigate  to carfax.com.
        System.setProperty("webdriver.gecko.driver", "/Users/fmirzaev/Documents/Selenium Packages/drivers/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        // Click on Find a Used Car.
        driver.get("http://www.carfax.com/");
        driver.findElement(By.xpath("//a[contains(text(), 'Find a Used Car')]")).click();

        // Verify the page title contains the word “Used Cars”.
        String Actual = driver.getTitle();
        String Expected = "Used Cars for Sale | with Free CARFAX";

        if (Actual.equals(Expected)) {
            System.out.println("Word: \"Used Cars for Sale | with Free CARFAX\" is verified!");
        } else {
            System.out.println("Word: \"Used Cars for Sale | with Free CARFAX\" Page Title Is NOT Verified, Try Again!");
        }

        // Choose “Tesla” for  Make.
        Select allMakes = new Select(driver.findElement(By.name("make")));
        allMakes.selectByIndex(31);

        // Verify that the Select Model dropdown box contains 4 current Tesla models -
        // (Model 3, Model S, Model X, Model Y).
        List<WebElement> currentModels = driver.findElements(By.xpath("//*[@class='current-models']//option"));
        System.out.println(" \"Verifying that the Select Model dropdown box contains " + currentModels.size() + " "
                                                                                       + "current Tesla models\"");
        for (WebElement getAllModels : currentModels) {
            System.out.println(getAllModels.getText());
        }

        // Choose “Model S” for Model.
        Select allModels = new Select(driver.findElement(By.name("model")));
        allModels.selectByIndex(2);

        // Enter the zip code 22182 and click Next.
        driver.findElement(By.name("zip")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify that the page contains the text “Step 2 – Show me cars with”.
        String pageContains = "Step 2 - Show me cars with";
        if ( driver.getPageSource().contains("Step 2 -")){
            System.out.println("Text: \"" + pageContains + "\" is present. ");
        } else {
            System.out.println("Text: \"" + pageContains + "\" is not present. ");
        }

        // Check all 4 checkboxes.
        List <WebElement> selectAllCheckboxes =  driver.findElements(By.xpath("//span[@role='checkbox']"));
        int size = selectAllCheckboxes.size();
        System.out.println("How many check boxes are currently selected?: "+size);
        for(int i = 0; i < size; i++) {

            selectAllCheckboxes.get(i).click();

        }

        // Save the count of results from “Show me X Results” button.
        String saveCountResult = driver.findElement(By.xpath("//span[@class='totalRecordsText']")).getText().split(" ")[0];
        System.out.println("Results count is: " + saveCountResult);

        // Click on “Show me x Results” button.
        driver.findElement(By.xpath("//button[@class='button large primary-green show-me-search-submit']")).click();

        // Verify the results count by getting the actual number of results displayed in the page by getting the count
        // of WebElements that represent each result.
        String resultCount = driver.findElement(By.xpath("//span[@id='totalResultCount']")).getText().split(" ")[0];
        System.out.println("Current Search Result Count: "+resultCount);

        // Verify that each result header contains “Tesla Model S”.
        String resultHeader = "Tesla Model S";
        if ( driver.getPageSource().contains("Tesla Model S")){
            System.out.println("Each result header contains: \"" + resultHeader + "\"");
        } else {
            System.out.println("Result does not contain: \"" + resultHeader);
        }

        // Get the price of each result and save them into a List in the order of their appearance.
        // (You can exclude “Call for price” options).
        List<WebElement> allListedPrices = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
        System.out.println(" \"List of all " + allListedPrices.size() + " "
                + "prices in the order they appear\"");
        for (WebElement getPrices : allListedPrices) {
            System.out.println(getPrices.getText());
        }

        // Choose “Price - High to Low” option from the Sort By menu.
        Select priceHighToLow = new Select(driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")));
        priceHighToLow.selectByIndex(1);

        // Verify that the results are displayed from high to low price.
        String resultHighToLow = "Price - High to Low";
        if ( driver.getPageSource().contains("Price - High")){
            System.out.println("Price Result is displayed from: \"" + resultHighToLow + "\"");
        } else {
            System.out.println("Result does not display: \"" + resultHighToLow);
            Thread.sleep(1000);
        }

        // Choose “Mileage - Low to High” option from Sort By menu.
        Select mileageLowToHigh = new Select(driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")));
        mileageLowToHigh.selectByIndex(4);

        // Verify that the results are displayed from low to high mileage.
        String resultMileageLowToHigh = "Mileage - Low to High";
        if ( driver.getPageSource().contains("Mileage - Low")){
            System.out.println("Mileage Result is displayed from: \"" + resultMileageLowToHigh + "\"");
        } else {
            System.out.println("Result does not display: \"" + resultMileageLowToHigh);
            Thread.sleep(1000);
        }

        // Choose “Year - New to Old” option from Sort By menu.
        Select yearNewToOld = new Select(driver.findElement(By.xpath("//select[@class='srp-header-sort-select srp-header-sort-select-desktop--srp']")));
        yearNewToOld.selectByIndex(5);

        // Verify that the results are displayed from new to old year.
        String resultYearNewToOld = "Year - New to Old ";
        if ( driver.getPageSource().contains("Year - New")){
            System.out.println("Year Result is displayed from: \"" + resultYearNewToOld + "\"");
        } else {
            System.out.println("Result does not display: \"" + resultYearNewToOld);
            Thread.sleep(1000);
        }
    }
}
