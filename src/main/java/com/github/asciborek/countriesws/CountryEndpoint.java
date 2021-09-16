package com.github.asciborek.countriesws;

import com.github.asciborek.countries_ws.Country;
import com.github.asciborek.countries_ws.GetCountriesByCurrencyRequest;
import com.github.asciborek.countries_ws.GetCountriesByCurrencyResponse;
import com.github.asciborek.countries_ws.GetCountryRequest;
import com.github.asciborek.countries_ws.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

  private static final Logger LOG = LoggerFactory.getLogger(CountryEndpoint.class);

  private static final String NAMESPACE_URI = "http://asciborek.github.com/countries-ws";

  private final CountryProvider countryProvider;

  public CountryEndpoint(CountryProvider countryProvider) {
    this.countryProvider = countryProvider;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest getCountryRequest) {
    LOG.info("getCountry endpoint called for {}", getCountryRequest.getName());
    return countryProvider.findByCountry(getCountryRequest.getName())
        .map(this::fromCountry)
        .orElseGet(this::countryNotFound);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountriesByCurrencyRequest")
  @ResponsePayload
  public GetCountriesByCurrencyResponse getCountriesByCurrency(@RequestPayload GetCountriesByCurrencyRequest request) {
    LOG.info("getCountriesByCurrency endpoint called for {}", request.getCurrency());
    var data =  countryProvider.getCountriesByCurrency(request.getCurrency());
    var response = new GetCountriesByCurrencyResponse();
    response.getCountry().addAll(data);
    return response;
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
