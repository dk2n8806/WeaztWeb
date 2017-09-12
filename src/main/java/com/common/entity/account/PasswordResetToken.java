package com.common.entity.account;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.common.entity.AbstractPersistenceToken;

@Entity
@Table(name="PASSWORD_RESET_TOKEN")
public class PasswordResetToken extends AbstractPersistenceToken {

	private static final long serialVersionUID = 1L;


	private static final int EXPIRATION = 60 * 24;
	
	
	private Date expiryDate;
	private Account account;
	
	public PasswordResetToken() {}
	private PasswordResetToken(String prefix) {
		super(prefix);
	}

    public static PasswordResetToken create(final Account account) {
    	PasswordResetToken passwordResetToken = new PasswordResetToken("pr");
    	passwordResetToken.account = account;
    	passwordResetToken.expiryDate = passwordResetToken.calculateExpiryDate(EXPIRATION);
    	return passwordResetToken;
    }

    /*
    public String getTokenLink() {
    	return new StringBuilder()
						.append(InAppResource.HOST)
						.append(UriPageRequestMapping.User.VERIFIED_ACCOUNT)
						.append("?email=").append(email)
						.append("&token=").append(this.getToken())
    					.toString();
    }
    
*/
	
    
    
    @ManyToOne
    @JoinColumn(name="ACCOUNT_ID")
    public Account getAccount() {		return account;	}
    public void setAccount(Account account) {	this.account = account;}
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EXPIRY_DATE", nullable=false)
    public Date getExpiryDate() {        return expiryDate;}
    public void setExpiryDate(final Date expiryDate) {    this.expiryDate = expiryDate; }
    
    
    
	


    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

	

}
