package com.common.util;

import java.text.StringCharacterIterator;

import org.apache.commons.validator.EmailValidator;
import com.common.util.AccountUtil;

@SuppressWarnings("deprecation")
public class AccountUtil{


	public static final String DEFAULT_IMG = "https://s3-us-west-2.amazonaws.com/mingofy.com/resource/default/default-user-icon-profile.png";
			
			
	private static final int PWD_MIN_LENGTH =  6;				// Max length of pwd
	private static final int PWD_MAX_LENGTH =  32;			// Min length of pwd
	private static final int USERNAME_MIN_LENGTH = 3;
	private static final int USERNAME_MAX_LENGTH = 32;
	
	
	/**
	 * Validate a given string against email format
	 * @param val - email value
	 * @return true if value is good email format, false otherwise
	 */
	public static boolean isEmailFormat(String val) {
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(val);
	}

	public static boolean isUsernameFormat(String val) {
		//boolean isValid_flag =false;
		boolean userHasContent = ((val != null)
										&& (!val.equals(""))
										&& (val.length() >= USERNAME_MIN_LENGTH)
										&& (val.length() < USERNAME_MAX_LENGTH));

		if(!userHasContent) {
			return false;
		}
		
		return  val.matches("^[a-zA-Z0-9-_.]*$");
		
		/*if(!userHasContent) {
			log.error("User is not valid");
			isValid_flag=false; 
		} else {
			StringCharacterIterator iterator = new StringCharacterIterator(val);
			char aChar = iterator.current();
			while( aChar != StringCharacterIterator.DONE ) {
				isValid_flag = ((aChar >= 'A' && aChar <= 'Z')
						|| (aChar >= 'a' && aChar <= 'z')
						|| aChar == ' ' || aChar == '_'
						|| Character.isDigit(aChar));
				
				if( !isValid_flag ) {		return isValid_flag;	}
				aChar = iterator.next();	//---Next letter			
			}
		}
		return isValid_flag;
		*/
	}
	
	
	
	/**
	 * Validate given String against Customized Password Format
	 * @param val - password value
	 * @return true if val is good password format, false otherwise.
	 */
	public static boolean isSecuredPasswordFormat(String val) {
		boolean isValid_flag = false;
		boolean passwordHasContent = ((val != null) 
									&& (!val.equals(""))
									&& (val.length() >= PWD_MIN_LENGTH)		// Inclusive min length
									&& (val.length() < PWD_MAX_LENGTH));		// Exclusive max length

		//---Invalid password's minimum requirements.
		if( !passwordHasContent ) {		
			isValid_flag = false; 
		} else {
			//---Iterate the String to validate each character.
			StringCharacterIterator iterator = new StringCharacterIterator( val );
			char aChar = iterator.current();			// Get the current character.
				
			while( aChar != StringCharacterIterator.DONE ) {
				isValid_flag = ((aChar >= 'A' && aChar <= 'Z')
						|| (aChar >= 'a' && aChar <= 'z')
						|| Character.isDigit(aChar));
				
				if( !isValid_flag ) {		return isValid_flag;	}
				aChar = iterator.next();	//---Next letter			
			}
		}
		return isValid_flag;
	}
	
	/*public static void main(String args[]) {
		System.out.println(AccountUtil.isUsernameFormat("coff_Minggo"));
	}
	 */
}
