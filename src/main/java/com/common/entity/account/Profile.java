package com.common.entity.account;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceObject;
import com.common.entity.address.Address;
import com.common.entity.support.embeded.BirthDate;
import com.common.entity.support.embeded.Phone;
import com.common.type.Gender;


@Entity
@Table(name="PROFILE")
public class Profile  extends AbstractPersistenceObject{

	private static final long serialVersionUID = 1L;

	
	
	private Gender gender;
	private BirthDate birthDate;
	private Address address;
	private Phone phone;
	private String fullName;
	
	
	
	@Embedded
	public Phone getPhone() {return phone;}
	public void setPhone(Phone phone) {	this.phone = phone;}
	
	
	
	@Embedded
	public BirthDate getBirthDate() {	return birthDate;}
	public void setBirthDate(BirthDate birthDate) {	this.birthDate = birthDate;}
	
	
	@OneToOne
	@JoinColumn(name="ADDRESS_ID")
	public Address getAddress() {	return address;}
	public void setAddress(Address address) {this.address = address;}

	@Enumerated(EnumType.STRING)
	@Column(name="GENDER")
	public Gender getGender() {	return gender;}
	public void setGender(Gender gender) {this.gender = gender;}
	
	
	@Column(name="FULL_NAME")
	public String getFullName() {return fullName;}
	public void setFullName(String fullName) {	this.fullName = fullName;}
	
	
	
	
	
	
	
}
