package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admin.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Long> {
	@Query(value="select * from admin_details where e_mail= :eMail", nativeQuery = true)
	AdminEntity findByEMail(String eMail);


}
