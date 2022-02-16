package com.admin.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.admin.DTO.AdminDataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="admin_details")
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long adminId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String eMail;
	private String profilePath;
	private boolean status;
	private String password;
	private LocalDate creatorStamp;
	private LocalDate updateStamp;

	
	
	public AdminEntity(AdminDataDTO dataDTo) {
		this.firstName=dataDTo.firstName;
		this.lastName=dataDTo.lastName;
		this.mobileNo=dataDTo.mobileNo;
		this.eMail=dataDTo.eMail;
		this.profilePath=dataDTo.profilePath;
		
		this.password=dataDTo.password;
		
	}
	

}
