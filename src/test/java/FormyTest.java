import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FormyTest {
    WebDriver driver;
    @Test
    public void keyPress()
    {
        driver.get("https://formy-project.herokuapp.com/keypress");
        WebElement name= driver.findElement(By.id("name"));
        name.sendKeys("Lilly Kada");
        driver.findElement(By.id("button")).click();
    }
    @Test
    public void autoComplete()
    {
        driver.get("https://formy-project.herokuapp.com/autocomplete");
        WebElement autoComplete= driver.findElement(By.id("autocomplete"));
        autoComplete.sendKeys("Niemölleralle 12");
       // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofMillis(1000));
        WebElement autoCompleteResult=wait.until(ExpectedConditions.visibilityOfElementLocated((By.className("pac-item"))));
        autoCompleteResult.click();
    }

    @Test
    public void scroll(){
        driver.get ("https://formy-project.herokuapp.com/scroll");
        WebElement scrollPageSignature = driver.findElement(By.id("name"));
        Actions actions = new Actions(driver);
        actions.moveToElement(scrollPageSignature);
        scrollPageSignature.sendKeys("Lilly Kada");
        WebElement date= driver.findElement(By.id("date"));
        date.sendKeys("27/11.2023");
    }

    @Test
    public void switchWindow(){
        driver.get("https://formy-project.herokuapp.com/switch-window");
        WebElement newTabButton= driver.findElement(By.id("new-tab-button"));
        newTabButton.click();
        String originalHandle= driver.getWindowHandle();
        for (String Handle1: driver.getWindowHandles()) {

            driver.switchTo().window(Handle1);
        }
        driver.switchTo().window(originalHandle);
        Assert.assertEquals(originalHandle,driver.getWindowHandle());
        System.out.println("You are in the Switch window page.");
    }

    @Test
    public void javascriptExecutor() {
        driver.get("https://formy-project.herokuapp.com/modal");
        WebElement modalButton = driver.findElement(By.id("modal-button"));
        WebElement closeButton= driver.findElement(By.id("close-button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('modal-button').click();");
        js.executeScript("document.getElementById('close-button').click();");
    }

    @Test
    public void openAlert(){
        driver.get("https://formy-project.herokuapp.com/switch-window");
        WebElement alertButton = driver.findElement(By.id("alert-button"));
        alertButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

    }
    @Test
    public void dragDrop(){
        driver.get("https://formy-project.herokuapp.com/dragdrop");
        WebElement image= driver.findElement(By.id("image"));
        WebElement box= driver.findElement(By.id("box"));
        Actions actions= new Actions(driver);
        actions.dragAndDrop(image,box).build().perform();
    }
    @Test
    public void radioButtons() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/radiobutton");
        WebElement radioButton1= driver.findElement(By.id("radio-button-1"));
        radioButton1.click();
        Thread.sleep(1000);
        WebElement radioButton2= driver.findElement(By.cssSelector("input[value='option2']"));
        radioButton2.click();
        Thread.sleep(1000);
        WebElement radioButton3= driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/input[1]"));
        radioButton3.click();
        Thread.sleep(1000);
    }

    @Test
    public void dropDownMenu(){
        driver.get("https://formy-project.herokuapp.com/dropdown");
        WebElement dropdownMenu= driver.findElement(By.id("dropdownMenuButton"));
        dropdownMenu.click();
        WebElement autoComplete= driver.findElement(By.id("autocomplete"));
        autoComplete.click();
    }
    @Test
    public void checkBox() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/checkbox");
        WebElement checkBox1=driver.findElement(By.id("checkbox-1"));
        checkBox1.click();
        Thread.sleep(1000);
        WebElement checkBox2=driver.findElement(By.cssSelector("input#checkbox-2"));
        checkBox2.click();
        Thread.sleep(1000);
        WebElement checkBox3=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/input[1]"));
        checkBox3.click();
        Thread.sleep(1000);
    }
    @Test
    public void datePicker() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/datepicker");
        WebElement datePicker= driver.findElement(By.cssSelector("input#datepicker"));
        datePicker.sendKeys("09/11/2020");
        datePicker.sendKeys(Keys.RETURN);
        Thread.sleep(1000);
    }
    @Test
    public void fileUpload() throws InterruptedException {
        driver.get("https://formy-project.herokuapp.com/fileupload");
        WebElement fileUploadField=driver.findElement(By.cssSelector("input#file-upload-field"));
        fileUploadField.sendKeys("file-to-upload.png");
        Thread.sleep(1000);
    }

    @BeforeTest
    public void launchPage()
    {
        System.setProperty("webdriver.chrome.driver","/Users/shravankumarkada/Documents/Formy/chromedriver");
        driver= new ChromeDriver();
        driver.get("https://formy-project.herokuapp.com");
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        String expectedTitle= "Formy";
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
