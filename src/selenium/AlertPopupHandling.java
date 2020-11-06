package selenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;


public class AlertPopupHandling {

            public static WebDriver driver;
            public static AlertPopupHandling alertPopupHandling;
            public static WebDriver SetUpDriver (){
            driver =new ChromeDriver();
            return driver;
            }
            @BeforeMethod
            public static void init(){
                alertPopupHandling = PageFactory.initElements(driver,AlertPopupHandling.class);
                SetUpDriver ();

            }
            @AfterMethod
            public static void TearDown(){
                driver.close();
            }
            /*
            Teat case N01:Handling PopUp alert
             */
      @Test
      public static void Test_PopUp() throws InterruptedException {
          driver.get("http://www.popuptest.com/goodpopups.html");
          driver.findElement(By.xpath("//a[contains(text(),'Good PopUp #1')]")).click();
          Set<String> handler = driver.getWindowHandles();
          Iterator<String> handlerIt =handler.iterator();
          String parentWindId =handlerIt.next();
          System.out.println("This is the parent window ID : "+parentWindId);
          String childWindId =handlerIt.next();
          System.out.println("This is the child window ID : "+childWindId);
          Thread.sleep(1000);
          driver.switchTo().window(childWindId);
          System.out.println("This is the child window "+driver.getTitle());
          Thread.sleep(1000);
          driver.close();
          Thread.sleep(1000);
          driver.switchTo().window(parentWindId);
          System.out.println("This is the Parent window "+driver.getTitle());
            }
      @Test
      public static void Test_HandeL_PopUp_Using_Java_Script() throws InterruptedException {
          driver.get("http://demo.guru99.com/test/delete_customer.php");
          System.out.println(driver.getTitle());
          driver.manage().deleteAllCookies();
          driver.findElement(By.cssSelector("input[name='cusid']")).sendKeys("13457", Keys.ENTER);
//          driver.findElement(By.cssSelector("input[name='cusid']")).sendKeys("13457",Keys.TAB);
//          driver.findElement(By.cssSelector("input[name='submit']")).click();
          Thread.sleep(2000);
          Thread.sleep(1000);
          WebDriverWait wait = new WebDriverWait(driver,2);
          wait.until(ExpectedConditions.alertIsPresent());
          Alert alert = driver.switchTo().alert();
          String text =alert.getText();
          System.out.println(text);
          alert.dismiss();
          driver.findElement(By.xpath("//a[contains(text(),'Agile Project')]")).sendKeys(Keys.ENTER);
          }


}
