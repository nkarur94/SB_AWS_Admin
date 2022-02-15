package com.admin.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class AdminDataDTO {
	
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "admin firstname is invalid")
	public String firstName;

	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "admin lastname is invalid")
	public String lastName;
	
	@Pattern(regexp="^[7-9][0-9]{9}",message="mobile no is not valid ")
	public String mobileNo;
	
	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "invalid email address")
	public String eMail;
	public String profilePath;
	public boolean status;
	public String password;
	
	@JsonFormat(pattern="dd MM yyyy")
	public LocalDate creatorStamp;
	
	@JsonFormat(pattern="dd MM yyyy")
	public LocalDate updateStamp;

}
