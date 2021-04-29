package StepsDefinitions;

import Util.CucumberTest;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {


    @Before
    public void beforeHook(Scenario scenario) throws Exception {

        CucumberTest.startTest(scenario);

    }

    @After
    public void afterHook(Scenario scenario) throws Exception {

        CucumberTest.finishTest(scenario);
    }
}
