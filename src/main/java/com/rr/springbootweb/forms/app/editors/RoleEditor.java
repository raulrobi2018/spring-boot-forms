package com.rr.springbootweb.forms.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rr.springbootweb.forms.app.services.RoleService;

@Component
public class RoleEditor extends PropertyEditorSupport {


	@Autowired
	private RoleService roleService;
	
	@Override
	public void setAsText(String roleIdStr) throws IllegalArgumentException {
		try {
			Integer id = Integer.parseInt(roleIdStr);
			this.setValue(roleService.getRoleById(id));
		} catch (NumberFormatException e) {
			this.setValue(null);
		}
	}
	
}
