package com.rr.springbootweb.forms.app.models.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Country {
	
	private Integer id;
	@NotEmpty
	private String code;
	private String name;

	public Country() {
		
	}
	
	public Country(Integer id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
