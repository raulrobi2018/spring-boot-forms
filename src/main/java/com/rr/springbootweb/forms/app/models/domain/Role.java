package com.rr.springbootweb.forms.app.models.domain;

public class Role {
	private Integer id;
	private String name;
	private String role;

	public Role() {

	}

	public Role(Integer id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object obj) {
		// Si la instancia es igual al objeto pasado por parametro, retorna true
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Role)) {
			return false;
		}

		return ((Role) obj).getId() != null && this.id.equals(((Role) obj).getId());
	}
}
