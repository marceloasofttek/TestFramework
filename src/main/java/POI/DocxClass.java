package POI;

import Datos.DataExcel;
import Util.GestionImagenes;
import cucumber.api.Scenario;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Properties;
import org.junit.runners.model.TestClass;

public class DocxClass extends DataExcel {
	
	
	public void crearDocx(String rutatemplate, String rutaDocx, String nameDocx, String typeImg, String rutaCarpeta, String[] descripcionImg, String fecha, String CP, Scenario scenario) throws IOException, InvalidFormatException
	{
		System.out.println("Inicio de generación de Reporte...");

		Properties mavenProps = new Properties();
		InputStream in = TestClass.class.getResourceAsStream("/maven.properties");
		mavenProps.load(in);

		XWPFDocument document = new XWPFDocument(OPCPackage.open(rutatemplate));
		XWPFParagraph paragraph = document.createParagraph();
		
		
		/*for (XWPFTable tbl : document.getTables()) {
			   for (XWPFTableRow row : tbl.getRows()) {
			      for (XWPFTableCell cell : row.getTableCells()) {
			         for (XWPFParagraph p : cell.getParagraphs()) {
			            for (XWPFRun r : p.getRuns()) {
			              String text = r.getText(0);
			              
			              if (text != null && text.contains("<TITLE>")) {
			                text = text.replace("<TITLE>", retornarValor("TITULOREPORTE", CP));
			                r.setText(text,0);
			              }
			              
			              if (text != null && text.contains("<MODGW>")) {
				                text = text.replace("<MODGW>", retornarValor("MODULO", CP));
				                r.setText(text,0);
				              }
			              
			              if (text != null && text.contains("<USER>")) {
				                text = text.replace("<USER>", retornarValor("NAMEUSER", CP));
				                r.setText(text,0);
				              }
			              
			              if (text != null && text.contains("FECH")) {
			            	  //System.out.println("FECH");
				                text = text.replace("FECH", fecha);
				                r.setText(text,0);
				              }
			              if (text != null && text.contains("<CTEST>")) {
				                text = text.replace("<CTEST>", retornarValor("CP", CP));
				                r.setText(text,0);
				              }
			              
			              if (text != null && text.contains("<CODCP>")) {
				                text = text.replace("<CODCP>", CP);
				                r.setText(text,0);
				              }
			              if (text != null && text.contains("<REQ>")) {
				                text = text.replace("<REQ>", retornarValor("REQ", CP));
				                r.setText(text,0);
				              }
			            }
			         }
			      }
			   }
			}*/

		paragraph.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun run = paragraph.createRun();
		run.setFontSize(16);
		run.setFontFamily("Times New Roman");
		run.setText(retornarValor("TITULOREPORTE", CP));
		run.addBreak();
		run.addBreak();
		paragraph.setAlignment(ParagraphAlignment.LEFT);

		XWPFRun rundetalle = paragraph.createRun();
		rundetalle.setFontSize(12);
		rundetalle.setText("TEST: "+CP+" - "+retornarValor("CP", CP));
		rundetalle.addBreak();
		rundetalle.setText("MODULO: "+retornarValor("MODULO", CP));
		rundetalle.addBreak();
		rundetalle.setText("REQUERIMIENTO: "+retornarValor("REQ", CP));
		rundetalle.addBreak();
		rundetalle.setText("EJECUTOR: "+retornarValor("NAMEUSER", CP));
		rundetalle.addBreak();
		rundetalle.setText("FECHA: "+fecha);
		rundetalle.addBreak();
		rundetalle.setText("ESTADO: "+scenario.getStatus());
		rundetalle.addBreak();
		//rundetalle.setText("USUARIO: "+retornarValor("USER", CP));
		//rundetalle.addBreak();
		rundetalle.addBreak();

		String[] listado = getImagen(rutaCarpeta);

		if (listado != null || listado.length > 0) 
		{

			/*XWPFRun run = paragraph.createRun();

			run.setText("STATUS: "+scenario.getStatus());
			run.addBreak();
			run.addBreak();
			run.setText("DNI: "+retornarValor("USER", CP));
			run.addBreak();
			run.setText("USUARIO: "+retornarValor("USER", CP));
			run.addBreak();
			run.setText("CONTRASEÑA: "+retornarValor("PASS", CP));*/
			
			
			int i = 0;
			while(i < listado.length)
			{
				
				//System.out.println("cantidad de nombre -> "+listado[i].length());
				XWPFRun runImg = paragraph.createRun();
				//run.addBreak();
				//run.addBreak();
				//runImg.addBreak();
				//run.setText(descripcionImg[i]);
				//System.out.println(rutaCarpeta+listado[i]);
				BufferedImage bImage = null;
				File initialImage = new File(rutaCarpeta+listado[i]);
	            bImage = ImageIO.read(initialImage);
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bImage, typeImg, baos);
				baos.flush();
				ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
				baos.close();

				runImg.addBreak();
				switch (Integer.parseInt(mavenProps.getProperty("entorno")))
				{
					case 1:
						runImg.addPicture(bis, XWPFDocument.PICTURE_TYPE_PNG, "image file", Units.toEMU(500), Units.toEMU(250));
						break;
					case 2:
						runImg.addPicture(bis, XWPFDocument.PICTURE_TYPE_PNG, "image file", Units.toEMU(220), Units.toEMU(400));
						break;
				}
				
				i++;
			}
		}
		
		FileOutputStream out = new FileOutputStream(new File(rutaDocx+nameDocx));
		document.write(out);
		out.close();

		System.out.println("Se genero el Reporte Correctamente!!!");
	
	}
	
	private String[] getImagen(String rCarpeta)
	{
		GestionImagenes gestionImagenes = new GestionImagenes();
		String[] listado = gestionImagenes.getImg(rCarpeta);
		
		return listado;
		
	}

}
