package StepsDefinitions;

import PAGE.GooglePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StepsDefinitions {

    //#Declarar las variable pages
    private GooglePage googlePage;

    public StepsDefinitions()
    {
        //#instanciar las clases Pages
        googlePage = new GooglePage();

    }

    //#create funtions Given When Then And
    @Given("^Ingresar Ciudad$")
    public void ingresar_ciudad() throws Throwable {
        googlePage.ingresarCiudad();
    }

    @When("^Obtener tempreratura$")
    public void obtener_tempreratura() throws Throwable {
        googlePage.obtenerTemperatura();
    }

    @Then("^Imprimir temperatura$")
    public void imprimir_temperatura() throws Throwable {
        googlePage.imprimirTemperatura();
    }




}
