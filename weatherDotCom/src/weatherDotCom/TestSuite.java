package weatherDotCom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class TestSuite {
	
	private WebDriver driver;
	Params p = new Params();
	
	
	public void pause(int time) throws Exception{
		Thread.sleep(time);
	}
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void openSite() {
		driver.manage().window().maximize();
		driver.get(p.getEndPoint());
	}
	
	@Test
	public void startSearch(String loc) {
		p.setLocation(loc);
		WebElement input = driver.findElement(By.xpath("//input"));
		input.sendKeys(p.getLocation());
		WebElement dropDown = driver.findElement(By.xpath("//ul[@class=\"results\"]/li[1]//p"));
		dropDown.click();
	}
	
	@Test
	public void daysOfForecast() {
		WebElement numDays = driver.findElement(By.xpath("//nav/ul/li[3]"));
		numDays.click();
	}
	
	@Test
	public void getDataFromTable() {
		//get table's header
		String day = driver.findElement(By.xpath("//thead/tr//th[@id=\"day\"]")).getText();
		String precip = driver.findElement(By.xpath("//thead/tr//th[@id=\"precip\"]")).getText();
		String wind = driver.findElement(By.xpath("//thead/tr//th[@id=\"wind\"]")).getText();
		String humidity = driver.findElement(By.xpath("//thead/tr//th[@id=\"humidity\"]")).getText();
		
		//table drawing elements
		String l = String.join("", Collections.nCopies(56, "-"));
		String c = "|  ";
		 
		//print header
		System.out.println(l);
		System.out.format("%-3s%-10s%-3s%-10s%-3s%-13s%-3s%-10s%-3s\n", c, day, c, precip, c, humidity, c, wind, c);
		System.out.println(l);
		
		//get and print table's body
		for(int i=1; i<6; i++){
			String date = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]/span[@class=\"day-detail clearfix wx-dsxdate\"]")).getText();		
			String dateP = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class=\"precip\"]")).getText();		
			String dateW = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class=\"wind\"]")).getText();		
			String dateH = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[@class=\"humidity\"]")).getText();
			//System.out.println(date + " | " + dateP + " | " + dateW + " | " + dateH );
			System.out.format("%-3s%-10s%-3s%-10s%-3s%-13s%-3s%-10s%-3s\n", c, date, c, dateP, c, dateH, c, dateW, c);
		}
		System.out.println(l);
	}
}
