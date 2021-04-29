package Util;

import Datos.DataExcel;
import POI.DocxClass;
import cucumber.api.Scenario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MetodosVarios extends DataExcel {
	
	private static String carpetaEvidencia;

	public MetodosVarios() {

		//this.carpetaEvidencia = nameClass+"_"+obtenerFechaHora();
		//this.carpetaEvidencia.replaceAll(".", "-");
		//System.out.println(this.carpetaEvidencia);
	}
	
	
	public String[] FraccionarCadena(String valor) {
		
		String cadena[];
		int i = 0;
		int x = 0;
		int letters = 3;
		float  registros;
		if((float)valor.length() > letters)
		{
		 registros = (float)valor.length()/letters;
		}
		else
		{
			registros = 1;
		}

		
		
		int cant_registros = (int) Math.ceil(registros);

		cadena = new String[cant_registros];
		
		while(i < valor.length())
		{
		    int initIndex = i;
		    int endIndex = i + letters;
		    
		    if(endIndex > valor.length())
		    {
		    	endIndex = valor.length();
		    }

		    cadena[x] = valor.substring(initIndex, endIndex);
		    x++;
		    i = endIndex;
		}

		return cadena;
	}
	
	
	public static boolean CompararDatos(String Valor, String nameInput,String CP)
	{
		
		String inputText = retornarValor(nameInput, CP);
		System.out.println(Valor+" - "+inputText);
		boolean compara = false;
		if(inputText.equals(Valor))
			compara = true;
		else
			compara = false;
		
		return compara;
	}

	public static boolean CompararDatos(String Valor, String nameInput,String CP, String complemento)
	{

		String inputText = retornarValor(nameInput, CP);
		boolean compara = false;
		inputText = complemento+inputText;
		//System.out.println(Valor+" - "+inputText);
		if(inputText.equals(Valor))
			compara = true;
		else
			compara = false;

		return compara;
	}



	public static String createCarpeta(String CP)
	{
		String CPBase = CP.replace("@", "");
		String subcarpeta = retornarValor("MODULO",CP);
		//System.out.println("CPBASE1->"+CPBase);

		String rutatemp = CPBase+"_"+obtenerFechaHora();
		//carpetaEvidencia = rutatemp.replace(".", "-");
		carpetaEvidencia = subcarpeta+"\\"+rutatemp;
		return carpetaEvidencia;
	}

	public static String getCarpeta(){return carpetaEvidencia;}

	public static String obtenerFechaHora() {

		Date objDate = new Date();


		String strDateFormat = "dd-MM-yyyy_hh-mm-ss";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		String FechaHora = objSDF.format(objDate);

		return FechaHora;
	}

	public static String nombreImagen() {

		String nombre = "Evidencia_"+obtenerFechaHora()+".png";
		return nombre;
	}

	public static String nombreReporte(String CP) {

		String CPBase = CP.replace("@", "");

		//String sub = CP.replace("@", "");
		String nombre = CPBase+"_"+obtenerFechaHora()+".docx";
		return nombre;

	}

	public static String rutaEvidencia(){
		//String CPBase = CP.replace("@", "");
		//String subcarpeta = retornarValor("MODULO","@"+CPBase);

		String ruta = System.getProperty("user.dir")+"\\ScreenShot\\";
		return ruta;
	}

	public static String nroAleatorio(int total, int letras)
	{
		// Inicializamos la variable que almacenará la matrícula.
		String placa = "";
		// Inicializamos la instancia de la clase Random con la que
		// generaremos el valor aleatorio.
		Random rnd = new Random();

		// Creamos un ciclo que se ejecute 7 veces, que corresponden al
		// texto de la matrícula.
		for (int i = 0; i < total; i++)
		{
			// Con este condicional verificamos si estamos en la parte
			// numérica o alfabética de la matrícula.
			// Solo debe entrar al condicional si estamos generando los
			// números de la matrícula.
			if(i < letras)
			{
				// Con esta instrucción se genera un número aleatorio entre
				// 65 y 90, no se incluye el 91. Luego se convierte a un
				// caracter ASCII.
				placa += (char)((int) Math.floor(Math.random()*26+65));

			}
			// Entrará en esta parte del condicional cuando estemos generando
			// las letras de la matrícula.
			else
			{
				// Con esta instrucción se genera un número aleatorio entre
				// 0 y 9, no se incluye el 10.
				placa += rnd.nextInt(10);
			}
		}

		return placa;
	}

		public static void crearArchivoLog(String contenido){
			try {
				File file = new File(getRutaDocument()+"\\pclog_"+obtenerFechaHora()+".txt");
				// Si el archivo no existe es creado
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(contenido);
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String getRutaDocument()
		{
			String subcarpeta = retornarValor("MODULO",MetodosFeature.getScenario());
			String Carpeta = System.getProperty("user.dir")+"\\Documents\\"+subcarpeta+"\\";
			File directorio = new File(Carpeta);
			if (!directorio.exists()) {
				if (directorio.mkdirs()) {
					System.out.println("Directorio creado");
				} else {
					System.out.println("Error al crear directorio");
				}
			}

			return Carpeta;
		}

	public static void generarReporte(Scenario scenario) throws Exception {
		String CP = MetodosFeature.getScenario();
		String[] descripcionImg = new String[10];
		descripcionImg[0] = "step1";
		descripcionImg[1] = "step2";
		descripcionImg[2] = "step3";
		descripcionImg[3] = "step4";
		descripcionImg[4] = "step5";
		descripcionImg[5] = "step6";
		descripcionImg[6] = "step7";
		descripcionImg[7] = "step8";
		descripcionImg[8] = "step9";
		descripcionImg[9] = "step10";
		DocxClass docxClass = new DocxClass();
		docxClass.crearDocx(System.getProperty("user.dir")+"\\Formato.docx", getRutaDocument(), nombreReporte(CP), "png", rutaEvidencia()+carpetaEvidencia+"\\", descripcionImg, obtenerFechaHora(),CP, scenario);
	}


	/*public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);

    }
	
	public void screenShot(WebDriver webdriver, String nameClass) throws Exception
	{
		System.out.println(System.getProperty("user.dir"));
		
		String fileWithPath = rutaEvidencia()+nombreImagen();
		
		this.takeSnapShot(webdriver, fileWithPath);
		
	}
	
	public String nombreImagen() {
		
	String nombre = "Evidencia_"+this.obtenerFechaHora()+".jpg";
	return nombre;
	}
	
	public String rutaEvidencia() {
		
		String ruta = System.getProperty("user.dir")+"\\Evidencia\\"+this.carpetaEvidencia+"\\";
		return ruta;
		}
	
	public static String obtenerFechaHora() {
		
		Date objDate = new Date(); 
		
				
		String strDateFormat = "dd-MM-yyyy_hh-mm-ss"; 
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String FechaHora = objSDF.format(objDate);
		
		return FechaHora;
	}*/



}
