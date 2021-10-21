package web.demo;

import org.junit.Test;

import core.Constantes;
import core.DriverFactory;
import web.home.PageHome;

public class TestDemo implements Constantes {
	
	PageHome pageHome = new PageHome();
	PageDemo pageDemo = new PageDemo();
	
	@Test
	public void imprimirRequisitosDesafio() {
		
		pageHome.url(discourse);
		pageDemo.clicarBotaoDemo();
		pageDemo.imprimirInformacoesDesafio();
		DriverFactory.finalizarDriverFactory();
	}

}
