package web.demo;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.BasePageWeb;

public class PageDemo extends BasePageWeb {
	
	@FindBy(xpath = "//a[text() = 'Demo']")
	private WebElement botaoDemo;
	
	@FindBy(id = "site-text-logo")
	private WebElement demo;
	
	@FindBy(xpath = "//h3[text() = 'There are no more latest topics.']")
	private WebElement finalPagina;
	
	@FindBy(xpath = "//span[@title= 'This topic is closed; it no longer accepts new replies']/../../a")
	private WebElement listaCadeado;
	
	@FindBy(xpath = "//th[text() = 'Views']")
	private WebElement views;
	
	@FindBy(xpath = "(//tr[@class = 'topic-list-item category-movies ember-view'])[1]/td/span/a[@class = 'title raw-link raw-topic-link']")
	private WebElement topicMaisViews;

	@FindBy(xpath = "(//tr[@class = 'topic-list-item category-movies ember-view'])[1]/td/span[@class = 'number']")
	private WebElement qtdeViwes;

	@FindBy(xpath = "//li[@title = 'all topics grouped by category']")
	private WebElement categorias;
	
	@FindBy(xpath = "//li[@title = 'all topics grouped by category']")
	private WebElement category;
	
	
	public void clicarBotaoDemo(){
		
		click(botaoDemo);
		alternarAbas(1);
	}
	
	public void imprimirInformacoesDesafio(){
		
		waitToBeVisibility(demo);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 2000)");
		
		String text = "";
		while (!text.equalsIgnoreCase("There are no more latest topics.")) {
			try {
				text = getText(finalPagina);
				js.executeScript("window.scrollBy(0, 2000)");
			} catch (Exception e) {
				js.executeScript("window.scrollBy(0, 2000)");
			}
		}
	
		ArrayList<String> list = new ArrayList<String>();
		System.out.println("Descrição dos tópicos fechados:" + "\n");
		
		int count = 1;
		boolean cadeado = true;
		while (cadeado != false) {
			try {
				for (int i = 0; i < count; i++) {
					String topicosFechados = driver.findElement(By.xpath("(//span[@title= 'This topic is closed; it no longer accepts new replies']/../../a)["+count+"]")).getText();
					list.add(topicosFechados);
					System.out.println(" - Tópico: "+count+" " + list.get(i));
					count++;
				}
			} catch (Exception e) {
				cadeado = false;
			}
		}
	    
		js.executeScript("window.scrollBy(0, -4000)");
		click(views);
		String topicComMaisViews = getText(topicMaisViews);
		String qtde = getText(qtdeViwes);
		
		System.out.println("\n" + "-------------------- // -------------------" + "\n" + "\n" +
		"Tópico com maior número de visualização: " + "\n" + "\n" + " - " + topicComMaisViews + " |" +
		" Qtde visualizações: " + qtde);		
		
		System.out.println("\n" + "-------------------- // -------------------" + "\n" + "\n" +
		"Quantidade de itens em cada categoria: " + "\n" + "\n");
		
		click(categorias);
		waitToBeVisibility(category);
		js.executeScript("window.scrollBy(0, 3000)");
		
		ArrayList<String> listCategory = new ArrayList<String>();
		ArrayList<String> listQtdeTopics= new ArrayList<String>();
		
		int countCategory = 1;
		int countTopics = 1;
		
		for (int i = 0; i < countCategory; i++) {
			
			String descricaoCategoria = driver.findElement(By.xpath("(//div[@class = 'category-text-title'])["+countCategory+"]")).getText();
			listCategory.add(descricaoCategoria);
			String qtdeTopics = driver.findElement(By.xpath("(//td[@class = 'topics'])["+countTopics+"]")).getText();
			listQtdeTopics.add(qtdeTopics);
			 
			System.out.println(" - Categoria: " + listCategory.get(i) + " Topics: " + qtdeTopics);
			
			countCategory++;
			countTopics++;
			
			if (countCategory == 13 && countTopics == 13) {
				break;
			}
		}
	}
}
