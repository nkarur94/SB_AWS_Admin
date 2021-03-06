package com.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.DTO.AdminDataDTO;
import com.admin.DTO.ResetPassword;
import com.admin.DTO.ResponseDTO;
import com.admin.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService serviceMethodUse;
	
	
	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> adminAdd(@Valid @RequestBody AdminDataDTO dataDTO){
		ResponseDTO response = serviceMethodUse.adminDataToDB(dataDTO);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateAdmin(@RequestHeader String token,@Valid @RequestBody AdminDataDTO dataDTO){
		ResponseDTO response = serviceMethodUse.updateAdminData(token, dataDTO);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/view")
	public ResponseEntity<ResponseDTO> view(){
		ResponseDTO response = serviceMethodUse.viewAllAdmin();
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/viewbyid")
	public ResponseEntity<ResponseDTO> viewById(@RequestHeader String token){
		ResponseDTO response = serviceMethodUse.viewAdminById(token);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDTO> deleteAdmin(@RequestHeader String token){
		ResponseDTO response = serviceMethodUse.deleteAdminById(token);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/verify/{token}")
	public ResponseEntity<ResponseDTO> verifying(@PathVariable String token){
		ResponseDTO response = serviceMethodUse.verifyAdmin(token);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> loginPage(@RequestParam(value="email") String eMail, @RequestParam(value="pwd") String password){
		ResponseDTO response = serviceMethodUse.UserLogin(eMail, password);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@PutMapping("/resetpwd")
	public ResponseEntity<ResponseDTO> resetPassword(@RequestHeader String token, @RequestBody ResetPassword pwdreset){
		ResponseDTO response = serviceMethodUse.resetAdminpassword(token, pwdreset);
		return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/verifiedOrNot/{token}")
	public boolean adminverified(@PathVariable String token) {
		boolean verify = serviceMethodUse.adminVerifiedOrNot(token);
		return verify;
	}
	
	@GetMapping("/getadminid/{token}")
	public Long adminId(@PathVariable String token) {
		Long id = serviceMethodUse.adminIdRetriev(token);
		return id;
	}
	
	@GetMapping("/adminpresentornot/{token}")
	public boolean adminpresent(@PathVariable String token) {
		boolean contains = serviceMethodUse.adminPresentOrNot(token);
		return contains;
	}
	
	@GetMapping("/sendemail/{eMail}/{subj}/{body}")
	public void sendEMail(@PathVariable String eMail, @PathVariable String subj, @PathVariable String body) {
		serviceMethodUse.sendMail(eMail, subj, body);
	}
}
