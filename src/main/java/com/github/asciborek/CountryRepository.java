package com.github.asciborek;

import com.github.asciborek.countries_ws.Currency;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.github.asciborek.countries_ws.Country;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public final class CountryRepository {

  private final ConcurrentMap<String, Country> countriesMap = new ConcurrentHashMap<>();

  @PostConstruct
  private void init() {

    Country spain = new Country();
    spain.setName("Spain");
    spain.setCapital("Madrid");
    spain.setCurrency(Currency.EUR);
    spain.setPopulation(46704314);
    spain.getLocales().add("es");

    countriesMap.put(spain.getName(), spain);

    Country poland = new Country();
    poland.setName("Poland");
    poland.setCapital("Warsaw");
    poland.setCurrency(Currency.PLN);
    poland.setPopulation(38186860);
    poland.getLocales().add("pl");

    countriesMap.put(poland.getName(), poland);

    Country uk = new Country();
    uk.setName("United Kingdom");
    uk.setCapital("London");
    uk.setCurrency(Currency.GBP);
    uk.setPopulation(63705000);
    uk.getLocales().add("uk");


    countriesMap.put(uk.getName(), uk);

  }

  Country findByName(String name) {
    return countriesMap.get(name);
  }



}
