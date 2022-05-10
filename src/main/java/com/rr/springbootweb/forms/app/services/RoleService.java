package com.rr.springbootweb.forms.app.services;

import java.util.List;

import com.rr.springbootweb.forms.app.models.domain.Role;

public interface RoleService {

	public List<Role> list();

	public Role getRoleById(Integer id);

}
