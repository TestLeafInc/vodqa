package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.protocol.commands.Animation;
import com.qeagle.devtools.protocol.commands.BackgroundService;
import com.qeagle.devtools.protocol.commands.Browser;
import com.qeagle.devtools.protocol.commands.DOM;
import com.qeagle.devtools.protocol.commands.Emulation;
import com.qeagle.devtools.protocol.commands.Inspector;
import com.qeagle.devtools.protocol.commands.Overlay;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.commands.Performance;
import com.qeagle.devtools.protocol.types.page.CaptureScreenshotFormat;
import com.qeagle.devtools.protocol.types.page.FrameTree;
import com.qeagle.devtools.protocol.types.page.HandleFileChooserAction;
import com.qeagle.devtools.protocol.types.page.LayoutMetrics;
import com.qeagle.devtools.protocol.types.page.Viewport;
import com.qeagle.devtools.protocol.types.performance.Metric;
import com.qeagle.devtools.services.ChromeDevToolsService;
import com.qeagle.devtools.services.ChromeService;
import com.qeagle.devtools.services.types.ChromeTab;
import com.qeagle.devtools.utils.HighlightNode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qeagle.devtools.utils.FullScreenshot;

/*
 * No Drivers needed
 * 
 */


public class SeleniumChromeExtension {

	private ChromeDriver driver;
	private ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() {
		
		// Alternate: Use WebDriverManager
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true");

		// Launch chrome using WebDriver
		driver = new ChromeDriver();


	}

	@Test
	public void chromeExtension() throws InterruptedException {
		
		driver.get("http://leaftaps.com/opentaps");
		
		System.out.println(driver.getTitle());
		
		WebElement userName = driver.findElementById("username");
		userName.sendKeys("babu");

	
	}
	
	

}
