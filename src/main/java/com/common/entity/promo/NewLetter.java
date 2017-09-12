package com.common.entity.promo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.validator.EmailValidator;

import com.common.entity.AbstractPersistenceObject;

/** 
 * <h1>New Letter</h1>
 * 
 * <p>The class severs as a book keeping to keep potential stay in the loop</p>
 * 
 * @author dk2n_
 *
 */
@Entity
@Table(name="NEW_LETTER")
public class NewLetter extends AbstractPersistenceObject {

	private static final long serialVersionUID = 1L;
	private String email;
	
	
		
	/**
	 * Create a new NewLetter instance with a passing email
	 * 
	 * @param email The value passing to
	 * @return NewLetter instance or null
	 */
	public static NewLetter create(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		if(validator.isValid(email)) {
			NewLetter newLetter = new NewLetter();
			newLetter.email = email;
			return newLetter;	
		}
		return null;
	}
	
	
	@Column(name="EMAIL", nullable=false, unique=true, updatable=false)
	public String getEmail() {	return email;}
	public void setEmail(String email) {	this.email = email;}
}
