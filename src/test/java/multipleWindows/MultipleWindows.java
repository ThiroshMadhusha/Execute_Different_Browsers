package multipleWindows;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleWindows {

	WebDriver driver;	
	FirefoxOptions firefoxoptions = new FirefoxOptions();
	ChromeOptions chromeoptions = new ChromeOptions();
	EdgeOptions edgeoptions = new EdgeOptions();
	
	@Parameters("browser")
	@BeforeTest
	public void initializeCrossBrowser(@Optional("chrome") String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome is Launch");
			System.out.println();
			
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge is Launch");
			System.out.println();

		}else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox is Launch");
			System.out.println();

		}else {
			System.out.println("Browser Launch Failed...!");
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testApplicationDemo() {
		
		driver.get("https://tutorialsninja.com/demo/");
		
		String title = driver.getTitle();
		System.out.println("Page Title Is : " + title);
		
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL : " + currentURL);
		
		driver.manage().window().maximize();
		
		String value = driver.findElement(By.xpath("//nav[@id='top']")).getText();
		System.out.println("Name is : " + value);
		
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		
		boolean visibleLogo = driver.findElement(By.xpath("//input[@type=\"submit\"]")).isDisplayed();
		System.out.println("Button is Display : " + visibleLogo);
		
		boolean enableEement = driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).isEnabled();
		System.out.println("Radio button Enable : " + enableEement);
		
		enableEement = driver.findElement(By.xpath("//input[@name='firstname']")).isEnabled();
		System.out.println("Text Field Enable : " + enableEement);
		
		boolean selectedElement = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).isSelected();
		System.out.println("Radio button Select : " + selectedElement);
		
		selectedElement = driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).isSelected();
		System.out.println("Radio button Select : " + selectedElement);
		
		driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=common/home");
		
		String tagName = driver.findElement(By.xpath("//a[normalize-space()='Desktops']")).getTagName();
		System.out.println("Tag Name Is : " + tagName);
		
		Dimension d = driver.findElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")).getSize();
		System.out.println("Width : " + d.width);
		System.out.println("Height : " + d.height);
		
		Point p = driver.findElement(By.xpath("//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")).getLocation();
		System.out.println("Width : " + p.x);
		System.out.println("Height : " + p.y);
		
		List<WebElement> element = driver.findElements(By.tagName("a"));
			for(int i=0; i<element.size(); i++) {
				
				//null text elements are remove
				if(element.get(i).getText().length()>0) {
					System.out.println("All Elements : " + element.get(i).getText());

				}
		}
		//browser back
		driver.navigate().back();

	}
	
	

}
