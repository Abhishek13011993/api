package com.ninza.hrm.api.employeetest;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class NinzaTest {
	@Test
	public void createTest()
	{
		Random ran=new Random();
		int ranNum=ran.nextInt(100);
		String Projectname="International_"+ranNum;
		WebDriver driver =new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8091/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
//		Set<String> allwh = driver.getWindowHandles();
//		for(String wh:allwh)
//		{
//			driver.switchTo().window(wh);
//			if(driver.getTitle().contains("Create Project"))
//			{
//				break;
//			}
//		}
		driver.findElement(By.name("projectName")).sendKeys(Projectname);
		driver.findElement(By.name("createdBy")).sendKeys("Abhi");
	    WebElement dropdown = driver.findElement(By.xpath("//label[text()='Project Status* ']/following-sibling::select"));
	    Select sel=new Select(dropdown);
	    sel.selectByVisibleText("Created");
	    driver.findElement(By.xpath("//input[@value='Add Project']")).click();
	    driver.findElement(By.xpath("//div[@class='table-title']//select[@class='form-control']"));
	    sel.selectByVisibleText("Search by Project Name");
	    driver.findElement(By.xpath("//div[@class='col-sm-6']//input")).click();
	    String ProjectId = driver.findElement(By.xpath("//tbody/tr/td[1]")).getText();
	    System.out.println(ProjectId);
	    
	    
	    
	    
//	  given()
//	  	.contentType(ContentType.JSON)
//	  .when()
//	  	.get("http://49.249.28.218:8091/")
//	  .then()
//	  	.log().all();
	  }
}
