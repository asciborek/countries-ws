package com.github.asciborek;

import com.github.asciborek.countries_ws.Country;
import com.github.asciborek.countries_ws.GetCountryRequest;
import com.github.asciborek.countries_ws.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public final class CountryEndpoint {

  private static final String NAMESPACE_URI = "http://asciborek.github.com/countries-ws";
  private static final GetCountryResponse NOT_FOUND_RESPONSE;

  private final CountryRepository countryRepository;

  static {
    NOT_FOUND_RESPONSE = new GetCountryResponse();
    NOT_FOUND_RESPONSE.setStatus(1);
  }

  public CountryEndpoint(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    return countryRepository.findByName(request.getName()).map(country -> {
      GetCountryResponse response = new GetCountryResponse();
      response.setCountry(country);
      response.setStatus(0);
      return response;
    }).orElse(NOT_FOUND_RESPONSE);
  }

}
