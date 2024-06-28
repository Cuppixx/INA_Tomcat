package de.whs.ina;

import java.util.Set;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;


/**
 * Diese Klasse dient zur Vereinfachung der Nutzung von Hibernate Validator.
 * 
 * Diese Klasse ist neu und nur ein einem Projekt getestet. Zudem ist sie nicht
 * auf Laufzeit oder Threadsicherheit optimiert.
 * 
 * Sie dient zu Lehrzwecken und kann nach Belieben verändert werden.
 * Verbesserungsvorschläge und Erweiterungen sind willkommen!
 * 
 * Kompatibel mit Hibernate Validator 7, Java 11
 * 
   	<dependency>
  		<groupId>org.hibernate.validator</groupId>
  		<artifactId>hibernate-validator</artifactId>
  		<version>7.0.4.Final</version>
  	</dependency>
 *
 * @author Martin Schulten
 * @version 0.2
 */
public class ValidationUtil<BeanType> {

	private static Validator validator;
	private Set<ConstraintViolation<BeanType>> violations;

	/**
	 * Konstruktor erzeugt Validator
	 */
	public ValidationUtil() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * Prüft bean auf Validität.
	 *
	 * @return true/false
	 */
	public Boolean isValid(BeanType bean) {
		violations = validator.validate(bean);
		return violations.isEmpty();
	}

	/**
	 * Accessor für Validierungsverletzungen.
	 *
	 * @return Set von Validierungsverletzungen
	 */
	public Set<ConstraintViolation<BeanType>> getViolations() {
		return violations;
	}
}