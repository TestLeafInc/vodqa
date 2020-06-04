package vodqa.demo.examples;

import com.qeagle.devtools.launch.ChromeLauncher;
import com.qeagle.devtools.protocol.commands.DOM;
import com.qeagle.devtools.protocol.commands.Emulation;
import com.qeagle.devtools.protocol.commands.Overlay;
import com.qeagle.devtools.protocol.commands.Page;
import com.qeagle.devtools.protocol.types.page.CaptureScreenshotFormat;
import com.qeagle.devtools.protocol.types.page.FrameTree;
import com.qeagle.devtools.protocol.types.page.HandleFileChooserAction;
import com.qeagle.devtools.protocol.types.page.LayoutMetrics;
import com.qeagle.devtools.protocol.types.page.Viewport;
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

import org.openqa.selenium.chrome.ChromeDriver;
import org.qeagle.devtools.webdriver.DevToolsService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qeagle.devtools.utils.FullScreenshot;

/*
 * No Drivers needed
 * 
 */


public class FullScreenShot {

	private ChromeDriver driver;
	private ChromeDevToolsService devToolsService;

	@BeforeMethod
	public void launch() {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		// Launch chrome using Selenium
		driver = new ChromeDriver();
		driver.get("https://www.amazon.in/ref=cs_503_link/");

		// Get the Devtools Service
		devToolsService = DevToolsService.getDevToolsService(driver);

	}

	@Test
	public void fullScreenSHot() throws InterruptedException {
		captureFullPageScreenshot(
				devToolsService, 
				devToolsService.getPage(),
				"snap.png"
		);

	}
	
	private static void captureFullPageScreenshot(
			ChromeDevToolsService devToolsService, Page page, String outputFilename) {
		final LayoutMetrics layoutMetrics = page.getLayoutMetrics();

		final double width = layoutMetrics.getContentSize().getWidth();
		final double height = layoutMetrics.getContentSize().getHeight();

		final Emulation emulation = devToolsService.getEmulation();
		emulation.setDeviceMetricsOverride(
				Double.valueOf(width).intValue(), Double.valueOf(height).intValue(), 1.0d, Boolean.FALSE);

		Viewport viewport = new Viewport();
		viewport.setScale(1d);

		// You can set offset with X, Y
		viewport.setX(0d);
		viewport.setY(0d);

		// Set a width, height of a page to take screenshot at
		viewport.setWidth(width);
		viewport.setHeight(height);

		dump(
				outputFilename,
				page.captureScreenshot(CaptureScreenshotFormat.PNG, 100, viewport, Boolean.TRUE));
	}

	private static void dump(String fileName, String data) {
		FileOutputStream fileOutputStream = null;
		try {
			File file = new File(fileName);
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(Base64.getDecoder().decode(data));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}
