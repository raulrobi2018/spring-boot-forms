package com.rr.springbootweb.forms.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rr.springbootweb.forms.app.services.CountryService;

//Lo declaramos como Component para poder hacer inyección de dependencia
@Component
public class CountryPropertyEditor extends PropertyEditorSupport{

	@Autowired
	private CountryService service;
	
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException {
		Integer id = Integer.parseInt(idStr);
		this.setValue(service.getCountryById(id));
	}
	
}
