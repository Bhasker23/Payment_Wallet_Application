package com.masai.userInput;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
	
	private String phone;
	private String password;
	private String name;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInput other = (UserInput) obj;
		return Objects.equals(password, other.password) && Objects.equals(phone, other.phone);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(password,phone);
	}
	
	

}
