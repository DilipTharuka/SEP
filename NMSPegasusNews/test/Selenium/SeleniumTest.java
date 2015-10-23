/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Dilip
 */
public class SeleniumTest {
    
    @Test
    public void display() throws InterruptedException 
    {
        
       WebDriver driver = new FirefoxDriver();
       driver.get("http://localhost:8080/NMSPegasusNews/"); 
       Thread.sleep(5000);
       driver.findElement(By.xpath("/html/body/div[1]/ul/li[5]/div[2]/div[4]/a[2]")).click();
       Thread.sleep(5000);
       driver.findElement(By.xpath("//*[@id=\"newsModal4\"]/div[2]/div/div[1]/button/span")).click();
       Thread.sleep(5000);
       driver.findElement(By.xpath("//*[@id=\"mobile_form\"]/div[1]/input")).sendKeys("0755798148");
       driver.findElement(By.xpath("//*[@id=\"mobile_form\"]/div[1]/span/button")).click();
//       Thread.sleep(5000);
//       driver.findElement(By.xpath("/html/body/div[9]/div[1]/div[1]/button")).click();
//       Thread.sleep(5000);
//       driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[1]/input")).sendKeys("dilini");
//       driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/input")).sendKeys("dilini");
//       driver.findElement(By.xpath("//*[@id=\"login-form\"]/input")).click();
       
    }
}
