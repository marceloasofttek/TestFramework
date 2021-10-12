package PAGE;

import DriverTest.WebDriverTest;
import Keywords.KeywordsWeb;
import Util.MetodosVarios;

public class GooglePage extends KeywordsWeb{

    MetodosVarios metodosVarios = new MetodosVarios();
    String inputBusqueda = "name:q";
    String temperatura = "id:wob_tm";
    String temp;

    public GooglePage() {
        super(WebDriverTest.getDriver());
    }

    public void ingresarCiudad()
    {
        type("CIUDAD",inputBusqueda);
        screenShot();
        actionEnter(inputBusqueda);

    }

    public void obtenerTemperatura()
    {
        temp = findElement_getText(temperatura);
        metodosVarios.registrarValor("TEMPERATURA",temp);
        screenShot();
    }


    public void imprimirTemperatura()
    {
        System.out.println(temp);
    }
}
