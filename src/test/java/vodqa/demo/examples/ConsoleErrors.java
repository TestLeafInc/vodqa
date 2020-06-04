package vodqa.demo.examples;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qeagle.devtools.protocol.commands.Console;
import com.qeagle.devtools.protocol.commands.DOM;
import com.qeagle.devtools.protocol.commands.Log;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.types.console.ConsoleMessage;
import com.qeagle.devtools.protocol.types.console.ConsoleMessageLevel;
import com.qeagle.devtools.protocol.types.console.ConsoleMessageSource;
import com.qeagle.devtools.protocol.types.dom.Node;
import com.qeagle.devtools.protocol.types.dom.ShadowRootType;
import com.qeagle.devtools.protocol.types.log.LogEntry;
import com.qeagle.devtools.services.ChromeDevToolsService;

public class ConsoleErrors {

	private ChromeDriver driver;
	private ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() {

		// Alternate: Use WebDriverManager
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// Launch chrome using WebDriver
		driver = new ChromeDriver();


		// Get the Devtools Service
		devToolsService = DevToolsService.getDevToolsService(driver);

	}

	@Test
	public void getConsoleErrors() throws InterruptedException {


		/*Console console = devToolsService.getConsole();
		console.enable();*/

		Log log = devToolsService.getLog();
		log.enable();

		log.onEntryAdded(
				event -> {
					LogEntry entry = event.getEntry();

					System.out.println(entry.getText());
					System.out.println(entry.getStackTrace());
					System.out.println(entry.getSource().name());
				});

		@SuppressWarnings("deprecation")
		Console console = devToolsService.getConsole();
		console.enable();

		console.onMessageAdded(

				event -> {
					ConsoleMessage message = event.getMessage();

					ConsoleMessageLevel level = message.getLevel();
					System.out.println(level.name());
					System.out.println(message.getText());
					ConsoleMessageSource source = message.getSource();
					System.out.println(source.name());
					System.out.println(message.getUrl());


				}
				);

		

		driver.get("https://devfindgood.goodgrid.com/FindGood.TMS.Web/ws-findgood/findgood/index.html#");

		Thread.sleep(10000000);
	}



}
