package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.protocol.commands.DOM;
import com.qeagle.devtools.protocol.commands.Overlay;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.types.page.FrameTree;
import com.qeagle.devtools.protocol.types.page.HandleFileChooserAction;
import com.qeagle.devtools.services.ChromeDevToolsService;
import com.qeagle.devtools.services.ChromeService;
import com.qeagle.devtools.services.types.ChromeTab;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * No Drivers needed
 * 
 */


public class ChangeDomAttribute {

	ChromeLauncher launcher;
	ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() throws InterruptedException {
		// Instantiates a new Chrome launcher
		ChromeLauncher launcher = new ChromeLauncher();

		// Launch the chrome with head with maximized
		ChromeService chromeService = launcher.launch(false);

		// Find the page tab and attach the devtools to the specific tab
		devToolsService = chromeService.createDevToolsService(chromeService.getTabs().get(chromeService.getTabs().size()-1));

		// Get the page and load the URL
		Page page = devToolsService.getPage();
		page.navigate("http://www.leafground.com/pages/Edit.html");		  
		
		Thread.sleep(5000);

	}


	@Test
	public void changeAttributesOfDOM() throws InterruptedException {


		// Get the DOM from the DevTools
		DOM dom = devToolsService.getDOM();

		// Find the element that you like to interact
		Integer nodeId = dom.querySelector(devToolsService.getDOM().getDocument().getNodeId(), "input");
		System.out.println(nodeId);

		// Type something on that element
		dom.setAttributeValue(nodeId, "value", "Babu");

		// Find the element that you like to interact
		List<Integer> nodes = dom.querySelectorAll(devToolsService.getDOM().getDocument().getNodeId(), "input");

		// Clear the existing value on the last textbox
		dom.setAttributeValue(nodes.get(nodes.size()-2), "value", "");
		
		// Get all the attributes
		List<String> attributes = dom.getAttributes(nodes.get(2));
		for (String eachAttribute : attributes) {
			System.out.println(eachAttribute);
		}
		
		
		Thread.sleep(5000);

	}

}
