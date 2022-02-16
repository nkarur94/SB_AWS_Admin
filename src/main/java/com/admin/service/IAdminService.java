package com.admin.service;

import com.admin.DTO.AdminDataDTO;
import com.admin.DTO.ResetPassword;
import com.admin.DTO.ResponseDTO;

public interface IAdminService {

	public ResponseDTO adminDataToDB(AdminDataDTO dataDTO);
	public ResponseDTO updateAdminData(String token, AdminDataDTO dataDTO);
	public ResponseDTO viewAllAdmin();
	public ResponseDTO viewAdminById(String token);
	public ResponseDTO deleteAdminById(String token);
	public ResponseDTO verifyAdmin(String token);
	
	public ResponseDTO UserLogin(String eMail, String password);
	public ResponseDTO resetAdminpassword(String token, ResetPassword pwdReset);
	
	public boolean adminPresentOrNot(String token);
	public boolean adminVerifiedOrNot(String token);
	public Long adminIdRetriev(String token);
}
