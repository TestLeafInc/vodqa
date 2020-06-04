package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.protocol.commands.Emulation;
import com.qeagle.devtools.protocol.commands.LayerTree;
import com.qeagle.devtools.protocol.commands.Network;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.events.layertree.LayerTreeDidChange;
import com.qeagle.devtools.protocol.support.types.EventHandler;
import com.qeagle.devtools.protocol.support.types.EventListener;
import com.qeagle.devtools.protocol.types.layertree.Layer;
import com.qeagle.devtools.services.ChromeDevToolsService;
import com.qeagle.devtools.services.ChromeService;
import com.qeagle.devtools.services.impl.ChromeServiceImpl;
import com.qeagle.devtools.services.types.ChromeTab;
import com.qeagle.devtools.services.types.EventListenerImpl;
import com.qeagle.devtools.utils.LayerChange;

import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.print.attribute.standard.Sides;

import org.omg.Messaging.SyncScopeHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;

/**
 * Waiting for the layers to disappear !!
 *
 * @author Babu
 */
public class EmulateGeoLocation {
	public static void main(String[] args) throws InterruptedException, ExecutionException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		// Create chrome launcher.
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		// Launch chrome using Selenium
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Get the Devtools Service
		ChromeDevToolsService devToolsService = DevToolsService.getDevToolsService(driver);
		
		Emulation emulation = devToolsService.getEmulation();
		emulation.setGeolocationOverride(37.354107, -121.955238,1.00);
		
		driver.get("https://oldnavy.gap.com/stores");
		
		Thread.sleep(10000);
		
		List<WebElement> addresses = driver.findElementsByClassName("address");
		for (WebElement eachAddress : addresses) {
			System.out.println(eachAddress.getText());
		}
		
	

		
	}




}
