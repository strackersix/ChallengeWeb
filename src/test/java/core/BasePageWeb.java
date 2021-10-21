package core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageWeb extends DriverFactory {
	
	public static WebDriver driver = inicializaDriverFactory();
	WebDriverWait wait = new WebDriverWait(driver, 5);
		
	public BasePageWeb () {
		
		PageFactory.initElements(driver, this);
	}
	
	public void click (WebElement element) {

		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		}
	}
	
	public void sendKeys (WebElement element, String text) {
		
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(text);
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		}		
	}
	
	public String getText(WebElement element) {
		
		wait.until(ExpectedConditions.visibilityOf(element)).getText();
		return element.getText();
	}
		
	public void waitToBeClickable (WebElement element) {

		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitToBeVisibility (WebElement element) {

		wait.until(ExpectedConditions.visibilityOf(element));
	}
		
	public void alternarAbas(int index){
		
		List<String> abas = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(abas.get(index));
	}
}
