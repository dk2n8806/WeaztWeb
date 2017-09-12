package com.common.util;

public interface AccountSecureUtil {

	/**
	 * Encode the raw password
	 * @param rawPwd - password reference
	 * @return
	 */
	public String encodePwd(final String rawPwd);
	
	public boolean match(final String rawPwd, final String encodedPwd);
}
