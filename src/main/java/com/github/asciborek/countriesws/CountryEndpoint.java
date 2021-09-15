package com.github.asciborek.countriesws;

import com.github.asciborek.countries_ws.Country;
import com.github.asciborek.countries_ws.GetCountryRequest;
import com.github.asciborek.countries_ws.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

  private static final String NAMESPACE_URI = "http://asciborek.github.com/countries-ws";

  private CountryProvider countryProvider;

  public CountryEndpoint(CountryProvider countryProvider) {
    this.countryProvider = countryProvider;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest getCountryRequest) {
    var response = new GetCountryResponse();
    return countryProvider.findByCountry(getCountryRequest.getName())
        .map(this::fromCountry)
        .orElseGet(this::countryNotFound);
  }

  private GetCountryResponse fromCountry(Country country) {
    var response = new GetCountryResponse();
    response.setCountry(country);
    return response;
  }

  private GetCountryResponse countryNotFound() {
    var response = new GetCountryResponse();
    response.setErrorCode(1);
    return response;
  }
}
