package com.masai.userInput;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
	
	private Credentials cred;
	public PasswordGenerator(){};
	
	public String getPass(Credentials cred) {
		this.cred=cred;
		return Math.abs(Objects.hash(cred))+"";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PasswordGenerator other = (PasswordGenerator) obj;
		return Objects.equals(cred, other.cred);
	};

}

