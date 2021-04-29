package Keywords;

import Datos.DataExcel;
import Util.MetodosFeature;
import Util.MetodosVarios;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class KeywordsWeb extends DataExcel {

	private WebDriver driver;
	private String carpetaEvidencia;

	private MetodosVarios metodos = new MetodosVarios();
	private static final Logger logger = LogManager.getLogger(KeywordsWeb.class);

	public KeywordsWeb(WebDriver driver) {

		this.driver = driver;

	}


	public WebElement findElement(By locator)
	{
		return driver.findElement(locator);
	}

	public WebElement findElement(String varLocator)
	{
		return driver.findElement(locator(varLocator));
	}

	public void switchToWindows()
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> switchToWindows",clase,metodo);

		try {
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newTab.get(1));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en switchToWindows",false);
		}

	}


	public List<WebElement> findElements(String  varLocator)
	{
		List<WebElement> getText=null;
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> findElements",clase,metodo);

		try {
			getText = driver.findElements(locator(varLocator));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

		return getText;
	}

	public List<WebElement> findElementsList(List<WebElement> locatorLis, int posicion, By locator)
	{
		return locatorLis.get(posicion).findElements(locator);
	}

	public List<WebElement> subListIndex(List<WebElement> locatorLis, int posicion)
	{
		return locatorLis.subList(posicion,posicion);
	}


	public void findElementsListClic(List<WebElement> locatorLis, int posicion, String varLocator)
	{
		locatorLis.get(posicion).findElement(locator(varLocator)).click();
	}


	public String findElement_getText(List<WebElement> locatorLis, int posicion)
	{
		System.out.println(locatorLis.get(posicion).toString());
		System.out.println("locatorLis.get(posicion).getText(); -> "+locatorLis.get(posicion).getText());
		return locatorLis.get(posicion).getText();
	}

	public String findElement_getText(String varLocator)
	{
		String getText="";
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();

		logger.info("Clase -> {} , -> Metodo {} y accion -> findElement_getText",clase,metodo);

		try {
			getText = driver.findElement(locator(varLocator)).getText();
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

		return getText;
	}


	public void switchToAlert()
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> switchToAlert",clase,metodo);

		try {
			Alert alert = driver.switchTo().alert();
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en switchToAlert",false);
		}
	}

	public void clicJavaScript(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> clicJavaScript",clase,metodo);

		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", findElement(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void typeJavaScript(String nameInput, String varLocator)
	{
		waitLoaderClickable(varLocator,20);
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> clicJavaScript",clase,metodo);

		try {
			String inputText = retornarValor(nameInput, MetodosFeature.getScenario());
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].setAttribute('value', '"+inputText+"')", findElement(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void tabChange(){
		//System.out.println(driver.getContextHandles());
		//System.out.println(driver.getWindowHandle());
		String parent = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		//System.out.println(newTab.size());
		newTab.remove(parent);
		driver.switchTo().window(newTab.get(0));
	}


	public void actionKeysALTSHIFT(String valor)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> actionKeysALTSHIFT",clase,metodo);

		try {
			String selectAll = Keys.chord(Keys.ALT, Keys.SHIFT,valor);
			driver.findElement(By.id("TabBar:AdminTab")).sendKeys(selectAll);
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en actionKeysALTSHIFT",false);
		}

	}

	public void actionEnter(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> actionEnter",clase,metodo);

		try {
			driver.findElement(locator(varLocator)).sendKeys(Keys.ENTER);
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void scroll(int x_pixels, int y_pixels)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> scroll",clase,metodo);

		try {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("window.scrollBy("+x_pixels+","+y_pixels+")");
		}catch (Exception e)
		{
			logger.error(e.getMessage());
		}



	}

	public void scrollIntoViewCenter(By locator)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView({block: \"center\"});", findElement(locator));

	}

	public void clear(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> clear",clase,metodo);

		try {
			driver.findElement(locator(varLocator)).clear();
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void click(String varLocator)
	{
		waitLoaderClickable(varLocator,20);
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> click",clase,metodo);

		try {
			driver.findElement(locator(varLocator)).click();
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void type_fraccionacadena(String nameInput, By locator, String CP)
	{
		String inputText = retornarValor(nameInput,CP);

		String cadenaUsuario[]  = metodos.FraccionarCadena(inputText);
		// WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		int contador1 = 0;
		while(contador1 < cadenaUsuario.length)
		{
			driver.findElement(locator).sendKeys(cadenaUsuario[contador1]);
			//elem.sendKeys(cadenaUsuario[contador1]);
			contador1++;
		}


	}

	public void type(String nameInput, String stringlocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> type",clase,metodo);

		try {
			String inputText = retornarValor(nameInput, MetodosFeature.getScenario());
			driver.findElement(locator(stringlocator)).sendKeys(inputText);
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+stringlocator,false);
		}

	}

	/*public void type(By locator, String nameInput)
	{
		driver.findElement(locator).sendKeys(nameInput);
	}*/


	public By locator(String varLocator)
	{
		By byLocator;
		String[] part = varLocator.split(":",2);
		String valor = part[0];
		int tipo = (valor.equals("id"))?1:(valor.equals("name"))?2:(valor.equals("className"))?3:(valor.equals("tagName"))?4:(valor.equals("linkText"))?5:(valor.equals("partialLinkText"))?6:(valor.equals("cssSelector"))?7:(valor.equals("xpath"))?8:0;

		switch (tipo)
		{
			case 1: byLocator=By.id(part[1]);break;
			case 2: byLocator=By.name(part[1]);break;
			case 3: byLocator=By.className(part[1]);break;
			case 4: byLocator=By.tagName(part[1]);break;
			case 5: byLocator=By.linkText(part[1]);break;
			case 6: byLocator=By.partialLinkText(part[1]);break;
			case 7: byLocator=By.cssSelector(part[1]);break;
			case 8: byLocator=By.xpath(part[1]);break;
			default: byLocator=By.id(part[1]);break;
		}

		return byLocator;
	}

	public void changeFrame(String varLocator, int defaultContent)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> changeFrame",clase,metodo);

		try {
			switch(defaultContent)
			{
				case 0:  driver.switchTo().frame(driver.findElement(locator(varLocator))); break;
				case 1:  driver.switchTo().defaultContent(); break;
			}
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator changeFrame",false);
		}


	}


	public Boolean isDisplayed(String varLocator)
	{
		Boolean isDisplayed = false;
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> isDisplayed",clase,metodo);

		try {
			isDisplayed = driver.findElement(locator(varLocator)).isDisplayed();
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}return isDisplayed;
	}

	public Boolean existsLocator(String  varLocator)
	{
		Boolean sizeLocator = false;
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , Metodo -> {} y accion -> existsLocator",clase,metodo);

		try {
			 sizeLocator = driver.findElements(locator(varLocator)).size() > 0;
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

		return sizeLocator;
	}

	public Boolean existsLocator(String  varLocator, int size)
	{
		Boolean sizeLocator = false;
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , Metodo -> {} y accion -> existsLocator",clase,metodo);

		try {
			sizeLocator = driver.findElements(locator(varLocator)).size() > size;
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

		return sizeLocator;
	}


	public void selectCombo(String nameInput, String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> selectCombo",clase,metodo);

		try {
			String inputText = retornarValor(nameInput,MetodosFeature.getScenario());
			Select combo =new Select(driver.findElement(locator(varLocator)));
			combo.selectByVisibleText(inputText);
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void selectCombo(String texto, String varLocator,Boolean notNameInput)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> selectCombo",clase,metodo);

		try {
		Select combo =new Select(driver.findElement(locator(varLocator)));
		combo.selectByVisibleText(texto);
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void alertConfirm(String mensaje, String boton)
	{
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		if(mensaje.equals(text)) {
			if (boton.equals("ok"))
				alert.accept();
			else if (boton.equals("cancel"))
				alert.dismiss();
		}
		else
		{
			System.out.println("EL MENSAJE DEL ALERT NO COINCIDE");
		}

	}

	public void alertConfirm(String boton)
	{
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
			if (boton.equals("ok"))
				alert.accept();
			else if (boton.equals("cancel"))
				alert.dismiss();
	}



	/*public String getcontextoWebview(String ContexWebView) throws Exception
	{
		String CPBase = CP.replace("@", "");
		String subcarpeta = retornarValor("MODULO","@"+CPBase);
		//System.out.println("CPBASE1->"+CPBase);

		String rutatemp = CPBase+"_"+obtenerFechaHora();
		//carpetaEvidencia = rutatemp.replace(".", "-");
		carpetaEvidencia = subcarpeta+"\\"+rutatemp;
		return carpetaEvidencia;
	}*/



	public static void takeSnapShot(WebDriver webdriver, String fileWithPath){

		try {
			//Convert web driver object to TakeScreenshot

			TakesScreenshot scrShot =((TakesScreenshot)webdriver);

			//Call getScreenshotAs method to create image file

			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination

			File DestFile=new File(fileWithPath);

			//Copy file at destination


			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void screenShot()
	{
		String NameCarpeta = MetodosVarios.getCarpeta();
		String fileWithPath = metodos.rutaEvidencia()+NameCarpeta+"\\"+metodos.nombreImagen();

		takeSnapShot(driver, fileWithPath);

	}


	public void waitLoaderInvisible(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> waitLoaderInvisible",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void waitLoaderInvisible(String varLocator,int timeOut)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> waitLoaderInvisible",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void waitLoaderVisible(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> type",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void waitLoaderVisible(String varLocator, int timeOut)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> waitLoaderVisible",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator(varLocator)));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}
	}

	public void waitLoaderClickable(String varLocator, int timeOut)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> waitLoaderClickable",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(findElement(locator(varLocator))));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void waitLoaderClickable(String varLocator)
	{
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String clase = elements[2].getClassName();
		String metodo = elements[2].getMethodName();
		logger.info("Clase -> {} , -> Metodo {} y accion -> waitLoaderClickable",clase,metodo);

		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(findElement(locator(varLocator))));
		}catch (Exception e)
		{
			screenShot();
			logger.error(e.getMessage());
			assertTrue("Error en Locator "+varLocator,false);
		}

	}

	public void zoom75()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='75%'");
	}

	public String changeventana(String getWindowOrigin, Boolean principal)
	{
		//Almacena el ID de la ventana original
		String originalWindow = driver.getWindowHandle();


		//Espera a la nueva ventana o pestaña
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(numberOfWindowsToBe(2));

		if(!principal) {
			//Recorrelas hasta encontrar el controlador de la nueva ventana
			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
		}
		else {

			driver.switchTo().window(getWindowOrigin);

		}

		return originalWindow;

	}






}

