package com.rr.springbootweb.forms.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rr.springbootweb.forms.app.models.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private List<Role> roles;

	public RoleServiceImpl() {
		roles = Arrays.asList(new Role(1, "Administrator", "ROLE_ADMIN"), new Role(2, "User", "ROLE_USER"),
				new Role(3, "Moderator", "ROLE_MODERATOR"));
	}

	@Override
	public List<Role> list() {
		return roles;
	}

	@Override
	public Role getRoleById(Integer id) {
		Role r = null;

		for (Role role : roles) {
			if (role.getId() == id) {
				r = role;
				break;
			}
		}

		return r;
	}

}
