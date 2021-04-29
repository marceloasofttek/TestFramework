package Datos;

import POI.ExcelClass;
import Util.MetodosFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runners.model.TestClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataExcel {
	
	static ExcelClass excelClass;
	static Properties mavenProps;

	private static final Logger logger = LogManager.getLogger(DataExcel.class);

	public static void rutaDatos() {


		try {
			mavenProps = new Properties();
			InputStream in = TestClass.class.getResourceAsStream("/maven.properties");
			mavenProps.load(in);
			//excelClass = new ExcelClass(System.getProperty("user.dir")+"\\data\\dataTest.xlsx");
			//excelClass = new ExcelClass(mavenProps.getProperty("testData")+"\\dataTest.xlsx"); //para acceso remoto
			excelClass = new ExcelClass(mavenProps.getProperty("testData")); //Acceso a la Data Excel
		} catch (Exception e) {
			logger.error(e.getStackTrace().getClass().getName());
			logger.error(e.getMessage());
		}

	}
	
	
	
	public static String retornarValor(String nameInput, String CP)
	{
		rutaDatos();
		return excelClass.searchCampo("Hoja1",nameInput, CP);
		
		
	}

	public void registrarValor(String nameInput, String valor)
	{

		excelClass.writeCellValue("Hoja1",nameInput, MetodosFeature.getScenario(), valor);


	}

}
