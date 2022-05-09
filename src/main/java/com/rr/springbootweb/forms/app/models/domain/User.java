package com.rr.springbootweb.forms.app.models.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.rr.springbootweb.forms.app.validators.IdentifierRegex;
import com.rr.springbootweb.forms.app.validators.Required;

public class User {

	// Atributo que no estará mapeado en el formulario sino que estará accesible a
	// través del
	// objeto session. Esto es útil por ejemplo cuando se quiere tener un id de la
	// base de datos
	// Utilizamos Pattern para validar el valor con una expresión regular
	// La expresión regular corresponde a:
	// [\\d]: números del 0 al 9. También se puede escribir [0-9]
	// {2}: la cantidad, en este caso 2 dígitos
	// [.]: seguido de un punto o coma
	// [-]: seguido de un guión
	// [A-Z]: letras mayúsculas de la A a la Z
	// Ej. 32.654.228.D
	// @Pattern(regexp = "[0-9]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")

	// Otra forma de utilizar validación. Creando un custom anotation
	@IdentifierRegex()
	private String identifier;

	/*
	 * Especificamos las reglas de validación que vamos a aplicar en cada campo Esto
	 * lo chequeará el Controller con la anotación @Valid
	 */
	// Esta es una forma de especificar un mensaje personalizado. Otra es utilizar
	// un
	// archivo properties el cual sobreescribirá este mensaje
	// @NotEmpty(message = "This field is required")
	private String name;

	// @NotEmpty
	// Aquí validamos con una anotation customizada
	@Required
	private String lastName;

	// Campo requerido. Si tiene blancos también lo valida
	@NotBlank
	// Tamaño entre 5 y 10 caracteres
	@Size(min = 5, max = 10)
	private String username;

	// @NotEmpty
	// Aquí utilizamos nuestra anotation pero con el mensaje por defecto
	@Required
	private String password;

	@NotEmpty
	// Controla formato email
	@Email
	private String email;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer account;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past
	private Date birthDate;

	@NotNull
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date otherDate;

	@Valid
	private Country country;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getOtherDate() {
		return otherDate;
	}

	public void setOtherDate(Date otherDate) {
		this.otherDate = otherDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

}
