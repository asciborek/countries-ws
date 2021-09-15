package com.github.asciborek.countriesws;

import java.util.HashMap;
import java.util.Map;
import com.github.asciborek.countries_ws.Country;
import com.github.asciborek.countries_ws.Currency;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryCountryProvider implements CountryProvider {

  private final Map<String, Country> countries = new HashMap<>();

  @PostConstruct
  public void initData() {
    Country spain = new Country();
    spain.setName("Spain");
    spain.setCapital("Madrid");
    spain.setCurrency(Currency.EUR);
    spain.setPopulation(46704314);

    countries.put(spain.getName(), spain);

    Country poland = new Country();
    poland.setName("Poland");
    poland.setCapital("Warsaw");
    poland.setCurrency(Currency.PLN);
    poland.setPopulation(38186860);

    countries.put(poland.getName(), poland);

    Country uk = new Country();
    uk.setName("United Kingdom");
    uk.setCapital("London");
    uk.setCurrency(Currency.GBP);
    uk.setPopulation(63705000);

    countries.put(uk.getName(), uk);
  }

  @Override
  public Optional<Country> findByCountry(String country) {
    return Optional.ofNullable(countries.get(country));
  }
}
