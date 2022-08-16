package com.masai.userInput;

import java.util.Objects;

public class Credentials {
	private String name;
	private String password;
	
	@Override
	public int hashCode() {
		return Objects.hash(name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	public Credentials(String name,String Pass) {this.name=name;this.password=password;};
}
