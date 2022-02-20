package com.admin.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.DTO.AdminDataDTO;
import com.admin.DTO.ResetPassword;
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
	@Autowired
	EMailService eMailService;

	@Override
	public ResponseDTO adminDataToDB(AdminDataDTO dataDTO) {
		// TODO Auto-generated method stub
		AdminEntity entity = new AdminEntity(dataDTO);
		entity.setCreatorStamp(LocalDate.now());
		repoToAdd.save(entity);
		
		String link = eMailService.getLink("http://localhost:8085/admin/verify/", entity.getAdminId());

		eMailService.send("nkdaaku94@gmail.com", "admin verification", link);
		
		String token = tokenUtil.createToken(entity.getAdminId());
		ResponseDTO response = new ResponseDTO("admin data added to DB", entity, token);
		return response;
	}

	@Override
	public ResponseDTO updateAdminData(String token, AdminDataDTO dataDTO) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);

		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		if(entity.isStatus()) {
			entity = new AdminEntity(dataDTO);
			entity.setAdminId(id);
			entity.setUpdateStamp(LocalDate.now());
			repoToAdd.save(entity);
			ResponseDTO response = new ResponseDTO("updated the admin details", entity);
			return response;
			
		}else {
			throw new AdminCustomException("user not verified");

		}
		
	}

	@Override
	public ResponseDTO viewAllAdmin() {
		// TODO Auto-generated method stub
		List<AdminEntity> entity = repoToAdd.findAll();
		ResponseDTO response = new ResponseDTO("view all admin", entity);
		return response;
	}

	@Override
	public ResponseDTO viewAdminById(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);

		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		if(entity.isStatus()) {
			ResponseDTO response = new ResponseDTO("view admin by id:" + id, entity);
			return response;
		}else {
			throw new AdminCustomException("user not verified");

		}
		
	}

	@Override
	public ResponseDTO deleteAdminById(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);

		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		if (entity.isStatus()) {
			repoToAdd.delete(entity);
			ResponseDTO response = new ResponseDTO("succesfully deleted admin details of id:" + id);
			return response;
		}else {
			throw new AdminCustomException("user not verified");

		}
		
	}

	@Override
	public ResponseDTO UserLogin(String eMail, String password) {
		// TODO Auto-generated method stub
		AdminEntity entity = repoToAdd.findByEMail(eMail);
		if (entity == null) {
			throw new AdminCustomException("email id or password dont match ");

		} else if (entity.getPassword().equals(password)) {
			ResponseDTO response = new ResponseDTO("Login successfull");
			return response;
		} else {
			ResponseDTO response = new ResponseDTO("password or email is not correct");

			return response;
		}

	}

	@Override
	public ResponseDTO verifyAdmin(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));

		if (entity.isStatus() == false) {
			entity.setStatus(true);
			repoToAdd.save(entity);
			ResponseDTO response = new ResponseDTO("admin profile has been is ACTIVATED");
			return response;

		} else {
			ResponseDTO response = new ResponseDTO("admin profile has already been ACTIVATED");
			return response;
		}

	}

	@Override
	public ResponseDTO resetAdminpassword(String token, ResetPassword pwdReset) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		AdminEntity entity = repoToAdd.findById(id).orElseThrow(() -> new AdminCustomException("id not found"));
		if (entity.isStatus()) {
			if (entity.getPassword().equals(pwdReset.oldPassword)) {
				if (pwdReset.newPassword.equals(pwdReset.reEnterPassword)) {
					entity.setPassword(pwdReset.newPassword);
					repoToAdd.save(entity);
					ResponseDTO response = new ResponseDTO("reset password successfull");
					return response;
				} else {
					throw new AdminCustomException("new password should match reenter password");

				}
			} else {
				throw new AdminCustomException("old password dont match, please enter the valid password");

			}
		}else {
			throw new AdminCustomException("user not verified");

		}
	}

	@Override
	public boolean adminVerifiedOrNot(String token) {
		// TODO Auto-generated method stub
		Long id = tokenUtil.decodeToken(token);
		Optional<AdminEntity> entity = repoToAdd.findById(id);
		if(entity.get().isStatus()) {
			
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Long adminIdRetriev(String token) {
		// TODO Auto-generated method stub
		
		long id = tokenUtil.decodeToken(token);
		
		return id;
	}

	@Override
	public boolean adminPresentOrNot(String token) {
		// TODO Auto-generated method stub
		Long id =tokenUtil.decodeToken(token);
		if(repoToAdd.findById(id).isPresent()) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void sendMail(String email, String subj, String body) {
		// TODO Auto-generated method stub
		
		eMailService.send(email, subj, body);
		
	}
	
	

}
