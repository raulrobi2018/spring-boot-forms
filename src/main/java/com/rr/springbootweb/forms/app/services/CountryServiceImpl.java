package com.rr.springbootweb.forms.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rr.springbootweb.forms.app.models.domain.Country;

//@Service es lo mismo que utilizar @Component. La diferencia es solamente semántica
@Service
public class CountryServiceImpl implements CountryService {

	private List<Country> countries;

	public CountryServiceImpl() {
		this.countries = Arrays.asList(new Country(1, "UY", "Uruguay"), new Country(2, "AR", "Argentina"),
				new Country(3, "BR", "Brasil"), new Country(4, "ES", "España"), new Country(5, "EU", "EEUU"),
				new Country(6, "GR", "Alemania"));
	}

	@Override
	public List<Country> list() {
		return countries;
	}

	@Override
	public Country getCountryById(Integer id) {
		Country c = null;
		for (Country country : countries) {
			if (id == country.getId()) {
				c = country;
				break;
			}
		}
		return c;
	}

}
