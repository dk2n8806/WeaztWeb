package com.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AbstractPersistenceObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private long version;
	private Date createdOn = new Date();
	
	
	
	
	
	
	@Override
	public String toString() {
		return getClass().getName() + " {\n\tid: " + id + "\n\tversion: " + version + "\n\tcreatedOn: " + createdOn
				+ "\n}";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="WEAZT_SEQ")
	@SequenceGenerator(allocationSize=3, name="WEAZT_SEQ", initialValue=10000)
	public Long getId() {return id;}
	public void setId(long id) {this.id = id;}
	
	@Version
	@Column(name="VERSION")
	public long getVersion() {return version;}
	public void setVersion(long version) {this.version = version;}
	
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_ON", nullable=false, updatable=false)
	public Date getCreatedOn() {return createdOn;}
	public void setCreatedOn(Date createdOn) {this.createdOn = createdOn;}
	
	
}
