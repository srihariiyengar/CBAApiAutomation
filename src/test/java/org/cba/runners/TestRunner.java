package org.cba.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * TestRunner class configured to run Cucumber feature files.
 * It sets up the Cucumber test environment, specifying feature file locations and step definition locations.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "org.cba.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}
