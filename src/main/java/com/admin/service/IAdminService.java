package com.admin.service;

import com.admin.DTO.AdminDataDTO;
import com.admin.DTO.ResponseDTO;

public interface IAdminService {

	public ResponseDTO adminDataToDB(AdminDataDTO dataDTO);
	public ResponseDTO updateAdminData(String token, AdminDataDTO dataDTO);
	public ResponseDTO viewAllAdmin();
	public ResponseDTO viewAdminById(String token);
	public ResponseDTO deleteAdminById(String token);
}
