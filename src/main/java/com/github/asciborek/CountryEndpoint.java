package com.github.asciborek;

import com.github.asciborek.countries_ws.GetCountryRequest;
import com.github.asciborek.countries_ws.GetCountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public final class CountryEndpoint {

  private static final String NAMESPACE_URI = "http://asciborek.github.com/countries-ws";

  private final CountryRepository countryRepository;

  public CountryEndpoint(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    GetCountryResponse response = new GetCountryResponse();
    response.setCountry(countryRepository.findByName(request.getName()));

    return response;
  }

}
