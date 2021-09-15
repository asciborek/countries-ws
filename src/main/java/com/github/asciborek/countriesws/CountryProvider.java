package com.github.asciborek.countriesws;

import java.util.Optional;
import com.github.asciborek.countries_ws.Country;


public interface CountryProvider {

  Optional<Country> findByCountry(String country);

}
