package com.github.asciborek.countriesws;

import com.github.asciborek.countries_ws.Currency;
import java.util.List;
import java.util.Optional;
import com.github.asciborek.countries_ws.Country;


public interface CountryProvider {

  Optional<Country> findByCountry(String country);

  List<Country> getCountriesByCurrency(Currency currency);

}
