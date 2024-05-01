package org.cba.stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import org.cba.utils.APIUtils;
import org.cba.config.Configuration;

import static org.junit.Assert.assertTrue;

/**
 * Step definitions for the Weather API feature file.
 * It includes steps for setting up the city context, fetching weather data,
 * fetching air pollution data, and asserting conditions on the air quality index.
 */
public class WeatherSteps {

    private Response weatherResponse;
    private Response airPollutionResponse;
    private String cityName;
    private Scenario scenario;

    /**
     * Initializes the Scenario object before each test.
     * This method uses the Cucumber Before hook to capture the Scenario instance.
     * @param scenario The Scenario context provided by Cucumber.
     */
    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("I have the city {string}")
    public void i_have_the_city(String city) {
        this.cityName = city;
    }

    @When("I fetch the weather data")
    public void i_fetch_the_weather_data() {
        weatherResponse = APIUtils.getWeatherData(cityName, Configuration.API_KEY);
    }

    @And("I fetch the air pollution data using the coordinates")
    public void i_fetch_the_air_pollution_data_using_the_coordinates() {
        if (weatherResponse != null) {
            float lat = weatherResponse.jsonPath().getFloat("coord.lat");
            float lon = weatherResponse.jsonPath().getFloat("coord.lon");
            scenario.log("Using coordinates: Latitude = " + lat + ", Longitude = " + lon);
            airPollutionResponse = APIUtils.getAirPollutionData(lat, lon, Configuration.API_KEY);
            scenario.log("Air Pollution Response: " + (airPollutionResponse != null ? airPollutionResponse.prettyPrint() : "No Data"));
        } else {
            scenario.log("No weather data available for " + cityName);
        }
    }

    @Then("I check if the air quality index is 2 or above")
    public void i_check_if_the_air_quality_index_is_2_or_above() {
        if (airPollutionResponse != null) {
            int aqi = airPollutionResponse.jsonPath().getInt("list[0].main.aqi");
            scenario.log("The air quality index (AQI) for " + cityName + " is: " + aqi);
            assertTrue("The air quality index is less than 2", aqi >= 2);
        } else {
            scenario.log("No air pollution data available to check AQI");
        }
    }
}