package com.rr.springbootweb.forms.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rr.springbootweb.forms.app.editors.CountryPropertyEditor;
import com.rr.springbootweb.forms.app.editors.RoleEditor;
import com.rr.springbootweb.forms.app.editors.UpperCaseEditor;
import com.rr.springbootweb.forms.app.models.domain.Country;
import com.rr.springbootweb.forms.app.models.domain.Role;
import com.rr.springbootweb.forms.app.models.domain.User;
import com.rr.springbootweb.forms.app.services.CountryService;
import com.rr.springbootweb.forms.app.services.RoleService;
import com.rr.springbootweb.forms.app.validators.UserValidation;

@Controller
//Con esta anotación mantenemos el objeto user en sesión por si queremos acceder a algún
//dato que no esté mapeado al formulario
@SessionAttributes("user")
public class FormController {

	@Autowired
	private UserValidation validator;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryPropertyEditor countryEditor;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleEditor roleEditor;

	/**
	 * Esta es otra forma de validar. Si utilizamos binder.setValidator reemplaza el
	 * validador utilizado con anotaciones por este y por lo tanto solo se va a
	 * aplicar este validador, por eso utilizamos addValidator
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Agrega un validador
		binder.addValidators(validator);

		// Captura un campo ingresado en el formulario
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		// Deshabilita el formateador automático para que sea estricto y no tolerante
		formatter.setLenient(false);
		// Esta es otra forma de validar el formato de un campo del formulario
		binder.registerCustomEditor(Date.class, "otherDate", new CustomDateEditor(formatter, false));

		binder.registerCustomEditor(String.class, "name", new UpperCaseEditor());
		binder.registerCustomEditor(String.class, "lastName", new UpperCaseEditor());

		binder.registerCustomEditor(Country.class, "country", countryEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}

	@ModelAttribute("genres")
	public List<String> genres() {
		return Arrays.asList("Male", "Female");
	}

	@ModelAttribute("countries")
	public List<Country> countries() {
		return countryService.list();
	}

	@ModelAttribute("countriesMap")
	public Map<String, String> countriesMap() {
		Map<String, String> countries = new HashMap<>();
		countries.put("ES", "España");
		countries.put("UY", "Uruguay");
		countries.put("AR", "Argentina");
		countries.put("CO", "Colombia");
		countries.put("GR", "Alemania");
		countries.put("EU", "EEUU");
		return countries;
	}

	@ModelAttribute("roles")
	public List<Role> rolesList() {
		return roleService.list();
	}

	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> roles = new HashMap<>();
		roles.put("ROLE_ADMIN", "Administrator");
		roles.put("ROLE_USER", "User");
		roles.put("ROLE_MODERATOR", "Moderator");
		return roles;
	}

	@GetMapping("/form")
	public String form(Model model) {
		// Se crea el usuario para que no de null cuando se inicia el request
		User user = new User();
		user.setName("Silvia");
		user.setLastName("Fernandez");
		user.setIdentifier("3232.654.228.D");
		user.setEnable(true);
		user.setHiddenValue("Some hidden value");
		user.setCountry(new Country(3, "BR", "Brasil"));
		user.setRoles(Arrays.asList(new Role(2, "ROLE_USER", "User"), new Role(3, "ROLE_MODERATOR", "Moderator")));

		model.addAttribute("title", "Form login");
		model.addAttribute("user", user);
		return "form";
	}

	/*
	 * Aquí estamos pasando el User directamente al método. Spring manejará la
	 * creación del mismo al recibir los datos del formulario mediante el Request,
	 * creará el user tomando los parámetros recibidos.
	 * 
	 * IMPORTANTE: los nombres de los atributos de la clase User deben de coincidir
	 * con los del formulario para que mapee
	 */

	// El objeto BindingResult contiene el resultado de la validación de campos.
	// Contendrá los mensajes de errores en caso de que los hubiera
	// Siempre tiene que ir inmediatamente después que el objeto que vamos a validar
	// en los parametros
	// Con @ModelAttribute especificamos el nombre con el que queremos mapear el
	// objeto en la vista. En este caso
	// no sería necesrio porque el objeto tiene el mismo nombre "user", pero si le
	// quisieramos dar otro nombre
	// se podría hacer de esta forma
	@PostMapping("/form")
	public String processForm(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			SessionStatus status) {

		// Esta es otra forma de registrar el validador
		// validator.validate(user, result);

		model.addAttribute("title", "Form result");

		if (result.hasErrors()) {
			// Esta es una manera de mapear los errores. Otra forma es retornar el
			// formulario
			// para que Thymeleaf lo maneje de forma automática
//			Map<String, String> errors = new HashMap<>();
//			result.getFieldErrors().forEach((err) -> {
//				errors.put(err.getField(),
//						"The field ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
//			});
//			model.addAttribute("error", errors);
			return "form";
		}

		model.addAttribute("user", user);

		// Elimina el objeto user de la sesión luego de procesada la información
		status.setComplete();

		return "result";
	}
}
