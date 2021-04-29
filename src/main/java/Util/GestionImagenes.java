package Util;

import java.io.File;
import java.io.FilenameFilter;

public class GestionImagenes {
	
	
	public String[] getImg(String sCarpAct)
	{
		File carpeta = new File(sCarpAct);

		FilenameFilter pngFilefilter = new FilenameFilter(){
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".png")) {
					return true;
				} else {
					return false;
				}
			}
		};


		String[] listado = carpeta.list(pngFilefilter);
		if (listado == null || listado.length == 0) 
		{ 
			System.out.println("No hay elementos dentro de la carpeta actual");
		}
		
		//System.out.println(listado[0]);
		
		return listado;
		
	}


}
