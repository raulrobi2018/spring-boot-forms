package com.rr.springbootweb.forms.app.services;

import java.util.List;

import com.rr.springbootweb.forms.app.models.domain.Country;

public interface CountryService {
	public List<Country> list();
	
	public Country getCountryById(Integer id);
	
}
