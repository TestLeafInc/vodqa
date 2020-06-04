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


public class NotifyNodeChanges {

	ChromeLauncher launcher;
	ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() {
		// Instantiates a new Chrome launcher
		ChromeLauncher launcher = new ChromeLauncher();

		// Launch the chrome with head with maximized
		ChromeService chromeService = launcher.launch(false);

		// Find the page tab and attach the devtools to the specific tab
		devToolsService = chromeService.createDevToolsService(chromeService.getTabs().get(chromeService.getTabs().size()-1));
		// Get the page and load the URL
		Page page = devToolsService.getPage();
		page.navigate("http://www.leafground.com/pages/Edit.html");		    

	}


	@Test
	public void trackNodeChanges() throws InterruptedException {


		// Get the DOM from the DevTools
		DOM dom = devToolsService.getDOM();

		// Find the element that you like to interact
		Integer nodeId = dom.querySelector(devToolsService.getDOM().getDocument().getNodeId(), "input");
		System.out.println(nodeId);

		dom.onAttributeModified(
			event -> {
				System.out.println("The Node Id has been modified: "+ event.getNodeId());
				System.out.println("The Attribute has been modified to: "+ event.getName());
				System.out.println("The Value has been modified to: "+ event.getValue());

			});

		List<Integer> nodes = dom.querySelectorAll(devToolsService.getDOM().getDocument().getNodeId(), "input");
		//for (Integer node : nodes) {
			dom.setAttributeValue(nodes.get(0), "value", nodes.get(0) + "- changed");

		//}
		
		Thread.sleep(10000);
	}

}
