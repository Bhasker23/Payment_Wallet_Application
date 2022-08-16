package com.masai.userInput;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentSession {
	
	@Id
	private String uniqueid;
	private String userId;
	private String name;
	
}
