package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		String url = "http://localhost:8080/listarJogos";
		driver.get(url);
		
		WebElement nome = driver.findElement(By.name("nome"));
		WebElement descricao = driver.findElement(By.name("descricao"));
		WebElement nota = driver.findElement(By.name("nota"));
		
		nome.sendKeys("Jogo 11");
		descricao.sendKeys("Muito bom");
		nota.sendKeys("10");
		
		WebElement botao = driver.findElement(By.id("btnSubmit"));
		botao.click();
	}
}
