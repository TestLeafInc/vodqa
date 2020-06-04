package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.launch.config.ChromeLauncherConfiguration;
import com.qeagle.devtools.services.ChromeDevToolsService;
import com.qeagle.devtools.services.ChromeService;
import com.qeagle.devtools.services.types.ChromeTab;

import java.util.List;

import org.testng.annotations.Test;

/*
 * 1) No Drivers needed to communicate with browser
 * 2) Faster than the Selenium WebDriver Actions
 * 3) Really your version of chrome does not matter !!
 */
		
		
public class LaunchTest {
	
	@Test
	public void launchChrome() throws InterruptedException {

		// Instantiates a new Chrome launcher
		ChromeLauncher launcher = new ChromeLauncher();
		
		// Launch the chrome (with head) with maximized
		ChromeService chromeService = launcher.launch(false);

		// Find the page tab and attach the devtools to the specific tab
		 ChromeDevToolsService devToolsService = chromeService.createDevToolsService(chromeService.getTabs().get(chromeService.getTabs().size()-1));
		 
		 
		 /*List<ChromeTab> tabs = chromeService.getTabs();
		 for (ChromeTab chromeTab : tabs) {
			System.out.println(chromeTab.getTitle());
		}
*/
	    // Get the page and load the URL
	    devToolsService.getPage().navigate("http://www.leafground.com/");	
	    
	    Thread.sleep(5000);
	    
	    System.out.println("Page loaded");
	    
	    // Close te browser
		launcher.close();

	}

}
