package vodqa.demo.examples;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.qeagle.devtools.protocol.commands.DOM;
import com.qeagle.devtools.protocol.types.dom.Node;
import com.qeagle.devtools.protocol.types.dom.ShadowRootType;
import com.qeagle.devtools.services.ChromeDevToolsService;

public class ShadowElement {

	private ChromeDriver driver;
	private ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() {

		// Alternate: Use WebDriverManager
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// Launch chrome using WebDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.virustotal.com/gui/home/search");

		// Get the Devtools Service
		devToolsService = DevToolsService.getDevToolsService(driver);

	}

	@Ignore
	@Test
	public void WebDriverShadowDomTest() {

		WebElement search = driver.findElementById("searchInput");

	}

	//@Ignore
	@Test
	public void shadowElementsUsingDevTools() throws InterruptedException {

		DOM dom = devToolsService.getDOM();
		dom.enable();
		
		Integer nodeId;

		// Find the element that you like to interact
		dom.requestChildNodes(devToolsService.getDOM().getDocument().getNodeId(),-1,true);
		List<Integer> nodes = dom.querySelectorAll(devToolsService.getDOM().getDocument().getNodeId(), "input");
		
		// Pierce through the document to search for the element
		List<Node> flattenedDocument = dom.getFlattenedDocument(-1, true);
		for (Node node : flattenedDocument) {	
			if(node.getNodeName().equalsIgnoreCase("input")) {
				if(dom.getAttributes(node.getNodeId()).contains("URL, IP address, domain, or file hash")) {
					nodeId = node.getNodeId();
				}			
			}

		}
		
		//dom.setAttributeValue(nodeId, "value", "testing");
		Thread.sleep(15000);
 
	}

}
