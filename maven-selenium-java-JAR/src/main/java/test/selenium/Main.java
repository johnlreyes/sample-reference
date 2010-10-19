package test.selenium;


import org.openqa.selenium.server.SeleniumServer;
import com.thoughtworks.selenium.DefaultSelenium;


public class Main {

	public Main() {
		try {
			SeleniumServer server = new SeleniumServer();
			server.start();


			runSeleniumScripts();


			server.stop();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void runSeleniumScripts() {
		System.out.println("["+getClass().getName()+":runSeleniumScripts] START");
		try {
			DefaultSelenium client = new DefaultSelenium("localhost", 4444, "*iexplore", "http://www.dzone.com/");
			client.start();
			
			client.open("links/index.html");

			for (int i=1; i<7; i++) {
				
				if (client.isElementPresent("//div[@id='globalmenu']/ul/li["+i+"]/a")) {
					String text = client.getText("//div[@id='globalmenu']/ul/li["+i+"]/a");
					System.out.println("["+i+"] text="+text);
				}
			}

			client.stop();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("["+getClass().getName()+":runSeleniumScripts] END");
	}


	public static void main(String[] args) {
		new Main();
		System.out.println("DONE ...");
	}
}
