package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.protocol.commands.Network;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.types.network.RequestPattern;
import com.qeagle.devtools.services.ChromeDevToolsService;
import com.qeagle.devtools.services.ChromeService;
import com.qeagle.devtools.services.types.ChromeTab;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


public class BasicAuth {

	
	@BeforeMethod
	public void launch() {

		// Alternate: Use WebDriverManager
		

	}

	@Test
	public void WebDriverShadowDomTest() {
		

		// Launch chrome using WebDriver

	}

	//@Ignore

	@Test(invocationCount=2,threadPoolSize=2)
	public void autoAuthorize() throws InterruptedException {
		ChromeDriver driver;
		ChromeDevToolsService devToolsService;

		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


		// Get the Devtools Service
		devToolsService = DevToolsService.getDevToolsService(driver);

		Network network = devToolsService.getNetwork();

		// Intercept using listener and add the headers to bypass
		network.onRequestIntercepted(
				event -> {

					Map<String, Object> headers = new HashMap<>();
					headers.put("Authorization", "Basic YWRtaW46YWRtaW4=");
					
					network.continueInterceptedRequest(
							event.getInterceptionId(), null, null, null, null, null, headers, null);
				});

		// set and enable
		network.setRequestInterception(Collections.singletonList(new RequestPattern()));
		network.enable();

		// Now, load
		driver.get("https://the-internet.herokuapp.com/basic_auth");

		Thread.sleep(5000);
	}
}
