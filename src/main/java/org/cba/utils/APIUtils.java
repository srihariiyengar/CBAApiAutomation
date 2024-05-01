package org.cba.utils;

import io.restassured.response.Response;
import org.cba.config.Configuration;

import static io.restassured.RestAssured.given;

/**
 * APIUtils class provides utility methods to interact with external APIs.
 * It contains methods to fetch weather data and air pollution data using REST Assured.
 */
public class APIUtils {

    /**
     * Fetches weather data for a specified city using the OpenWeatherMap Weather API.
     * @param cityName the name of the city to fetch the weather data for.
     * @param apiKey the API key for authenticating the request.
     * @return a Response object containing the API response.
     */
    public static Response getWeatherData(String cityName, String apiKey) {
        return given()
                .queryParam("q", cityName)
                .queryParam("appid", apiKey)
                .when()
                .get(Configuration.WEATHER_URL)
                .then()
                .extract()
                .response();
    }

    /**
     * Fetches air pollution data for specified latitude and longitude using the OpenWeatherMap Air Pollution API.
     * @param lat latitude of the location.
     * @param lon longitude of the location.
     * @param apiKey the API key for authenticating the request.
     * @return a Response object containing the API response.
     */
    public static Response getAirPollutionData(float lat, float lon, String apiKey) {
        return given()
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .when()
                .get(Configuration.AIR_POLLUTION_URL)
                .then()
                .extract()
                .response();
    }
}
