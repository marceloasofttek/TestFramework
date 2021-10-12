package Util;

import DriverTest.WebDriverTest;
import cucumber.api.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertTrue;

public class CucumberTest extends WebDriverTest {

    private static final Logger logger = LogManager.getLogger(CucumberTest.class);

    public static void startTest(Scenario scenario){
        logger.info("Escenario -> {}, Tags -> {}",scenario.getName(),scenario.getSourceTagNames());
        String valor = scenario.getSourceTagNames().toArray(new String[0])[1];
        if(valor.equals("@Google"))
            MetodosFeature.addScenario(scenario.getSourceTagNames().toArray(new String[0])[0]);
        else
            MetodosFeature.addScenario(scenario.getSourceTagNames().toArray(new String[0])[1]);

        //System.out.println(MetodosFeature.getScenario().toString());

        MetodosVarios.createCarpeta(MetodosFeature.getScenario());
        String browser = retornarValor("BROWSER",MetodosFeature.getScenario());
        int intBrowser = (browser.equals("CHROME"))?0:(browser.equals("IE"))?1:(browser.equals("FIREFOX"))?2:(browser.equals("CHROMEREMOTE"))?3:100;
        switch (intBrowser)
        {
            case 0: WebDriverTest.setDriverChrome();break;
            case 1: WebDriverTest.setDriverIE();break;
            case 2: WebDriverTest.setDriverFirefox();break;
            case 3: WebDriverTest.driverRemote();break;
            default:assertTrue("NAVEGADOR NO IDENTIFICADO",false);logger.error("ERROR "+intBrowser+" -> NAVEGADOR NO IDENTIFICADO");
        }

    }

    public static void finishTest(Scenario scenario)
    {
        try {
            setDriverClose();
            MetodosVarios.generarReporte(scenario);

            System.out.println("RESULTADOS------------------------------");
            System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
            System.out.println("FIN DE RESULTADOS-----------------------");
        }catch(Exception e)
        {
            logger.error(e.getMessage());
        }
    }

}
