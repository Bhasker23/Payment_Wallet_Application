package com.masai.models;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import ch.qos.logback.core.subst.Token.Type;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankAccount {

	@Id
	private Integer accountNumber;
	@Size(min = 6,max = 12)
	private String ifscCode;
	@Size(min = 3,max = 20)
	private String bankName;
	@Min(value = 0)
	private Double balance;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private UserAccountDetails user;

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber, balance, bankName, ifscCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(accountNumber, other.accountNumber) && Objects.equals(balance, other.balance)
				&& Objects.equals(bankName, other.bankName) && Objects.equals(ifscCode, other.ifscCode);
	}
	
	

}
