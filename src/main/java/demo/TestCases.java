package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        
        String userDataDirectory = System.getProperty("user.dir") + "/data";
        options.addArguments("--user-data-dir=" + userDataDirectory);


        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    // public  void testCase01(){
    //     System.out.println("Start Test case: testCase01");
    //     driver.get("https://www.google.com");
    //     System.out.println("end Test case: testCase02");
    // }

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }

    //TestCase01: Verify Calendar Home Page
    public  void TestCase01(){
        System.out.println("Start Test case: TestCase01");
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		boolean status = true;
		driver.get(" https://calendar.google.com/");

		if (driver.getCurrentUrl().contains("calendar")){
            logStatus("End TestCase :", "The URL of the Calendar homepage contains 'calendar':",
                    status ? "Pass" : "Fail");
        }
		else{
			logStatus("End TestCase :", "The URL of the Calendar homepage contains 'calendar':",
                    status ? "Pass" : "Fail");
		}
        System.out.println("End Test case: TestCase01");
    }


    public  void TestCase02(){
        System.out.println("Start Test case: TestCase02");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		boolean status = true;
        driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//ul[@role='menu']/li"));
		for(WebElement wb : menu){
			if(wb.getText().contains("Month")){
				wb.click();
				break;
			}
		}
		driver.findElement(By.xpath("//div[@data-period-type='month']//div[@role='grid']")).click();
		driver.findElement(By.id("tabTask")).click();
		driver.findElement(By.xpath("//input[contains(@aria-label,'title')]")).sendKeys("Crio INTV Task Automation");
		driver.findElement(By.xpath("//textarea")).sendKeys("Crio INTV Calendar Task Automation");
        JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div/div/div[2]/span/div/div[1]/div[2]/div[2]/div[4]/button")).click();
        logStatus("End Test Case", "The Calendar switched to month view and a task was created.", status ? "Pass" : "Fail");
        System.out.println("End Test case: TestCase02");
    }



    //TestCase03: Verify the Task Updation
    public  void TestCase03() throws InterruptedException{
        System.out.println("Start Test case: TestCase03");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		boolean status = true;
        driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//ul[@role='menu']/li"));
		for(WebElement wb : menu){
			if(wb.getText().contains("Month")){
				wb.click();
				break;
			}
		}
        
		driver.findElement(By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[3]/div[3]/div/div[4]/div/div/div")).click();
        driver.findElement(By.xpath("//button[@aria-label='Edit task']")).click();
        driver.findElement(By.xpath("//textarea")).clear();
		driver.findElement(By.xpath("//textarea")).sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
		driver.findElement(By.xpath("//*[@id='yDmH0d']/div/div/div[2]/span/div/div[9]/div/button")).click();
        Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[3]/div[3]/div/div[4]/div/div/div")).click();
        String expected = "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
        String displayed = driver.findElement(By.xpath("(//div[contains(@data-eventid,'tasks')]//div[contains(@class,'toUqff ')])[3]")).getText();
        if(displayed.contains(expected)){
            logStatus("End TestCase :", "The task description is successfully updated and displayed",
                    status ? "Pass" : "Fail");
        }
        else{
            status = false;
            logStatus("End TestCase :", "The task description is successfully updated and displayed",
                    status ? "Pass" : "Fail");
        }

        System.out.println("End Test case: TestCase03");
    }
    
    public  void TestCase04() throws InterruptedException{
        System.out.println("Start Test case: TestCase04");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		boolean status = true;
        driver.findElement(By.xpath("(//button[@aria-haspopup='menu'])[2]")).click();
		List<WebElement> menu = driver.findElements(By.xpath("//ul[@role='menu']/li"));
		for(WebElement wb : menu){
			if(wb.getText().contains("Month")){
				wb.click();
				break;
			}
		}
        
		driver.findElement(By.xpath("//*[@id='YPCqFe']/div/div/div/div[2]/div[3]/div[3]/div/div[4]/div/div/div")).click();
        String expected = "Crio INTV Task Automation";
        String displayed = driver.findElement(By.xpath("(//div[contains(@data-eventid,'tasks')]//div[contains(@class,'toUqff')])[1]")).getText();
        if(displayed.contains(expected)){
            logStatus("End TestCase :", "Verified the title of the task",
                    status ? "Pass" : "Fail");
        }
        else{
            status = false;
            logStatus("End TestCase :", "Verified the title of the task",
                    status ? "Pass" : "Fail");
        }

        driver.findElement(By.xpath("//button[@aria-label='Delete task']")).click();
        String expectedText = "Task deleted";
        String output = driver.findElement(By.xpath("//div[@class='YrbPvc']/div")).getText();
        if(output.equals(expectedText)){
            logStatus("End TestCase :", "The task is successfully deleted, and the confirmation message indicates \"Task deleted\"",
                    status ? "Pass" : "Fail");
        }
        else{
            status = false;
            logStatus("End TestCase :", "The task is successfully deleted, and the confirmation message indicates \"Task deleted\"",
                    status ? "Pass" : "Fail");
        }
        System.out.println("End Test case: TestCase04");
    }

}
