package com.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class AccountSecureUtilImpl implements AccountSecureUtil {

	@Override
	public String encodePwd(String rawPwd) {
		return new BCryptPasswordEncoder().encode(rawPwd);
	}

	@Override
	public boolean match(String rawPwd, String encodedPwd) {
		return new BCryptPasswordEncoder().matches(rawPwd, encodedPwd);
	}

}
