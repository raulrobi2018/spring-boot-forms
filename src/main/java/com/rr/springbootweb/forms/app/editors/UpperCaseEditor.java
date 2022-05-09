package com.rr.springbootweb.forms.app.editors;

import java.beans.PropertyEditorSupport;

public class UpperCaseEditor extends PropertyEditorSupport{

	
	//Convierte un campo de tipo String a mayusculas
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		setValue(text.toUpperCase().trim());
	}

	
	
}
