package web.home;

import core.BasePageWeb;

public class PageHome extends BasePageWeb {
	
	public void url(String url) {
		
		driver.manage().window().maximize();
		driver.get(url);
	}
}
