package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.DTO.AdminDataDTO;
import com.admin.DTO.ResponseDTO;
import com.admin.entity.AdminEntity;
import com.admin.exception.AdminCustomException;
import com.admin.repository.AdminRepo;
import com.admin.util.TokenUtil;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminRepo repoToAdd;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Override
	public ResponseDTO adminDataToDB(AdminDataDTO dataDTO) {
		// TODO Auto-generated method stub
		AdminEntity entity =new AdminEntity(dataDTO);
		repoToAdd.save(entity);
		String token = tokenUtil.createToken(entity.getAdminId());
		ResponseDTO response = new ResponseDTO("admin data added to DB", entity, token);
		return response;
	}

	@Override
	public ResponseDTO updateAdminData(String token, AdminDataDTO dataDTO) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		
		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		entity = new AdminEntity(dataDTO);
		entity.setAdminId(id);

		repoToAdd.save(entity);
		ResponseDTO response = new ResponseDTO ("updated the admin details",entity);
		return response;
	}

	@Override
	public ResponseDTO viewAllAdmin() {
		// TODO Auto-generated method stub
		List <AdminEntity> entity = repoToAdd.findAll();
		ResponseDTO response = new ResponseDTO("view all admin",entity);
		return response;
	}

	@Override
	public ResponseDTO viewAdminById(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		
		AdminEntity entity =repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		ResponseDTO response = new ResponseDTO("view admin by id:"+id,entity);
		return response;
	}

	@Override
	public ResponseDTO deleteAdminById(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		
		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		repoToAdd.delete(entity);
		ResponseDTO response = new ResponseDTO ("succesfully deleted admin details of id:"+id);
		return response;
	}
	
	
	
	

}
