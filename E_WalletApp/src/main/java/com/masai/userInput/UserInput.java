package com.masai.userInput;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
	
	@Pattern(regexp = "^[6-9][0-9]{9}",message = "Invaild Phone Number")
	private String phone;
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}",message = "Invalid Password")
	private String password;
	@Size(min = 3,max = 30,message = "Invaild Name")
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
