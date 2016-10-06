package com.bitwise.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {

	public static boolean validateEmail(String email) {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean validateMobile(String mobile) {
		return mobile.matches("^[789]\\d{9}$");
	}

	public static boolean validateString(String str) {
		return str.matches("[a-zA-Z]");
	}

	public static boolean validatePassword(String password) {
		return password.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})");
	}

	

	}


