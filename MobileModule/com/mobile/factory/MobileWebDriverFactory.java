/**
 * 
 */
package com.mobile.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

/**
 * A factory for creating MobileWebDriver objects.
 *
 * @author Anil Pandey
 */
public class MobileWebDriverFactory {

	/** The capabilities. */
	private static DesiredCapabilities capabilities = null;
	// private static AndroidDriver<WebElement> androidDriver = null;

	/** The android driver. */
	private static WebDriver androidDriver = null;

	/** The appium service. */
	private static AppiumDriverLocalService appiumService;

	/** The appium url. */
	private static String appiumUrl = null;

	private MobileWebDriverFactory() {
	}

	/**
	 * Creates a new MobileWebDriver object.
	 *
	 * @param deviceName
	 *            the device name
	 * @param platform
	 *            the platform
	 * @param browserType
	 *            the browser type
	 * @return the web driver
	 */
	public static WebDriver createDefaultDriverSession(String deviceName, String platform, String browserType,
			String baseUrl) {

		// String appiumServerUrl = startAppiumServer();
		try {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability(CapabilityType.PLATFORM, platform);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, browserType);
			capabilities.setCapability("clearSystemFiles", true);
			// capabilities.setCapability("clearSystemFiles", true);
			androidDriver = new AndroidDriver<MobileElement>(new URL(appiumUrl), capabilities);

			// Waiting for appium server to start properly
			androidDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			androidDriver.get(baseUrl);

		} catch (MalformedURLException e) {
			System.out.println("Error creating Default Driver Session");
			e.printStackTrace();
		}
		return androidDriver;
	}

	public static WebDriver createNewDriverForApp(String deviceName, String platform, String appPkg,
			String mainActivity) {

		try {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability("appPackage", appPkg);
			// capabilities.setCapability("clearSystemFiles", true);
			capabilities.setCapability("appActivity", mainActivity);
			androidDriver = new AndroidDriver<MobileElement>(new URL(appiumUrl), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Error creating New Android Driver Session");
			e.printStackTrace();
		}

		return androidDriver;
	}

	/**
	 * Creates a new MobileWebDriver object.
	 *
	 * @param deviceName
	 *            the device name
	 * @param platform
	 *            the platform Ex Android, iOS etc
	 * @param apkAbsolutePath
	 *            the apk absolute path "could be Web URL for APK </br>
	 *            or Local APK file"
	 * @param appPkg
	 *            the app pkg
	 * @param appActivity
	 *            the app activity
	 * @return the web driver
	 */
	public static WebDriver createNewDriverForApp(String deviceName, String platform, String platformVersion,
			String apkAbsolutePath, String appPkg, String appActivity) {

		try {
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
			capabilities.setCapability("platformVersion", platformVersion);
			capabilities.setCapability(MobileCapabilityType.APP, apkAbsolutePath);
			capabilities.setCapability("appPackage", appPkg);
			capabilities.setCapability("appActivity", appActivity);
			// capabilities.setCapability("clearSystemFiles", true);
			androidDriver = new AndroidDriver<MobileElement>(new URL(appiumUrl), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Error creating New Android Driver Session");
			e.printStackTrace();
		}
		return androidDriver;
	}

	/**
	 * Start appium server.
	 */
	public static void startAppiumServer() {
		try {
			appiumService = AppiumDriverLocalService.buildDefaultService();
			appiumService.start();
			System.out.println("Appium Server Started");
			appiumUrl = appiumService.getUrl().toString();
		} catch (AppiumServerHasNotBeenStartedLocallyException e) {
			System.out.println("Error Starting Appium Server" + e.getMessage());
		}
	}

	/**
	 * Stop appium server.
	 */
	public static void stopAppiumServer() {
		try {
			if (appiumService.isRunning()) {
				appiumService.stop();
				System.out.println("Appium Server stopped");
			}else {
				System.out.println("Appium Server is already stopped");
			}
			
		} catch (Exception e) {
			System.out.println("Error stopping appium server" + e.getMessage());
		}
	}
}
