package com.common.entity.account;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.entity.AbstractPersistenceToken;

@Entity
@Table(name="VERIFICATION_TOKEN")
public class VerificationToken extends AbstractPersistenceToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int EXPIRATION = 60 * 24;
	
    private Date expiryDate;
    private Account account;

    
    
    public VerificationToken() {}
    private VerificationToken(String prefix) {
        super(prefix);

    }
    
    public static VerificationToken create(final Account account) {
    	try {
    		if(account == null)
    			throw new IllegalArgumentException("account entity is required");
    		
	    	VerificationToken token = new VerificationToken("verify");
	    	token.setAccount(account);
	    	token.setExpiryDate(token.calculateExpiryDate(EXPIRATION));
	    	return token;
    	} catch(IllegalArgumentException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    @OneToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    public Account getAccount() {return account;}
    public void setAccount(Account account) {	this.account = account;}
    
	
	@Column(name="EXPIRY_DATE", nullable=false, updatable=false)
    public Date getExpiryDate() {        return expiryDate;    }
    public void setExpiryDate(final Date expiryDate) {    this.expiryDate = expiryDate;}

    
    


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

    //

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VerificationToken other = (VerificationToken) obj;
        if (expiryDate == null) {
            if (other.expiryDate != null) {
                return false;
            }
        } else if (!expiryDate.equals(other.expiryDate)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (account == null) {
            if (other.account != null) {
                return false;
            }
        } else if (!account.equals(other.account)) {
            return false;
        }
        return true;
    }


}
